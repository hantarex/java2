package ru.sportdepo.shikov.lesson7;

import android.app.FragmentTransaction;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements mainMenu.getSetActiveMenu{
    protected static Integer activeMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Workout.fillWorkout();
//        System.out.println(Workout.workout);
    }

    @Override
    public void setActiveMenu(Integer menu) {
        activeMenu=menu;
        View fragment=findViewById(R.id.main);
        mainSpace newSpace=new mainSpace();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main,newSpace);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public Integer getActiveMenu() {
        return activeMenu;
    }
}
