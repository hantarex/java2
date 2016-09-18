package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private String title="Lesson4";
    public MainWindow(){
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
    }
}
