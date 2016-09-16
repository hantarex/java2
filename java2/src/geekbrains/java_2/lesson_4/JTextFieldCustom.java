package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JTextFieldCustom extends JTextField {
    public boolean focus;

    public JTextFieldCustom(int columns) {
        super(columns);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !focus) g.drawString("Введите сообщение", 2, 14);
    }
}
