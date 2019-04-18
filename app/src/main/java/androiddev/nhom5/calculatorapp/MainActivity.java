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
    private double result = 0;
    private boolean isOperator = false;
    private boolean mIsCalculating = false, mIsTyping = false, mIsError = false;

    //Xu li nhan button number man hinh ngang
    public void OnNumberButtonClick_land(View v) {
        try {
            Button b = (Button) v;
            expressionView.setText(expressionView.getText().toString() + b.getText());
            calculate();
            isOperator=false;
        }catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }
    }

    //Xu li nhan button number man hinh doc
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

    //Xu li dau bang man hinh ngang
    public void Result_land (View v){
        try{
            savekq kq = new savekq(expressionView.getText().toString(), Double.valueOf(resultView.getText().toString()));
            Writehistory(list, kq);
            calculate();
            expressionView.setText(resultView.getText());
            result=Double.valueOf(resultView.getText().toString());
            resultView.setText("");
        }catch (Exception e){
            resultView.setText("0");
            expressionView.setText("Error to calculate");
            result=0;
        }
    }

    //Xu li nhap toan tu man hinh ngang
    public void OnOperatorButtonClick_land (View v) {
        try {
            checkOperator(expressionView);
            if(isOperator==false) {
                Button b = (Button) v;
                expressionView.setText(expressionView.getText().toString()+ b.getText());
                isOperator = true;
            }
            else
            {
                Button b = (Button) v;
                expressionView.setText(expressionView.getText().toString().trim()
                        .substring(0,expressionView.getText().toString().length()-1) + b.getText());
                isOperator=true;
            }
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
    }
    }

    //Xu li nhap toan tu man hinh doc
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
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }
    }

    //Xu li dau bang man hinh doc
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
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }
    }

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
            kqtrave = (savekq) args.getSerializable("kqtrave");`00`
            resultView.setText(Double.toString(kqtrave.ketqua));
            expressionView.setText(kqtrave.bieuthu);
        }

    }

    //Thuat toan Balan dung thu vien co san
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
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }
    }

    //Xu li button xoa man hinh doc
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
        result = 0;
        expressionView.setText("");
        result = 0;
    }

    //Xu li dau CE --- Xoa ket qua resultView
    public void buttonCE (View view){
        resultView.setText("0");
        result = 0;
    }

    //Xu li dau cham. man hinh ngang
    public void buttonDot_land (View view){
        expressionView.setText(expressionView.getText()+".");
    }

    //Xu li dau cham. man hinh doc
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

    //Xu li dau Mod
    public void buttonMod (View view){ expressionView.setText(expressionView.getText() + "Mod");}

    //Xu li button am duong man hinh doc
    public void buttonMinus (View view){
        if (mIsTyping) {
            if (!resultView.getText().toString().equals("0"))
                if (resultView.getText().toString().substring(0, 1).equals("-"))
                    resultView.setText(resultView.getText().toString().substring(1));
                else
                    resultView.setText("-" + resultView.getText());
        }
    }

    //Xu li button am duong man hinh ngang
    public void buttonMinus_land (View view) {
        expressionView.setText(expressionView.getText() + "(-");
    }

    //Ham kiem tra khong cho nhap 2 toan tu cung luc
    public void checkOperator (TextView tv){
        String s = tv.getText().toString().substring(tv.getText().length() - 1);
        if (s.equals(")") || s.equals("!")) {
            //int a = 1;
            isOperator = false;
        } else if (Character.isDigit(tv.getText().toString().charAt(tv.getText().toString().length() - 1)))
            isOperator = false;
        else
            isOperator = true;
    }

    //Xu li dau = man hinh doc
    public void onResultClick (View v){
        try {
            savekq kq = new savekq(expressionView.getText().toString(), Double.valueOf(resultView.getText().toString()));
            Writehistory(list, kq);
            calculate();
            expressionView.setText(resultView.getText());
            result = Double.valueOf(resultView.getText().toString());
            resultView.setText("");
        } catch (Exception e) {
            resultView.setText("0");
            expressionView.setText("Cannot Divide");
            result = 0;
        }

    }

    //Xu li dau !
    public void buttonFactorial (View v){
        expressionView.setText(expressionView.getText() + "!");
        calculate();
    }

    //Xu li dau %
    public void buttonPercent(View v){
        expressionView.setText(expressionView.getText() + "%");
        calculate();
    }

    //Xu li dau ")"
    public void buttonRightBrack (View v){
        expressionView.setText(expressionView.getText() + ")");
    }

    //Xu li dau "("
    public void buttonLeftBrack (View v){
        expressionView.setText(expressionView.getText() + "(");
    }

    //Xu li dau √
    public void buttonSqrt (View v){
        expressionView.setText(expressionView.getText() + "√(");
    }

    //Xu li dau π
    public void buttonPi (View v){
        expressionView.setText(expressionView.getText() + "π");
    }

    //Ham kiem tra xoa neu la loi bang chu xoa het , neu la xoa tu ki tu
    public boolean checkDel (TextView tv){
        String s = expressionView.getText().toString();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    //Xu li button xoa man hinh doc
    public void buttonDel_land(View v)
    {
        try {
            if(checkDel(expressionView)==false || expressionView.length()==1)
            {
                resultView.setText("0");
                expressionView.setText("");
                result=0;
            }
            else {
                if (expressionView.length() != 0) {
                    expressionView.setText(expressionView.getText().toString().trim()
                            .substring(0, expressionView.getText().length() - 1));

                    calculate();
                }
            }
        } catch (Exception e) {
            expressionView.setText("0");
        }
    }

    //Xu li log
    public void buttonLog (View v){
        expressionView.setText(expressionView.getText() + "log(");
        mIsTyping=true;
    }

    //Xu li dau mu
    public void buttonExpone (View v){
        expressionView.setText(expressionView.getText() + "^");
    }

    //Xu li Sin
    public void buttonSin (View v){
        expressionView.setText(expressionView.getText() + "sin");
    }

    //Xu li Cos
    public void buttonCos (View v){
        expressionView.setText(expressionView.getText() + "cos");
    }

    //Xu li Tan
    public void buttonTan (View v){
        expressionView.setText(expressionView.getText() + "tan");
    }

    //Xu li lich su
    public void lichsu (View view){
        Intent myIntent = new Intent(view.getContext(), save_history
                .class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) list);
        myIntent.putExtra("BUNDLE", args);
        this.startActivityForResult(myIntent, MY_REQUEST_CODE);
    }

    //Ghi bieu thuc va ket qua vao list
    private void Writehistory (List list, savekq savehistory){
        if (kiemtrasopt(list) < 10) {
            list.add(0, savehistory);
        } else {
            list.remove(9);
            list.add(0, savehistory);
        }
    }

    //Kiem tra so phan tu trong list
    private int kiemtrasopt (List list){
        int dem = list.size();
        return dem;
    }
}

