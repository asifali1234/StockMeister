package com.f5.stockmeister;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;





public class fragment_2 extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static fragment_2 newInstance(int page) {
        fragment_2 fragment2 = new fragment_2();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment2.setArguments(args);
        return fragment2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        TextView text2 = (TextView) view.findViewById(R.id.txt2);
        text2.setText("Fragment #" + mPage);
        return view;
    }
}
