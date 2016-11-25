package ru.sportdepo.ashikov.lesson5;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout=null;
    EditText to=null;
    EditText message=null;
    Button sendButton=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridLayout=new GridLayout(getApplicationContext());
        GridLayout.LayoutParams gridLparam = new GridLayout.LayoutParams();
        gridLparam.width= GridLayout.LayoutParams.MATCH_PARENT;
        gridLparam.height= GridLayout.LayoutParams.MATCH_PARENT;
        gridLayout.setRowCount(2);
        gridLayout.setColumnCount(2);
//        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
//        gridLparam.setGravity(Gravity.FILL);
        gridLayout.setLayoutParams(gridLparam);

        setContentView(gridLayout);

        to = new EditText(getApplicationContext());
        GridLayout.LayoutParams toLparam=new GridLayout.LayoutParams();
        toLparam.columnSpec=GridLayout.spec(0,GridLayout.FILL,1);
//        toLparam.rowSpec = GridLayout.spec(0);
        to.setHint(R.string.to);
        to.setLayoutParams(toLparam);
        gridLayout.addView(to);

        message = new EditText(getApplicationContext());
        GridLayout.LayoutParams messageLparam=new GridLayout.LayoutParams();
        messageLparam.columnSpec=GridLayout.spec(0,GridLayout.FILL,1);
//        messageLparam.rowSpec = GridLayout.spec(1);
        message.setHint(R.string.message);
        message.setLayoutParams(messageLparam);
        gridLayout.addView(message);

        Button button = new Button(getApplicationContext());
        button.setText(R.string.btnSend);
        GridLayout.LayoutParams toBparam = new GridLayout.LayoutParams();
        toBparam.columnSpec = GridLayout.spec(1);
        toBparam.rowSpec = GridLayout.spec(0,GridLayout.FILL);
//        button.setLayoutParams(toBparam);
        gridLayout.addView(button,toBparam);



//        Button button = new Button(getApplicationContext());
//        button.setText("Button");
//        GridLayout.LayoutParams toBparam=new GridLayout.LayoutParams();
//        toBparam.columnSpec=GridLayout.spec(0, GridLayout.FILL,1);
//        toBparam.rowSpec=GridLayout.spec(1, GridLayout.FILL);
//        button.setLayoutParams(toBparam);
//        gridLayout.addView(button);
//
//        Button button1 = new Button(getApplicationContext());
//        button1.setText("Button1");
//        GridLayout.LayoutParams toBparam1=new GridLayout.LayoutParams();
//        toBparam1.columnSpec=GridLayout.spec(1, GridLayout.FILL,1);
//        toBparam1.rowSpec=GridLayout.spec(1, GridLayout.FILL);
//        button1.setLayoutParams(toBparam1);
//        gridLayout.addView(button1);

//        Button button1 = new Button(getApplicationContext());
//        button1.setText("Button1");
//        GridLayout.LayoutParams toBparam1=new GridLayout.LayoutParams();
//        toBparam1.columnSpec=GridLayout.spec(1,GridLayout.FILL);
//        toBparam1.rowSpec=GridLayout.spec(1,GridLayout.FILL);
//        button1.setLayoutParams(toBparam1);
//        gridLayout.addView(button1);

//        Button button1 = new Button(getApplicationContext());
//        button1.setText("Button1");
//        GridLayout.LayoutParams toBparam1=new GridLayout.LayoutParams();
//        toBparam1.columnSpec=GridLayout.spec(2,GridLayout.FILL);
//        toBparam1.rowSpec=GridLayout.spec(1,GridLayout.FILL);
//        button1.setLayoutParams(toBparam1);
//        gridLayout.addView(button1);
//        gridLayout.addView(button,new GridLayout.LayoutParams(GridLayout.spec(1,GridLayout.FILL),GridLayout.spec(1,GridLayout.FILL)));

//        Button button1 = new Button(getApplicationContext());
//        GridLayout.LayoutParams toBparam1=new GridLayout.LayoutParams();
////        toBparam1.setGravity(Gravity.FILL);
//        toBparam1.columnSpec=GridLayout.spec(1,1);
////        toBparam.rowSpec=GridLayout.spec(0,1);
//        button1.setLayoutParams(toBparam1);
//        gridLayout.addView(button1);

//        message = new EditText(getApplicationContext());
//        GridLayout.LayoutParams messageLparam=new GridLayout.LayoutParams();
//        messageLparam.setGravity(Gravity.FILL);
//        messageLparam.columnSpec=GridLayout.spec(0);
//        messageLparam.rowSpec=GridLayout.spec(1);
//        message.setLayoutParams(messageLparam);
//        gridLayout.addView(message);
    }
}
