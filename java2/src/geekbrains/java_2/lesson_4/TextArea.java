package geekbrains.java_2.lesson_4;

import javax.swing.*;

public class TextArea extends JFrame{
    public JTextArea text_area;
    public TextArea(JFrame main_window){
        JTextArea text_area=new JTextAreaCustom(18,30);
        this.text_area=text_area;
        text_area.setLineWrap(true);
        text_area.setEditable(false);
        JScrollPane area_scroll = new JScrollPane(text_area);
        main_window.add(area_scroll);
    }
}
