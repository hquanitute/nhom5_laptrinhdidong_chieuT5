package androiddev.nhom5.calculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {

    ArrayList<savekq> list = new ArrayList<>();

    TextView resultView, expressionView;
    private boolean mIsCalculating = false, mIsTyping = false, mIsError = false;

    public void OnButtonClick_land(View v) {
        try {
            Button b = (Button) v;
            if (!mIsCalculating) {
                expressionView.setText("");
            }
            if(!mIsTyping) {
                resultView.setText(b.getText());
                mIsTyping = true;
            }
           else
               resultView.setText(resultView.getText().toString() + b.getText());
        } catch (Exception e) {

        }
    }
        public void OnNumberButtonClick (View v){
            try {
                Button b = (Button) v;
                if (mIsCalculating == false)
                    expressionView.setText("");
                if (mIsTyping == false) {
                    resultView.setText(b.getText());
                    mIsTyping = true;
                } else {
                    resultView.setText(resultView.getText().toString() + b.getText());
                }
            } catch (Exception e) {

            }
        }
        public void Result_land (View v){
            try {
                expressionView.setText(expressionView.getText() + " " + resultView.getText());
                calculate();
                mIsCalculating = false;
                mIsTyping = false;
                if (!mIsError) {
                    savekq kq;
                    kq = new savekq(expressionView.getText().toString(), Double.valueOf(resultView.getText().toString()));
                    Writehistory(list, kq);
                }
            } catch (Exception e) {

            }
        }
        public void OnOperatorButtonClick (View v) {
            try {
                Button b = (Button) v;
                if (!mIsCalculating) {
                    expressionView.setText(resultView.getText().toString() + " " + b.getText());
                    mIsTyping = false;
                    mIsCalculating = true;
                } else if (mIsTyping) {
                    expressionView.setText(expressionView.getText() + " " + resultView.getText());
                    calculate();
                    expressionView.setText(expressionView.getText() + " " + b.getText());
                    mIsTyping = false;
                }
            } catch (Exception e) {

            }
        }
        public void Result (View v) {
            try {
                if (mIsTyping) {
                    expressionView.setText(expressionView.getText() + " " + resultView.getText());
                    calculate();
                    mIsTyping = false;
                    mIsCalculating = false;
                    if (!mIsError) {
                        savekq kq;
                        kq = new savekq(expressionView.getText().toString(), Double.valueOf(resultView.getText().toString()));
                        Writehistory(list, kq);
                    }
                }
            } catch (Exception e) {

            }
        }

        //Xu li dau =

        public static final int MY_REQUEST_CODE = 100;
        savekq kqtrave;

        @Override
        protected void onSaveInstanceState (Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putString("result", resultView.getText().toString());
            outState.putString("expression", expressionView.getText().toString());
        }

        @Override
        protected void onCreate (Bundle savedInstanceState){
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

        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
                Bundle args = data.getBundleExtra("bundle");
                kqtrave = (savekq) args.getSerializable("kqtrave");
                resultView.setText(Double.toString(kqtrave.ketqua));
                expressionView.setText(kqtrave.bieuthu);
            } else {
                Toast.makeText(this, "ko co gi", Toast.LENGTH_LONG).show();
            }

        }

        //Thuat toan Banlan dung thu vien---------------------------------------------------
        private void calculate () {
            try {
                char[] expression = expressionView.getText().toString().trim().toCharArray();
                String temp = expressionView.getText().toString().trim();
                for (int i = 0; i < expression.length; i++) {
                    if (expression[i] == '\u00D7')
                        expression[i] = '*';
                    if (expression[i] == '\u00f7')
                        expression[i] = '/';
                    if (expression[i] == '√')
                        expression[i] = '²';
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
                        mIsError = true;
                        resultView.setText(error);
                        if (error == "Error div 0")
                            resultView.setText("Math Error");
                    } else { // show result
                        expressionView.setText(temp);
                        resultView.setText(finalResult);
                    }
                }
            } catch (Exception e) {

            }
        }
        public void ButtonDel (View v){
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
        //Xu li dau AC --- Reset lai bieu thuc moi
        public void buttonAC (View view){
            resultView.setText("0");
            expressionView.setText("");
            mIsTyping = false;
            mIsCalculating = false;
        }

        //Xu li dau CE --- Xoa ket qua resultView
        public void buttonCE (View view){
            resultView.setText("0");
            mIsTyping = false;
            mIsCalculating = true;
        }

        //Xu li dau cham
        public void buttonDot (View view){
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

        public void buttonMinus (View view){
            if (mIsTyping) {
                if (!resultView.getText().toString().equals("0"))
                    if (resultView.getText().toString().substring(0, 1).equals("-"))
                        resultView.setText(resultView.getText().toString().substring(1));
                    else
                        resultView.setText("-" + resultView.getText());
            }
        }


        //Xu li dau √
        public void buttonSqrt (View v){
            try {
                if (mIsCalculating == false)
                    expressionView.setText("");
                if (mIsTyping == false) {
                    resultView.setText("√(");
                    mIsTyping = true;
                } else {
                    resultView.setText(resultView.getText().toString() + "√(");
                }
            } catch (Exception e) {

            }
        }

        //--------------------------------------------------------
        //Xu li dau mu
        public void buttonExpone (View v){
            try {
                if (mIsCalculating == false)
                    expressionView.setText("");
                if (mIsTyping == false) {
                    resultView.setText("√(");
                    mIsTyping = true;
                } else {
                    resultView.setText(resultView.getText().toString() + "^");
                }
            } catch (Exception e) {

            }
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

