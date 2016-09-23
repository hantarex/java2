package geekbrains.java_2.SocketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    private static int port = 8181;
    private static ServerSocket ss;
    private static ArrayList<SocketSProcessor> Clientlist = new ArrayList<>();

    public static void main(String[] args) {
        Socket sock = null;
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
        try {
            while (true) {
                try {
                    sock = ss.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Clientlist.add(new SocketSProcessor(ss,sock)); // Добавляем клиента в коллекцию
            }
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

class SocketSProcessor {
    private Scanner in;
    public PrintWriter out;
    private ServerSocket serverSocket;

    SocketSProcessor(ServerSocket serverSocket, Socket sock) {
        this.serverSocket = serverSocket;
        try {
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
            System.err.println("Клиент подключился c IP"+sock.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread in_stream = new Thread(new Runnable() { // Создаём поток для клиента
            @Override
            public void run() {
                String input;
                while (in.hasNextLine()) {
                    input = in.nextLine();
                    if (input.equals("END")) break;
                    if (input.equals("STOP")) {
                        ServerStop();
                        System.exit(1);
                    }
                    System.err.println("Клиент: " + input);
                }
                try {
                    out.close();
                    in.close();
                    sock.close();
                    System.err.println("Клиент отключился c IP"+sock.getRemoteSocketAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        in_stream.setDaemon(true);
        in_stream.start();
    }


    private void ServerStop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ConsoleThread implements Runnable {
    private ArrayList<SocketSProcessor> Clientlist;
    private Scanner console;
    ConsoleThread(ArrayList<SocketSProcessor> Clientlist){
        this.Clientlist=Clientlist;
        console = new Scanner(System.in);
    }

    private void SendToAll(String msg){
        for(SocketSProcessor s:Clientlist){
            s.out.println(msg);
            s.out.flush();
        }
    }

    @Override
    public void run() {
        String input;
        try {
            while (console.hasNextLine()) {
                input = console.nextLine();
                SendToAll(input);
                System.err.println("Сервер: " + input);
            }
        } finally {
            console.close();
        }
    }
}
