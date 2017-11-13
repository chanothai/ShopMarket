package com.company.zicure.shopmarket.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class ResponseStatusLight {
    @SerializedName("status")
    private String status;

    @SerializedName("led1")
    private String led1;

    @SerializedName("led2")
    private String led2;

    @SerializedName("led3")
    private String led3;

    @SerializedName("led4")
    private String led4;

    public String getLed1() {
        return led1;
    }

    public void setLed1(String led1) {
        this.led1 = led1;
    }

    public String getLed2() {
        return led2;
    }

    public void setLed2(String led2) {
        this.led2 = led2;
    }

    public String getLed3() {
        return led3;
    }

    public void setLed3(String led3) {
        this.led3 = led3;
    }

    public String getLed4() {
        return led4;
    }

    public void setLed4(String led4) {
        this.led4 = led4;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
