package geekbrains.java_2.lesson_1;

public class Course {
    public Obstacle[] obstacles;

    public Course(Obstacle[] obstacles){
        this.obstacles=obstacles;
    }

    public void dolt(Team team){
        for(Animal animal : team.animals){
            for(Obstacle obstacle : this.obstacles){
                obstacle.doIt(animal);
                if(!animal.isOnDistance()) break;
            }
        }
    }
}
