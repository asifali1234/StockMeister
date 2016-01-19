package com.f5.stockmeister;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.f5.stockmeister.adapters.stock_adapter;
import com.f5.stockmeister.model_realm.portfolio;
import com.f5.stockmeister.model_realm.stock;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class portfolio_detail extends AppCompatActivity {
    portfolio port;
    List<stock> st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        portfolio portfolio = AppController.portfolio;
        st=portfolio.getStocks();
        toolbar.setTitle(portfolio.getName());



        ListView list= (ListView) findViewById(R.id.list1);
        list.setClickable(true);



        final stock_adapter port= new stock_adapter(getApplicationContext(),R.layout.stock_item);
        for (stock c : portfolio.getStocks()) {
            port.add(c);

        }

        list.setAdapter(port);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AppController.stock = (stock) port.getItem(i);
                Intent intent = new Intent(getApplicationContext(), stock_detail.class).putExtra("stock", (stock) port.getItem(i));
                startActivity(intent);
            }
        });

        PieChart mChart = (PieChart)findViewById(R.id.chart);

        PieData data = new PieData(getxvals(), getdataset());

        data.setValueTextSize(10f);
        data.setValueTextColor(Color.rgb(0, 0, 0));
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.setDescription("stocks");
        mChart.invalidate();

//        LinearLayout layout = (LinearLayout) findViewById(R.id.detail);
//        TextView tv = new TextView(this);
//        tv.setText(port.getName() + "\n\t" + port.getTotal_gain_loss() + "\n\t" + port.getTotal_share_count()+"\n\t"+port.getTotal_value());
//
//        layout.addView(tv);



    }

    private PieDataSet getdataset() {


        ArrayList<Entry> yVals1 = new ArrayList<Entry>();


        ArrayList  set1=new ArrayList();
        int i=0;
        for (stock c : st) {

            yVals1.add(new Entry(c.getCount(),i));
            i++;

        }

        PieDataSet dataSet = new PieDataSet(yVals1, "tasks");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(10f);


        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);

//        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        return dataSet;
    }

    private ArrayList<String> getxvals() {

        ArrayList<String> xVals = new ArrayList<String>();
        for (stock c : st) {
            xVals.add(c.getName());

        }

        return xVals;




    }

}
