package androiddev.nhom5.calculatorapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androiddev.nhom5.calculatorapp.R;
import androiddev.nhom5.calculatorapp.savekq;

public class customAdapter extends ArrayAdapter<savekq> {

    private Context context;
    private  int resource;
    private ArrayList<savekq> arrkq;

    public customAdapter(Context context, int resource, ArrayList<savekq> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrkq=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_bieuthuc = convertView.findViewById(R.id.tv_bieuthuc);
            viewHolder.tv_ketqua = convertView.findViewById(R.id.tv_ketqua);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        savekq savekq = arrkq.get(position);
        viewHolder.tv_bieuthuc.setText(savekq.getBieuthu()+"=");
        viewHolder.tv_ketqua.setText(Long.toString(savekq.getKetqua()));
        return convertView;
    }
    public class ViewHolder {
        TextView tv_bieuthuc, tv_ketqua;

    }
}
