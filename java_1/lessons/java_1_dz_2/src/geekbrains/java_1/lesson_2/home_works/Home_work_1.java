package geekbrains.java_1.lesson_2.home_works;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ashikov on 14.07.2016.
 */
public class Home_work_1 {
    public static void main(String[] args) {
        // Задание 1,2
        int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i]==1) arr[i]=0;
            else arr[i]=1;
        }
        System.out.println(Arrays.toString(arr));

        // Задание 3
        int mass[]=new int[8];
        for (int i = 0,iter=1; i < mass.length; i++, iter=iter+3) mass[i]=iter;
        System.out.println(Arrays.toString(mass));

        // Задание 4
        int[] mas = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < mas.length ; i++) if(mas[i]<6) mas[i]*=2;
        System.out.println(Arrays.toString(mas));

        // Задание 5
        int[] mas5 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println(Arrays.toString(array_min_max(mas5)));

        // Задание 6
        Calculator();

    }

    private static void Calculator(){
        int ab[]=new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое действие:");
        ab[0]=scanner.nextInt();
        String math=scanner.next();
        ab[1]=scanner.nextInt();
        String res=math(ab,math);
        if(res!="false") System.out.println(res);
        else Calculator();
    }

    private static String math(int[] ab,String math){
        switch (math){
            case "+":
                return Integer.toString(ab[0]+ab[1]);
            case "-":
                return Integer.toString(ab[0]-ab[1]);
            case "*":
                return Integer.toString(ab[0]*ab[1]);
            case "/":
                return Float.toString((float)ab[0]/ab[1]);
            case "%":
                return Integer.toString(ab[0]%ab[1]);
            default:
                System.out.println("Арифметический оператор введен не верно! Попробуйте ещё раз.");
                return "false";
        }
    }

    private static int[] array_min_max(int[] a){
        int[] mas_min_max={a[a.length-1],a[a.length-1]};
        for (int i = 0; i < a.length; i++) {
            if(a[i]<mas_min_max[0]) mas_min_max[0]=a[i];
            if(a[i]>mas_min_max[1]) mas_min_max[1]=a[i];
        }
        return mas_min_max;
    }
}
