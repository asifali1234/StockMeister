package com.f5.stockmeister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.f5.stockmeister.adapters.stock_adapter;
import com.f5.stockmeister.model_realm.portfolio;
import com.f5.stockmeister.model_realm.stock;

import java.util.ArrayList;

import io.realm.RealmResults;


public class fragment_3 extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;


    public static MainActivity CustomListView = null;

    public static ArrayList<portfolio> CustomListViewValuesArr=new ArrayList<>();

    public static fragment_3 newInstance(int page) {
        fragment_3 fragment3 = new fragment_3();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment3.setArguments(args);
        return fragment3;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);

        ListView list= (ListView) view.findViewById(R.id.loser);
        list.setClickable(true);



        final stock_adapter port= new stock_adapter(getContext(),R.layout.stock_item);

        RealmResults <stock> stocks = MainActivity.realm.where(stock.class).findAllSorted("ChangeinPercent",false);
        for (stock c : stocks) {
            port.add(c);

        }

        list.setAdapter(port);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AppController.stock = (stock) port.getItem(i);
                Intent intent = new Intent(getContext(), stock_detail.class).putExtra("stock", (stock) port.getItem(i));
                startActivity(intent);
            }
        });



        return view;
    }

}


