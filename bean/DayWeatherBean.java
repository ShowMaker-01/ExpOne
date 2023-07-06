package com.example.myapplication3.bean;

import com.google.gson.annotations.SerializedName;

public class DayWeatherBean {
    @SerializedName("date")
    private String date;

    @SerializedName("wea")
    private String wea;

    @SerializedName("tem_day")
    private String tem_day;

    @SerializedName("tem_night")
    private String tem_night;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getTem_day() {
        return tem_day;
    }

    public void setTem_day(String tem_day) {
        this.tem_day = tem_day;
    }

    public String getTem_night() {
        return tem_night;
    }

    public void setTem_night(String tem_night) {
        this.tem_night = tem_night;
    }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "date='" + date + '\'' +
                ", wea='" + wea + '\'' +
                ", tem_day='" + tem_day + '\'' +
                ", tem_night='" + tem_night + '\'' +
                '}';
    }
}
