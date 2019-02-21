package com.example.sunshine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<JSONWeatherData> getWeatherData(@Query("q") String cityName,
                                         @Query("APPID") String APPID,
                                         @Query("units") String units);

    @GET("forecast")
    Call<JSONForecastData> getForecastData(@Query("q") String cityName,
                                           @Query("APPID") String APPID,
                                           @Query("units") String units);

}
