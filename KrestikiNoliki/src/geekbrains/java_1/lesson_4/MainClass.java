package geekbrains.java_1.lesson_4;

/**
 * Created by ashikov on 20.07.2016.
 */
public class MainClass {
    public static void main(String[] args) {
        Cat cat= new Cat();
        Cat[] cats = new Cat[5];
        cat.name="Мурзик";
        cat.color="Чёрный";
        cat.age=3;
        System.out.println("Кот "+cat.name+" Цвет "+cat.color+" Возраст "+ cat.age);

        for (int i = 0; i <cats.length ; i++) {
            cats[i]=new Cat();
        }
    }
}
