package com.example.android.quakereport;

/**
 * Created by mikol on 02.12.2017.
 */

public class Earthquake {

    /*parameters of the earthquake class*/

    //magnitude described with double precision number
    private double mMagnitude;
    //nearest place to where the earthquake happened stored in String
    private String mPlaceName;
    //when the earthquake happened, displayed in form Month DD, YYYY
    private long mTimeInMiliseconds;
    //the address for the webapage with onfo about this earthquake
    private String mWebAddress;

    //public constructor for the earthquake object
    public Earthquake(double magnitude, String placeName, long date, String webAddress)
    {
        mMagnitude = magnitude;
        mPlaceName = placeName;
        mTimeInMiliseconds = date;
        mWebAddress = webAddress;

    }

    //getters for private members of the earthquake object
    public double getMagnitude() {
        return mMagnitude;
    }

    public long getDate() {
        return mTimeInMiliseconds;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public String getWebAddress() {return mWebAddress;}
}
