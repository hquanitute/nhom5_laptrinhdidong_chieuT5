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
            buttonMul, buttonMinus, buttonC, buttonCE, buttonEqual,
            buttonDot, buttonPercent, buttonSqrt, buttonSqr, buttonFraction;
    ImageButton buttonDel;
    TextView resultView, expressionView;

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
        //buttonCE = findViewById(R.id.buttonCE);
       // buttonDel = findViewById(R.id.buttonDel);
        buttonDot = findViewById(R.id.buttonDot);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPercent = findViewById(R.id.buttonPer);
        buttonSqr = findViewById(R.id.buttonSqr);
        buttonSqrt = findViewById(R.id.buttonSqrt);
       // buttonFraction = findViewById(R.id.buttonFrac);
        buttonEqual = findViewById(R.id.buttonEqual);
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);

        ButtonEvents btnEvent = new ButtonEvents();
        btnEvent.ClickOnNumberButton(button0, resultView);
        btnEvent.ClickOnNumberButton(button1, resultView);
        btnEvent.ClickOnNumberButton(button2, resultView);
        btnEvent.ClickOnNumberButton(button3, resultView);
        btnEvent.ClickOnNumberButton(button4, resultView);
        btnEvent.ClickOnNumberButton(button5, resultView);
        btnEvent.ClickOnNumberButton(button6, resultView);
        btnEvent.ClickOnNumberButton(button7, resultView);
        btnEvent.ClickOnNumberButton(button8, resultView);
        btnEvent.ClickOnNumberButton(button9, resultView);
        btnEvent.ClickOnNumberButton(buttonAdd, resultView);
        btnEvent.ClickOnNumberButton(buttonSub, resultView);
        btnEvent.ClickOnNumberButton(buttonDiv, resultView);
        btnEvent.ClickOnNumberButton(buttonMul, resultView);

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
                expressionView.setText("");
            }
        });
//        buttonCE.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resultView.setText("0");
//            }
//        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + ".");
            }
        });
//        buttonDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String expression = resultView.getText().toString();
//                if(expression.length() != 1)
//                    expression = expression.substring(0, expression.length() - 1);
//                else expression = "0";
//                resultView.setText(expression);
//            }
//        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal();
            }
        });

    }

    private void cal() {
        char[] expression = resultView.getText().toString().trim().toCharArray();
        String temp = resultView.getText().toString().trim();
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
