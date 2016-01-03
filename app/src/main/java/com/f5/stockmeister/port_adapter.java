package com.f5.stockmeister;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.f5.stockmeister.model_realm.portfolio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asif on 1/3/2016.
 */
public class port_adapter extends ArrayAdapter {

    private List list = new ArrayList();
    public port_adapter(Context context, int resource) {
        super(context, resource);
    }

    static class viewholder
    {
         TextView text1;
         TextView text2; TextView text4;
        TextView text3;


    }

    public void add(portfolio port)
    {
        list.add(port);
        super.add(port);
    }

    public int getCount()
    {
        return this.list.size();
    }


    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        viewholder vh;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.port_item, parent, false);
            vh = new viewholder();
            vh.text1 = (TextView) row.findViewById(R.id.portfolio_name);
            vh.text2 = (TextView) row.findViewById(R.id.total_value);
            vh.text3 = (TextView) row.findViewById(R.id.total_gain_loss);
            vh.text4 = (TextView) row.findViewById(R.id.total_sharecount);
            row.setTag(vh);
        }
        else
        {
            vh=(viewholder) row.getTag();

        }
        portfolio p = (portfolio) getItem(position);
        vh.text1.setText(p.getName());
        vh.text2.setText(Float.toString(p.getTotal_value()));

        vh.text3.setText(Float.toString(p.getTotal_gain_loss()));
        if(p.getTotal_gain_loss()<0)
            vh.text3.setTextColor(Color.rgb(255,0,0));
        else
            vh.text3.setTextColor(Color.rgb(0,200,0));
        vh.text4.setText(Float.toString(p.getTotal_share_count()));

       return row;
    }

}
