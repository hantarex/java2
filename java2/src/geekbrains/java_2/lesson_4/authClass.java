package geekbrains.java_2.lesson_4;

import geekbrains.java_2.SocketServer.*;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class authClass extends JFrame {
    private MainWindow mainWindow;
    public JPanel auth_panel;
    public JPasswordField pass;
    public JTextField login;
    authClass(MainWindow mainWindow){
        this.mainWindow=mainWindow;
        JPanel authPanel=new JPanel(new GridLayout());
        auth_panel=authPanel;
        login=new JTextFieldCustom("Логин");
        pass = new JPasswordField();
        Button button=new Button("Авторизация");
        authPanel.add(login);
        authPanel.add(pass);
        authPanel.add(button);
        mainWindow.add(authPanel,BorderLayout.NORTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.connect();
            }
        });
    }
}
