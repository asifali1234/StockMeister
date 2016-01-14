package com.f5.stockmeister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.f5.stockmeister.data.DataHelper;
import com.f5.stockmeister.data.searchSuggestion;
import com.f5.stockmeister.model_realm.count;
import com.f5.stockmeister.model_realm.portfolio;
import com.f5.stockmeister.model_realm.stock;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class CreatePortfolio extends AppCompatActivity {
    String stockname[];
    int stockcount[];
    float stockvalue[];
    private int portid=0;



    private List list = new ArrayList();
    URL url;

    HttpURLConnection urlConnection =null;
    BufferedReader reader=null;

    String JsonStr = null;
    static JSONObject jObj = null;
    static String json = "";


    LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_portfolio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        l= (LinearLayout) findViewById(R.id.cont);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final EditText portname = (EditText) findViewById(R.id.portname);

        Button button = (Button) findViewById(R.id.createport);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<portid;i++)
                {


                    stock result = new stock();
                    result.setName(stockname[i]);

                    String u = "https://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.quotes where symbol %3D '"+result.getSymbol()+"'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

//                    try {
//                        url = new URL(u);
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//
//
////                    try{
//
//
//                        //url= new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=683110&mode=json&units=metric&cnt=7");
//
//                        urlConnection = (HttpURLConnection) url.openConnection();
//                        urlConnection.setRequestMethod("GET");
//                        urlConnection.connect();
//
//                        InputStream inputStream = urlConnection.getInputStream();
//                        StringBuilder buffer = new StringBuilder();
//
//                        if(inputStream==null)
//                        {
//                            JsonStr=null;
//
//                        }
//                        else {
//                            reader = new BufferedReader(new InputStreamReader(inputStream));
//                            String line;
//
//                            while ((line = reader.readLine()) != null) {
//                                buffer.append(line).append("\n");
//
//                            }
//
//                            if (buffer.length() == 0) {
//                                JsonStr = null;
//
//                            }
//                            else {
//
//                                JsonStr = buffer.toString();
//                                //Log.v(LOG_TAG,"Forecast JSON String: " + forecastJsonStr);
//                            }
//                        }
//
//                    } catch (IOException e) {
//                        Log.e("dddd", "ERROR", e);
//                    }finally {
//                        if (urlConnection != null)
//                            urlConnection.disconnect();
//                        if (reader != null)
//                            try {
//                                reader.close();
//                            } catch (IOException e) {
//                                Log.e("dddd", "ERROR", e);
//                            }
//                    }
//
//
//                    Toast.makeText(getApplicationContext(),JsonStr,Toast.LENGTH_LONG).show();




                    result.setCount(stockcount[i]);
                    result.setBuy_value(Float.parseFloat(String.valueOf(stockvalue[i])));
                    result.setGain_loss(result.getLastTradePriceOnly() - result.getBuy_value());
                    result.setPerc_gain_loss(result.getGain_loss() / result.getBuy_value() * 100);
                    result.setStock_value(result.getCount() * result.getLastTradePriceOnly());

                    MainActivity.realm.beginTransaction();
                    MainActivity.realm.copyToRealmOrUpdate(result);
                    MainActivity.realm.commitTransaction();
                }

                MainActivity.portfolio_count++;
                count count= new count();
                count.setId(0);
                count.setStock_count(MainActivity.stock_count);
                count.setPortfolio_count(MainActivity.portfolio_count);
                MainActivity.realm.beginTransaction();
                MainActivity.realm.copyToRealmOrUpdate(count);
                MainActivity.realm.commitTransaction();


                MainActivity.realm.beginTransaction();
                portfolio port=  MainActivity.realm.createObject(portfolio.class);
                port.setId(String.valueOf(MainActivity.portfolio_count));
                port.setName(String.valueOf(portname.getText()));


                RealmResults<stock> stocks = MainActivity.realm.where(stock.class).findAll();
                for(stock c:stocks)
                {

                    for(int i=0;i<portid;i++) {
                        if (c.getName().equalsIgnoreCase(stockname[i])) {

                           port.setTotal_gain_loss(port.getTotal_gain_loss()+c.getGain_loss());
                            port.setTotal_perc_gain_loss((port.getTotal_perc_gain_loss()+c.getPerc_gain_loss()));
                            port.setTotal_value(port.getTotal_value()+c.getStock_value());
                            port.setTotal_share_count(port.getTotal_share_count()+c.getCount());

                            port.getStocks().add(c);
                            break;

                        }
                    }


                }
                for(int i=0;i<portid;i++)
                    stockname[i]="";
                MainActivity.realm.commitTransaction();
                portid=0;



                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

            }
        });

        stockname=new String[MainActivity.stock_count];
        stockcount=new int[MainActivity.stock_count];
        stockvalue=new float[MainActivity.stock_count];





        final com.arlib.floatingsearchview.FloatingSearchView mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();

                    //simulates a query call to a data source
                    //with a new query.
                    DataHelper.find(CreatePortfolio.this, newQuery, new DataHelper.OnFindResultsListener() {

                        @Override
                        public void onResults(List<searchSuggestion> results) {

                            //this will swap the data and
                            //render the collapse/expand animations as necessary
                            mSearchView.swapSuggestions(results);

                            //let the users know that the background
                            //process has completed
                            mSearchView.hideProgress();
                        }
                    });
                }
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion search) {

                final PopupWindow pop = new PopupWindow(((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.popup_view, null, false), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                final View pop_view = pop.getContentView();
                pop.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(android.R.color.black));
                // Animation appear = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.abc_slide_in_bottom);
                pop.showAtLocation(findViewById(R.id.cont), Gravity.CENTER, 0, 0);
                //pop_view.startAnimation(appear);
                final EditText e1 = (EditText) pop_view.findViewById(R.id.editText);
                final EditText e2 = (EditText) pop_view.findViewById(R.id.editText2);
                final Button b = (Button) pop_view.findViewById(R.id.button);
                Button b2 = (Button) pop_view.findViewById(R.id.button1);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });


                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            searchSuggestion colorSuggestion = (searchSuggestion) search;
                            stockname[portid] = colorSuggestion.getColor().getName();



                            stockcount[portid] = Integer.parseInt(String.valueOf(e1.getText()));

                            stockvalue[portid++] = Float.parseFloat(String.valueOf(e2.getText()));
                            TextView tv= new TextView(getApplicationContext());
                            tv.setText(stockname[portid-1]+"\t"+stockvalue[portid-1]+"\t"+stockcount[portid-1]);
                            l.addView(tv);
                            pop.dismiss();





                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "please enter credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }

            @Override
            public void onSearchAction() {

                Toast.makeText(getApplicationContext(), "ON SEARCH ACTION", Toast.LENGTH_LONG).show();
            }
        });
        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

                //show suggestions when search bar gains focus (typically history suggestions)
                //mSearchView.swapSuggestions(DataHelper.getHistory(CreatePortfolio.this, 3));
                Toast.makeText(getApplicationContext(), "FOCUS", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFocusCleared() {
                Toast.makeText(getApplicationContext(), "ON FOCUS CLEARED", Toast.LENGTH_LONG).show();

            }
        });


    }

}
