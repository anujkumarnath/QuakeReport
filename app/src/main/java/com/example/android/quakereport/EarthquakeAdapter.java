package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private Context mContext;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);
        ((TextView) convertView.findViewById(R.id.tv_magnitude)).setText(currentEarthquake.getMagnitude());
        ((TextView) convertView.findViewById(R.id.tv_locality)).setText(currentEarthquake.getmLocality());
        ((TextView) convertView.findViewById(R.id.tv_date)).setText(currentEarthquake.getDate());
        return convertView;
    }

}
