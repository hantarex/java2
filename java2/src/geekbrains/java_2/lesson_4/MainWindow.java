package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainWindow extends JFrame {
    private String title="Lesson4";
    private String file_name="chat.txt";
    private BufferedWriter pw;

    public MainWindow(){
        try {
            pw = new BufferedWriter(new FileWriter(file_name,true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle(title);
        setBounds(300,300,400,400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MenuBar(this);
        TextArea text_area=new TextArea(this);
        JPanel input_area = new JPanel(new BorderLayout());
        new InputArea(input_area,(JTextAreaCustom)text_area.text_area);
        add(input_area,BorderLayout.SOUTH);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    pw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    public BufferedWriter getPw() {
        return pw;
    }
}
