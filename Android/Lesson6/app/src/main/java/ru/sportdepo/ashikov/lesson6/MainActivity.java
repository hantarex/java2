package ru.sportdepo.ashikov.lesson6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String HEAD_MENU = "HeadMenu";
    public static final String PAGE = "page";
    public static final String ID = "id";
    public static final String SUB_CAT = "subCat";
    public static final String PARENT_MENU = "ParentMenu";
    public static final String PRODUCT = "product";
    public static final String PARENT = "parent";
    Map values = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        XMLParser xml = new XMLParser(this);
        values = xml.returnArrayFromXml();
        System.out.println(values);

        Program program=new Program(this);
        program.addListMain(values,HEAD_MENU,SUB_CAT,SubCatalog.class);
    }




}
