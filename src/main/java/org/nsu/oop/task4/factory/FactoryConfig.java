package org.nsu.oop.task4.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class FactoryConfig {
    public final int engineStorageSize = 1000;
    public final int trunkStorageSize = 1000;
    public final int accessoryStorageSize = 1000;
    public final int carStorageSize = 1000;
    public final int accessoryProducers = 10;
    public final int assemblyWorkers = 8;
    public final int carDealers = 5;
    public final boolean enableLogging = false;

    public FactoryConfig(String name) {
        InputStream configFile = getClass().getResourceAsStream(name);
        Properties properties = new Properties();

        try {
            properties.load(configFile);
        } catch (IOException e) {
            System.err.println("error loading config file");
        }

        for (Field field : getClass().getFields()) {
            String fieldName = field.getName();
            if (properties.contains(fieldName)) {
                // allow booleans
                int value = Integer.parseInt(properties.getProperty(fieldName));

                try {
                    field.set(this, value);
                } catch (IllegalAccessException ignored) {
                    // change
                    System.err.println("Illegal access!");
                }
            }
        }
    }

    public FactoryConfig() {
        this("factory.cfg");
    }
}
