package com.example.myapplication3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean {

    @SerializedName("cityid")
    private  String cityId;

    @SerializedName("city")
    private String city;

    @SerializedName("update_time")
    private String update_time;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeatherBeans;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<DayWeatherBean> getDayWeatherBeans() {
        return dayWeatherBeans;
    }

    public void setDayWeatherBeans(List<DayWeatherBean> dayWeatherBeans) {
        this.dayWeatherBeans = dayWeatherBeans;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", update_time='" + update_time + '\'' +
                ", dayWeatherBeans=" + dayWeatherBeans +
                '}';
    }
}
