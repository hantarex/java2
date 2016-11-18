package ru.sportdepo.ashikov.lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Receive extends AppCompatActivity {

    public static final String MESSAGE = "message";
    public static final String returnMessage = "Был произведён возрат в главное окно";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        TextView textView=(TextView) findViewById(R.id.receive_text);
        Intent intent=getIntent();
        if(intent.getComponent().getPackageName().equals(getApplicationContext().getPackageName())) { // Ограничиваем доступ из дугих приложений
            String message = intent.getStringExtra(MESSAGE);
            textView.setText(message);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent();
        intent.putExtra(MESSAGE, returnMessage);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}
