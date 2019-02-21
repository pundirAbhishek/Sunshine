package com.example.sunshine;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class JSONForecastData {

    @SerializedName("cnt")
    @Expose
    private int count;

    @SerializedName("cod")
    @Expose
    private int successCode;

    @SerializedName("list")
    @Expose
    private List<JSONWeatherData> jsonWeatherData;

    @SerializedName("city")
    @Expose
    private City city;

    public int getCount() {
        return count;
    }

    int getSuccessCode() {
        return successCode;
    }

    public List<JSONWeatherData> getJsonWeatherData() {
        return jsonWeatherData;
    }

    public City getCity() {
        return city;
    }
}

class City {

    @SerializedName("name")
    @Expose
    private String cityName;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("population")
    @Expose
    private Long population;

    @SerializedName("coord")
    @Expose
    private Coordinates coordinates;

    @SerializedName("id")
    @Expose
    private Long cityId;

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public Long getPopulation() {
        return population;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getCityId() {
        return cityId;
    }
}

class JSONWeatherData {

    @SerializedName("cod")
    @Expose
    private int successCode;

    @SerializedName("dt")
    @Expose
    private Long date;

    @SerializedName("id")
    @Expose
    private Long cityId;

    @SerializedName("weather")
    @Expose
    private List<Weather> weatherList;

    @SerializedName("coord")
    @Expose
    private Coordinates coordinations;

    @SerializedName("name")
    @Expose
    private String cityName;

    @SerializedName("visibility")
    @Expose
    private Long visibility;

    @SerializedName("main")
    @Expose
    private WeatherData weatherData;

    @SerializedName("sys")
    @Expose
    private Country country;

    @SerializedName("rain")
    @Expose
    private Rain rain;

    @SerializedName("snow")
    @Expose
    private Snow snow;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    public int getSuccessCode() {
        return successCode;
    }

    public Long getDate() {
        return date;
    }

    public Long getCityId() {
        return cityId;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public Coordinates getCoordinations() {
        return coordinations;
    }

    public String getCityName() {
        return cityName;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public Country getCountry() {
        return country;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Long getVisibility() {
        return visibility;
    }

}

class Weather {

    @SerializedName("id")
    @Expose
    private int weatherConditionId;

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    public int getWeatherConditionId() {
        return weatherConditionId;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}

class Coordinates {
    @SerializedName("lat")
    @Expose
    private Double latitude;

    @SerializedName("lon")
    @Expose
    private Double Longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }
}

class Wind {

    @SerializedName("speed")
    @Expose
    private Double windSpeed;

    @SerializedName("deg")
    @Expose
    private Double windDegree;

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getWindDegree() {
        return windDegree;
    }
}

class Clouds {

    @SerializedName("all")
    @Expose
    private Double cloudiness;

    public Double getCloudiness() {
        return cloudiness;
    }
}

class WeatherData {

    @SerializedName("temp")
    @Expose
    private Double temperature;

    @SerializedName("pressure")
    @Expose
    private Double pressure;

    @SerializedName("humidity")
    @Expose
    private Double humidity;

    @SerializedName("temp_min")
    @Expose
    private Double minTemperature;

    @SerializedName("temp_max")
    @Expose
    private Double maxTemperature;

    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;

    @SerializedName("grnd_level")
    @Expose
    private Double groundLevel;

    public Double getTemperature() {
        return temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public Double getGroundLevel() {
        return groundLevel;
    }
}

class Country {

    @SerializedName("country")
    @Expose
    private String countryCode;

    @SerializedName("sunrise")
    @Expose
    private Long sunrise;


    @SerializedName("sunset")
    @Expose
    private Long sunset;

    public String getCountryCode() {
        return countryCode;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }
}

class Rain {

    @SerializedName("1h")
    @Expose
    private Double volumeForLast1Hour;

    @SerializedName("3h")
    @Expose
    private Double volumeForLast3Hour;

    public Double getVolumeForLast1Hour() {
        return volumeForLast1Hour;
    }

    public Double getVolumeForLast3Hour() {
        return volumeForLast3Hour;
    }
}

class Snow {
    @SerializedName("1h")
    @Expose
    private Double volumeForLast1Hour;

    @SerializedName("3h")
    @Expose
    private Double volumeForLast3Hour;

    public Double getVolumeForLast1Hour() {
        return volumeForLast1Hour;
    }

    public Double getVolumeForLast3Hour() {
        return volumeForLast3Hour;
    }
}


