package com.ferchoo.conversorjavafx.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ExchangeResponse {
    @SerializedName("result")
    private String result;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public boolean isSuccess() {
        return "success".equalsIgnoreCase(result);
    }
}
