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

public class Main_Ngang extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonMinus, buttonCE, buttonAC, buttonEqual,buttonPi,buttonLeftBrack,buttonRightBrack,buttonExpone,
            buttonDot, buttonPercent, buttonSqrt, buttonSqr, buttonFraction,buttonDel,buttonSin,buttonCos,buttonTan,buttonStage;
    ImageButton buttonhis;

    TextView resultView, expressionView;

    double mValueOne, mValueTwo;
    List<savekq> savehistories = new ArrayList<savekq>();
    public static final int MY_REQUEST_CODE = 100;
    savekq kqtrave;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result",resultView.getText().toString());
        outState.putString("expression",expressionView.getText().toString());
    }

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
        buttonCE = findViewById(R.id.buttonCE);
        buttonAC = findViewById(R.id.buttonAC);
        buttonDel = findViewById(R.id.buttonDel);
        buttonDot = findViewById(R.id.buttonDot);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPercent = findViewById(R.id.buttonPer);
        buttonStage = findViewById(R.id.buttonStage);
        buttonExpone=findViewById(R.id.buttonExpon);
        //buttonSqr = findViewById(R.id.buttonSqr);
        buttonSqrt = findViewById(R.id.buttonSqrt);
        // buttonFraction = findViewById(R.id.buttonFrac);

        //Luong giac
        buttonPi=findViewById(R.id.buttonPi);
        buttonSin=findViewById(R.id.buttonSin);
        buttonCos = findViewById(R.id.buttonCos);
        buttonTan=findViewById(R.id.buttonTan);

        buttonEqual = findViewById(R.id.buttonEqual);
        buttonLeftBrack=findViewById(R.id.buttonLeftBrack);
        buttonRightBrack=findViewById(R.id.buttonRightBrack);
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);
        buttonhis = findViewById(R.id.buttonhis);
        ButtonEvents btnEvent = new ButtonEvents();
        btnEvent.ClickOnNumberButton(button0, expressionView);
        btnEvent.ClickOnNumberButton(button1, expressionView);
        btnEvent.ClickOnNumberButton(button2, expressionView);
        btnEvent.ClickOnNumberButton(button3, expressionView);
        btnEvent.ClickOnNumberButton(button4, expressionView);
        btnEvent.ClickOnNumberButton(button5, expressionView);
        btnEvent.ClickOnNumberButton(button6, expressionView);
        btnEvent.ClickOnNumberButton(button7, expressionView);
        btnEvent.ClickOnNumberButton(button8, expressionView);
        btnEvent.ClickOnNumberButton(button9, expressionView);
        btnEvent.ClickOnNumberButton(buttonAdd, expressionView);
        btnEvent.ClickOnNumberButton(buttonSub, expressionView);
        btnEvent.ClickOnNumberButton(buttonDiv, expressionView);
        btnEvent.ClickOnNumberButton(buttonMul, expressionView);
        if(savedInstanceState!=null) {
            resultView.setText(savedInstanceState.getString("result"));
            expressionView.setText(savedInstanceState.getString("expression"));
        }
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"(-");
            }
        });
        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"√(");
//                char[] expression = expressionView.getText().toString().trim().toCharArray();
//                String temp = expressionView.getText().toString().trim();
//                for(int i=0;i<expression.length;i++)
//                    if(expression[i]=='√')
//                        resultView.setText(resultView.getText()+Math.sqrt(expression[i+1]));
            }
        });
        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
                expressionView.setText("");
            }
        });
        buttonStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"!");
            }
        });
        buttonRightBrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+")");
            }
        });
        buttonExpone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"^");
            }
        });
        buttonLeftBrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"(");
            }
        });
        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"%");
            }
        });
        buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"sin(");
            }
        });
        buttonCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"cos(");
            }
        });
        buttonTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText()+"tan(");
            }
        });
      buttonPi.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
             expressionView.setText(expressionView.getText()+"π");
           }
       });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText() + ".");
            }
        });
        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = expressionView.getText().toString();
                if(expression.length() != 1)
                    expression = expression.substring(0, expression.length() - 1);
                else expression = "0";
                expressionView.setText(expression);
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
        } else{

        }

    }
    private void cal() {
        char[] expression = expressionView.getText().toString().trim().toCharArray();
        String temp = expressionView.getText().toString().trim();
        boolean flat = false;
        int k = expression.length;
//        String a = "Math.sqrt";
        for (int i = 0; i < k; i++) {
            if (expression[i] == '\u00D7')
                expression[i] = '*';
            if (expression[i] == '\u00f7')
                expression[i] = '/';
        for(int j =0 ;j<k;j++)
        {
            if(expression[j]=='√')
            {
                flat = true;
                expression[j] = expression[j+1];
                k--;
            }
            if(flat==true && expression[j]==')')
            {
                flat = false;
                for(int x =j+1;x<k;x++)
                {
                    expression[x]=expression[x-1];
                }
                expression[j]='²';
            }
        }

            if (expression.length > 0) {
                Balan balan = new Balan();
                double realResult = balan.valueMath(String.copyValueOf(expression));
                int naturalResult;
                String finalResult;
                if (realResult % 1 == 0) {
                    naturalResult = (int) Math.round(realResult);
                    finalResult = String.valueOf(naturalResult);
                } else
                    finalResult = String.valueOf(realResult);
                String error = balan.getError();

                // check error
                if (error != null) {
                    resultView.setText(error);
                } else { // show result
                    expressionView.setText(temp);
                    resultView.setText(finalResult);
                }
            }
        }


    }
    public void lichsu(View view) {
        Intent myIntent = new Intent(view.getContext(), save_history
                .class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)savehistories);
        myIntent.putExtra("BUNDLE",args);
        this.startActivityForResult(myIntent,MY_REQUEST_CODE);
    }

    private  void Writehistory (List list,savekq savehistory)
    {
        if (kiemtrasopt(list)<5)
        {
            list.add(0,savehistory);
        }
        else
        {
            list.remove(4);
            list.add(0,savehistory);
        }

    }
    private int kiemtrasopt (List list)
    {
        int dem = list.size();
        return dem;
    }
}

