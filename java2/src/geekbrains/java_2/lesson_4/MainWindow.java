package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainWindow extends JFrame {
    private String title = "Lesson4";
    private String file_name = "chat.txt";
    private int clientPort = 8181;
    private String clientAdress = "127.0.0.1";
    private BufferedWriter pw;
    private PrintWriter outs;
    private Socket cs;
    private TextArea text_area;

    public MainWindow() {
        try {
            pw = new BufferedWriter(new FileWriter(file_name, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cs = new Socket(clientAdress, clientPort);
        } catch (IOException e) {
            System.err.println("Не могу подключиться к "+clientAdress+":"+clientPort);
            System.exit(1);
        }
        Scanner in=null;
        PrintWriter out=null;
        try {
            in = new Scanner(cs.getInputStream());
            out = new PrintWriter(cs.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        outs=out;
        new Thread(new ClientThread(this, in)).start();

        setTitle(title);
        setBounds(300, 300, 400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MenuBar(this);
        text_area = new TextArea(this);
        JPanel input_area = new JPanel(new BorderLayout());
        new InputArea(input_area, (JTextAreaCustom) text_area.text_area);
        add(input_area, BorderLayout.SOUTH);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    pw.close();
                    cs.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public BufferedWriter getPw() {
        return pw;
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public TextArea getText_area() {
        return text_area;
    }

    public PrintWriter getOuts() {
        return outs;
    }
}
