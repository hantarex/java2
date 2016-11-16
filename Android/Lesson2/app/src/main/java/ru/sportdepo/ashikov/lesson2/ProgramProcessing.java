package ru.sportdepo.ashikov.lesson2;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProgramProcessing {
    protected int active_id;
    protected Canvas canvas;
    protected List<Question> questions;
    protected AppCompatActivity Activity;
    ProgramProcessing(AppCompatActivity Activity){
        this.canvas=new Canvas(Activity);
        this.Activity=Activity;
        List<Question> questions=new ArrayList<Question>();
        XMLParser xmlParser=new XMLParser(Activity);
        questions=xmlParser.returnArrayFromXml();
        this.questions=questions;
        active_id=0;
        showQuestion(active_id);
    }
    public void showQuestion(int id){
        Question q = questions.get(id);
        canvas.showQuestion(q.getQuestion());
        canvas.showButton(q);
    }

    public void Prev(View view) {
        if((active_id-1)>=0) active_id=active_id-1;
        else active_id=questions.size()-1;
        Log.e("ActiveId",String.valueOf(active_id));
        showQuestion(active_id);
    }

    public void Next(View view) {
        if((active_id+1)<questions.size()) active_id=active_id+1;
        else active_id=0;
        Log.e("ActiveId",String.valueOf(active_id));
        showQuestion(active_id);
    }

    public void btnNo(View view) {
        Question q=questions.get(active_id);
        Log.e("Right Answer",q.getAnswer()?"yes":"no");
        setAnswer(view,false);
            updateProgress();
            Next(view);
    }

    private void updateProgress() {
        String user_answer=null;
        int all=questions.size();
        int answ=0;
        for(Question q : questions){
            user_answer=q.isUser_answer();
            if(user_answer!=null){
                answ++;
            }
        }
        Log.e("Answer",String.valueOf(answ));
        Log.e("All",String.valueOf(all));
        canvas.setProgress((int)(answ*100/all));
    }

    public void setAnswer(View view,boolean answer) {
        Question q=questions.get(active_id);
        q.setUser_answer(answer);
        canvas.changeColor(view,q.getAnswer()==(q.isUser_answer().equals("1")?true:false));
        questions.set(active_id,q);
    }
}
