package com.f5.stockmeister;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f5.stockmeister.model_realm.portfolio;

import java.util.ArrayList;



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





        return view;
    }

}

