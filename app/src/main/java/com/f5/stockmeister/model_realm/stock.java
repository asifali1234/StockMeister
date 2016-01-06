package com.f5.stockmeister.model_realm;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class stock extends RealmObject implements Serializable {


    private float Ask;
    private float AverageDailyVolume;
    private float Bid;
    private float AskRealtime;
    private float BidRealtime;


    private float Change;

    private String Currency;
    private float ChangeRealtime;
    private float AfterHoursChangeRealtime;
    private float DividendShare;
    private String LastTradeDate;
    private String TradeDate;
    private float EarningsShare;


    private float DaysLow;
    private float DaysHigh;
    private float YearLow;
    private float YearHigh;

    private float HoldingsGainRealtime;
    private String MoreInfo;
    private float OrderBookRealtime;
    private String MarketCapitalization;
    private String MarketCapRealtime;


    private float ChangeFromYearLow;
    private String PercentChangeFromYearLow;
    private String LastTradeRealtimeWithTime;
    private String ChangePercentRealtime;
    private float ChangeFromYearHigh;
    private String PercebtChangeFromYearHigh;

    private float LastTradePriceOnly;

    private String DaysRange;
    private String DaysRangeRealtime;
    private float FiftydayMovingAverage;


    private float ChangeFromFiftydayMovingAverage;
    private String PercentChangeFromFiftydayMovingAverage;


    private float Open;
    private float PreviousClose;
    private float PricePaid;
    private String ChangeinPercent;


    private float PERatio;

    private float PERatioRealtime;


    private String LastTradeTime;

    private float Volume;

    private String YearRange;
    private float DaysValueChange;
    private float DaysValueChangeRealtime;
    private String StockExchange;

    private String PercentChange;


    //FOR PORTFOLIO ONLY
    private int count;
    private float buy_value;
    private float gain_loss;                //current value - buy value
    private float perc_gain_loss;
    private float stock_value;              //current value * count


    /*   ///////////////////////////////////EXAMPLE DATA FOR PARSING  - DETERMINED DATA TYPES WITH THIS
    "symbol":"BHP.AX",
            "Change":"+0.93",

            "Currency":"AUD",
            "ChangeRealtime":null,
            "AfterHoursChangeRealtime":null,
            "DividendShare":null,
            "LastTradeDate":"12/24/2015",
            "TradeDate":null,
            "Earning"Ask":"18.35",
            "AverageDailyVolume":"10577900",
            "Bid":"18.30",
            "AskRealtime":null,
            "BidRealtime":null,


            sShare":"0.36",




            "DaysLow":"18.09",
            "DaysHigh":"18.37",
            "YearLow":"16.25",
            "YearHigh":"32.04",

            "HoldingsGainRealtime":null,
            "MoreInfo":null,
            "OrderBookRealtime":null,
        0    "MarketCapitalization":"97.59B",
            "MarketCapRealtime":null,

            "ChangeFromYearLow":"2.09",
            "PercentChangeFromYearLow":"+12.86%",
            "LastTradeRealtimeWithTime":null,
            "ChangePercentRealtime":null,
            "ChangeFromYearHigh":"-13.70",
            "PercebtChangeFromYearHigh":"-42.76%",
            "LastTradeWithTime":"2:10pm - <b>18.34</b>",
            "LastTradePriceOnly":"18.34",

            "DaysRange":"18.09 - 18.37",
            "DaysRangeRealtime":null,
            "FiftydayMovingAverage":"19.05",



            "ChangeFromFiftydayMovingAverage":"-0.71",
            "PercentChangeFromFiftydayMovingAverage":"-3.72%",
            "Name":"BHP BLT FPO",

            "Open":"18.23",
            "PreviousClose":"17.41",
            "PricePaid":null,
            "ChangeinPercent":"+5.34%",


            "PERatio":"51.23",

            "PERatioRealtime":null,





            "LastTradeTime":"2:10pm",

            "Volume":"8315566",

            "YearRange":"16.25 - 32.04",
            "DaysValueChange":null,
            "DaysValueChangeRealtime":null,
            "StockExchange":"ASX",

            "PercentChange":"+5.34%"
     */


    @PrimaryKey
    private String name;
    private String symbol;
    private int stock_array_id;

    public float getAsk() {
        return Ask;
    }

    public void setAsk(float ask) {
        Ask = ask;
    }

    public float getAverageDailyVolume() {
        return AverageDailyVolume;
    }

    public void setAverageDailyVolume(float averageDailyVolume) {
        AverageDailyVolume = averageDailyVolume;
    }

    public float getBid() {
        return Bid;
    }

    public void setBid(float bid) {
        Bid = bid;
    }

    public float getAskRealtime() {
        return AskRealtime;
    }

    public void setAskRealtime(float askRealtime) {
        AskRealtime = askRealtime;
    }

    public float getBidRealtime() {
        return BidRealtime;
    }

    public void setBidRealtime(float bidRealtime) {
        BidRealtime = bidRealtime;
    }

    public float getChange() {
        return Change;
    }

    public void setChange(float change) {
        Change = change;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public float getChangeRealtime() {
        return ChangeRealtime;
    }

    public void setChangeRealtime(float changeRealtime) {
        ChangeRealtime = changeRealtime;
    }

    public float getAfterHoursChangeRealtime() {
        return AfterHoursChangeRealtime;
    }

    public void setAfterHoursChangeRealtime(float afterHoursChangeRealtime) {
        AfterHoursChangeRealtime = afterHoursChangeRealtime;
    }

    public float getDividendShare() {
        return DividendShare;
    }

    public void setDividendShare(float dividendShare) {
        DividendShare = dividendShare;
    }

    public String getLastTradeDate() {
        return LastTradeDate;
    }

    public void setLastTradeDate(String lastTradeDate) {
        LastTradeDate = lastTradeDate;
    }

    public String getTradeDate() {
        return TradeDate;
    }

    public void setTradeDate(String tradeDate) {
        TradeDate = tradeDate;
    }

    public float getEarningsShare() {
        return EarningsShare;
    }

    public void setEarningsShare(float earningsShare) {
        EarningsShare = earningsShare;
    }

    public float getDaysLow() {
        return DaysLow;
    }

    public void setDaysLow(float daysLow) {
        DaysLow = daysLow;
    }

    public float getDaysHigh() {
        return DaysHigh;
    }

    public void setDaysHigh(float daysHigh) {
        DaysHigh = daysHigh;
    }

    public float getYearLow() {
        return YearLow;
    }

    public void setYearLow(float yearLow) {
        YearLow = yearLow;
    }

    public float getHoldingsGainRealtime() {
        return HoldingsGainRealtime;
    }

    public void setHoldingsGainRealtime(float holdingsGainRealtime) {
        HoldingsGainRealtime = holdingsGainRealtime;
    }

    public String getMoreInfo() {
        return MoreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        MoreInfo = moreInfo;
    }

    public float getOrderBookRealtime() {
        return OrderBookRealtime;
    }

    public void setOrderBookRealtime(float orderBookRealtime) {
        OrderBookRealtime = orderBookRealtime;
    }

    public float getYearHigh() {
        return YearHigh;
    }

    public void setYearHigh(float yearHigh) {
        YearHigh = yearHigh;
    }

    public String getMarketCapRealtime() {
        return MarketCapRealtime;
    }

    public void setMarketCapRealtime(String marketCapRealtime) {
        MarketCapRealtime = marketCapRealtime;
    }

    public String getMarketCapitalization() {
        return MarketCapitalization;
    }

    public void setMarketCapitalization(String marketCapitalization) {
        MarketCapitalization = marketCapitalization;
    }

    public float getChangeFromYearLow() {
        return ChangeFromYearLow;
    }

    public void setChangeFromYearLow(float changeFromYearLow) {
        ChangeFromYearLow = changeFromYearLow;
    }

    public String getPercentChangeFromYearLow() {
        return PercentChangeFromYearLow;
    }

    public void setPercentChangeFromYearLow(String percentChangeFromYearLow) {
        PercentChangeFromYearLow = percentChangeFromYearLow;
    }

    public String getLastTradeRealtimeWithTime() {
        return LastTradeRealtimeWithTime;
    }

    public void setLastTradeRealtimeWithTime(String lastTradeRealtimeWithTime) {
        LastTradeRealtimeWithTime = lastTradeRealtimeWithTime;
    }

    public String getChangePercentRealtime() {
        return ChangePercentRealtime;
    }

    public void setChangePercentRealtime(String changePercentRealtime) {
        ChangePercentRealtime = changePercentRealtime;
    }

    public float getChangeFromYearHigh() {
        return ChangeFromYearHigh;
    }

    public void setChangeFromYearHigh(float changeFromYearHigh) {
        ChangeFromYearHigh = changeFromYearHigh;
    }

    public String getPercebtChangeFromYearHigh() {
        return PercebtChangeFromYearHigh;
    }

    public void setPercebtChangeFromYearHigh(String percebtChangeFromYearHigh) {
        PercebtChangeFromYearHigh = percebtChangeFromYearHigh;
    }

    public float getLastTradePriceOnly() {
        return LastTradePriceOnly;
    }

    public void setLastTradePriceOnly(float lastTradePriceOnly) {
        LastTradePriceOnly = lastTradePriceOnly;
    }

    public String getDaysRange() {
        return DaysRange;
    }

    public void setDaysRange(String daysRange) {
        DaysRange = daysRange;
    }

    public String getDaysRangeRealtime() {
        return DaysRangeRealtime;
    }

    public void setDaysRangeRealtime(String daysRangeRealtime) {
        DaysRangeRealtime = daysRangeRealtime;
    }

    public float getFiftydayMovingAverage() {
        return FiftydayMovingAverage;
    }

    public void setFiftydayMovingAverage(float fiftydayMovingAverage) {
        FiftydayMovingAverage = fiftydayMovingAverage;
    }

    public float getChangeFromFiftydayMovingAverage() {
        return ChangeFromFiftydayMovingAverage;
    }

    public void setChangeFromFiftydayMovingAverage(float changeFromFiftydayMovingAverage) {
        ChangeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
    }

    public String getPercentChangeFromFiftydayMovingAverage() {
        return PercentChangeFromFiftydayMovingAverage;
    }

    public void setPercentChangeFromFiftydayMovingAverage(String percentChangeFromFiftydayMovingAverage) {
        PercentChangeFromFiftydayMovingAverage = percentChangeFromFiftydayMovingAverage;
    }

    public float getStock_value() {
        return stock_value;
    }

    public void setStock_value(float stock_value) {
        this.stock_value = stock_value;
    }

    public float getPerc_gain_loss() {
        return perc_gain_loss;
    }

    public void setPerc_gain_loss(float perc_gain_loss) {
        this.perc_gain_loss = perc_gain_loss;
    }

    public float getGain_loss() {
        return gain_loss;
    }

    public void setGain_loss(float gain_loss) {
        this.gain_loss = gain_loss;
    }

    public float getBuy_value() {
        return buy_value;
    }

    public void setBuy_value(float buy_value) {
        this.buy_value = buy_value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPercentChange() {
        return PercentChange;
    }

    public void setPercentChange(String percentChange) {
        PercentChange = percentChange;
    }

    public String getStockExchange() {
        return StockExchange;
    }

    public void setStockExchange(String stockExchange) {
        StockExchange = stockExchange;
    }

    public float getDaysValueChangeRealtime() {
        return DaysValueChangeRealtime;
    }

    public void setDaysValueChangeRealtime(float daysValueChangeRealtime) {
        DaysValueChangeRealtime = daysValueChangeRealtime;
    }

    public float getDaysValueChange() {
        return DaysValueChange;
    }

    public void setDaysValueChange(float daysValueChange) {
        DaysValueChange = daysValueChange;
    }

    public String getYearRange() {
        return YearRange;
    }

    public void setYearRange(String yearRange) {
        YearRange = yearRange;
    }

    public float getVolume() {
        return Volume;
    }

    public void setVolume(float volume) {
        Volume = volume;
    }

    public String getLastTradeTime() {
        return LastTradeTime;
    }

    public void setLastTradeTime(String lastTradeTime) {
        LastTradeTime = lastTradeTime;
    }

    public float getPERatioRealtime() {
        return PERatioRealtime;
    }

    public void setPERatioRealtime(float PERatioRealtime) {
        this.PERatioRealtime = PERatioRealtime;
    }

    public float getPERatio() {
        return PERatio;
    }

    public void setPERatio(float PERatio) {
        this.PERatio = PERatio;
    }

    public String getChangeinPercent() {
        return ChangeinPercent;
    }

    public void setChangeinPercent(String changeinPercent) {
        ChangeinPercent = changeinPercent;
    }

    public float getPricePaid() {
        return PricePaid;
    }

    public void setPricePaid(float pricePaid) {
        PricePaid = pricePaid;
    }

    public float getPreviousClose() {
        return PreviousClose;
    }

    public void setPreviousClose(float previousClose) {
        PreviousClose = previousClose;
    }

    public float getOpen() {
        return Open;
    }

    public void setOpen(float open) {
        Open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getStock_array_id() {
        return stock_array_id;
    }

    public void setStock_array_id(int stock_array_id) {
        this.stock_array_id = stock_array_id;
    }
}


