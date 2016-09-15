package geekbrains.java_2.lesson_1;

public class Horse extends Animal implements Swimable, Jumpable{
    public Horse(String name){
        this.name=name;
        this.animalType="Horse";
        this.onDistance=true;
        this.maxRunDistance=5000;
    }

    @Override
    public void swim(float dist) {
        if(dist < 100f){
            System.out.println(animalType + " " + name + " swim ok");
        }
        else {
            getOutOfDist();
        }
    }

    @Override
    public void jump(float height) {
        if(height < 1.5f){
            System.out.println(animalType + " " + name + " jump ok");
        }
        else {
            getOutOfDist();
        }
    }
}
