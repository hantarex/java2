package geekbrains.java_1.lesson_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameWindow extends JFrame {
    private static final int WINDOW_WIDTH=507;
    private static final int WINDOW_HEIGHT=555;
    private static final int POS_X=800;
    private static final int POS_Y=300;
    private static final String WINDOW_NAME="Крестики-нолики";
    private final GameWindow this_window=this;
    private final StartGameWindow start_game_window;
    private final Map map;

    GameWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(WINDOW_NAME);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLocation(POS_X,POS_Y);
        start_game_window=new StartGameWindow(this_window);
        setResizable(false);

        JButton button_newgame=new JButton("Новая игра");
        JButton button_exit=new JButton("Выход");

        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        button_newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start_game_window.setVisible(true);
            }
        });
        this.map=new Map();
        JPanel panel_bottom=new JPanel();

        panel_bottom.setLayout(new GridLayout(1,2));
        panel_bottom.add(button_newgame);
        panel_bottom.add(button_exit);

        add(panel_bottom,BorderLayout.SOUTH);
        add(map,BorderLayout.CENTER);

        setVisible(true);
        start_game_window.setVisible(true);
    }

    void startNewGame(int mode,int field_size_x,int field_size_y,int win_len){
        map.startNewGame(mode,field_size_x,field_size_y,win_len);
    }
}
