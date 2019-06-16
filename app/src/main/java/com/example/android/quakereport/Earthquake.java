package com.example.android.quakereport;

public class Earthquake {
    private String mMagnitude;
    private String mLocality;
    private Long mTimeInMiliseconds;

    public Earthquake(String magnitude, String locality, Long timeInMiliseconds) {
        mMagnitude = magnitude;
        mLocality = locality;
        mTimeInMiliseconds = timeInMiliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getmLocality() {
        return mLocality;
    }

    public Long getTimeInMiliseconds() {
        return mTimeInMiliseconds;
    }
}
