//package com.ferchoo.conversorjavafx.service;
//
//import com.ferchoo.conversorjavafx.model.CryptoResponse;
//import com.ferchoo.conversorjavafx.model.ExchangeResponse;
//import com.ferchoo.conversorjavafx.util.Config;
//import com.google.gson.Gson;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.io.ObjectInputFilter;
//
//public class ApiClient {
//    private static final String API_KEY = Config.getApiKey();
//    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
//    private final Gson gson = new Gson();
//
//    public ExchangeResponse getExchangeRates(String baseCurrency) {
//        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;
//        try {
//            return executeRequest(url, ExchangeResponse.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public CryptoResponse getCryptoRate(String crypto, String targetCurrency) throws IOException {
//        String url = BASE_URL + API_KEY + "/pair/" + crypto + "/" + targetCurrency;
//        return executeRequest(url, CryptoResponse.class);
//    }
//
//    private <T> T executeRequest(String url, Class<T> responseClass) throws IOException {
//        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpGet request = new HttpGet(url);
//            try (CloseableHttpResponse response = httpClient.execute(request)) {
//                String jsonResponse = EntityUtils.toString(response.getEntity());
//                return gson.fromJson(jsonResponse, responseClass);
//            }
//        }
//    }
//}

package com.ferchoo.conversorjavafx.service;

import com.ferchoo.conversorjavafx.model.ExchangeResponse;
import com.ferchoo.conversorjavafx.model.CryptoResponse;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class ApiClient {
    private static final String API_KEY = "a5bb8a0eb0f7c839d51e9a0e";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final Gson gson = new Gson();

    public ExchangeResponse getExchangeRates(String baseCurrency) throws IOException {
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;
        return executeRequest(url, ExchangeResponse.class);
    }

    public CryptoResponse getCryptoRate(String crypto, String targetCurrency) throws IOException {
        String url = BASE_URL + API_KEY + "/pair/" + crypto + "/" + targetCurrency;
        return executeRequest(url, CryptoResponse.class);
    }

    private <T> T executeRequest(String url, Class<T> responseClass) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                return gson.fromJson(jsonResponse, responseClass);
            }
        }
    }
}