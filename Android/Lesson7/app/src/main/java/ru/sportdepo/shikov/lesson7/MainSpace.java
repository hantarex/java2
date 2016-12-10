package ru.sportdepo.shikov.lesson7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class mainSpace extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_space,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Integer activeId=MainActivity.activeMenu;
        if(activeId==null) activeId=0;
        System.out.println(((Workout)Workout.workout.get(activeId)).getContent());
        TextView text=(TextView) getView().findViewById(R.id.mainspace);
        text.setText(((Workout)Workout.workout.get(activeId)).getContent());
        System.out.println(text.getText());
    }
}
