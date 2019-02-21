package com.example.sunshine;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.ViewHolder> {

    final private WeatherAdapterOnClickHandler ItemClickListener;
    private List<WeatherForecastModel> weatherDataList = new ArrayList<>();


    public interface WeatherAdapterOnClickHandler {
        void onItemClick(String weatherOfTheDay);
    }

    WeatherDataAdapter(WeatherAdapterOnClickHandler listItemClickListener) {
        this.ItemClickListener = listItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        viewHolder.textViewTemp.setText(String.valueOf(weatherDataList.get(position).getTemp()) + (char) 0x00B0);
        viewHolder.textViewTimeDate.setText(String.valueOf(weatherDataList.get(position).getTime()));
        viewHolder.textViewWeatherType.setText(weatherDataList.get(position).getWeatherCondition());
        viewHolder.textViewWeatherDay.setText(String.valueOf(weatherDataList.get(position).getDay()));

        String icon = weatherDataList.get(position).getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

        Glide.with(viewHolder.imageViewWeatherType.getContext())
                .load(iconUrl)
                .into(viewHolder.imageViewWeatherType);

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public void setWeatherData(List<WeatherForecastModel> weatherData) {
        this.weatherDataList = weatherData;
        notifyDataSetChanged();
    }

    void clearList() {
        weatherDataList.clear();
        notifyDataSetChanged();
    }

    void insertInList(WeatherForecastModel weatherData) {
        weatherDataList.add(weatherData);
        notifyItemInserted(getItemCount() - 1);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTemp;
        TextView textViewTimeDate;
        TextView textViewWeatherDay;
        TextView textViewWeatherType;
        ImageView imageViewWeatherType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTemp = itemView.findViewById(R.id.text_view_temp);
            textViewWeatherDay = itemView.findViewById(R.id.text_view_day_name);
            textViewWeatherType = itemView.findViewById(R.id.text_view_weather_condition);
            textViewTimeDate = itemView.findViewById(R.id.text_view_time_date);
            imageViewWeatherType = itemView.findViewById(R.id.weather_type_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String weatherOfTheDay = String.valueOf(weatherDataList.get(position).getTemp());
            ItemClickListener.onItemClick(weatherOfTheDay);

        }
    }

}