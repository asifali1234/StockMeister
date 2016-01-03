package com.f5.stockmeister;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        ListView list= (ListView) view.findViewById(R.id.list1);
        list.setClickable(true);

        RealmResults<stock> po = MainActivity.realm.where(stock.class).findAll();

        final stock_adapter port= new stock_adapter(getContext(),R.layout.stock_item);
        for (stock c : po) {
            port.add(c);

        }

        list.setAdapter(port);



        return view;
    }

}


