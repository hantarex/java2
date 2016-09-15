package geekbrains.java_2.lesson_1;

public class Dog extends Animal implements Swimable, Jumpable{
    public Dog(String name){
        this.name=name;
        this.animalType="Dog";
        this.onDistance=true;
        this.maxRunDistance=1500;
    }

    @Override
    public void swim(float dist) {
        if(dist < 1100f){
            System.out.println(animalType + " " + name + " swim ok");
        }
        else {
            getOutOfDist();
        }
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
