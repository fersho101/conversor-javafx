package com.ferchoo.conversorjavafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Currency {
    private final StringProperty code;
    private final StringProperty name;

    public Currency(String code, String name) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty codeProperty() {
        return code;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getCode() {
        return code.get();
    }

    public String getName() {
        return name.get();
    }

    @Override
    public String toString() {
        return code.get() + " - " + name.get();
    }
}
