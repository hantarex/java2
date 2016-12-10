package ru.sportdepo.shikov.lesson7;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    public static List<Workout> workout=new ArrayList<>();
    protected String content;
    protected String menu;

    Workout(String menu,String content){
        this.menu=menu;
        this.content=content;
        Workout.workout.add(this);
    }

    public String getContent() {
        return content;
    }

    public String getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return menu;
    }

    public static void fillWorkout(){
        new Workout("1","1m");
        new Workout("2","2m");
        new Workout("3","3m");
    }
}
