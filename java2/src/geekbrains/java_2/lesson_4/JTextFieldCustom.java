package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;

public class JTextFieldCustom extends JTextField {
    public boolean focus;
    private String hint="Введите сообщение";

    public JTextFieldCustom(String hint){
        this.hint=hint;
    }

    public JTextFieldCustom(int columns) {
        super(columns);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !focus) g.drawString(hint, 2, 17);
    }
}
