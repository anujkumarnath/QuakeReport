/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {
    private final static String LOG_TAG = EarthquakeActivity.class.getSimpleName();
    /**
     * Using a sample query URL
     */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=1&limit=100";
    private EarthquakeAdapter mAdapter;
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mEmptyStateTextView = findViewById(R.id.emptyView);
        ListView earthquakeListView = findViewById(R.id.list);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        if (isConnected) {
            Log.d(LOG_TAG, "initLoader()");
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            mEmptyStateTextView.setText("No internet connection");
        }

        earthquakeListView.setEmptyView(mEmptyStateTextView);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri url = Uri.parse(mAdapter.getItem(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader()");
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        Log.d(LOG_TAG, "onLoadFinished()");
        mEmptyStateTextView.setText(R.string.no_earthquake);
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        Log.d(LOG_TAG, "onLoadReset()");
        // Clear the adapter of previous earthquake data
        mAdapter.clear();
    }
}
