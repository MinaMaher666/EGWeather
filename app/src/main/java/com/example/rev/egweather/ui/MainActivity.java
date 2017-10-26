package com.example.rev.egweather.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.rev.egweather.R;
import com.example.rev.egweather.adapters.CitiesPagerAdapter;
import com.example.rev.egweather.data.City;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rev.egweather.R.drawable.ic_cities;
import static com.example.rev.egweather.R.drawable.ic_maps;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_type)
    ViewPager typeViewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    CitiesPagerAdapter mPagerAdapter;


    private ArrayList<City> mCities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mCities = getIntent().getParcelableArrayListExtra(SplashScreen.CITIES);

        mPagerAdapter = new CitiesPagerAdapter(getSupportFragmentManager(), this, mCities);
        typeViewPager.setAdapter(mPagerAdapter);

        tabLayout.setupWithViewPager(typeViewPager);
        styleTabLayout();
    }

    void styleTabLayout() {
        LayoutInflater inflater = LayoutInflater.from(this);
        TextView tab0 = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, tabLayout, false);
        TextView tab1 = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, tabLayout, false);

//        tab0.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
//        tab1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

        tab0.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, ic_cities), null, null, null);
        tab1.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, ic_maps), null, null, null);

        tab0.setCompoundDrawablePadding(6);
        tab1.setCompoundDrawablePadding(6);

        tabLayout.getTabAt(0).setCustomView(tab0);
        tabLayout.getTabAt(1).setCustomView(tab1);
    }

}
