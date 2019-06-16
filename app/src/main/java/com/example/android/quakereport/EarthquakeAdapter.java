package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        String location = getItem(position).getmLocation();
        String primary_location = location;
        String offset = "Near the";
        String[] strings = location.split(" of ");
        if (location.contains(" of ")) {
            offset = strings[0] + " of";
            primary_location = strings[1];
        }

        ((TextView) convertView.findViewById(R.id.tv_primary_location)).setText(primary_location);
        ((TextView) convertView.findViewById(R.id.tv_offset)).setText(offset);
        ((TextView) convertView.findViewById(R.id.tv_date)).setText(formatDate(new Date(currentEarthquake.getTimeInMiliseconds())));
        ((TextView) convertView.findViewById(R.id.tv_time)).setText(formatTime(new Date(currentEarthquake.getTimeInMiliseconds())));
        return convertView;
    }

    private String formatDate(Date dateObject) {
        return new SimpleDateFormat("MMM dd yyyy").format(dateObject);
    }

    private String formatTime(Date dateObject) {
        return new SimpleDateFormat("h:mm a").format(dateObject);

    }

}
