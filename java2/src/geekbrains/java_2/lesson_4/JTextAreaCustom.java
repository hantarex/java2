package geekbrains.java_2.lesson_4;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.*;
import java.io.*;

public class JTextAreaCustom extends JTextArea {
    protected BufferedWriter pw;
    protected DataOutputStream outs;
    protected MainWindow mainWindow;

    public JTextAreaCustom(int rows, int columns, MainWindow mainWindow) {
        super(rows, columns);
        this.mainWindow = mainWindow;
        pw = mainWindow.getPw();
        outs = mainWindow.getOuts();
    }

    @Override
    public void append(String str) {
//        str="Server said: "+str;
        super.append(str);
        setCaretPosition(getDocument().getLength());
        addFile(str);
    }

    public void appendAndSend(String str) {
//        super.append("Client said: "+str);
//        addFile("Client said: "+str);
        String[] reg = str.split("\\s");
        JSONObject json = new JSONObject();
        if (reg[0].equals("/pm") && reg.length >= 3) {
            json.put("type", "PM");
            json.put("to", reg[1]);
            json.put("msg", str.substring(reg[0].length() + reg[1].length() + 2));
        } else if(reg[0].equals("/changenick") && reg.length >= 2){
            json.put("type", "CN"); //Change Nick
            json.put("nick", str.substring(reg[0].length() + 1));
        }
        else {
            json.put("type", "MSG");
            json.put("msg", str);
        }
        outs = mainWindow.getOuts();
        try {
            outs.writeUTF(json.toString());
            outs.flush();
        } catch (Exception e) {
//            e.printStackTrace();
            append("Connection lost..." + "\n");
        }
    }

    protected void addFile(String string) {
        try {
            pw.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
