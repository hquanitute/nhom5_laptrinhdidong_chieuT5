package androiddev.nhom5.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonMínus, buttonC, buttonAC, buttonEqual,buttonPi,buttonLeftBrack,buttonRightBrack,
            buttonDot, buttonPercent, buttonSqrt, buttonSqr, buttonFraction,buttonDel;
    TextView resultView, expressionView;

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
        //buttonC = findViewById(R.id.button);
        buttonAC = findViewById(R.id.buttonAC);
        buttonDel = findViewById(R.id.buttonDel);
        buttonDot = findViewById(R.id.buttonDot);
        buttonMínus = findViewById(R.id.buttonMinus);
        buttonPercent = findViewById(R.id.buttonPer);
        buttonSqr = findViewById(R.id.buttonSqr);
        buttonSqrt = findViewById(R.id.buttonSqrt);
        // buttonFraction = findViewById(R.id.buttonFrac);
        buttonPi=findViewById(R.id.buttonPi);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonLeftBrack=findViewById(R.id.buttonLeftBrack);
        buttonRightBrack=findViewById(R.id.buttonRightBrack);
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);

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
//        buttonC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resultView.setText("0");
//                expressionView.setText("");
//            }
//        });
        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
                expressionView.setText("");
            }
        });
//        buttonRightBrack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expressionView.setText(expressionView.getText()+")");
//            }
//        });
//        buttonSqrt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expressionView.setText(expressionView.getText()+"\u221A");
//            }
//        });
//      buttonPi.setOnClickListener(new View.OnClickListener() {
//         @Override
//          public void onClick(View v) {
//             expressionView.setText(expressionView.getText()+"3.14");
//           }
//       });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressionView.setText(expressionView.getText() + ".");
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

    private void cal() {
        char[] expression = expressionView.getText().toString().trim().toCharArray();
        String temp = expressionView.getText().toString().trim();
        for (int i = 0; i < expression.length; i++) {
            if (expression[i] == '\u00D7')
                expression[i] = '*';
            if (expression[i] == '\u00f7')
                expression[i] = '/';
        }
        if (expression.length > 0) {
            Balan balan = new Balan();
            double realResult = balan.valueMath(String.copyValueOf(expression));
            int naturalResult;
            String finalResult;
            if (realResult % 1 == 0) {
                naturalResult = (int) Math.round(realResult);
                finalResult = String.valueOf(naturalResult);
            }
            else
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