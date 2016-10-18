package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bro on 18.10.2016.
 */
public class UserList extends JFrame {
    public JTextArea text_area;
    public UserList(MainWindow main_window){
        JTextArea text_area=new JTextArea();
        this.text_area=text_area;
        text_area.setLineWrap(true);
        text_area.setEditable(false);
        JScrollPane area_scroll = new JScrollPane(text_area);
        main_window.add(area_scroll, BorderLayout.EAST);
    }
}
