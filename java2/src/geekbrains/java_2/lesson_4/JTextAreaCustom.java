package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.io.*;

public class JTextAreaCustom extends JTextArea{
    protected BufferedWriter pw;

    public JTextAreaCustom(int rows, int columns, BufferedWriter pw) {
        super(rows, columns);
        this.pw=pw;
    }

    @Override
    public void append(String str) {
        super.append(str);
        addFile(str);
    }
    protected void addFile(String string) {
        try {
            pw.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
