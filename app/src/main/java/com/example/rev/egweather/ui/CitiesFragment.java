package com.example.rev.egweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rev.egweather.R;
import com.example.rev.egweather.adapters.CitiesAdapter;
import com.example.rev.egweather.adapters.CitiesPagerAdapter;
import com.example.rev.egweather.data.City;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CitiesFragment extends Fragment implements CitiesAdapter.CitiesOnClickListener{
    private ArrayList<City> mCities;

    @BindView(R.id.cities_rv) RecyclerView citiesRv;

    @Override
    public void setArguments(Bundle args) {
        mCities = args.getParcelableArrayList(SplashScreen.CITIES);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cities, container, false);
        ButterKnife.bind(this, rootView);

        CitiesAdapter adapter = new CitiesAdapter(mCities, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        citiesRv.setAdapter(adapter);
        citiesRv.setLayoutManager(manager);

        return rootView;
    }

    @Override
    public void onClick(int position) {
        Intent detailIntent = new Intent(getContext(), DetailActivity.class);
        detailIntent.putExtra(CitiesPagerAdapter.DETAIL_CITY, mCities.get(position));
        startActivity(detailIntent);
    }
}
