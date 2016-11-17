package ru.sportdepo.ashikov.lesson2;

import android.app.Activity;
import android.graphics.Color;
import android.renderscript.Byte2;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Canvas {
    AppCompatActivity Activity;
    public Canvas(AppCompatActivity Activity){
        this.Activity=Activity;
    }
    public void showQuestion(Question q){
        TextView q_area=(TextView) Activity.findViewById(R.id.q_area);
        Button btn_yes = (Button) Activity.findViewById(R.id.button_yes);
        Button btn_no = (Button) Activity.findViewById(R.id.button_no);
        q_area.setText(q.getQuestion());
        if(q.isUser_answer()==null){
            btn_yes.setBackgroundResource(android.R.drawable.btn_default);
            btn_no.setBackgroundResource(android.R.drawable.btn_default);
        } else {
            btn_no.setEnabled(false);
            btn_yes.setEnabled(false);
            if(q.isUser_answer().equals("1")) {
                btn_no.setBackgroundResource(android.R.drawable.btn_default);
                if (q.getAnswer()) btn_yes.setBackgroundColor(Color.GREEN);
                else btn_yes.setBackgroundColor(Color.RED);
            }
            else {
                btn_yes.setBackgroundResource(android.R.drawable.btn_default);
                if (!q.getAnswer()) btn_no.setBackgroundColor(Color.GREEN);
                else btn_no.setBackgroundColor(Color.RED);
            }
        }
    }

    public void changeColor(View view, boolean b) {
        Button button=(Button) view;
        if(b) button.setBackgroundColor(Color.GREEN);
        else button.setBackgroundColor(Color.RED);
    }

    public void setProgress(int progress) {
        ProgressBar progressBar=(ProgressBar) Activity.findViewById(R.id.progressBar);
        Log.d("Progress",String.valueOf(progress));
        progressBar.setProgress(progress);
    }

    public void showButton(Question q) {

    }
}
