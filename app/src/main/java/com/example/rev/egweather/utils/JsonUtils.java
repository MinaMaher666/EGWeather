package com.example.rev.egweather.utils;

import android.content.Context;

import com.example.rev.egweather.R;
import com.example.rev.egweather.data.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class JsonUtils {
    private JsonUtils() {
    }

    public static final List<City> extractCitiesFromJson(Context context, String jsonResponse) {
        ArrayList<City> cities = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray citiesList = root.getJSONArray(context.getString(R.string.cities_list));

            for (int i=0 ; i<citiesList.length() ; i++) {
                JSONObject currentCity = citiesList.getJSONObject(i);
                cities.add(extractCityFromJsonObject(currentCity, context));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return cities;
        }
        return cities;
    }

    public static final City extractCityFromJsonObject(JSONObject currentCity, Context context) {
        String cityId = null;
        try {
            cityId = currentCity.getString(context.getString(R.string.city_id));
            String cityName = currentCity.getString(context.getString(R.string.city_name));
            JSONObject currentCityCoord = currentCity.getJSONObject(context.getString(R.string.city_coord));
            Double lat = currentCityCoord.getDouble(context.getString(R.string.coord_lat));
            Double lon = currentCityCoord.getDouble(context.getString(R.string.coord_lon));
            JSONObject currentCityMain = currentCity.getJSONObject(context.getString(R.string.main_weather));
            Double temp = currentCityMain.getDouble(context.getString(R.string.temp));
            Double minTemp = currentCityMain.getDouble(context.getString(R.string.min_temp));
            Double maxTemp = currentCityMain.getDouble(context.getString(R.string.max_temp));
            Double pressure = currentCityMain.getDouble(context.getString(R.string.pressure));
            Double humidity = currentCityMain.getDouble(context.getString(R.string.humidity));
            JSONObject currentCityWind = currentCity.getJSONObject(context.getString(R.string.wind));
            Double windSpeed = currentCityWind.getDouble(context.getString(R.string.wind_speed));
            Double windDegree = currentCityWind.getDouble(context.getString(R.string.wind_degree));
            JSONArray weatherArr = currentCity.getJSONArray(context.getString(R.string.weather));
            JSONObject firstWeather = weatherArr.getJSONObject(0);
            String weatherId = firstWeather.getString(context.getString(R.string.weather_id));
            String weatherMain = firstWeather.getString(context.getString(R.string.weather_main));
            String weatherDes = firstWeather.getString(context.getString(R.string.weather_des));
            String weatherIcon = firstWeather.getString(context.getString(R.string.weather_icon));
            return new City(cityId, cityName, lat, lon, temp, minTemp, maxTemp, pressure,
                    humidity, windSpeed, windDegree, weatherId, weatherMain, weatherDes, weatherIcon);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
