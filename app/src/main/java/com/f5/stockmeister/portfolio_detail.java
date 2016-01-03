package com.f5.stockmeister;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.f5.stockmeister.model_realm.portfolio;

public class portfolio_detail extends AppCompatActivity {
    portfolio port = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();
        if(intent!=null&&intent.hasExtra("portfolio")) {
            port = (portfolio) intent.getSerializableExtra("portfolio");
        }


        LinearLayout layout = (LinearLayout) findViewById(R.id.detail);
        TextView tv = new TextView(this) ;
        tv.setText(port.getName()+"\n\t"+port.getTotal_gain_loss()+"\n\t"+port.getTotal_share_count()+"\n\t"+port.getTotal_value());
        //csfcsfdsgdg
        layout.addView(tv);
    }

}
