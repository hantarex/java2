package geekbrains.java_1.lesson_1.dz_1;

/**
 * Created by Bro on 10.07.2016.
 */
public class Dz1_3 {
    public static void main(String[] args) {
        System.out.println("Результат = "+MathCustom(1,2,3,4));
    }
    private static double MathCustom(int a,int b, int c, int d){
        return a*(b+((double)c/d));
    }
}
