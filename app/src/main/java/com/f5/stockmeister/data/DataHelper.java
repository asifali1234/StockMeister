package com.f5.stockmeister.data;


import android.content.Context;
import android.widget.Filter;
import android.widget.Toast;

import com.f5.stockmeister.MainActivity;
import com.f5.stockmeister.model_realm.stock;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class DataHelper {



        private static List<stockname> stocks = new ArrayList<>();

        public interface OnFindResultsListener{

                void onResults(List<searchSuggestion> results);
        }

        public static List<searchSuggestion> getHistory(Context context, int count){



                List<searchSuggestion> suggestionList = new ArrayList<>();

                searchSuggestion searchSuggestion;
                for (int i = 0; i < count; i++) {
                        searchSuggestion = new searchSuggestion(stocks.get(i));
                        searchSuggestion.setIsHistory(true);
                        suggestionList.add(searchSuggestion);
                }

                return suggestionList;
        }

        public static void find(final Context context, String query, final OnFindResultsListener listener){



                new Filter(){

                        @Override
                        protected FilterResults performFiltering(CharSequence constraint) {


                                List<searchSuggestion> suggestionList = new ArrayList<>();


                                RealmResults<stock> results = MainActivity.realm.where(stock.class).contains("name", String.valueOf(constraint)).findAll();
                            if (!(constraint == null || constraint.length() == 0)) {
                                for (stock c : results) {
                                    stockname r = new stockname();
                                    r.setName(c.getName());
                                    suggestionList.add(new searchSuggestion(r));

                                }
                                Toast.makeText(context,constraint,Toast.LENGTH_LONG).show();
                            }


                                FilterResults result;
                                result = new FilterResults();
                                result.values = suggestionList;
                                result.count = suggestionList.size();

                                return result;
                        }

                        @Override
                        protected void publishResults(CharSequence constraint, FilterResults results) {


                            List<searchSuggestion> suggestionList = new ArrayList<>();


                            RealmResults<stock> res = MainActivity.realm.where(stock.class).contains("name", String.valueOf(constraint)).findAll();
                            if (!(constraint == null || constraint.length() == 0)) {
                                for (stock c : res) {
                                    stockname r = new stockname();
                                    r.setName(c.getName());
                                    suggestionList.add(new searchSuggestion(r));

                                }
                                //Toast.makeText(context,constraint,Toast.LENGTH_LONG).show();
                            }


                            FilterResults resultf;
                            resultf = new FilterResults();
                            resultf.values = suggestionList;
                            resultf.count = suggestionList.size();




                                if(listener!=null) {
                                        listener.onResults((List<searchSuggestion>) resultf.values);
                                }
                        }
                }.filter(query);

        }





}