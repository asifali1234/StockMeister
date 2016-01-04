package com.f5.stockmeister;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.f5.stockmeister.adapters.stock_adapter;
import com.f5.stockmeister.model_realm.portfolio;
import com.f5.stockmeister.model_realm.stock;

import io.realm.RealmResults;

public class portfolio_detail extends AppCompatActivity {
    portfolio port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        portfolio portfolio = AppController.portfolio;
        toolbar.setTitle(portfolio.getName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        ViewFlipper flip = (ViewFlipper) findViewById(R.id.flip);
        flip.setAutoStart(true);
        flip.startFlipping();
        flip.setFlipInterval(2000);

//        LinearLayout layout = (LinearLayout) findViewById(R.id.detail);
//        TextView tv = new TextView(this);
//        tv.setText(port.getName() + "\n\t" + port.getTotal_gain_loss() + "\n\t" + port.getTotal_share_count()+"\n\t"+port.getTotal_value());
//
//        layout.addView(tv);



    }

}
