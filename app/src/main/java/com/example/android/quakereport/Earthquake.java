package com.example.android.quakereport;

public class Earthquake {
    private float mMagnitude;
    private String mLocation;
    private Long mTimeInMiliseconds;

    public Earthquake(float magnitude, String location, Long timeInMiliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMiliseconds = timeInMiliseconds;
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
}
