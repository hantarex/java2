package geekbrains.java_2.lesson_1;

public class Team {
    protected String name;
    protected Animal[] animals;

    public Team(String name,Animal[] animals){
        this.name=name;
        this.animals=animals;
    }

    public void printInfo(){
        for(Animal a : animals){
            System.out.println(name+ " "+ a.animalType + " " +a.name);
        }
    }

    public void ShowResults(){
        for(Animal a : animals){
            System.out.println(name+ " "+ a.animalType + " " +a.name + (a.isOnDistance()?" ok":" out"));
        }
    }
}
