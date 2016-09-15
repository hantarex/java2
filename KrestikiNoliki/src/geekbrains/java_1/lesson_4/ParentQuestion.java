package geekbrains.java_1.lesson_4;

/**
 * Created by ashikov on 25.07.2016.
 */
class Master{
    int i;
}

class Child_this extends Master{
    public Child_this(int i)
    {
        this.i=i;
    }
}

class Child_super extends Master{
    public Child_super(int i)
    {
        super.i=i;
    }
}

public class ParentQuestion {
    public static void main(String[] args) {
        Master master_this = new Child_this(1);
        System.out.println(master_this.i);

        Master master_super = new Child_super(1);
        System.out.println(master_super.i);
    }
}
