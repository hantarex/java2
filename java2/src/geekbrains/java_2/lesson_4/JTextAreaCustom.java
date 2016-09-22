package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.io.*;

public class JTextAreaCustom extends JTextArea{
    protected BufferedWriter pw;
    protected PrintWriter outs;

    public JTextAreaCustom(int rows, int columns, MainWindow mainWindow) {
        super(rows, columns);
        pw=mainWindow.getPw();
        outs=mainWindow.getOuts();
    }

    @Override
    public void append(String str) {
        str="Server said: "+str;
        super.append(str);
        addFile(str);
    }
    public void appendAndSend(String str) {
        super.append("Client said: "+str);
        addFile("Client said: "+str);
        outs.print(str);
        outs.flush();
    }
    protected void addFile(String string) {
        try {
            pw.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
