package geekbrains.java_2.lesson_1;

public class Duck extends Animal implements Swimable{
    public Duck(String name){
        this.name=name;
        this.animalType="Duck";
        this.onDistance=true;
        this.maxRunDistance=10;
    }

    @Override
    public void swim(float dist) {
        if(dist < 3000f){
            System.out.println(animalType + " " + name + " swim ok");
        }
        else {
            getOutOfDist();
        }
    }
}
