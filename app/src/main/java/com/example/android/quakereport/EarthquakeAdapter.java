package com.example.android.quakereport;

import android.app.Activity;
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

/**
 * Created by mikol on 02.12.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    //public constructor which takes earthquakes list for and argument
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the magnitude from the current Earthquake object and format it into 1 decimal place decimal value
        // set this text on the name TextView
        double magnitude = currentEarthquake.getMagnitude();
        String magnitudeString = formatMagnitude(magnitude);
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle and set the text inside the circle for the proper value
        magnitudeCircle.setColor(magnitudeColor);
        magnitudeView.setText(magnitudeString);


        // Find the TextView in the list_item.xml layout with the name of the location and the offset
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        //Split the obtained string into two strings - one with offset and one with primary location
        String locationString = currentEarthquake.getPlaceName();
        String offsetLocation = "Near the";
        String primaryLocation;
        String[] splitLocation;
        //if 'of' sequence was found split the location and put into separate variables
        if(locationString.contains("of")) {
            splitLocation = locationString.split("(?<=of )",2);
            offsetLocation = splitLocation[0];
            primaryLocation = splitLocation[1];
        }
        else
            primaryLocation = locationString;
        //populate the views with obtained strings
        locationTextView.setText(primaryLocation);
        offsetTextView.setText(offsetLocation);

        // Find the TextView in the list_item.xml layout with the date of the earthquake
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Get the date of the earthquake and format it in a way : MMM dd, yyyy
        Date dataObject  = new Date(currentEarthquake.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        //format the date and send it to the TextView
        String dateString = formatter.format( dataObject);
        dateTextView.setText(dateString);
        //do the same for time as for the date
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String timeString = timeFormatter.format(dataObject);
        timeTextView.setText(timeString);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private String formatMagnitude(double magnitude)
    {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
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
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
