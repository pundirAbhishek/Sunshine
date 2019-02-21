package com.example.sunshine;

class WeatherForecastModel {

    private int temp;
    private String date;
    private String time;
    private String icon;
    private String day;
    private String weatherCondition;
    private int weatherId;

    WeatherForecastModel(int temp, String date, String day, String time, String weatherCondition, int weatherId, String icon) {
        this.temp = temp;
        this.day = day;
        this.date = date;
        this.time = time;
        this.icon = icon;
        this.weatherCondition = weatherCondition;
        this.weatherId = weatherId;
    }

    int getTemp() {
        return temp;
    }

    public String getDay() {
        return day;
    }

    String getDate() {
        return date;
    }

    String getTime() {
        return time;
    }

    String getIcon() {
        return icon;
    }

    String getWeatherCondition() {
        return weatherCondition;
    }

    int getWeatherId() {
        return weatherId;
    }
}
