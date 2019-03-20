package androiddev.nhom5.calculatorapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class save_history extends AppCompatActivity {

    private ListView lvhistory;
   // ArrayAdapter<savekq> savehistoryArrayAdapter;
    ArrayList<savekq> savekqArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_history);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        savekqArrayList = (ArrayList<savekq>) args.getSerializable("ARRAYLIST");
        datalist();
        addevent();
    }

    private void datalist() {
        lvhistory =(ListView) findViewById(R.id.lvhistory);
        customAdapter customAdapter = new customAdapter(this,R.layout.list_row,savekqArrayList);
        lvhistory.setAdapter(customAdapter);
    }

    private void addevent() {
        lvhistory.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        savekq  kqtrave= new savekq(savekqArrayList.get(position).getBieuthu(),
                                savekqArrayList.get(position).getKetqua());
                        Intent myIntent = new Intent(view.getContext(), MainActivity
                                .class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("kqtrave",(Serializable) kqtrave);
                        myIntent.putExtra("bundle",bundle);
                        setResult(Activity.RESULT_OK, myIntent);
                        finish();
                    }
                }
        );
    }



}
