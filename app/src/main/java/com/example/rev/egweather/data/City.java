package com.example.rev.egweather.data;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
    private String mId;
    private String mName;
    private double mLat;
    private double mLon;
    private double mTemp;
    private double mMinTemp;
    private double mMaxTemp;
    private double mPressure;
    private double mHumidity;
    private double mWindSpeed;
    private double mWindDegree;
    private String mWeatherId;
    private String mWeatherMain;
    private String mWeatherDes;
    private String mWeatherIcon;

    public City(String mId, String mName, double mLat, double mLon, double mTemp, double mMinTemp, double mMaxTemp, double mPressure, double mHumidity, double mWindSpeed, double mWindDegree, String mWeatherId, String mWeatherMain, String mWeatherDes, String mWeatherIcon) {
        this.mId = mId;
        this.mName = mName;
        this.mLat = mLat;
        this.mLon = mLon;
        this.mTemp = mTemp;
        this.mMinTemp = mMinTemp;
        this.mMaxTemp = mMaxTemp;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        this.mWindSpeed = mWindSpeed;
        this.mWindDegree = mWindDegree;
        this.mWeatherId = mWeatherId;
        this.mWeatherMain = mWeatherMain;
        this.mWeatherDes = mWeatherDes;
        this.mWeatherIcon = mWeatherIcon;
    }

    protected City(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mLat = in.readDouble();
        mLon = in.readDouble();
        mTemp = in.readDouble();
        mMinTemp = in.readDouble();
        mMaxTemp = in.readDouble();
        mPressure = in.readDouble();
        mHumidity = in.readDouble();
        mWindSpeed = in.readDouble();
        mWindDegree = in.readDouble();
        mWeatherId = in.readString();
        mWeatherMain = in.readString();
        mWeatherDes = in.readString();
        mWeatherIcon = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public double getmLon() {
        return mLon;
    }

    public void setmLon(double mLon) {
        this.mLon = mLon;
    }

    public double getmTemp() {
        return mTemp;
    }

    public void setmTemp(double mTemp) {
        this.mTemp = mTemp;
    }

    public double getmMinTemp() {
        return mMinTemp;
    }

    public void setmMinTemp(double mMinTemp) {
        this.mMinTemp = mMinTemp;
    }

    public double getmMaxTemp() {
        return mMaxTemp;
    }

    public void setmMaxTemp(double mMaxTemp) {
        this.mMaxTemp = mMaxTemp;
    }

    public double getmPressure() {
        return mPressure;
    }

    public void setmPressure(double mPressure) {
        this.mPressure = mPressure;
    }

    public double getmWindSpeed() {
        return mWindSpeed;
    }

    public void setmWindSpeed(double mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public double getmWindDegree() {
        return mWindDegree;
    }

    public void setmWindDegree(double mWindDegree) {
        this.mWindDegree = mWindDegree;
    }

    public String getmWeatherId() {
        return mWeatherId;
    }

    public void setmWeatherId(String mWeatherId) {
        this.mWeatherId = mWeatherId;
    }

    public String getmWeatherMain() {
        return mWeatherMain;
    }

    public void setmWeatherMain(String mWeatherMain) {
        this.mWeatherMain = mWeatherMain;
    }

    public String getmWeatherDes() {
        return mWeatherDes;
    }

    public void setmWeatherDes(String mWeatherDes) {
        this.mWeatherDes = mWeatherDes;
    }

    public String getmWeatherIcon() {
        return mWeatherIcon;
    }

    public void setmWeatherIcon(String mWeatherIcon) {
        this.mWeatherIcon = mWeatherIcon;
    }

    public double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
        parcel.writeDouble(mLat);
        parcel.writeDouble(mLon);
        parcel.writeDouble(mTemp);
        parcel.writeDouble(mMinTemp);
        parcel.writeDouble(mMaxTemp);
        parcel.writeDouble(mPressure);
        parcel.writeDouble(mHumidity);
        parcel.writeDouble(mWindSpeed);
        parcel.writeDouble(mWindDegree);
        parcel.writeString(mWeatherId);
        parcel.writeString(mWeatherMain);
        parcel.writeString(mWeatherDes);
        parcel.writeString(mWeatherIcon);
    }
}
