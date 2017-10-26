package com.example.rev.egweather.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.rev.egweather.R;
import com.example.rev.egweather.data.City;
import com.example.rev.egweather.utils.JsonUtils;
import com.example.rev.egweather.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<City>>{
    @BindView(R.id.logo) ImageView logoIv;

    private ArrayList<City> mCities;

    public static final int LOADER_ID = 666;
    public static final String CITIES = "cities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(3000);

        logoIv.startAnimation(fadeIn);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();;

        mCities = new ArrayList<>();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<City>> onCreateLoader(int id, Bundle args) {
        final Context context = SplashScreen.this;
        return new AsyncTaskLoader<List<City>>(context) {
            @Override
            protected void onStartLoading() {
                forceLoad();
            }

            @Override
            public List<City> loadInBackground() {
                String urlString = NetworkUtils.buildUrl(context);
                URL url = NetworkUtils.getUrl(urlString);
                String jsonResponse = NetworkUtils.getJsonResponse(url);
                return JsonUtils.extractCitiesFromJson(context, jsonResponse);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<City>> loader, List<City> data) {
        mCities.clear();
        mCities.addAll(data);

        Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
        mainIntent.putParcelableArrayListExtra(CITIES, mCities);

        startActivity(mainIntent);
        finish();
    }

    @Override
    public void onLoaderReset(Loader<List<City>> loader) {

    }
}
