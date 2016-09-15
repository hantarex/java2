package geekbrains.java_2.lesson_1;

public class Cat extends Animal implements Jumpable{
    public Cat(String name){
        this.name=name;
        this.animalType="Cat";
        this.onDistance=true;
        this.maxRunDistance=500;
    }

    @Override
    public void jump(float height) {
        if(height < 2f){
            System.out.println(animalType + " " + name + " jump ok");
        }
        else {
            getOutOfDist();
        }
    }
}
