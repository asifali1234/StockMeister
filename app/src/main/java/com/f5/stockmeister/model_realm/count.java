package com.f5.stockmeister.model_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by white_devil on 12/13/2015.
 */
public class count extends RealmObject {
    private int stock_count;
    private int portfolio_count;
    @PrimaryKey
    private int id;

    public int getStock_count() {
        return stock_count;
    }

    public void setStock_count(int stock_count) {
        this.stock_count = stock_count;
    }

    public int getPortfolio_count() {
        return portfolio_count;
    }

    public void setPortfolio_count(int portfolio_count) {
        this.portfolio_count = portfolio_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
