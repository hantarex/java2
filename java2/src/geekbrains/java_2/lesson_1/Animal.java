package geekbrains.java_2.lesson_1;

public abstract class Animal {
    protected String name;
    protected boolean onDistance;
    protected String animalType;
    protected int maxRunDistance;

    public void cross(float dist){
        if(dist < maxRunDistance){
            System.out.println(animalType + " " + name + " run ok");
        }
        else{
            getOutOfDist();
        }
    }

    public boolean isOnDistance() {
        return onDistance;
    }

    public void setOnDistance(boolean onDistance) {
        this.onDistance = onDistance;
    }

    public void printInfo(){
        System.out.println("name "+name);
    }

    public void getOutOfDist(){
        onDistance=false;
    }

}
