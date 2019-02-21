package com.example.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements WeatherDataAdapter.WeatherAdapterOnClickHandler, SharedPreferences.OnSharedPreferenceChangeListener {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String APPID = "93e8169286c123bb09600cda3c7012e8";
    WeatherDataAdapter weatherDataAdapter;

    TextView minTempTV;
    TextView tempTV;
    ImageView currentImageView;
    TextView currentDayTV;
    TextView currentWeatherTypeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minTempTV = findViewById(R.id.current_min_temp_tv);
        tempTV = findViewById(R.id.current_temp_tv);
        currentImageView = findViewById(R.id.current_weather_image_view);
        currentDayTV = findViewById(R.id.current_day_text_view);
        currentWeatherTypeTV = findViewById(R.id.current_weather_type_tv);

        ConstraintLayout constraintLayout = findViewById(R.id.today_weather_information_block);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        weatherDataAdapter = new WeatherDataAdapter(this);
        recyclerView.setAdapter(weatherDataAdapter);
        weatherDataAdapter.clearList();
        getWeatherData();
    }


    private String getSharedPreferenceTempUnits(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(getString(R.string.temp_units_key),
                getString(R.string.temp_default_value));

    }


    private void getWeatherData() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        Retrofit.Builder builder = new Retrofit.Builder();

        Retrofit retrofit = builder.addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_URL)
                .build();

        Api api = retrofit.create(Api.class);

        // TODO Get the location and units from shared preferences
        api.getWeatherData("Chandigarh", APPID, getSharedPreferenceTempUnits(sharedPreferences))
                .enqueue(new Callback<JSONWeatherData>() {
                    @Override
                    public void onResponse(Call<JSONWeatherData> call, Response<JSONWeatherData> response) {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        JSONWeatherData jsonWeatherData = response.body();
                        assert jsonWeatherData != null;
                        if (jsonWeatherData.getSuccessCode() == 200) {
                            Double tempDouble = jsonWeatherData.getWeatherData().getTemperature();
                            int temp = (int) Math.round(tempDouble);
                            Double minTempDouble = jsonWeatherData.getWeatherData().getMinTemperature();
                            int minTemp = (int) Math.round(minTempDouble);
                            Double maxTempDouble = jsonWeatherData.getWeatherData().getMaxTemperature();
                            int maxTemp = (int) Math.round(maxTempDouble);
                            Long time = jsonWeatherData.getDate();
                            String day = SunshineDateUtils.getFriendlyDateString(MainActivity.this, time * 1000, false);
                            String icon = jsonWeatherData.getWeatherList().get(0).getIcon();
                            String weatherCond = jsonWeatherData.getWeatherList().get(0).getDescription();
                            int weatherId = jsonWeatherData.getWeatherList().get(0).getWeatherConditionId();

                            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                            Date d = new Date();
                            String dayOfTheWeek = sdf.format(d);


                            WeatherCurrentModel weatherCurrentModel = new WeatherCurrentModel(minTemp, maxTemp, temp,
                                    icon, dayOfTheWeek, weatherCond, weatherId);

                            minTempTV.setText(String.valueOf(minTemp) + (char) 0x00B0);
                            tempTV.setText(String.valueOf(temp) + (char) 0x00B0);


                            currentDayTV.setText(day);
                            currentWeatherTypeTV.setText(weatherCond);

                            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

                            Glide.with(MainActivity.this)
                                    .load(iconUrl)
                                    .into(currentImageView);


                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<JSONWeatherData> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                    }
                });

        api.getForecastData("Chandigarh", APPID, getSharedPreferenceTempUnits(sharedPreferences))
                .enqueue(new Callback<JSONForecastData>() {
                    @Override
                    public void onResponse(Call<JSONForecastData> call, Response<JSONForecastData> response) {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        JSONForecastData forecastData = response.body();

                        assert forecastData != null;
                        if (forecastData.getSuccessCode() == 200) {
                            List<JSONWeatherData> jsonWeatherDataList = forecastData.getJsonWeatherData();
                            for (int i = 0; i < forecastData.getCount(); i++) {
                                JSONWeatherData jsonWeatherData = jsonWeatherDataList.get(i);

                                Double temp = jsonWeatherData.getWeatherData().getTemperature();
                                int tempInt = (int) Math.round(temp);

                                Long timeInSec = jsonWeatherData.getDate();

                                String date = SunshineDateUtils.getDate(timeInSec * 1000);

                                String day = SunshineDateUtils.getDayName(MainActivity.this, timeInSec * 1000);

                                String time = SunshineDateUtils.getTime(timeInSec * 1000);

                                String weatherCondition = jsonWeatherData.getWeatherList().get(0).getMain();
                                String icon = jsonWeatherData.getWeatherList().get(0).getIcon();
                                int weatherId = jsonWeatherData.getWeatherList().get(0).getWeatherConditionId();

                                WeatherForecastModel weatherForecastModel = new WeatherForecastModel(tempInt,
                                        date, day, time, weatherCondition, weatherId, icon);
                                weatherDataAdapter.insertInList(weatherForecastModel);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONForecastData> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sunshine_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh_menu_item) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            weatherDataAdapter.clearList();
            getWeatherData();

        } else if (item.getItemId() == R.id.settings_menu_item) {
            Intent settingsIntent = new Intent(this, Settings.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(String weatherOfTheDay) {
        Intent intent = new Intent(this, DetailWeatherActivity.class);
        intent.putExtra("Temperature", weatherOfTheDay);
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(getString(R.string.temp_units_key))) {
            // TODO Instead of calling the api change the data using a formula
            getWeatherData();
            weatherDataAdapter.clearList();
            weatherDataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}


