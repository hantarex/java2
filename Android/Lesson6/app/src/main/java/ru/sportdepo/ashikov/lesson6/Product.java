package ru.sportdepo.ashikov.lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Map;

public class Product extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra(MainActivity.PAGE).equals(MainActivity.PRODUCT)) {
            int id = intent.getIntExtra(MainActivity.ID, 0);
            int parent = intent.getIntExtra(MainActivity.PARENT, 0);
            XMLParser xml = new XMLParser(this);
            Map values = xml.returnArrayFromXml();

            TextView title = (TextView) findViewById(R.id.title);
            TextView content = (TextView) findViewById(R.id.content);
            title.setText(Program.getTitleById(values, MainActivity.PARENT_MENU, id,parent));
            content.setText(Program.getTextById(values, MainActivity.PARENT_MENU, id,parent));
        }
    }
}
