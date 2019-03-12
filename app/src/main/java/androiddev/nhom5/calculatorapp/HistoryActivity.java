package androiddev.nhom5.calculatorapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androiddev.nhom5.calculatorapp.R;

public class HistoryActivity extends AppCompatActivity {
    GridView gv ;
    String[] arrayHistory = new String[]{"1+2=3","3+4*5+6=29","5*7=35"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.calculator);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("  History");
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gv = (GridView) findViewById(R.id.result);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, arrayHistory);
        gv.setAdapter(adapter);
    }
}
