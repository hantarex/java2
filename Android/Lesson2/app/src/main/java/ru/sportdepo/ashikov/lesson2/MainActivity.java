package ru.sportdepo.ashikov.lesson2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       XmlPullParser questions = getResources().getXml(R.xml.arrays);
        try {
            questions.next();
            questions.next();
            questions.next();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(questions.getName());
        System.out.println(questions.getNamespace());
        System.out.println(questions.getDepth());
        System.out.println(questions.getAttributeCount());
        System.out.println(questions.getAttributeValue(0));
        System.out.println(questions.getAttributeValue(1));
        System.out.println(questions.getText());
    }
}
