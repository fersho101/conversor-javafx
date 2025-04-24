package com.ferchoo.conversorjavafx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try(InputStream input = Config.class.getResourceAsStream("/com/ferchoo/conversorjavafx/config.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.err.println("Error cargando configuracion: " + e.getMessage());
        }
    }

    public static String getApiKey() {
        return props.getProperty("api.key", "");
    }
}
