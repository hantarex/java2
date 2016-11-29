package ru.sportdepo.ashikov.lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Map;

public class SubCatalog extends AppCompatActivity {
    String pageTitle;
    int id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity_main);
        Intent intent = getIntent();
        TextView head;
        if(intent!=null && intent.getStringExtra(MainActivity.PAGE).equals(MainActivity.SUB_CAT)){
            id=intent.getIntExtra(MainActivity.ID,0);
            head= (TextView) findViewById(R.id.head);

            XMLParser xml = new XMLParser(this);
            Map values = xml.returnArrayFromXml();

            head.setText(Program.getNameById(values,"HeadMenu",id));

            Program program=new Program(this);
            program.addListMain(values, MainActivity.PARENT_MENU, MainActivity.PRODUCT,Product.class,id);
        }
    }
}
