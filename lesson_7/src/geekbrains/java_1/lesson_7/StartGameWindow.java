package geekbrains.java_1.lesson_7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StartGameWindow extends JFrame {
    private static final int WINDOW_WIDTH=350;
    private static final int WINDOW_HEIGHT=230;
    private static final int MIN_WIN_LEN=3;
    private static final int MIN_FIELD_SIZE=3;
    private static final int MAX_FIELD_SIZE=10;

    private final GameWindow game_window;
    private JRadioButton rb_human_vs_ai;
    private JRadioButton rb_human_vs_human;
    private JSlider slider_field_size;
    private JSlider slider_win_len;

    StartGameWindow(GameWindow game_window){
        this.game_window=game_window;
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        Rectangle game_window_bounds=game_window.getBounds();
        int posx=(int)game_window_bounds.getCenterX()-WINDOW_WIDTH/2;
        int posy=(int)game_window_bounds.getCenterY()-WINDOW_HEIGHT/2;
        setLocation(posx,posy);
        setResizable(false);
        setTitle("Создание новой игры");

        this.setLayout(new GridLayout(10,1));
        addGameModControls();
        SliderControls();
        JButton button_start_game=new JButton("Начать игру");
        button_start_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPressButtonStart();
            }
        });
        add(button_start_game);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addGameModControls(){
        add(new JLabel("Выберите режим игры:"));
        rb_human_vs_ai=new JRadioButton("Игрок против компьютера",true);
        rb_human_vs_human=new JRadioButton("Игрок против игрока");
        ButtonGroup bg_game_mode = new ButtonGroup();
        bg_game_mode.add(rb_human_vs_ai);
        bg_game_mode.add(rb_human_vs_human);
        add(rb_human_vs_ai);
        add(rb_human_vs_human);
    }
    private void SliderControls(){
        final String WIN_LEN_PREFIX="Win len: ";
        final JLabel label_win_len=new JLabel(WIN_LEN_PREFIX+MIN_WIN_LEN);
        slider_win_len=new JSlider(MIN_WIN_LEN,MIN_FIELD_SIZE,MIN_WIN_LEN);
        slider_win_len.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label_win_len.setText(WIN_LEN_PREFIX+slider_win_len.getValue());
            }
        });

        final String FIELD_SIZE_PREFIX="Field size:";
        final JLabel label_field_size=new JLabel(FIELD_SIZE_PREFIX+MIN_FIELD_SIZE);
        slider_field_size=new JSlider(MIN_FIELD_SIZE,MAX_FIELD_SIZE,MIN_FIELD_SIZE);
        slider_field_size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int field_slider_value=slider_field_size.getValue();
                label_field_size.setText(FIELD_SIZE_PREFIX+field_slider_value);
                slider_win_len.setMaximum(field_slider_value);
            }
        });

        add(new JLabel("Выберите размер поля:"));
        add(label_field_size);
        add(slider_field_size);
        add(new JLabel("Выберите длину выигрышной последовательности:"));
        add(label_win_len);
        add(slider_win_len);

    }
    private void onPressButtonStart(){
        int mode;
        if(rb_human_vs_ai.isSelected()) mode=Map.GAME_MODE_HUMAN_VS_AI;
        else if(rb_human_vs_human.isSelected()) mode=Map.GAME_MODE_HUMAN_VS_HUMAN;
        else throw new RuntimeException("Ни одна радиокнопка не выбрана");

        int field_size=slider_field_size.getValue();
        int win_len=slider_win_len.getValue();
        setVisible(false);
        game_window.startNewGame(mode,field_size,field_size,win_len);
    }
}
