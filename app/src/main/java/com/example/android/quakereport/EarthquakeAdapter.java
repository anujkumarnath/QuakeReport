package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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

        TextView magnitudeView = convertView.findViewById(R.id.tv_magnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(currentEarthquake.getMagnitude()));
        magnitudeView.setText(new DecimalFormat("0.0").format(currentEarthquake.getMagnitude()));

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

    private int getMagnitudeColor(float magnitude) {
        int magnitudeColorResourceId;
        switch ((int) Math.floor(magnitude)) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(mContext, magnitudeColorResourceId);
    }

}
