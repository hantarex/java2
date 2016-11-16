package ru.sportdepo.ashikov.lesson2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgramProcessing programProcessing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        programProcessing=new ProgramProcessing(this);
    }

    public void Prev(View view) {
        programProcessing.Prev(view);
    }

    public void Next(View view) {
        programProcessing.Next(view);
    }

    public void btnNo(View view) {
        programProcessing.btnNo(view);
    }
}
