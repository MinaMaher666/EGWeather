package com.example.rev.egweather.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rev.egweather.R;
import com.example.rev.egweather.adapters.CitiesPagerAdapter;
import com.example.rev.egweather.data.City;
import com.example.rev.egweather.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.city_name) TextView cityNameTv;
    @BindView(R.id.main_temp) TextView mainTempTv;
    @BindView(R.id.min_temp) TextView minTempTv;
    @BindView(R.id.weather_icn) ImageView weatherIcn;
    @BindView(R.id.weather_main) TextView weatherMainTv;
    @BindView(R.id.pressure) TextView pressureTv;
    @BindView(R.id.wind) TextView windTv;
    @BindView(R.id.humidity) TextView humidityTv;

    private City mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        turnIntoPopUp();

        mCity = getIntent().getParcelableExtra(CitiesPagerAdapter.DETAIL_CITY);

        ButterKnife.bind(this);

        setViews();

    }

    void turnIntoPopUp() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.9), (int)(height*0.8));
    }

    void setViews() {
        cityNameTv.setText(mCity.getmName());
        mainTempTv.setText(String.valueOf((int)mCity.getmTemp()) + "\u00b0" + "/" );
        minTempTv.setText(String.valueOf((int)mCity.getmMinTemp()) + "\u00b0");
        weatherMainTv.setText(mCity.getmWeatherMain());
        pressureTv.setText("Pressure: " + String.valueOf(mCity.getmPressure()) + " pHa");
        windTv.setText("Wind: " + String.valueOf(mCity.getmWindSpeed()) + "km\\h\t" + String.valueOf(mCity.getmWindDegree()) + "\u00b0");
        humidityTv.setText("Humidity: " + String.valueOf(mCity.getmHumidity()) + "%");

        String icnUrl = NetworkUtils.buildWeatherIconUrl(mCity.getmWeatherIcon(), this);

        Picasso.with(this).load(icnUrl)
                .error(ContextCompat.getDrawable(this, R.drawable.ibs_logo))
                .into(weatherIcn);
    }
}
