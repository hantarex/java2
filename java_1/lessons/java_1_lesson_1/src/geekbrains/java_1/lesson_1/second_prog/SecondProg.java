package geekbrains.java_1.lesson_1.second_prog;

/**
 * Created by Bro on 10.07.2016.
 */
public class SecondProg {
    public static void main(String[] args) {
        int a=1;
        int b=2;
        int c=a+b;
        System.out.println("Сумма = " + c);

        int d=a-b;
        System.out.println("Разность = "+d);

        int e=a/b;
        System.out.println("Целочистенное деление = "+e);

        float t=(float) a/b;
        System.out.println("Деление с плавающей точкой = "+t);

        int i =10;
        System.out.println("i = "+i);
        i+=10;
        System.out.println("i = "+i);
    }
}
