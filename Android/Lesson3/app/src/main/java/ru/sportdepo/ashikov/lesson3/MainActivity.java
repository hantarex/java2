package ru.sportdepo.ashikov.lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String INTENT_NOT_FOUND = "Intent not found!";
    private Button send_button;
    private Button implicit_button;
    private EditText edit_text;
    private TextView check_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_button=(Button) findViewById(R.id.button);
        implicit_button=(Button) findViewById(R.id.buttonImplicit);
        edit_text=(EditText) findViewById(R.id.edit_text);
        check_intent=(TextView) findViewById(R.id.main_back_message);

        send_button.setOnClickListener(listener);
        implicit_button.setOnClickListener(listener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String message = data.getStringExtra(Receive.MESSAGE);
                check_intent.setText(message);
            }
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    sendIntent(edit_text.getText().toString());
                    break;
                case R.id.buttonImplicit:
                    sendImplicitIntent();
                    break;
            }

        }
    };

    private void sendImplicitIntent() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setType("*");
            startActivity(intent);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), INTENT_NOT_FOUND,Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void sendIntent(String string){
        Intent intent = new Intent(this,Receive.class);
        intent.putExtra(Receive.MESSAGE,string);
        startActivityForResult(intent,1);
    }
}
