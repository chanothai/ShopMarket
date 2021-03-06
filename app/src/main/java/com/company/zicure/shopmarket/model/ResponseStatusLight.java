package com.company.zicure.shopmarket.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class ResponseStatusLight {
    @SerializedName("status")
    private String status;

    @SerializedName("led")
    private String led;

    @SerializedName("id")
    private String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLed() {
        return led;
    }

    public void setLed(String led) {
        this.led = led;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
