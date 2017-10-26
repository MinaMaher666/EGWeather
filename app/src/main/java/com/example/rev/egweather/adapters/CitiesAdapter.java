package com.example.rev.egweather.adapters;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rev.egweather.R;
import com.example.rev.egweather.data.City;
import com.example.rev.egweather.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesAdapter  extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {
    private List<City> mCities;
    private CitiesOnClickListener mClickListener;

     public interface CitiesOnClickListener {
        void onClick(int position);
    }

    public CitiesAdapter (ArrayList<City> cities, CitiesOnClickListener listener) {
        mCities = cities;
        mClickListener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.city_name) TextView cityNameTv;
        @BindView(R.id.main_temp) TextView mainTempTv;
        @BindView(R.id.wind) TextView windTv;
        @BindView(R.id.humidity) TextView humidityTv;
        @BindView(R.id.weather_icn) ImageView weatherIcnIv;

        public CityViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            City city = mCities.get(position);
            cityNameTv.setText(city.getmName());
            mainTempTv.setText(String.valueOf((int)city.getmTemp()) + "\u00b0");
            windTv.setText("Wind: " + String.valueOf((int)city.getmWindSpeed()) + " ms\t" + String.valueOf(city.getmWindDegree()) + "\u00b0");
            humidityTv.setText("Humidity: " + String.valueOf(city.getmHumidity()) + "%");

            Picasso.with(itemView.getContext()).load(NetworkUtils.buildWeatherIconUrl(city.getmWeatherIcon(), itemView.getContext()))
                    .error(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ibs_logo))
                    .into(weatherIcnIv);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClick(getAdapterPosition());
        }
    }
}
