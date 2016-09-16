package geekbrains.java_2.lesson_4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuBar extends JFrame{
    private JFrame main_window;
    MenuBar(JFrame main_window) {
        this.main_window=main_window;
        JMenuBar top_menu=new JMenuBar();
        main_window.setJMenuBar(top_menu);

        JMenu file = new JMenu("File");
        top_menu.add(file);
        JMenu edit = new JMenu("Edit");
        top_menu.add(edit);
        JMenu view = new JMenu("View");
        top_menu.add(view);

        JMenuItem file_new = new JMenuItem("New");
        file.add(file_new);
        file.addSeparator();
        JMenuItem file_exit = new JMenuItem("Exit");
        file.add(file_exit);

        file_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
