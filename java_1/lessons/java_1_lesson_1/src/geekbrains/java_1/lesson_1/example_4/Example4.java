package geekbrains.java_1.lesson_1.example_4;

/**
 * Created by Bro on 10.07.2016.
 */
public class Example4 {
    public static void main(String[] args) {
        System.out.println("d = "+sum(10,20));
        System.out.println("d = "+sum(10,20,30));
        System.out.println("d = "+sum(10f,20));

        if(true){
            System.out.println("Истино");
        }
        else{
            System.out.println("Ложно");
        }
    }
    private static int  sum(int a,int b){return a+b;}
    private static int  sum(int a,int b,int c){return a+b+c;}
    private static float  sum(float a,int b){return a+b;}
}
