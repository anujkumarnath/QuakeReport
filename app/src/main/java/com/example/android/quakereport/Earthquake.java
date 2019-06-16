package com.example.android.quakereport;

public class Earthquake {
    private String mMagnitude;
    private String mLocality;
    private String mDate;

    public Earthquake(String magnitude, String locality, String date) {
        mMagnitude = magnitude;
        mLocality = locality;
        mDate = date;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getmLocality() {
        return mLocality;
    }

    public String getDate() {
        return mDate;
    }
}
