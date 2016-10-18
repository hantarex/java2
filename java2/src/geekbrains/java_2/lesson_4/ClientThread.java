package geekbrains.java_2.lesson_4;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;

public class ClientThread implements Runnable {
    private DataInputStream in;
    private MainWindow mainWindow;

    public ClientThread(MainWindow mainWindow, DataInputStream in) {
        this.mainWindow = mainWindow;
        this.in = in;
    }

    @Override
    public void run() {
        String input = null;
        JSONObject json = new JSONObject();
        String type=null;
        String msg=null;
        try {
            while (true) {
                try {
                    input = in.readUTF();
                    json = (JSONObject) JSONValue.parse(input);
                    if (json == null) {
                        JOptionPane.showMessageDialog(null, "Ошибка приёма сообщения от сервера");
                        continue;
                    }
                } catch (IOException e) {
                    mainWindow.getText_area().text_area.append("Connection lost...\n");
                    break;
                }
                switch (json.get("type").toString()) {
                    case "BR":
                    case "PM":
                        input=json.get("msg").toString();
                        if (input!= null) mainWindow.getText_area().text_area.append(input+"\n");
                        break;
                    case "CN":
                        input=json.get("msg").toString();
                        if (input!= null) mainWindow.setNickname(json.get("msg").toString());
                        break;
                    case "UL":
                        if(json.get("msg")!=null) {
                            String user_l="";
                            JSONArray json_user = (JSONArray) json.get("msg");
                            for (Object user : json_user) {
                                user_l=user_l+user.toString()+"\n";
                            }
                            mainWindow.getUser_list().text_area.setText(user_l);

                        }
                        break;
                    case "LR":
                        input=json.get("msg").toString();
                        if (input!= null) {
                            if (input.equals("Success") && json.get("nick")!=null) {
                                mainWindow.setNickname(json.get("nick").toString());
                                mainWindow.setIs_Auth(true);
                            }
                            else mainWindow.setIs_Auth(false);
                            mainWindow.getText_area().text_area.append(input+"\n");
                        }
                        break;
                    case "A":
                        JOptionPane.showMessageDialog(null, json.get("msg").toString());
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ошибка разбора сообщения от сервера");
                }
            }
        } finally {
            try {
                mainWindow.setIs_Auth(false);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
