package com.f5.stockmeister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.astuetz.PagerSlidingTabStrip;
import com.f5.stockmeister.model_realm.count;
import com.f5.stockmeister.model_realm.stock;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    public static Realm realm;
    public static int stock_count=0;
    public static int portfolio_count=0;


    @Override
    protected void onResume() {
        super.onResume();

        update();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("StockMeister");
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(viewPager);
        viewPager.setCurrentItem(0);

        realm = Realm.getInstance(this);

        if(isFirstTime())
        {

            for(int i=1;i<11;i++) {
                realm.beginTransaction();
                stock s = realm.createObject(stock.class);
                s.setName("stock " + i);
                s.setSymbol(Integer.toString(i));
                s.setLastTradePriceOnly((i - 1) * 10 + 1);
                s.setPreviousClose((i - 1) * 10 + 2);                                       // MOCK DATA
                s.setChange((i - 1) * 10 + 3);
                s.setPercentChange(String.valueOf((i - 1) * 10 + 4));
                s.setLastTradePriceOnly((i - 1) * 10 + 5);
                s.setBuy_value(((i - 1) * 10 + 6));
                s.setGain_loss((i - 1) * 10 + 7);



                realm.commitTransaction();
                stock_count++;
            }
            count count = new count();
            count.setId(0);
            count.setStock_count(stock_count);
            count.setPortfolio_count(portfolio_count);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(count);
            realm.commitTransaction();

            //WHEN APP RUNS FOR THE FIRST TIME
        }
        else
        {
            count count= realm.where(count.class).equalTo("id",0).findFirst();
            stock_count=count.getStock_count();
            portfolio_count = count.getPortfolio_count();

            //WHEN APP OPENS EVERY TIME AFTER FIRST TIME
        }

        //WHEN APP OPENS EVERY TIME

        //WRITING EXPLAINED
        //................                             SIMILARLY CLASSES PORTFOLIO AND COUNT CAN BE IMPLEMENTED.. EX IS SHOWN FOR STOCK CLASS FROM MODEL REALM
        //................
        stock stock = new stock();
        /*
                use SETTER FUNCTIONS to put data to this STOCK OBJECT...... then after all data of a particular stock is set to the stock objects call UPDATE(stock);

                MULTIPLE STOCKS SHOULD BE CREATED SO AS USUAL USE FOR LOOP
         */
        //update(stock);

        /*


        READING EXPLAINED
        ................
        .................

                        RealmResults stocks = realm.where(stock.class).findAll();
                                                                       .findAllSorted(parameters which is used to query .. ie. certain fieldnames with certain values)
                                                                       .findfirst(); //change variable type


                        then use the object list and get data with proper GETTER METHODS



                        EX:
                         stocks = realm.where(stock.class).findAll();
                            for(stock c:stocks)
                            {
                            showStatus(c.getName()+"\n\t"+"current value : "+c.getCurr_value()+"\n\t"+"previous close : "+c.getPrev_close()+"\n\t"+"% change : "+c.getPerc_change());
                             }





         */



    }

    private void update(stock stock) {

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(stock);
        realm.commitTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }

    void update(){

        StringRequest balanceRequest = new StringRequest(Request.Method.POST, "http://stockmeister-stockm.rhcloud.com/update/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
               //parse json here

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //no interet or other issues
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //passing params
                Map<String, String> params = new HashMap<String, String>();
                params.put("uname", "username");
                params.put("pass", "password");

                return params;
            }

        };
        //adding created request to Volley request queue to execute.
        AppController.getInstance().addToRequestQueue(balanceRequest);
    }


}
