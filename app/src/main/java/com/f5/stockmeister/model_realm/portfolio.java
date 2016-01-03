package com.f5.stockmeister.model_realm;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class portfolio extends RealmObject implements Serializable {

    private float total_value;
    private float total_gain_loss;
    private float total_perc_gain_loss;
    private int total_share_count;
    private RealmList<stock> stocks;



    @PrimaryKey
    private String  name;
    private String id;


    public float getTotal_value() {
        return total_value;
    }

    public void setTotal_value(float total_value) {
        this.total_value = total_value;
    }

    public float getTotal_gain_loss() {
        return total_gain_loss;
    }

    public void setTotal_gain_loss(float total_gain_loss) {
        this.total_gain_loss = total_gain_loss;
    }

    public float getTotal_perc_gain_loss() {
        return total_perc_gain_loss;
    }

    public void setTotal_perc_gain_loss(float total_perc_gain_loss) {
        this.total_perc_gain_loss = total_perc_gain_loss;
    }

    public int getTotal_share_count() {
        return total_share_count;
    }

    public void setTotal_share_count(int total_share_count) {
        this.total_share_count = total_share_count;
    }

    public RealmList<stock> getStocks() {
        return stocks;
    }

    public void setStocks(RealmList<stock> stocks) {
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
