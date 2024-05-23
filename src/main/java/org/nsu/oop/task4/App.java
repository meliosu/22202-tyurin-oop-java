package org.nsu.oop.task4;

import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.FactoryConfig;

public class App {
    public static void main(String[] args) {
        FactoryConfig factoryConfig = new FactoryConfig();
        Factory factory = new Factory(factoryConfig);
        factory.launch();
    }
}
