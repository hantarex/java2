package ru.sportdepo.ashikov.lesson6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Program {
    AppCompatActivity context;
    Integer id=null;
    static Map i;
    String Tag;
    public Program(AppCompatActivity context) {
        this.context=context;
    }

    public static String getNameById(Map values, String tag, int id){
        ArrayList list=new ArrayList();
        list=(ArrayList) values.get(tag);
        return ((Map)list.get(id)).get("value").toString();
    }
    public static void getContentById(Map values,String tag, int id, int parent){
        ArrayList <Map>list=new ArrayList();
        list=(ArrayList) values.get(tag);
        for(Map i : list){
            if(i.get("parent").equals(String.valueOf(parent)) && i.get("id").equals(String.valueOf(id))){
                Program.i=i;
                return;
            }
        }
    }
    public static String getTextById(Map values,String tag, int id, int parent){
        getContentById(values,tag,id,parent);
        return i.get("text").toString();
    }
    public static String getTitleById(Map values,String tag, int id, int parent){
        getContentById(values,tag,id,parent);
        return i.get("value").toString();
    }
    protected void addListMain(Map values, String Tag, final String Target, final Class TargetClass,int id) {
        this.id=id;
        addListMain(values, Tag, Target, TargetClass);
    }
    protected void addListMain(Map values, final String Tag, final String Target, final Class TargetClass) {
        this.Tag=Tag;
        ScrollView scrollView= (ScrollView) context.findViewById(R.id.scroll_main);
        ListView list = new ListView(context.getApplicationContext());
        ArrayList items = new ArrayList();
        Iterator entry = values.entrySet().iterator();
        while (entry.hasNext()) {
            Map.Entry ent = (Map.Entry) entry.next();
            if (ent.getKey().equals(Tag)) {
                ArrayList<Map> attr = (ArrayList<Map>) ent.getValue();
                for (Map val_attr : attr) {
                    if(id!=null) {
                        if(val_attr.get("parent").equals(id.toString())) items.add(val_attr.get("value"));
                    } else {
                        items.add(val_attr.get("value"));
                    }
                }
            }
        }
        ArrayAdapter<Map> listAdapter = new ArrayAdapter<Map>(context.getApplicationContext(), android.R.layout.simple_list_item_1, items);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=null;
                if(i==2 && Tag.equals(MainActivity.HEAD_MENU)) {
                    intent = new Intent(context.getApplicationContext(), Delivery.class);
                } else {
                    intent = new Intent(context.getApplicationContext(), TargetClass);
                }
                intent.putExtra(MainActivity.PAGE, Target);
                intent.putExtra(MainActivity.ID,i);
                if(id!=null) intent.putExtra(MainActivity.PARENT,id);
                context.startActivity(intent);
            }
        });

        scrollView.addView(list);
    }
}
