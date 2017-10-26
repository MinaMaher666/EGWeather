package com.example.rev.egweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.rev.egweather.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;


public class NetworkUtils {
    public static final String REQUEST_METHOD_GET = "GET";
    private NetworkUtils(){
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public static String buildUrl (Context context) {
        Uri uri = Uri.parse(context.getString(R.string.api_base_url))
                .buildUpon()
                .appendQueryParameter(context.getString(R.string.param_bbox_key), context.getString(R.string.param_bbox_value))
                .appendQueryParameter(context.getString(R.string.param_api_key), context.getString(R.string.api_key))
                .build();

        return uri.toString();
    }

    public static URL getUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static InputStream getInputStream(URL url) {
        HttpURLConnection urlConnection;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(REQUEST_METHOD_GET);
            inputStream = urlConnection.getInputStream();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    public static String getJsonResponse(URL url) {
        InputStream stream = getInputStream(url);
        if (stream == null)
            return null;

        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String buildWeatherIconUrl(String icnId, Context context) {
        dayOrNight(icnId);
        Uri uri = Uri.parse(context.getString(R.string.base_icon_url))
                .buildUpon()
                .appendPath(icnId+".png")
                .build();
        return uri.toString();
    }

    public static void dayOrNight(String icnId) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour<6 || hour>18) {
            icnId.replace("d", "n");
        }
    }
}
