package geekbrains.java_2.SocketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainClass {
    private static int port=8181;
    private static ServerSocket ss;
    public static void main(String[] args) {
        Socket sock=null;
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Не могу создать сервер на порту "+port);
            System.exit(1);
        }
        System.err.println("Сервер запущен на "+port+" порту");
        try {
            while (true) {
                try {
                    sock = ss.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.err.println("Клиент подключился");
                new Thread(new SocketSProcessor(ss,sock)).start();
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
class SocketSProcessor implements Runnable{
    Scanner in;
    PrintWriter out;
    Socket sock;
    ServerSocket serverSocket;
    Scanner console;
    SocketSProcessor(ServerSocket serverSocket,Socket sock){
        this.sock=sock;
        this.serverSocket=serverSocket;
        try {
            in = new Scanner(sock.getInputStream());
            out=new PrintWriter(sock.getOutputStream());
            console=new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String input;
                System.err.println("Консоль запущена");
                try{
                    while(console.hasNextLine()){
                        input=console.nextLine();
                        out.println(input);
                        out.flush();
                        System.err.println("Сервер: "+input);
                    }
                } finally {
                    console.close();
                    System.out.println("Консоль отключена");
                }

            }
        }).start();
    }
    @Override
    public void run() {
        String input;
        while(in.hasNextLine()){
            input=in.nextLine();
            if(input.equals("END")) break;
            if(input.equals("STOP")) {ServerStop(); System.exit(1);}
            System.err.println("Клиент: "+input);
        }
        try {
            sock.close();
            System.err.println("Клиент отключился");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ServerStop(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
