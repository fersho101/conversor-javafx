package com.ferchoo.conversorjavafx.model;

import com.google.gson.annotations.SerializedName;

public class CryptoResponse {
    @SerializedName("conversion_rate")
    private double rate;

    @SerializedName("base_code")
    private String base;

    @SerializedName("target_code")
    private String target;

    // Getters
    public double getRate() {
        return rate;
    }
}
