package com.example.rev.egweather.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.rev.egweather.R;
import com.example.rev.egweather.data.City;
import com.example.rev.egweather.ui.CitiesFragment;
import com.example.rev.egweather.ui.DetailActivity;
import com.example.rev.egweather.ui.SplashScreen;
import com.example.rev.egweather.utils.MapUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class CitiesPagerAdapter extends FragmentPagerAdapter implements OnMapReadyCallback{
    private Context mContext;
    private List<City> mCities;
    private String[] mTabs = {"Cities", "Map"};

    public static final String DETAIL_CITY = "city";

    public CitiesPagerAdapter(FragmentManager fm, Context context, List<City> cities) {
        super(fm);
        mContext = context;
        mCities = cities;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return getCitiesFragment();
            case 1:
                return getMapFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    private Fragment getCitiesFragment() {
        CitiesFragment fragment = new CitiesFragment();
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelableArrayList(SplashScreen.CITIES, (ArrayList<? extends Parcelable>) mCities);
        fragment.setArguments(fragmentBundle);
        return fragment;
    }

    private SupportMapFragment getMapFragment() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);
        return mapFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapUtils.addMarkers(mCities, googleMap);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent detailIntent = new Intent(mContext, DetailActivity.class);
                int position = Integer.valueOf((String)marker.getTag());
                detailIntent.putExtra(DETAIL_CITY, mCities.get(position));
                mContext.startActivity(detailIntent);
                return false;
            }
        });
    }
}
