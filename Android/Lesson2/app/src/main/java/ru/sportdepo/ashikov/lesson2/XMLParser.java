package ru.sportdepo.ashikov.lesson2;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser{
    protected int xml_path=R.xml.arrays;
    protected AppCompatActivity Activity;
    public XMLParser(AppCompatActivity Activity){
        this.Activity=Activity;
    }
    public List<Question> returnArrayFromXml() {
        List<Question> q_list=new ArrayList<Question>();
        String question=null;
        Boolean answer=false;
        try {
            XmlPullParser questions = Activity.getResources().getXml(xml_path);
            while (questions.getEventType() != XmlPullParser.END_DOCUMENT) {
                if(questions.getEventType()==XmlPullParser.START_TAG) {
                    if(questions.getName().equals("item")) {
                        for (int i = 0; i < questions.getAttributeCount(); i++) {
                            if(questions.getAttributeName(i).equals("value")){
//                                System.out.println(questions.getAttributeValue(i));
                                answer=questions.getAttributeValue(i).equals("yes")?true:false;
                            }
                        }
                    }
                }
                if(questions.getEventType()==XmlPullParser.TEXT){
//                    System.out.println(questions.getText());
                    q_list.add(new Question(questions.getText(),answer));
                }
                questions.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return q_list;
    }

}
