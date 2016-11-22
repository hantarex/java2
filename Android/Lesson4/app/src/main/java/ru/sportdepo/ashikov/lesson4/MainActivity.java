package ru.sportdepo.ashikov.lesson4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText spaceA=null;
    private EditText spaceB=null;
    private EditText spaceC=null;
    private EditText spaceAct=null;
    private Calculate calculate=null;
    private Toast toast=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculate=new Calculate(this);

        if(savedInstanceState!=null){
            calculate.setAct(savedInstanceState.getString("act"));
            calculate.setSpaceA(savedInstanceState.getString("spaceA"));
            calculate.setSpaceB(savedInstanceState.getString("spaceB"));
        }

        spaceA=(EditText) findViewById(R.id.space_a);
        spaceB=(EditText) findViewById(R.id.space_b);
        spaceC=(EditText) findViewById(R.id.space_c);
        spaceAct=(EditText) findViewById(R.id.act);

        findViewById(R.id.btn_minus).setOnClickListener(listener);
        findViewById(R.id.btn_plus).setOnClickListener(listener);
        findViewById(R.id.btn_un).setOnClickListener(listener);
        findViewById(R.id.btn_uno).setOnClickListener(listener);
        findViewById(R.id.btn_eq).setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case  R.id.btn_minus:
                    spaceAct.setText(R.string.minus);
                    calculate.setAct(getResources().getString(R.string.minus));
                    break;
                case R.id.btn_plus:
                    spaceAct.setText(R.string.plus);
                    calculate.setAct(getResources().getString(R.string.plus));
                    break;
                case R.id.btn_un:
                    spaceAct.setText(R.string.un);
                    calculate.setAct(getResources().getString(R.string.un));
                    break;
                case R.id.btn_uno:
                    spaceAct.setText(R.string.uno);
                    calculate.setAct(getResources().getString(R.string.uno));
                    break;
                case R.id.btn_eq:
                    calculate.setSpaceA(spaceA.getText().toString());
                    calculate.setSpaceB(spaceB.getText().toString());
//                    calculate.setAct(spaceAct.getText().toString());
                    calculate.compute();
                    break;
                default:
            }
        }
    };

    void showError(String s) {
        if(toast != null) toast.cancel();
        toast=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        toast.show();
    }

    void showResult(String s) {
        spaceC.setText(s);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("spaceA",calculate.getSpaceA());
        outState.putString("spaceB",calculate.getSpaceB());
        outState.putString("act",calculate.getAct());
        super.onSaveInstanceState(outState);
    }
}
