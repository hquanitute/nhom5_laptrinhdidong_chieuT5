package androiddev.nhom5.calculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonMínus, buttonC, buttonCE, buttonEqual,
            buttonDot, buttonPercent, buttonSqrt, buttonSqr, buttonFraction;
    ImageButton buttonDel,buttonhis;
    TextView resultView, expressionView;
    double mValueOne, mValueTwo;
    List<savekq> savehistories = new ArrayList<savekq>();
    public static final int MY_REQUEST_CODE = 100;
    savekq kqtrave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonC = findViewById(R.id.buttonC);
        buttonCE = findViewById(R.id.buttonCE);
        buttonDel = findViewById(R.id.buttonDel);
        buttonDot = findViewById(R.id.buttonDot);
        buttonMínus = findViewById(R.id.buttonMinus);
        buttonPercent = findViewById(R.id.buttonPer);
        buttonSqr = findViewById(R.id.buttonSqr);
        buttonSqrt = findViewById(R.id.buttonSqrt);
        buttonFraction = findViewById(R.id.buttonFrac);
        buttonEqual = findViewById(R.id.buttonEqual);
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);
        buttonhis = findViewById(R.id.buttonhis);
        ButtonEvents btnEvent = new ButtonEvents();
        btnEvent.ClickOnNumberButton(button0, resultView);
        btnEvent.ClickOnNumberButton(button1, resultView);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "0");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + ".");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + "+");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal();
            }
        });
    }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            Bundle args = data.getBundleExtra("bundle");
            kqtrave =(savekq) args.getSerializable("kqtrave");
            Toast.makeText(this,savehistories.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(this,kqtrave.toString(),Toast.LENGTH_LONG).show();
        } else{

        }

    }
    private void cal() {
        String math = resultView.getText().toString().trim();
        if (math.length() > 0) {
            Balan balan = new Balan();
            String result = balan.valueMath(math) + "";
            String error = balan.getError();

            // check error
            if (error != null) {
                resultView.setText(error);
            } else { // show result
                resultView.setText(result);
            }
        }
    };

    public void lichsu(View view) {
        savekq ab = new savekq("1+2",3);
        savekq ac = new savekq("1+2",4);
        savekq ad = new savekq("1+2",5);
        savekq ae = new savekq("1+2",6);
        savekq af = new savekq("1+2",7);
        savehistories.add(ab);
        savehistories.add(ac);
        savehistories.add(ad);
        savehistories.add(ae);
        savehistories.add(af);
        Intent myIntent = new Intent(view.getContext(), save_history
                .class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)savehistories);
        myIntent.putExtra("BUNDLE",args);
        this.startActivityForResult(myIntent,MY_REQUEST_CODE);
    }
}
