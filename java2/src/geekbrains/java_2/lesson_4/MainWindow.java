package geekbrains.java_2.lesson_4;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainWindow extends JFrame {
    private String title = "Lesson4";
    private String file_name = "chat.txt";
    private int clientPort = 8181;
    private String clientAdress = "127.0.0.1";
    private BufferedWriter pw;
    private Socket cs=null;
    private TextArea text_area;
    private UserList user_list;
    private authClass auth_panel;
    private JPanel input_area;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    public boolean is_Auth=false;
    private String nickname;

    public UserList getUser_list() {
        return user_list;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        setTitle("Client["+nickname+"]");
    }

    public void setIs_Auth(boolean is_Auth) {
        this.is_Auth = is_Auth;
        auth_panel.auth_panel.setVisible(!is_Auth);
        input_area.setVisible(is_Auth);
    }

    public MainWindow() {
        try {
            pw = new BufferedWriter(new FileWriter(file_name, true));
        } catch (IOException e) {
            e.printStackTrace();
        }


        setTitle(title);
        setBounds(300, 300, 400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MenuBar(this);
        text_area = new TextArea(this);
        user_list = new UserList(this);
        auth_panel = new authClass(this);
        input_area = new JPanel(new BorderLayout());
        input_area.setVisible(false);
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
                } catch (Exception e1) {
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

    public DataOutputStream getOuts() {
        return out;
    }

    public void connect() {
        if(cs!=null){
            try {
                cs.close();
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            cs = new Socket(clientAdress, clientPort);
        } catch (IOException e) {
//            System.err.println("Не могу подключиться к " + clientAdress + ":" + clientPort);
//            System.exit(1);
            text_area.text_area.append("Server is down...\n");
            return;
        }

        try {
            in = new DataInputStream(cs.getInputStream());
            out = new DataOutputStream(cs.getOutputStream());
        } catch (IOException e) {
//            e.printStackTrace();
            text_area.text_area.append("No connection...\n");
        }
        try {
            JSONObject json=new JSONObject();
            json.put("type","auth");
            json.put("login",auth_panel.login.getText());
            json.put("pass",new String(auth_panel.pass.getPassword()));
            out.writeUTF(json.toString());
            out.flush();
        } catch (Exception e) {
//            e.printStackTrace();
            text_area.text_area.append("Connection lost...\n");
        }
        new Thread(new ClientThread(this, in)).start();
    }
}
