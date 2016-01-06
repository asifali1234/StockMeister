package com.f5.stockmeister.data;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;


public  class searchSuggestion implements SearchSuggestion {

    private stockname mColor;

    private String mColorName;

    private boolean mIsHistory;

    public searchSuggestion(stockname color){

        this.mColor = color;
        this.mColorName = mColor.getName();
    }

    public searchSuggestion(Parcel source) {
        this.mColorName = source.readString();
    }

    public stockname getColor(){
        return mColor;
    }

    public void setIsHistory(boolean isHistory){
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory(){return this.mIsHistory;}

    @Override
    public String getBody() {
        return mColor.getName();
    }

    @Override
    public Creator getCreator() {
        return CREATOR;
    }

    ///////

    public static final Creator<searchSuggestion> CREATOR = new Creator<searchSuggestion>() {
        @Override
        public searchSuggestion createFromParcel(Parcel in) {
            return new searchSuggestion(in);
        }

        @Override
        public searchSuggestion[] newArray(int size) {
            return new searchSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mColorName);
    }
}