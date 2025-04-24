package com.ferchoo.conversorjavafx.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {
    private static final JsonParser parser = new JsonParser();

    public static double getConversionRate(String json, String targetCurrency) {
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
        return rates.get(targetCurrency).getAsDouble();
    }

    public static double getCryptoRate(String json) {
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        return jsonObject.get("conversion_rate").getAsDouble();
    }
}
