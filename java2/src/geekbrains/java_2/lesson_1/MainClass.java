package geekbrains.java_2.lesson_1;

public class MainClass {
    public static void main(String[] args) {
        Animal[] an = new Animal[4];
        an[0]=new Cat("Barsik");
        an[1]=new Dog("Tomas");
        an[2]=new Duck("Donald");
        an[3]=new Horse("Pegas");

        Obstacle[] obs=new Obstacle[4];
        obs[0]=new OCross(200);
        obs[1]=new OWater(1000);
        obs[2]=new OJump(1.5f);
        obs[3]=new OCross(800);

        for(Animal a : an){
            for(Obstacle o : obs){
                o.doIt(a);
                if(!a.isOnDistance()) break;
            }
        }

        for(Animal a : an){
            if(a.isOnDistance()) a.printInfo();
        }
        System.out.println("Домашнее задание:");
        Animal[] an2 = new Animal[4];
        an2[0]=new Cat("Barsik1");
        an2[1]=new Dog("Tomas1");
        an2[2]=new Duck("Donald1");
        an2[3]=new Horse("Pegas1");


        Team t1 = new Team("1 Команда", an);
        Team t2 = new Team("2 Команда", an2);

        Obstacle[] obs1=new Obstacle[4];
        obs1[0]=new OCross(200);
        obs1[1]=new OWater(1000);
        obs1[2]=new OJump(1.5f);
        obs1[3]=new OCross(800);

        Course course = new Course(obs1);

        course.dolt(t1);
        course.dolt(t2);

        t1.ShowResults();
        t2.ShowResults();


    }


}
