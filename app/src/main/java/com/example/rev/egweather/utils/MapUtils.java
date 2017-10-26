package com.example.rev.egweather.utils;

import com.example.rev.egweather.data.City;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapUtils {
    private MapUtils(){
    }

    public static void addMarkers(List<City> cities, GoogleMap map) {
        for (int i=0 ; i<cities.size() ; i++) {
            City city = cities.get(i);
            LatLng curCityLatLng = new LatLng(city.getmLat(), city.getmLon());
            Marker curMarker = map.addMarker(new MarkerOptions().position(curCityLatLng));
            curMarker.setTitle(String.valueOf(city.getmTemp()));
            curMarker.setTag(String.valueOf(i));
        }

//        for (City city: cities) {
//            LatLng curCityLatLng = new LatLng(city.getmLat(), city.getmLon());
//            map.addMarker(new MarkerOptions().position(curCityLatLng)).setTag(city);
//        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28, 30), 5.7f));
    }
}
