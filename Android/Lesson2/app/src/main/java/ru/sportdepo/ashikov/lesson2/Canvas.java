package ru.sportdepo.ashikov.lesson2;

import android.app.Activity;
import android.graphics.Color;
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
    public void showQuestion(String question){
        TextView q_area=(TextView) Activity.findViewById(R.id.q_area);
        q_area.setText(question);
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
