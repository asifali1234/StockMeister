package com.f5.stockmeister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.f5.stockmeister.adapters.port_adapter;
import com.f5.stockmeister.model_realm.portfolio;
import com.melnykov.fab.FloatingActionButton;

import io.realm.RealmResults;



public class fragment_1 extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    ListView list;

    public static fragment_1 newInstance(int page) {
        fragment_1 fragment1 = new fragment_1();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag1, container, false);

        com.melnykov.fab.FloatingActionButton b = (FloatingActionButton) view.findViewById(R.id.button);

        RealmResults<portfolio> po = MainActivity.realm.where(portfolio.class).findAll();

        list= (ListView) view.findViewById(R.id.list);


        Object p[] ;
        p=  po.toArray();
        for(int i=0;i<p.length;i++)
        {   portfolio port=(portfolio)p[i];
            CreatePortfolio.updatePort(port.getName());
        }
        final port_adapter port= new port_adapter(getContext(),R.layout.port_item);
        for (portfolio c : po) {

                        port.add(c);

        }


        list.setAdapter(port);

        list.setClickable(true);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), CreatePortfolio.class);

                startActivity(intent);
            }
        });
       list.setItemsCanFocus(false);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View vi, int position, long id) {

                AppController.portfolio = (portfolio) port.getItem(position);
                try {
                    Intent intent = new Intent(getContext(), portfolio_detail.class);
                    startActivity(intent);
                } catch (Exception e) {e.printStackTrace();
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }

}
