package com.jacky.mysafari.Models;


import org.parceler.Parcel;

@Parcel
public class Destination {

    private String mName;
    private String mType;
    private String mSnippet;
    private String mWebsite;
    private double mRating;
    private String mImageUrl;
    private double mLatitude;
    private double mLongitude;

    public Destination() {
    }

    public Destination(String mName, String mType, String mSnippet, String mWebsite, double mRating, String mImageUrl, double mLatitude, double mLongitude) {
        this.mName = mName;
        this.mType = mType;
        this.mSnippet = mSnippet;
        this.mWebsite = mWebsite;
        this.mRating = mRating;
        this.mImageUrl = mImageUrl;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public String getmName() {
        return mName;
    }

    public String getmSnippet() {
        return mSnippet;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public String getmType() {
        return mType;
    }

    public double getmRating() {
        return mRating;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }
}
