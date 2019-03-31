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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {

    ArrayList<savekq> list = new ArrayList<>();

    TextView resultView, expressionView;
    private double result = 0;
    private String expression;
    private boolean isOperator = false;

    public void onNumberButtonClick(View view) {
        try {
            Button b = (Button) view;
            expressionView.setText(expressionView.getText().toString() + b.getText());
            calculate();
            isOperator=false;
        }catch (Exception e)
        {

        }
    }
    public void onOperatorButtonClick(View v) {

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

        }
    }

    //Xu li dau =

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
    //Thuat toan Banlan dung thu vien---------------------------------------------------
    private void calculate() {
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
                    resultView.setText(error);
//                if(error == "String input math error!")
                    //expressionView.setText("");
                    if(error.toString()=="Error div 0")
                        //resultView.setText("0");
                        expressionView.setText("Error");
                } else { // show result
                    expressionView.setText(temp);
                    resultView.setText(finalResult);
                    // result = Double.valueOf(finalResult);
                }
            }
        }catch (Exception e)
        {

        }
    }

    //    public void EnableEqual()
//    {
//
//    }
    //Xu li dau AC --- Reset lai bieu thuc moi
    public void buttonAC(View view) {
        resultView.setText("0");
        result=0;
        expressionView.setText("");
        result = 0;
    }

    //Xu li dau CE --- Xoa ket qua resultView
    public void buttonCE(View view) {
        resultView.setText("0");
        result=0;
    }

    //Xu li dau cham.
    public void buttonDot(View view) {
        expressionView.setText(expressionView.getText()+".");
        //resultView.setText(resultView.getText() + ".");
    }
    public void buttonMod(View view) {
        expressionView.setText(expressionView.getText()+"Mod " );
        //resultView.setText(resultView.getText() + ".");
    }
    public void buttonMinus(View view)
    {
//        checkOperator(expressionView);
//        String s=expressionView.getText().toString();
//        int location= findOperator(expressionView);
//        if(isOperator==false )
//        {
//            String digit=s.substring(location+1,s.length());
//            expressionView.setText(expressionView.getText().toString().substring(0,location+1)+"-");
//            expressionView.setText(expressionView.getText().toString()+digit);
//        }
        //-----------------------------------------
//        if (!expressionView.getText().toString().equals("0"))
//            if (expressionView.getText().toString().substring(0, expressionView.getText().charAt(expressionView.length()-1)).equals("-"))
//            {
//                expressionView.setText(expressionView.getText().toString().substring(1));
//                calculate();
//            }
//            else {
//                expressionView.setText("-" + expressionView.getText());
//                calculate();
//            }

        expressionView.setText(expressionView.getText()+"(-");
    }

    public void checkOperator(TextView tv)
    {
        String s = tv.getText().toString().substring(tv.getText().length()-1);
        if(s.equals(")") || s.equals("!")) {
            int a=1;
            isOperator = false;
//            return;
        }
        else if(Character.isDigit(tv.getText().toString().charAt(tv.getText().toString().length()-1)))
            isOperator=false;
        else
            isOperator=true;
    }
    public int findOperator (TextView tv)
    {
        String s=tv.getText().toString();
        int length=tv.getText().length();
        int location =-1;

        for (int i=length-1;i>=0;i--)
        {
            if (!Character.isDigit(s.charAt(i)))
            {
                location=i;
                break;
            }
        }

        return location;
    }

    //Xu li dau =
    public void onResultClick(View v)
    {
        try{
            savekq kq = new savekq(expressionView.getText().toString(), Long.valueOf(resultView.getText().toString()));
            Writehistory(list, kq);
            calculate();
            expressionView.setText(resultView.getText());
            result=Long.valueOf(resultView.getText().toString());
            resultView.setText("");
        }catch (Exception e){
            resultView.setText("0");
            expressionView.setText("Cannot Divide");
            result=0;
        }

    }
    //Xu li dau !
    public void buttonFactorial(View v)
    {
        expressionView.setText(expressionView.getText() + "!");
        calculate();
    }

    //Xu li dau ")"
    public void buttonRightBrack(View v) {
        expressionView.setText(expressionView.getText() + ")");
    }
    //
//    //Xu li dau "("
    public void buttonLeftBrack(View v) {
        expressionView.setText(expressionView.getText() + "(");
    }
    //
//    //Xu li dau √
    public void buttonSqrt(View v) {
        expressionView.setText(expressionView.getText() + "√(");
    }
    //
//    //Xu li dau π
    public void buttonPi(View v) {
        expressionView.setText(expressionView.getText() + "π");
    }

    public boolean checkDel(TextView tv)
    {
        String s = expressionView.getText().toString();
        for(int i=0;i<s.length();i++)
        {
            if(Character.isDigit(s.charAt(i)))
            {
                return true;
            }
        }
        return  false;
    }

    //Xu li dau C
    public void buttonC(View v) {

        try {
            if(checkDel(expressionView)==false || expressionView.length()==1)
            {
                resultView.setText("0");
                expressionView.setText("");
                result=0;
            }
            else
            {
                if (expressionView.length() != 0) {
//                    checkOperator(expressionView.getText().toString().trim()
//                            .substring(expressionView.getText().length(),expressionView.getText().length()));
                    expressionView.setText(expressionView.getText().toString().trim()
                            .substring(0, expressionView.getText().length() - 1));

                    calculate();
                }
//                else
//                {
//
//                    resultView.setText("0");
//                }
            }

        } catch (Exception e) {
            // expressionView.setText("0");
        }
        ;
    }
    //Xu li log
    public void buttonLog(View v){
        expressionView.setText(expressionView.getText() + "log(");
    }
    //    //Xu li dau !
//    public void buttonStage(View v) {
//        expressionView.setText(expressionView.getText() + "!");
//    }
//-----------------------------------------------------------------
//    //Tinh Tan
//    public double TinhTan(Double a)
//    {
//        double Results =0;
//        Results=Math.tan(Math.toRadians(a));
//        return Results;
//    }
//    //Tinh Cos
//    public double TinhCos(Double a)
//    {
//        double Results =0;
//        Results=Math.cos(Math.toRadians(a));
//        return Results;
//    }
//    //Tinh Sin
//    public double TinhSin(Double a)
//    {
//        double Results =0;
//        Results=Math.sin(Math.toRadians(a));
//        return Results;
//    }
    //--------------------------------------------------------
    //Xu li dau mu
    public void buttonExpone(View v) {
        expressionView.setText(expressionView.getText() + "^");
    }

    //Xu li %
//    public void buttonPercent(View v) {
//        expressionView.setText(expressionView.getText() + "%");
//    }

    //Xu li Sin
    public void buttonSin(View v) {
        expressionView.setText(expressionView.getText() + "sin");
    }

    //Xu li Cos
    public void buttonCos(View v) {
        expressionView.setText(expressionView.getText() + "cos");
    }

    //Xu li Tan
    public void buttonTan(View v) {
        expressionView.setText(expressionView.getText() + "tan");
    }


    //--------------------------------------------------------------------------
    public void lichsu(View view) {
        Intent myIntent = new Intent(view.getContext(), save_history
                .class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) list);
        myIntent.putExtra("BUNDLE", args);
        this.startActivityForResult(myIntent, MY_REQUEST_CODE);
    }

    private void Writehistory(List list, savekq savehistory) {
        if (kiemtrasopt(list) < 5) {
            list.add(0, savehistory);
        } else {
            list.remove(4);
            list.add(0, savehistory);
        }
    }

    private int kiemtrasopt(List list) {
        int dem = list.size();
        return dem;
    }
}

