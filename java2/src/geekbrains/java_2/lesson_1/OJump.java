package geekbrains.java_2.lesson_1;

public class OJump extends Obstacle{
    public OJump(float size) {
        this.size=size;
    }

    @Override
    public void doIt(Animal a) {
        if(a instanceof Swimable){
            ((Jumpable)a).jump(size);
        }
        else {
            a.getOutOfDist();
        }
    }
}
