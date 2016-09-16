package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InputArea extends JFrame{
    public InputArea(JFrame main_window, JTextAreaCustom text_area){
        JTextFieldCustom input= new JTextFieldCustom(20);
        main_window.add(input);
        JButton send_button = new JButton("Send");
        main_window.add(send_button);
        send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!input.getText().trim().isEmpty()) {
                    text_area.append(input.getText() + "\n");
                    input.setText("");
                    input.requestFocus();
                }
            }
        });
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!input.getText().trim().isEmpty()) {
                    text_area.append(input.getText() + "\n");
                    input.setText("");
                }
            }
        });
        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                input.focus=true;
                input.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                input.focus=false;
                input.repaint();
            }
        });
    }
}
