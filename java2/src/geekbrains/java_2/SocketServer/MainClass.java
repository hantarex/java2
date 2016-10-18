package geekbrains.java_2.SocketServer;

import com.sun.jmx.remote.internal.ClientListenerInfo;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainClass {
    private static int port = 8181;
    private static ServerSocket ss;
    private static SQLHandler sql;
    private static ArrayList<SocketSProcessor> Clientlist = new ArrayList<>();

    public static void main(String[] args) {
        Msg msg=new Msg();
        Socket sock = null;
        sql = new SQLHandler();
        sql.connect();
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Не могу создать сервер на порту " + port);
            System.exit(1);
        }

        Thread ct = new Thread(new ConsoleThread(Clientlist)); // Поток со сканером консоли
        ct.setDaemon(true);
        ct.start();

        System.err.println("Сервер запущен на " + port + " порту");
        SocketSProcessor ClientHandler = null;
        try {
            while (true) {
                try {
                    sock = ss.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new SocketSProcessor(Clientlist, ss, sock, sql);
            }
        } finally {
            try {
                sql.disconnect();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

class SocketSProcessor {
    public static int cl_id = 0;
    public String client_name = null;
    public boolean is_close = false;
    private DataInputStream in;
    public DataOutputStream out;
    private ServerSocket serverSocket;
    private ArrayList<SocketSProcessor> Clientlist;
    private Socket sock;
    private SocketSProcessor main;

    SocketSProcessor(ArrayList<SocketSProcessor> Clientlist, ServerSocket serverSocket, Socket sock, SQLHandler sql) {
//        client_name="Client #"+cl_id;
        cl_id++;
        main=this;
        this.serverSocket = serverSocket;
        this.Clientlist = Clientlist;
        this.sock = sock;
        try {
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
            System.err.println("Клиент " + client_name + " подключился c IP" + sock.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread in_stream = new Thread(new Runnable() { // Создаём поток для клиента
            @Override
            public void run() {
                String input = null;
                JSONObject json=new JSONObject();
                String type=null;
                outerloop:
                while (true) {
                    try {
                        input = in.readUTF();
                    } catch (IOException e) {
                        break;
                    }
                    json=(JSONObject) JSONValue.parse(input);
                    if(json==null) {
                        Msg.sendMsg(main,Msg.constuctMsg("Alert","Неверный синтаксис полученого от клиента сообщения!"));
                        continue;
                    }
                    type=json.get("type").toString();
                    switch (type){
                        case "auth":
                            String login = json.get("login").toString();
                            String pass = json.get("pass").toString();
                            String nick = sql.getNick(login, pass);
                            synchronized (Clientlist) {
                                if (nick != null) {
                                    if (NickReady(nick)) {
                                        Msg.sendMsg(main, Msg.constuctMsg("Login_result", "Этот логин уже используется!"));
                                        break;
                                    }
                                    client_name = nick;
                                    clientAdd();
                                } else {
                                    Msg.sendMsg(main, Msg.constuctMsg("Login_result", "Неверна комбинация логин-пароль!"));
                                }
                            }
                            break;
                        case "MSG":
                            System.err.println(client_name + ": " + json.get("msg").toString());
                            Msg.BrcMsg(Clientlist, Msg.constuctMsg("Broadcast",client_name + ": " + json.get("msg").toString()));
                            break;
                        case "CN":
                            System.err.println(client_name + ": изменение ника на " + json.get("nick").toString());
                            if(sql.changeNick(main.client_name,json.get("nick").toString())) {
                                Msg.BrcMsg(Clientlist, Msg.constuctMsg("Broadcast",client_name + ": поменял nick на " + json.get("nick").toString()));
                                Msg.sendMsg(main, Msg.constuctMsg("CN", json.get("nick").toString()));
                                main.client_name=json.get("nick").toString();
                                Msg.BrcMsg(Clientlist, Msg.constuctMsg("UL",main.AllNicks()));
                            }
                            else{
                                Msg.sendMsg(main, Msg.constuctMsg("Alert", "Изменение ника неудалось!"));
                            }
                            break;
                        case "PM":
                            if(main.client_name.equals(json.get("to").toString())) {
                                Msg.sendMsg(main, Msg.constuctMsg("Alert", "Вы пытаетесь послать сообщение себе!"));
                                break;
                            }
                            if(Msg.sendPersonalMessage(Clientlist,json.get("to").toString(),client_name + "(PM): " + json.get("msg").toString())){
                                Msg.sendMsg(main, Msg.constuctMsg("PersonalMessage",client_name + "(PM): " + json.get("msg").toString()));
                                System.err.println(client_name + ": Персональное сообщение клиенту ("+json.get("to").toString()+"): " + json.get("msg").toString());
                            }
                            else{
                                System.err.println(client_name + ": Персональное сообщение клиенту ("+json.get("to").toString()+"): клиент не найден!");
                                Msg.sendMsg(main, Msg.constuctMsg("Alert", "Клиент не найден!"));
                            }
                            break;
                        case "END":
                            break outerloop;
                        case "STOP":
                            ServerStop();
                            System.exit(1);
                            break;
                        default:
                            Msg.sendMsg(main,Msg.constuctMsg("Login_result","Неизвестный тип полученого от клиента сообщения!"));
                    }

//                    if (input != null && client_name == null) {
//                        String[] arr = input.split("\\s");
//                        if (arr.length == 3) {
//                            if (arr[0].equals("/auth")) {
//                                String login = arr[1];
//                                String pass = arr[2];
//                                String nick = sql.getNick(login, pass);
//                                if (nick != null) {
//                                    client_name = nick;
//                                    clientAdd();
//                                    continue;
//                                }
//                                else{
//                                    Msg.sendMsg(main,Msg.constuctMsg("Login_result","Неверна комбинация логин-пароль!"));
//                                }
//                            }
//                        }
//                    }
//
//                    if (input != null) {
//                        if (input.equals("END")) break;
//                        if (input.equals("STOP")) {
//                            ServerStop();
//                            System.exit(1);
//                        }
//                        System.err.println(client_name + ": " + input);
//                        Msg.BrcMsg(Clientlist, Msg.constuctMsg("Broadcast",client_name + ": " + input));
//                        Msg.BrcMsg(Clientlist, client_name + ": " + input);
//                    }
                }
                try {
                    ArrayList<String> nicks=new ArrayList<>();
                    synchronized (Clientlist) {
                        Clientlist.remove(main);
                        nicks=AllNicks();
                    }
//                    for(SocketSProcessor cl:Clientlist) System.out.println(cl.client_name);
                    out.close();
                    in.close();
                    sock.close();
                    is_close = true;
                    System.err.println("Клиент " + client_name + " отключился c IP" + sock.getRemoteSocketAddress());
                    Msg.BrcMsg(Clientlist, Msg.constuctMsg("Broadcast","Клиент " + client_name + " отключился c IP" + sock.getRemoteSocketAddress()));
                    Msg.BrcMsg(Clientlist, Msg.constuctMsg("UL",nicks));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        in_stream.setDaemon(true);
        in_stream.start();
    }

    private void clientAdd() {
        ArrayList<String> nicks=new ArrayList<>();
        synchronized (Clientlist) {
            Clientlist.add(this); // Добавляем клиента в коллекцию
            nicks=AllNicks();
        }
        Msg.BrcMsg(Clientlist, Msg.constuctMsg("Broadcast","Клиент " + client_name + " подключился c IP" + sock.getRemoteSocketAddress()));
        Msg.sendMsg(this,Msg.constuctMsg("Login_result","Success",client_name));
        Msg.BrcMsg(Clientlist, Msg.constuctMsg("UL",nicks));
    }

    private void ServerStop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean NickReady(String nick){
        for(SocketSProcessor cl:Clientlist) if(cl.client_name.equals(nick)) return true;
        return false;
    }
    public synchronized ArrayList<String> AllNicks(){
        ArrayList<String> nicks=new ArrayList<>();
        for(SocketSProcessor cl:Clientlist) nicks.add(cl.client_name);
        return nicks;
    }
}

class ConsoleThread implements Runnable {
    private ArrayList<SocketSProcessor> Clientlist;
    private Scanner console;

    ConsoleThread(ArrayList<SocketSProcessor> Clientlist) {
        this.Clientlist = Clientlist;
        console = new Scanner(System.in);
    }

    private void SendToAll(String msg) {
        for (SocketSProcessor s : Clientlist) {
            if (s.is_close) continue;
            try {
                s.out.writeUTF(msg);
                s.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        String input;
        try {
            while (console.hasNextLine()) {
                input = console.nextLine();
                SendToAll("Сервер: " + input + "\n");
                System.err.println("Сервер: " + input);
            }
        } finally {
            console.close();
        }
    }
}

class Msg {
    private static HashMap msg = new HashMap();

    Msg() {
        msg.put("Broadcast", "BR");
        msg.put("Login_result", "LR");
        msg.put("Alert", "A");
        msg.put("PersonalMessage", "PM");
        msg.put("CN", "CN"); //changenick
        msg.put("UL", "UL"); //UserList
    }

    public static String constuctMsg(String type, String msg, String nickname) {
        String msg_type=null;
        try {
            msg_type = Msg.msg.get(type).toString();
        } catch (NullPointerException ex){
            System.err.println("Неверный тип отправляемго сообщения");
            System.err.println(ex);
        }
        if(msg_type!=null && !msg.isEmpty() && msg!=null) {
            JSONObject json = new JSONObject();
            json.put("type",msg_type);
            json.put("msg", msg);
            json.put("nick", nickname);
            return json.toString();
        }
        return null;
    }

    public static String constuctMsg(String type, ArrayList<String> nicks) {
        String msg_type=null;
        try {
            msg_type = Msg.msg.get(type).toString();
        } catch (NullPointerException ex){
            System.err.println("Неверный тип отправляемго сообщения");
            System.err.println(ex);
        }
        if(msg_type!=null && nicks!=null) {
            JSONObject json = new JSONObject();
            JSONArray json_nicks=new JSONArray();
            json_nicks.addAll(nicks);
            json.put("type",msg_type);
            json.put("msg", json_nicks);
            return json.toString();
        }
        return null;
    }

    public static String constuctMsg(String type, String msg) {
        String msg_type=null;
        try {
                msg_type = Msg.msg.get(type).toString();
        } catch (NullPointerException ex){
            System.err.println("Неверный тип отправляемго сообщения");
            System.err.println(ex);
        }
        if(msg_type!=null && !msg.isEmpty() && msg!=null) {
            JSONObject json = new JSONObject();
            json.put("type",msg_type);
            json.put("msg", msg);
            return json.toString();
        }
        return null;
    }

    public static boolean sendPersonalMessage(ArrayList<SocketSProcessor> Clientlist,String nick,String msg){
        for(SocketSProcessor cl:Clientlist){
            if(cl.client_name.equals(nick)){
                sendMsg(cl,constuctMsg("PersonalMessage",msg));
                return true;
            }
        }
        return false;
    }

    public static void sendMsg(SocketSProcessor s, String msg) {
        if (!s.is_close) {
            try {
                s.out.writeUTF(msg);
                s.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void BrcMsg(ArrayList<SocketSProcessor> Clientlist, String msg) {
        for (SocketSProcessor s : Clientlist) {
            sendMsg(s, msg);
        }
    }
}
