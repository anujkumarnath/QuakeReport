package com.example.android.quakereport;

public class Earthquake {
    private float mMagnitude;
    private String mLocation;
    private Long mTimeInMiliseconds;
    private String mUrl;

    public Earthquake(float magnitude, String location, Long timeInMiliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMiliseconds = timeInMiliseconds;
        mUrl = url;
    }

    public float getMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Long getTimeInMiliseconds() {
        return mTimeInMiliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
