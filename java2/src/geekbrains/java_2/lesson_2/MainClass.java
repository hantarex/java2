package geekbrains.java_2.lesson_2;

import java.util.Arrays;
import java.util.Scanner;

public class MainClass {
    private static int rows = 4, cols = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = new String[rows]; // Строка 1x4
        String[][] matr;
        System.out.println("Введите 4 строки матрицы по 4 цифры в строке:");
        for (int i = 0; i < str.length; i++) {
            System.out.print((i + 1) + ":");
            str[i] = sc.nextLine();
        }
        try {
            System.out.println("Результат выполнения: " + rMatr(str));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
            System.out.println("В строке введены не числа!");
            ex.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static float rMatr(String[] str) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String[][] matr_str = new String[rows][cols]; // Строка 4х4
        int[][] matr_int = new int[rows][cols]; // числа 4х4
        float result = 0; // результат метода

        for (int i = 0; i < str.length; i++) {
            matr_str[i] = str[i].split(" ");
            if(matr_str[i].length!=4) throw new ArrayIndexOutOfBoundsException("Столбцы матрицы не равны "+cols+"!");
            for (int j = 0; j < matr_str[i].length; j++) {
                matr_int[i][j] = Integer.parseInt(matr_str[i][j]);
                result += matr_int[i][j];
            }
        }
        System.out.println("Матрица 4х4:");
        System.out.println(Arrays.deepToString(matr_int));
        return result / 2;
    }

}