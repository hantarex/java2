package geekbrains.java_2.lesson_1;

public class OCross extends Obstacle{

    public OCross(float size) {
        this.size=size;
    }

    @Override
    public void doIt(Animal a) {
        if(a instanceof Swimable){
            a.cross(size);
        }
        else {
            a.getOutOfDist();
        }
    }
}