package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.io.*;

public class JTextAreaCustom extends JTextArea{
    protected String file_name="chat.txt";
    protected BufferedWriter pw;

    public JTextAreaCustom(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public void append(String str) {
        super.append(str);
        addFile(str);
    }
    protected void addFile(String string) {
        try {
            pw = new BufferedWriter(new FileWriter(file_name,true));
            pw.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
