package com.f5.stockmeister;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.f5.stockmeister.model_realm.stock;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.LineDataProvider;

import java.util.ArrayList;

public class stock_detail extends AppCompatActivity {
    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        ViewFlipper flip = (ViewFlipper) findViewById(R.id.flip2);
        flip.setAutoStart(true);
        flip.startFlipping();
        flip.setFlipInterval(10000);

        mChart = (LineChart) findViewById(R.id.inter_day);
        create_chart();
        mChart = (LineChart) findViewById(R.id.intra_day);
        create_chart();
        stock stock= AppController.stock;

        Toast.makeText(getApplicationContext(),stock.getName()+" dg",Toast.LENGTH_LONG).show();

        toolbar2.setTitle(stock.getName());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void create_chart()
    {
        //background color
        mChart.setBackgroundColor(Color.rgb(200, 200, 200));

        //find
        mChart.setViewPortOffsets(0, 20, 0, 0);                         /////find//////////////////////////////////////////////////////////////////////////

        //set description
        mChart.setDescription("description");
        //mChart.setDescriptionColor(Color.rgb(0,0,0));
        //mChart.setDescriptionPosition(10,50);
        //mChart.setDescriptionTextSize();
        //mChart.setDescriptionTypeface();


        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        //when there is no data available
        mChart.setNoDataTextDescription("no data available at the moment");
        mChart.setNoDataText("no data");


        //highlight y val
       // mChart.setHighlightEnabled(true);
        mChart.setHighlightPerDragEnabled(true);

        //legends- disabled
        mChart.getLegend().setEnabled(false);

        mChart.animateXY(5000, 5000);


        //make x axis and y axis- interpolate them
        XAxis x = mChart.getXAxis();
        x.setTextColor(Color.YELLOW);
        x.setDrawGridLines(false);
        x.setEnabled(true);
        //x.setTextSize();
        x.setDrawAxisLine(true);
        //x.setAxisLineColor();
        //x.setAxisLineWidth();
        x.setDrawLabels(true);
        //x.setGridColor();
        x.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        x.setAvoidFirstLastClipping(true);                             /////find//////////////////////////////////////////////////////////////////////////

        YAxis y = mChart.getAxisLeft();
        y.setTextColor(Color.RED);
        y.setDrawGridLines(true);
        y.setEnabled(true);
        y.setDrawAxisLine(true);
        //y.setAxisLineColor();
        //y.setAxisLineWidth();
        y.setDrawLabels(true);
        //y.setGridColor();
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawTopYLabelEntry(true);
        //y.setTextSize();
        y.setStartAtZero(false);
        //y.setGridLineWidth();

        YAxis y2 = mChart.getAxisRight();
        y2.setEnabled(false);



        // CREATE DATA FOR THE CHART
        LineData data = new LineData();
        //data.setValueTextColor();
        data.setDrawValues(true);
        //data.setValueFormatter();                                  /////find//////////////////////////////////////////////////////////////////////////
        data.setHighlightEnabled(true);
        //data.setValueTextSize();



        //set chart data
        setdata(45,100);


        mChart.invalidate();


    }


    private void setdata(int count,float range) {
        ArrayList<String> xVals = new ArrayList<String>();
        /*for (int i = 0; i < count; i++) {
            xVals.add((1990 +i) + "");
        }*/
        xVals.add("19-o");
        xVals.add("19-m");
        xVals.add("19-c");
        xVals.add("20-o");xVals.add("20-m");xVals.add("20-c");
        xVals.add("21-o");xVals.add("21-m");xVals.add("21-c");
        xVals.add("22-o");xVals.add("22-m");xVals.add("22-c");
        xVals.add("23-o");xVals.add("23-m");xVals.add("23-c");
        xVals.add("24-o");xVals.add("24-m");xVals.add("24-c");
        //xVals.add("25-o");xVals.add("25-m");xVals.add("25-c");
        //xVals.add("26-o");xVals.add("26-m");xVals.add("26-c");



        ArrayList<Entry> vals1 = new ArrayList<Entry>();

        for (int i = 0; i < 18; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 20;// + (float)
            // ((mult *
            // 0.1) / 10);
            vals1.add(new Entry(val,i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(vals1, "DataSet 1");
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true);
        set1.setDrawCircles(true);
        set1.setLineWidth(2f);
        set1.setCircleSize(4f);
        set1.setColor(Color.rgb(0,0,0));
        set1.setCircleColor(Color.WHITE);
        set1.setCircleColorHole(Color.rgb(0,0,0));
        set1.setHighLightColor(Color.rgb(244, 117, 117));

        set1.setFillColor(Color.rgb(255,193,7));
        set1.setFillAlpha(100);
        set1.setDrawHorizontalHighlightIndicator(true);
        set1.setFillFormatter(new FillFormatter() {
            @Override
            public float getFillLinePosition(LineDataSet dataSet, LineDataProvider dataProvider) {
                return -10;
            }
        });

        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);

        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        mChart.setData(data);

    }


}
