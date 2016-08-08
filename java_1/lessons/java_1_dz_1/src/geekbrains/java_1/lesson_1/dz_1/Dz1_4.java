package geekbrains.java_1.lesson_1.dz_1;

/**
 * Created by Bro on 10.07.2016.
 */
public class Dz1_4 {
    public static void main(String[] args) {
        int a=1;
        int b=7;
        System.out.println("Сумма "+a+" и "+b+(MathCustom(a,b)?" Лежит ":" Не лежит ")+"в диапазоне от 10 до 20");
    }
    private static boolean MathCustom(int a,int b){
        return (10<=(a+b) & (a+b)<=20)?true:false;
    }
}
