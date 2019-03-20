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

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonMinus, buttonC, buttonCE, buttonEqual,
            buttonDot, buttonAC, buttonPi, buttonLeftBrack, buttonRightBrack;
    ImageButton buttonhis, buttonDel;
    ArrayList<savekq> list = new ArrayList<>();

    TextView resultView, expressionView;
    private boolean mIsCalculating = false, mIsTyping = false;
    private double result = 0;

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
            } else if (mIsTyping == true)
                expressionView.setText((resultView.getText().toString()));
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

                    savekq kq = new savekq(expressionView.getText().toString(), Long.valueOf(resultView.getText().toString()));
                    Writehistory(list, kq);
                    mIsTyping = false;
                    mIsCalculating = false;
                    result = 0;
                    break;
            }
        }
    }

    public void onAddButtonClick(View view) {
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result + Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(result));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());

    }

    public void onSubButtonClick(View view) {
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result - Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(result));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());
    }

    public void onMulButtonClick(View view) {
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result * Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(result));
        } else
            expressionView.setText(expressionView.getText().toString()
                    .substring(0, expressionView.getText().toString().length() - 1) + b.getText());
    }

    public void onDivButtonClick(View view) {
        Button b = (Button) view;
        if (mIsTyping == true) {
            expressionView.setText(expressionView.getText() + " " + resultView.getText() + " " + b.getText());
            result = result / Double.valueOf(resultView.getText().toString());
            if (result % 1 == 0)
                resultView.setText(String.valueOf((int) result));
            else resultView.setText(String.valueOf(result));
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
        //region Gán id cho các button
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
        buttonAC = findViewById(R.id.buttonAC);
        buttonDel = findViewById(R.id.buttonDel);
        buttonDot = findViewById(R.id.buttonDot);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPi = findViewById(R.id.buttonPi);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonLeftBrack = findViewById(R.id.buttonLeftBrack);
        buttonRightBrack = findViewById(R.id.buttonRightBrack);
        buttonhis = findViewById(R.id.buttonhis);
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);
        //endregion

        if (savedInstanceState != null) {
            resultView.setText(savedInstanceState.getString("result"));
            expressionView.setText(savedInstanceState.getString("expression"));
        }
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
                expressionView.setText("");
            }
        });

//        buttonAC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resultView.setText("0");
//                expressionView.setText("");
//                mIsTyping = false;
//                mIsCalculating = false;
//                result = 0;
//            }
//        });
        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
                mIsTyping = false;
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(resultView.getText() + ".");
                mIsTyping = true;
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
        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("0");
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = resultView.getText().toString();
                if (expression.length() != 1)
                    expression = expression.substring(0, expression.length() - 1);
                else {
                    expression = "0";
                    mIsTyping = false;
                }
                resultView.setText(expression);
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!resultView.getText().toString().equals("0"))
                    if (resultView.getText().toString().substring(0, 1).equals("-"))
                        resultView.setText(resultView.getText().toString().substring(1));
                    else
                        resultView.setText("-" + resultView.getText());
            }
        });
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
            kqtrave =(savekq) args.getSerializable("kqtrave");
            resultView.setText(Long.toString(kqtrave.ketqua));
            expressionView.setText(kqtrave.bieuthu);
        } else{
            Toast.makeText(this,"ko co gi",Toast.LENGTH_LONG).show();
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
    public void lichsu(View view) {
        Intent myIntent = new Intent(view.getContext(), save_history
                .class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) list);
        myIntent.putExtra("BUNDLE", args);
        this.startActivityForResult(myIntent, MY_REQUEST_CODE);
    }

    private  void Writehistory (List list,savekq savehistory) {
        if (kiemtrasopt(list)<5) {
            list.add(0,savehistory);
        }
        else {
            list.remove(4);
            list.add(0, savehistory);
        }
    }

    private int kiemtrasopt(List list) {
        int dem = list.size();
        return dem;
    }
}

