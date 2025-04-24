package com.ferchoo.conversorjavafx.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    private final LocalDateTime date;
    private final StringProperty fromCurrency;
    private final StringProperty toCurrency;
    private final SimpleDoubleProperty amount;
    private final SimpleDoubleProperty result;

    public Conversion(LocalDateTime date, String from, String to, double amount, double result) {
        this.date = date;
        this.fromCurrency = new SimpleStringProperty(from);
        this.toCurrency = new SimpleStringProperty(to);
        this.amount = new SimpleDoubleProperty(amount);
        this.result = new SimpleDoubleProperty(result);
    }

    public StringProperty dateProperty() {
        return new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")));
    }

    public StringProperty fromCurrencyProperty() {
        return fromCurrency;
    }

    public StringProperty toCurrencyProperty() {
        return toCurrency;
    }

    public StringProperty amountProperty() {
        return new SimpleStringProperty(String.format("%.2f", amount.get()));
    }

    public StringProperty resultProperty() {
        return new SimpleStringProperty(String.format("%.2f", result.get()));
    }

    //CSV export

    public String toCSV() {
        return String.format("%s, %s, %s, %.2f, %.2f",
                date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                fromCurrency.get(),
                toCurrency.get(),
                amount.get(),
                result.get());
    }
}
