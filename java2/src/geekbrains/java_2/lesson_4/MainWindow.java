package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private String title="Lesson4";
    public MainWindow(){
        setTitle(title);
        setBounds(300,300,400,400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MenuBar(this);
        TextArea text_area=new TextArea(this);
        new InputArea(this,(JTextAreaCustom)text_area.text_area);
        setVisible(true);
    }
}
