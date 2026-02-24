package com.example.listycity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class City implements Serializable {

    private String city;
    private String province;


    public City(@NonNull final String city, @NonNull final String province) {
        this.city = city;
        this.province = province;
    }

    @NonNull
    public String getCityName() {
        return this.city;
    }

    public void setCityName(@NonNull final String city) {
        this.city = city;
    }

    @NonNull
    public String getProvinceName() {
        return this.province;
    }

    public void setProvinceName(@NonNull final String province) {
        this.province = province;
    }
}
