package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InputArea extends JFrame{
    public InputArea(JPanel main_window, JTextAreaCustom text_area){
        JTextFieldCustom input= new JTextFieldCustom(20);
        main_window.add(input, BorderLayout.CENTER);
        JButton send_button = new JButton("Send");
        main_window.add(send_button,BorderLayout.EAST);
        send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!input.getText().trim().isEmpty()) {
                    text_area.appendAndSend(input.getText());
                    input.setText("");
                    input.requestFocus();
                }
            }
        });
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!input.getText().trim().isEmpty()) {
                    text_area.appendAndSend(input.getText());
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
