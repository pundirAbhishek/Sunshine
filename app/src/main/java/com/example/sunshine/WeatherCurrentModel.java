package com.example.sunshine;

public class WeatherCurrentModel {
    private Integer minTemp;
    private Integer maxTemp;
    private Integer temp;
    private String icon;
    private String day;
    private String weatherCondition;
    private Integer weatherId;

    WeatherCurrentModel(Integer minTemp, Integer maxTemp, Integer temp, String icon, String day,
                        String weatherCondition, Integer weatherId) {
        this.temp = temp;
        this.icon = icon;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.day = day;
        this.weatherCondition = weatherCondition;
        this.weatherId = weatherId;
    }


    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getTemp() {
        return temp;
    }

    public String getIcon() {
        return icon;
    }

    public String getDay() {
        return day;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public Integer getWeatherId() {
        return weatherId;
    }
}
