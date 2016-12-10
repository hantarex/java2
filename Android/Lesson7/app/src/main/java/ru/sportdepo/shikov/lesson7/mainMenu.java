package ru.sportdepo.shikov.lesson7;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class mainMenu extends ListFragment{
    getSetActiveMenu mainActivity;
    public static interface getSetActiveMenu{
        void setActiveMenu(Integer menu);
        Integer getActiveMenu();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<Workout> adapter=new ArrayAdapter<Workout>(inflater.getContext(), android.R.layout.simple_expandable_list_item_1,Workout.workout);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity context) {
        mainActivity=(getSetActiveMenu) context;
        System.out.println("onAttach");
        if(mainActivity.getActiveMenu()==null) mainActivity.setActiveMenu(0);
        super.onAttach(context);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mainActivity.setActiveMenu((int) id);
    }
}
