package ru.sportdepo.ashikov.lesson6;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class XMLParser{
    AppCompatActivity Activity=null;
    public XMLParser(AppCompatActivity Activity) {
        this.Activity=Activity;
    }

    public Map returnArrayFromXml() {
        int xml_path=R.xml.arrays;
        Map<String,ArrayList<Map>> values = new HashMap<>();
        Map<String,String> attr=new HashMap<>();
        ArrayList<Map> attr_id=new ArrayList<>();
        String tag=null;
        Boolean end=false;
        try {
            XmlPullParser xml = Activity.getResources().getXml(xml_path);
            while (xml.getEventType() != XmlPullParser.END_DOCUMENT) {
                while (xml.getName()!=null && xml.getName().equals("root")) xml.next();
                if(xml.getEventType()==XmlPullParser.START_TAG) {
                    tag=xml.getName();
//                    Log.i("tag",tag);
                    do {
                        end=false;
                        xml.next();
                        if(xml.getEventType()==XmlPullParser.START_TAG) {
                            for (int i = 0; i < xml.getAttributeCount(); i++) {
                                attr.put(xml.getAttributeName(i),xml.getAttributeValue(i));
                            }
                        }
                        if(xml.getEventType()==XmlPullParser.TEXT) {
                            attr.put("value",xml.getText());
                            attr_id.add(attr);
                            attr=new HashMap<>();
//                            System.out.println(attr_id);
                        }
//                        if(xml.getEventType()==XmlPullParser.START_TAG) Log.i("childTag",xml.getName());
//                        Log.i("getEventType",Integer.toString(xml.getEventType()));
//                        Log.i("getName",xml.getName()!=null?xml.getName():"null");
                        if (xml.getEventType()==XmlPullParser.END_TAG && xml.getName().equals(tag)) end=true;
                    } while (!end);
                    values.put(tag,attr_id);
                    attr_id=new ArrayList<>();
                }
                xml.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

}
