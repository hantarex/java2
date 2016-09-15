package geekbrains.java_1.lesson_3.kr_nol;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Bro on 17.07.2016.
 */
public class Kr_nol {
    private static final int MAP_SIZE_X=5; // Длинна X
    private static final int MAP_SIZE_Y=5; // Длинна Y
    private static final int LENGHT_WIN=4; // Длинна победы
    private static final char[][] current=new char[MAP_SIZE_X][MAP_SIZE_Y];
    private static final char HUMAN_DOT='X';
    private static final char AI_DOT='O';
    private static final char EMPTY_DOT='*';
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rnd=new Random();
    private static int checkWin; // Расчёт длинны последовательности


    public static void main(String[] args) {
        initMap();
        System.out.println(Arrays.deepToString(current));
        printMap();
        while(true){
            humanTurn();
            checkWin=0;
            printMap();
            if(checkWin(HUMAN_DOT)){
                System.out.println("Выиграл игрок!!!");
                break;
            }
            if(isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            checkWin=0;
            aiTurn();
            printMap();
            if(checkWin(AI_DOT)){
                System.out.println("Выиграл компьютер!!!");
                break;
            }
            if(isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        sc.close();

    }

    private static void checkWinNum(char c, int x, int y, int vx, int vy){
        if(vx==0 && vy==0) return;
        if(current[x-1][y-1]==c){
            checkWin++;
            if(vx==1) x+=1;
            if(vx==-1) x-=1;
            if(vy==1) y+=1;
            if(vy==-1) y-=1;
            if(x<1 || y<1 || x>MAP_SIZE_X || y>MAP_SIZE_Y) return;
            if(checkWin==LENGHT_WIN) return;
            checkWinNum(c,x,y,vx,vy);
        }
    }

    private static boolean checkWin(char c){
        // Идём по часам
        int maxline=0; // Найденная максимальная последовательность
        for (int i = -1; i <= 1 ; i++) { // Обходим все клетки и все направления
            for (int j = -1; j <= 1 ; j++) {
                for (int k = 0; k < MAP_SIZE_X ; k++) {
                    for (int l = 0; l < MAP_SIZE_Y; l++) {
                        if(current[k][l]==c) {
                            checkWin = 0;
                            checkWinNum(c, k+1, l+1, i, j);
                            if (checkWin > maxline) maxline = checkWin;
                        }
                    }
                }
            }
        }
        return maxline>=LENGHT_WIN;
    }

    private static boolean checkTurn(char c, char m){ // Делаем эфективных ход m, проверкой c
        for (int i = 0; i < MAP_SIZE_X ; i++) {
            for (int j = 0; j < MAP_SIZE_Y; j++) {
                if(current[i][j]==EMPTY_DOT){
                    current[i][j]=c; // Ставим точку и смотрим
                    if (checkWin(c)){
                        current[i][j]=m;
                        return true;
                    }
                    current[i][j]=EMPTY_DOT; // Убираем точку
                }
            }
        }
        return false;
    }

    private static void aiTurn(){
        int x,y;
        // Сходить с максимальной эфективностью
        if(checkTurn(AI_DOT,AI_DOT)) return;
        // Посмотреть блокировку игрока
        if(checkTurn(HUMAN_DOT,AI_DOT)) return;
        // Если эфективных ходов нет то в центр если не занято
        if(isEmptyCell((MAP_SIZE_X-1)/2+1,(MAP_SIZE_Y-1)/2+1)) {
            current[(MAP_SIZE_X-1) / 2][(MAP_SIZE_X-1) / 2] = AI_DOT;
            return;
        }
        // Ну и если совсем хороших ходов нет, то рандом
        do {
            x = rnd.nextInt(MAP_SIZE_Y+1);
            y = rnd.nextInt(MAP_SIZE_X+1);
        } while(!isValidCell(x,y) || !isEmptyCell(x,y));
        current[y-1][x-1]=AI_DOT;
    }

    private static void initMap(){
        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Y ; j++) {
                current[i][j]=EMPTY_DOT;
            }
        }
    }

    private static void printMap(){
        for (int i = 0; i <=MAP_SIZE_Y ; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < MAP_SIZE_X; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < MAP_SIZE_Y ; j++) {
                System.out.print(current[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void humanTurn(){
        int x,y;
        do {
            System.out.println("Введите координаты X и Y:");
            x = sc.nextInt();
            y = sc.nextInt();
        } while(!isValidCell(x,y) || !isEmptyCell(x,y));
        current[y-1][x-1]=HUMAN_DOT;
    }

    private static boolean isValidCell(int x,int y){
        return x>0 & y>0 & x<=MAP_SIZE_Y & y<=MAP_SIZE_X;
    }

    private static boolean isEmptyCell(int x, int y){
        return current[y-1][x-1]==EMPTY_DOT;
    }

    private static boolean isMapFull(){
        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Y  ; j++) {
                if(current[i][j]==EMPTY_DOT) return false;
            }
        }
        return true;
    }

}
