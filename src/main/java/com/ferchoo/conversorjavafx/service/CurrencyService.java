package com.ferchoo.conversorjavafx.service;

import com.ferchoo.conversorjavafx.model.Conversion;
import com.ferchoo.conversorjavafx.model.Currency;
import com.ferchoo.conversorjavafx.util.JsonUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {
    private static final String API_KEY = "a5bb8a0eb0f7c839d51e9a0e";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final List<Conversion> history = new ArrayList<>();

    public List<Currency> getAllCurrencies() {
        return List.of(
                new Currency("USD", "Dolar Estadounidense"),
                new Currency("EUR", "Euro"),
                new Currency("GBP", "Libra Esterlina"),
                new Currency("JPY", "Yen Japones"),
                new Currency("MXN", "Peso Mexicano"),
                new Currency("BTC", "Bitcoin"),
                new Currency("ETH", "Ethereum")
        );
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        if (isCrypto(fromCurrency) || isCrypto(toCurrency)) {
            try {
                return convertCrypto(fromCurrency, toCurrency, amount);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String url = BASE_URL + API_KEY + "/latest/" + fromCurrency;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponce = EntityUtils.toString(response.getEntity());
                double rate = JsonUtils.getConversionRate(jsonResponce, toCurrency);

                double result = amount * rate;
                saveToHistory(fromCurrency, toCurrency, amount, result);

                return result;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private double convertCrypto(String from, String to, double amount) throws IOException {
        String url = BASE_URL + API_KEY + "/pair/" + from + "/" + to;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                double rate = JsonUtils.getCryptoRate(jsonResponse);

                double result = amount * rate;
                saveToHistory(from, to, amount, result);

                return result;
            }
        }
    }

    private boolean isCrypto(String currency) {
        return currency.equals("BTC") || currency.equals("ETH");
    }

    private void saveToHistory(String from, String to, double amount, double result) {
        history.add(new Conversion(
                LocalDateTime.now(),
                from,
                to,
                amount,
                result
        ));
    }

    public List<Conversion> getConversionHistory() {
        return new ArrayList<>(history);
    }

}
