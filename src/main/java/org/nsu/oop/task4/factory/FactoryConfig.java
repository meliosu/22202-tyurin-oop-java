package org.nsu.oop.task4.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class FactoryConfig {
    public int engineStorageSize = 1000;
    public int trunkStorageSize = 1000;
    public int accessoryStorageSize = 1000;
    public int carStorageSize = 1000;
    public int accessoryProducers = 10;
    public int assemblyWorkers = 8;
    public int carDealers = 5;
    public boolean enableLogging = false;

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
            if (properties.containsKey(fieldName)) {
                Object value;

                if (fieldName.equals("enableLogging")) {
                    value = Boolean.parseBoolean(properties.getProperty(fieldName));
                } else {
                    value = Integer.parseInt(properties.getProperty(fieldName));
                }

                try {
                    field.set(this, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("error in factory config" + e.getMessage());
                }
            }
        }
    }

    public FactoryConfig() {
        this("/carFactory.cfg");
    }
}
