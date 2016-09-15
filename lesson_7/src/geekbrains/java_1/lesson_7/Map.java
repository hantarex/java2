package geekbrains.java_1.lesson_7;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

class Map extends JPanel {
    private BufferedImage krestik;
    private BufferedImage nolik;

    private int mode_game;

    public static final int GAME_MODE_HUMAN_VS_AI = 0;
    public static final int GAME_MODE_HUMAN_VS_HUMAN = 1;

    private static final int EMPTY_DOT = 0;
    private static final int HUMAN_DOT = 1;
    private static final int AI_DOT = 2;

    private static final int DRAW = 0;
    private static final int HUMAN_WIN = 1;
    private static final int AI_WIN = 2;

    private static final int DOT_MARGIN = 4;

    private static final String DRAW_MSG = "Ничья!";
    private static final String HUMAN_WIN_MSG = "Выиграл Игрок!";
    private static final String AI_WIN_MSG = "Выиграл Компьютер!";

    private final Random random = new Random();
    private int field_size_x;
    private int field_size_y;
    private int[][] field;
    private int win_len;
    private int cell_width;
    private int cell_height;
    private boolean initialized;
    private boolean game_over;
    private int game_over_state;
    private int checkWin = 0;
    private final Font font=new Font("Times new roman",Font.BOLD,48);
    private int next_step=1;

    Map() {
        try{
            this.krestik= ImageIO.read(GameWindow.class.getResourceAsStream("krestik.png"));
            this.nolik=ImageIO.read(GameWindow.class.getResourceAsStream("nolik.png"));
        } catch (IOException e){
            System.out.println("Файл картинок не найден"+e);
        }
        setLayout(new GridLayout(1, 1));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
    }

    private void showGameOver(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,200,getWidth(),70);
        g.setColor(Color.YELLOW);
        g.setFont(font);
        switch (game_over_state){
            case DRAW:
                g.drawString(DRAW_MSG,180,getHeight()/2);
                break;
            case HUMAN_WIN:
                g.drawString(HUMAN_WIN_MSG,70,getHeight()/2);
                break;
            case AI_WIN:
                g.drawString(AI_WIN_MSG,20,getHeight()/2);
                break;
            default:
                throw new RuntimeException("Неизвестный game_over_state = "+game_over_state);
        }

    }
    public boolean humanTurn(MouseEvent e,int c){
        int cell_x = e.getX() / cell_width;
        int cell_y = e.getY() / cell_height;
        if (!isValidCell(cell_x + 1, cell_y + 1) || !isEmptyCell(cell_y + 1, cell_x + 1)) return false;
        field[cell_y][cell_x] = c;
        if(c==1 && mode_game==1) next_step=2;
        else next_step=1;
        return true;
    }
    public void update(MouseEvent e) {
        if (game_over || !initialized) return;
        // ЛОгика игры
        if(!humanTurn(e,next_step)) return;
        repaint();
        if (checkWin(HUMAN_DOT)) {
            game_over_state = HUMAN_WIN;
            game_over=true;
            System.out.println("Выиграл человек");
            return;
        }
        if(isMapFull()){
            game_over_state=DRAW;
            game_over=true;
            System.out.println("Ничья");
            return;
        }
        if(mode_game==0) aiTurn();
        if(checkWin(AI_DOT)){
            game_over_state=AI_WIN;
            game_over=true;
            System.out.println("Выиграл компьютер");
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!initialized) return;
//        System.out.println("Сработал Рендер");
        int panel_width = this.getWidth();
        int panel_height = this.getHeight();
        cell_width = panel_width / field_size_x;
        cell_height = panel_height / field_size_y;
        for (int i = 0; i <= field_size_y; i++) {
            int y = i * cell_height;
            g.drawLine(0, y, panel_width, y);
        }
        for (int i = 0; i <= field_size_x; i++) {
            int x = i * cell_width;
            g.drawLine(x, 0, x, panel_height);
        }
        for (int i = 1; i <= field_size_y; i++) {
            for (int j = 1; j <= field_size_x; j++) {
                if (isEmptyCell(i, j)) continue;
                if (field[i - 1][j - 1] == HUMAN_DOT) {
                    g.drawImage(krestik,(j - 1) * cell_width + DOT_MARGIN, (i - 1) * cell_height + DOT_MARGIN, cell_width - DOT_MARGIN * 2, cell_height - DOT_MARGIN * 2,null);
                } else if (field[i - 1][j - 1] == AI_DOT) {
                    g.drawImage(nolik,(j - 1) * cell_width + DOT_MARGIN, (i - 1) * cell_height + DOT_MARGIN, cell_width - DOT_MARGIN * 2, cell_height - DOT_MARGIN * 2,null);
                } else {
                    throw new RuntimeException("Некорректное значени поля: " + field[i - 1][j - 1]);
                }
//                g.fillOval((j - 1) * cell_width + DOT_MARGIN, (i - 1) * cell_height + DOT_MARGIN, cell_width - DOT_MARGIN * 2, cell_height - DOT_MARGIN * 2);
            }
        }
        if(game_over) showGameOver(g);
    }

    void startNewGame(int mode, int field_size_x, int field_size_y, int win_len) {
        this.field = new int[field_size_x][field_size_y];
        this.field_size_x = field_size_x;
        this.field_size_y = field_size_y;
        this.win_len = win_len;
        System.out.println(mode);
        mode_game=mode;
        initialized = true;
        game_over=false;
        repaint();
    }

    private void checkWinNum(int c, int x, int y, int vx, int vy) {
        if (vx == 0 && vy == 0) return;
        if (field[x - 1][y - 1] == c) {
            checkWin++;
            if (vx == 1) x += 1;
            if (vx == -1) x -= 1;
            if (vy == 1) y += 1;
            if (vy == -1) y -= 1;
            if (x < 1 || y < 1 || x > field_size_x || y > field_size_y) return;
            if (checkWin == win_len) return;
            checkWinNum(c, x, y, vx, vy);
        }
    }

    private boolean checkWin(int c) {
        // Идём по часам
        int maxline = 0; // Найденная максимальная последовательность
        for (int i = -1; i <= 1; i++) { // Обходим все клетки и все направления
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < field_size_x; k++) {
                    for (int l = 0; l < field_size_y; l++) {
                        if (field[k][l] == c) {
                            checkWin = 0;
                            checkWinNum(c, k + 1, l + 1, i, j);
                            if (checkWin > maxline) maxline = checkWin;
                        }
                    }
                }
            }
        }
        return maxline >= win_len;
    }

    private boolean checkTurn(int c, int m) { // Делаем эфективных ход m, проверкой c
        for (int i = 0; i < field_size_x; i++) {
            for (int j = 0; j < field_size_y; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    field[i][j] = c; // Ставим точку и смотрим
                    if (checkWin(c)) {
                        field[i][j] = m;
                        return true;
                    }
                    field[i][j] = EMPTY_DOT; // Убираем точку
                }
            }
        }
        return false;
    }

    private void aiTurn() {
        int x, y;
        // Сходить с максимальной эфективностью
        if (checkTurn(AI_DOT, AI_DOT)) return;
        // Посмотреть блокировку игрока
        if (checkTurn(HUMAN_DOT, AI_DOT)) return;
        // Если эфективных ходов нет то в центр если не занято
        if (isEmptyCell((field_size_x - 1) / 2 + 1, (field_size_y - 1) / 2 + 1)) {
            field[(field_size_x - 1) / 2][(field_size_x - 1) / 2] = AI_DOT;
            return;
        }
        // Ну и если совсем хороших ходов нет, то рандом
        do {
            x = random.nextInt(field_size_y + 1);
            y = random.nextInt(field_size_x + 1);
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y - 1][x - 1] = AI_DOT;
    }

    private void initMap() {
        for (int i = 0; i < field_size_x; i++) {
            for (int j = 0; j < field_size_y; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    //    private static void printMap(){
//        for (int i = 0; i <=MAP_SIZE_Y ; i++) System.out.print(i + " ");
//        System.out.println();
//        for (int i = 0; i < MAP_SIZE_X; i++) {
//            System.out.print(i+1 + " ");
//            for (int j = 0; j < MAP_SIZE_Y ; j++) {
//                System.out.print(current[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//    private static void humanTurn(){
//        int x,y;
//        do {
//            System.out.println("Введите координаты X и Y:");
//            x = sc.nextInt();
//            y = sc.nextInt();
//        } while(!isValidCell(x,y) || !isEmptyCell(x,y));
//        current[y-1][x-1]=HUMAN_DOT;
//    }
//
    private boolean isValidCell(int x, int y) {
        return x > 0 & y > 0 & x <= field_size_x & y <= field_size_y;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x - 1][y - 1] == EMPTY_DOT;
    }

    private boolean isMapFull() {
        for (int i = 0; i < field_size_x; i++) {
            for (int j = 0; j < field_size_y; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
}
