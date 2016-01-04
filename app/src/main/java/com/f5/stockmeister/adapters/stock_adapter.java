package com.f5.stockmeister.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.f5.stockmeister.R;
import com.f5.stockmeister.model_realm.stock;
import com.hookedonplay.decoviewlib.DecoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asif on 1/3/2016.
 */
public class stock_adapter extends ArrayAdapter {

    private List list = new ArrayList();
    public stock_adapter(Context context, int resource) {
        super(context, resource);
    }

    static class viewholder
    {
        DecoView dec;
        TextView text1;
        TextView text2;
        TextView text4;
        TextView text3;


    }

    public void add(stock stock)
    {
        list.add(stock);
        super.add(stock);
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
            row = inflater.inflate(R.layout.stock_item, parent, false);
            vh = new viewholder();
            vh.dec = (DecoView) row.findViewById(R.id.dec);
            vh.text1 = (TextView) row.findViewById(R.id.textView3);
            vh.text2 = (TextView) row.findViewById(R.id.name);
            vh.text3 = (TextView) row.findViewById(R.id.chng);
            vh.text4 = (TextView) row.findViewById(R.id.perc);
            row.setTag(vh);
        }
        else
        {
            vh=(viewholder) row.getTag();

        }
        stock p = (stock) getItem(position);
        vh.text2.setText(p.getName());
        vh.text1.setText(p.getSymbol());


        vh.text3.setText(Float.toString(p.getChange()));
        if(p.getChange()<0)
            vh.text3.setTextColor(Color.rgb(255,0,0));
        else
            vh.text3.setTextColor(Color.rgb(0,200,0));
        String chngperc =p.getChangeinPercent();
/*       float perc = Float.parseFloat(chngperc.substring(0,7));
        vh.text4.setText(Float.toString(perc));
        if(perc<0)
            vh.text4.setTextColor(Color.rgb(255,0,0));
        else
            vh.text4.setTextColor(Color.rgb(0,200,0));*/

        return row;
    }

}
