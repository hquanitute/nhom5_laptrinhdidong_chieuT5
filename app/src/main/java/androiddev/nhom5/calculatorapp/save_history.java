package androiddev.nhom5.calculatorapp;

import android.app.Activity;
import android.content.Intent;
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
    ArrayAdapter<savekq> savehistoryArrayAdapter;
    ArrayList<savekq> savekqArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_history);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        savekqArrayList = (ArrayList<savekq>)
        args.getSerializable("ARRAYLIST");
        addcontrol();
        daydulieulen();
        addevent();
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


    private void daydulieulen() {


        for(savekq s : savekqArrayList)
        {
            savehistoryArrayAdapter.add(s);
        }
    }

    private void addcontrol() {
        lvhistory =(ListView) findViewById(R.id.lvhistory);
        savehistoryArrayAdapter = new ArrayAdapter<savekq>(save_history.this,android.R.layout.simple_list_item_1);
        lvhistory.setAdapter(savehistoryArrayAdapter);
    }
}
