package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getSimpleName();
    private String mUrl;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "onStartLoading()");
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground()");
        if (mUrl == null)
            return null;
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
