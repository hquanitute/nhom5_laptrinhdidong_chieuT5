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

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {

    ArrayList<savekq> list = new ArrayList<>();

    TextView resultView, expressionView;
    private boolean mIsCalculating = false, mIsTyping = false, mIsError = false, mIsMulDiv = false;
    private double result = 0, tempResult = 0;

    public void onNumberButtonClick(View view) {
        Button b = (Button) view;
        if (mIsTyping == false) {
            resultView.setText(b.getText());
            mIsTyping = true;
        } else
            resultView.setText(resultView.getText().toString() + b.getText());
    }

    public void onOperatorButtonClick(View v) {
        Button b = (Button) v;
        if (mIsCalculating == false) {
            if (!b.getText().toString().equals("=")) {
                result = result + Double.valueOf(resultView.getText().toString());
                expressionView.setText(resultView.getText().toString() + " " + b.getText());
                mIsTyping = false;
                mIsCalculating = true;
            } else if (mIsTyping == true) {
                expressionView.setText((resultView.getText().toString()));
                mIsTyping = false;
            }
        } else {
            String a = expressionView.getText().toString()
                    .substring(expressionView.getText().toString().length() - 1);
            String c = b.getText().toString();
            switch (c) {
                default:
                    switch (a) {
                        case ("+"):
                            onAddButtonClick(v);
                            break;
                        case ("-"):
                            onSubButtonClick(v);
                            break;
                        case ("\u00D7"):
                            onMulButtonClick(v);
                            break;
                        case ("\u00f7"):
                            onDivButtonClick(v);
                            break;
                    }
                    mIsTyping = false;
                    mIsCalculating = true;
                    break;
                case ("="):
                    switch (a) {
                        case ("+"):
                            onAddButtonClick(v);
                            break;
                        case ("-"):
                            onSubButtonClick(v);
                            break;
                        case ("\u00D7"):
                            onMulButtonClick(v);
                            break;
                        case ("\u00f7"):
                            onDivButtonClick(v);
                            break;
                    }
                    expressionView.setText(expressionView.getText().toString()
                            .substring(0, expressionView.getText().toString().length() - 1));
                    savekq kq;
                    if (!mIsError) {
                        kq = new savekq(expressionView.getText().toString(), Double.valueOf(resultView.getText().toString()));
                        Writehistory(list, kq);
                    }
                    mIsTyping = false;
                    mIsCalculating = false;
                    result = 0;
                    break;
            }
        }
}

    public void onAddButtonClick(View view) {
        DecimalFormat df = new DecimalFormat("#.#############");
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result + Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(df.format(result)));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());

    }

    public void onSubButtonClick(View view) {
        DecimalFormat df = new DecimalFormat("#.#############");
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result - Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(df.format(result)));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());
    }

    public void onMulButtonClick(View view) {
        DecimalFormat df = new DecimalFormat("#.#############");
        Button b = (Button) view;
        if(!mIsMulDiv) tempResult = Double.valueOf(resultView.getText().toString());
        else
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result * Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(df.format(result)));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());
    }

    public void onDivButtonClick(View view) {
        DecimalFormat df = new DecimalFormat("#.#############");
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            try {
                result = result / Double.valueOf(resultView.getText().toString());
                if (result == Double.POSITIVE_INFINITY ||
                        result == Double.NEGATIVE_INFINITY)
                    throw new ArithmeticException();
            } catch (ArithmeticException e) {
                resultView.setText("Syntax Error");
                mIsError = true;
                return;
            }
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(df.format(result)));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());
    }

    double mValueOne, mValueTwo;
    List<savekq> savehistories = new ArrayList<savekq>();
    public static final int MY_REQUEST_CODE = 100;
    savekq kqtrave;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", resultView.getText().toString());
        outState.putString("expression", expressionView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);
        //endregion

        if (savedInstanceState != null) {
            resultView.setText(savedInstanceState.getString("result"));
            expressionView.setText(savedInstanceState.getString("expression"));
        }


    }

    //    private void cal() {
//        char[] expression = (expressionView.getText().toString() + resultView.getText().toString()).trim().toCharArray();
//        for (int i = 0; i < expression.length; i++) {
//            if (expression[i] == '\u00D7')
//                expression[i] = '*';
//            if (expression[i] == '\u00f7')
//                expression[i] = '/';
//        }
//        if (expression.length > 0) {
//            Balan balan = new Balan();
//            double realResult = balan.valueMath(String.copyValueOf(expression));
//            int naturalResult;
//            String finalResult;
//            if (realResult % 1 == 0) {
//                naturalResult = (int) Math.round(realResult);
//                finalResult = String.valueOf(naturalResult);
//            } else
//                finalResult = String.valueOf(realResult);
//            String error = balan.getError();
//
//            // check error
//            if (error != null) {
//                resultView.setText(error);
//            } else { // show result
//                expressionView.setText(expressionView.getText().toString() + " " + resultView.getText().toString());
//                resultView.setText(finalResult);
//            }
//        }
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            Bundle args = data.getBundleExtra("bundle");
            kqtrave = (savekq) args.getSerializable("kqtrave");
            resultView.setText(Double.toString(kqtrave.ketqua));
            expressionView.setText(kqtrave.bieuthu);
        } else {
            Toast.makeText(this, "ko co gi", Toast.LENGTH_LONG).show();
        }

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


    //cac button chuc nang ----------------------------------------------------------------
    public void buttonC(View view) {
        resultView.setText("0");
        expressionView.setText("");
        mIsTyping = false;
        mIsCalculating = false;
        result = 0;
    }

    public void buttonAC(View view) {
        resultView.setText("0");
        expressionView.setText("");
        mIsTyping = false;
        mIsCalculating = false;
        result = 0;
    }

    public void buttonCE(View view) {
        resultView.setText("0");
        mIsTyping = false;
    }

    public void buttonDot(View view) {
        boolean dot = false;
        char[] expression = resultView.getText().toString().trim().toCharArray();
        for (int i = 0; i < expression.length; i++) {
            if (expression[i] == '.')
                dot = true;
        }
        if (!dot)
            resultView.setText(resultView.getText() + ".");
        mIsTyping = true;
    }


    public void buttonRightBrack(View v) {
        expressionView.setText(expressionView.getText() + ")");
    }

    public void buttonLeftBrack(View v) {
        expressionView.setText(expressionView.getText() + "(");
    }


    public void buttonSqrt(View v) {
        expressionView.setText(expressionView.getText() + "\u221A");
    }


    public void buttonPi(View v) {
        expressionView.setText(expressionView.getText() + "3.14");
    }

    public void buttonDel(View v) {
        {
            String expression = resultView.getText().toString();
            if (expression != "Syntax Error") {
                if (expression.length() > 2)
                    expression = expression.substring(0, expression.length() - 1);
                else if (expression.length() == 2 && !expression.substring(0, 1).equals("-"))
                    expression = expression.substring(0, expression.length() - 1);
                else {
                    expression = "0";
                    mIsTyping = false;
                }
                resultView.setText(expression);
            }
        }
    }

        public void buttonMinus (View v){
            if (mIsTyping) {
                if (!resultView.getText().toString().equals("0"))
                    if (resultView.getText().toString().substring(0, 1).equals("-"))
                        resultView.setText(resultView.getText().toString().substring(1));
                    else
                        resultView.setText("-" + resultView.getText());
            }
        }

        public void buttonStage (View v){
            expressionView.setText(expressionView.getText() + "!");
        }


        public void buttonExpone (View v){
            expressionView.setText(expressionView.getText() + "^");
        }


        public void buttonPercent (View v){
            expressionView.setText(expressionView.getText() + "%");
        }


        public void buttonSin (View v){
            expressionView.setText(expressionView.getText() + "sin(");
        }


        public void buttonCos (View v){
            expressionView.setText(expressionView.getText() + "cos(");
        }


        public void buttonTan (View v){
            expressionView.setText(expressionView.getText() + "tan(");
        }


        //--------------------------------------------------------------------------
        public void lichsu (View view){
            Intent myIntent = new Intent(view.getContext(), save_history
                    .class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", (Serializable) list);
            myIntent.putExtra("BUNDLE", args);
            this.startActivityForResult(myIntent, MY_REQUEST_CODE);
        }

        private void Writehistory (List list, savekq savehistory){
            if (kiemtrasopt(list) < 5) {
                list.add(0, savehistory);
            } else {
                list.remove(4);
                list.add(0, savehistory);
            }
        }

        private int kiemtrasopt (List list){
            int dem = list.size();
            return dem;
        }
    }

