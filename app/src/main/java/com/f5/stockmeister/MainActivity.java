package com.f5.stockmeister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.astuetz.PagerSlidingTabStrip;
import com.f5.stockmeister.adapters.adapter;
import com.f5.stockmeister.model_realm.count;
import com.f5.stockmeister.model_realm.stock;

import org.json.JSONArray;
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

        //update();
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

            for(int i=1;i<50;i++) {
                realm.beginTransaction();
                stock s = realm.createObject(stock.class);
                if(i<10)
                s.setName("stock " + i);
                else if(i<25)
                    s.setName("company " + i);
                else
                    s.setName("service " + i);
                s.setSymbol(Integer.toString(i));
                s.setLastTradePriceOnly((i - 1) * 10 + 1);
                //s.setPreviousClose((i - 1) * 10 + 2);                                       // MOCK DATA
                float temp = (float) (Math.random() * 5);
                int tmp=(int)temp;
                float dec= temp-tmp;
                dec= Float.parseFloat((Float.toString(dec)).substring(0,2));
                temp=tmp+dec;
                if(i%2==0) {
                    s.setChange((float) -(Math.random()*10));
                    s.setChangeinPercent(-temp);
                }
                else
                {
                    s.setChange((float) (Math.random()*10));
                    s.setChangeinPercent(temp);
                }
                //s.setLastTradePriceOnly((i - 1) * 10 + 5);
               /* s.setAsk((i - 1) * 10 + 6);
                s.setOpen((i - 1) * 10 + 7);
                s.setDaysLow((i - 1) * 10 + 8);
                s.setDaysHigh((i - 1) * 10 + 9);
                s.setYearLow((i - 1) * 10 + 10);
                s.setYearHigh((i - 1) * 10 + 11);
                s.setPERatio((i - 1) * 10 + 12);
                s.setVolume(i * 100 + i);
                s.setAverageDailyVolume(i * 101 + i);
                s.setFiftydayMovingAverage((i - 1) * 10 + 13);*/




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
           // update();
            //WHEN APP RUNS FOR THE FIRST TIME
        }
        else
        {//update();
            count count= realm.where(count.class).equalTo("id",0).findFirst();
            stock_count=count.getStock_count();
            portfolio_count = count.getPortfolio_count();

            //WHEN APP OPENS EVERY TIME AFTER FIRST TIME
        }





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



        StringRequest balanceRequest = new StringRequest(Request.Method.POST, "http://stockmeister-stockm.rhcloud.com/getdata/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
               //parse json here
                try {

                    //THIS IS THE RESPONSE... PARSE THIS
                    //{"results": [{"LastTradePriceOnly": "105.26", "Symbol": "AAPL", "Name": "Apple Inc.", "Change": "-2.06"}, {"LastTradePriceOnly": "55.48", "Symbol": "MSFT", "Name": "Microsoft Corporatio", "Change": "-0.83"}, {"LastTradePriceOnly": "105.26", "Symbol": "AAPL", "Name": "Apple Inc.", "Change": "-2.06"}, {"LastTradePriceOnly": "55.48", "Symbol": "MSFT", "Name": "Microsoft Corporatio", "Change": "-0.83"}, {"LastTradePriceOnly": "105.35", "Symbol": "AAPL", "Name": "Apple Inc.", "Change": "+0.09"}, {"LastTradePriceOnly": "54.80", "Symbol": "MSFT", "Name": "Microsoft Corporatio", "Change": "-0.68"}]}






                    JSONArray jsonArray = new JSONObject(response).getJSONArray("results");
                    int count = jsonArray.length();

                    for (int i=0;i<count;i++){
                        
                         stock stock = new stock();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       // stock.setYearLow(Float.parseFloat(jsonObject.getString("YearLow")));

                        ///stock.setDividendShare(Float.parseFloat(jsonObject.getString("DividendShare")));
                        //stock.setChangeFromFiftydayMovingAverage(Float.parseFloat(jsonObject.getString("ChangeFromFiftydayMovingAverage")));
                        //stock.setPricePaid(Float.parseFloat(jsonObject.getString("PricePaid")));
                        //stock.setDaysLow(Float.parseFloat(jsonObject.getString("DaysLow")));
                        //stock.setChangeFromYearLow(Float.parseFloat(jsonObject.getString("ChangeFromYearLow")));
                        //stock.setFiftydayMovingAverage(Float.parseFloat(jsonObject.getString("FiftydayMovingAverage")));
                        //stock.setEarningsShare(Float.parseFloat(jsonObject.getString("EarningsShare")));
                        //stock.setAverageDailyVolume(Float.parseFloat(jsonObject.getString("AverageDailyVolume")));
                        stock.setLastTradePriceOnly(Float.parseFloat(jsonObject.getString("LastTradePriceOnly")));
                        //stock.setYearHigh(Float.parseFloat(jsonObject.getString("YearHigh")));
                        //stock.setLastTradeTime(jsonObject.getString("YearHigh"));
                        stock.setSymbol(jsonObject.getString("Symbol"));
                        //stock.setAskRealtime(Float.parseFloat(jsonObject.getString("AskRealtime")));
                        //stock.setPreviousClose(Float.parseFloat(jsonObject.getString("PreviousClose")));
                        //stock.setDaysRangeRealtime(jsonObject.getString("DaysRangeRealtime"));
                        //stock.setVolume(Float.parseFloat(jsonObject.getString("Volume")));
                        //stock.setAsk(Float.parseFloat(jsonObject.getString("Ask")));
                        //stock.setPercentChange(jsonObject.getString("PercentChange"));
                        //stock.setChangeRealtime(Float.parseFloat(jsonObject.getString("ChangeRealtime")));
                        stock.setChange(Float.parseFloat(jsonObject.getString("Change")));
                        //stock.setMarketCapitalization(jsonObject.getString("MarketCapitalization"));
                        stock.setName(jsonObject.getString("Name").toLowerCase());
                        //stock.setAfterHoursChangeRealtime(Float.parseFloat(jsonObject.getString("AfterHoursChangeRealtime")));
                        //stock.setChangePercentRealtime(jsonObject.getString("ChangePercentRealtime"));
                        //stock.setDaysValueChange(Float.parseFloat(jsonObject.getString("DaysValueChange")));
                        //stock.setLastTradeTime(jsonObject.getString("LastTradeTime"));
                        //stock.setStockExchange(jsonObject.getString("StockExchange"));
                        //stock.setLastTradeRealtimeWithTime(jsonObject.getString("LastTradeRealtimeWithTime"));
                        //stock.setMarketCapRealtime(jsonObject.getString("MarketCapRealtime"));
                        //stock.setPERatio(Float.parseFloat(jsonObject.getString("PERatio")));
                        //stock.setDaysValueChangeRealtime(Float.parseFloat(jsonObject.getString("DaysValueChangeRealtime")));
                        //stock.setChangeFromYearHigh(Float.parseFloat(jsonObject.getString("ChangeFromYearHigh")));
                       stock.setChangeinPercent(Float.parseFloat(jsonObject.getString("ChangeinPercent")));
                        //stock.setPercentChangeFromFiftydayMovingAverage(jsonObject.getString("PercentChangeFromFiftydayMovingAverage"));
//                        stock.setDaysHigh(Float.parseFloat(jsonObject.getString("DaysHigh")));
//                        stock.setPercentChangeFromYearLow(jsonObject.getString("PercentChangeFromYearLow"));
//                        stock.setTradeDate(jsonObject.getString("TradeDate"));
//                        stock.setLastTradeTime(jsonObject.getString("LastTradeWithTime"));
//                        stock.setBidRealtime(Float.parseFloat(jsonObject.getString("BidRealtime")));
//                        stock.setYearRange(jsonObject.getString("YearRange"));
//                        stock.setOrderBookRealtime(Float.parseFloat(jsonObject.getString("OrderBookRealtime")));
//                        stock.setHoldingsGainRealtime(Float.parseFloat(jsonObject.getString("HoldingsGainRealtime")));
//                        stock.setCurrency(jsonObject.getString("Currency"));
//                        stock.setDaysRange(jsonObject.getString("DaysRange"));
//                        stock.setPERatioRealtime(Float.parseFloat(jsonObject.getString("PERatioRealtime")));
//                        stock.setPercebtChangeFromYearHigh(jsonObject.getString("PercebtChangeFromYearHigh"));
//                        stock.setOpen(Float.parseFloat(jsonObject.getString("Open")));
//                        stock.setMoreInfo(jsonObject.getString("MoreInfo"));
//                        stock.setBid(Float.parseFloat(jsonObject.getString("Bid")));


                        update(stock);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Log.e("ffffffff",response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //no interet or other issues
                Toast.makeText(getApplicationContext(), "on error response", Toast.LENGTH_LONG).show();
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
