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
import com.hookedonplay.decoviewlib.charts.SeriesItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asif on 1/3/2016.
 */
public class stock_adapter extends ArrayAdapter {

    private List list = new ArrayList();
    URL url;

    HttpURLConnection urlConnection =null;
    BufferedReader reader=null;

    String JsonStr = null;
    static JSONObject jObj = null;
    static String json = "";

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





//            vh.dec.addSeries(new SeriesItem.Builder(Color.rgb(255, 255, 0)).setInitialVisibility(false).setLineWidth(10f).setRange(0, 100, 50).setDrawAsPoint(false).setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT).setSpinDuration(500).build());
//            SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
//                   .setRange(0, 100, 0)
//                   .setLineWidth(10f).setInitialVisibility(false)
//                   .build();
//            int series1Index = vh.dec.addSeries(seriesItem1);

// Create background track
//            vh.dec.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
//                    .setRange(0, 100, 100)
//                    .setInitialVisibility(false)
//                    .setLineWidth(32f)
//                    .build());
//
////Create data series track
//            SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
//                    .setRange(0, 100, 0)
//                    .setLineWidth(32f)
//                    .build();
//
//            int series1Index = vh.dec.addSeries(seriesItem1);
//
//            vh.dec.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
//                    .setDelay(1000)
//                    .setDuration(2000)
//                    .build());
//
//            vh.dec.addEvent(new DecoEvent.Builder(25).setIndex(series1Index).setDelay(4000).build());
//            vh.dec.addEvent(new DecoEvent.Builder(100).setIndex(series1Index).setDelay(8000).build());
//            vh.dec.addEvent(new DecoEvent.Builder(10).setIndex(series1Index).setDelay(12000).build());
//            vh.dec.configureAngles(360, 0);
//
//            vh.dec.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218)).build());
//
//            SeriesItem seriesItem2 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
//                    .setRange(0, 70, 0)
//                    .setInitialVisibility(false)
//                    .setLineWidth(32f)
//                    .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_OUTER, Color.parseColor("#22000000"), 0.4f))
//                    .setSeriesLabel(new SeriesLabel.Builder("Percent %.0f%%").build())
//                    .setInterpolator(new OvershootInterpolator())
//                    .setShowPointWhenEmpty(false)
//                    .setCapRounded(false)
//                    .setInset(new PointF(32f, 32f))
//                    .setDrawAsPoint(false)
//                    .setSpinClockwise(true)
//                    .setSpinDuration(6000)
//                    .setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT)
//                    .build();




            row.setTag(vh);
        }
        else
        {
            vh=(viewholder) row.getTag();

        }
        stock p = (stock) getItem(position);
        vh.text2.setText(p.getName());
        vh.text1.setText(p.getSymbol());

        vh.dec.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(true)
                .setLineWidth(15f)
                .build());

        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.rgb(255,193,7))
                .setRange(0, 100, 56).setSpinDuration(4000).setInitialVisibility(true)
                .setLineWidth(15f)
                .build();

        int series1Index = vh.dec.addSeries(seriesItem1);
        vh.text3.setText(Float.toString(p.getChange()));
        if(p.getChange()<0)
        { vh.text3.setTextColor(Color.rgb(255,0,0));
            vh.text4.setTextColor(Color.rgb(255,0,0));}
        else {
            vh.text3.setTextColor(Color.rgb(0, 200, 0));
            vh.text4.setTextColor(Color.rgb(0, 200, 0));
        }
        vh.text4.setText(p.getChangeinPercent());


/*       float perc = Float.parseFloat(chngperc.substring(0,7));
        vh.text4.setText(Float.toString(perc));
        if(perc<0)
            vh.text4.setTextColor(Color.rgb(255,0,0));
        else
            vh.text4.setTextColor(Color.rgb(0,200,0));*/

        return row;
    }

}
