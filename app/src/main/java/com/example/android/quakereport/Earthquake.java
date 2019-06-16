package com.example.android.quakereport;

public class Earthquake {
    private String mMagnitude;
    private String mLocation;
    private Long mTimeInMiliseconds;

    public Earthquake(String magnitude, String location, Long timeInMiliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMiliseconds = timeInMiliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Long getTimeInMiliseconds() {
        return mTimeInMiliseconds;
    }
}
