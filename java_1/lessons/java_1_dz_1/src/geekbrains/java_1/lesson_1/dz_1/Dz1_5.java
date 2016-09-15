package geekbrains.java_1.lesson_1.dz_1;

/**
 * Created by Bro on 10.07.2016.
 */
public class Dz1_5 {
    public static void main(String[] args) {
        int year=2016;
        System.out.println("Год "+year+(CheckYear(year)?" Високосный":" Не високосный"));
    }
    private static boolean CheckYear(int year){
        if(year%4==0 & (year%100!=0 | year%400==0)) return true;
        else return false;
    }
}
