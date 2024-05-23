package org.nsu.oop.task4.factory.dealer;

import org.nsu.oop.task4.factory.parts.Car;
import org.nsu.oop.task4.factory.storage.Storage;

import java.util.logging.Logger;

public class Dealer extends Thread {
    private final int id;
    private final int sleepMs;
    private final Storage<Car> carStorage;
    private final Logger logger;

    public Dealer(int id, int sleepMs, Storage<Car> carStorage, Logger logger) {
        this.id = id;
        this.sleepMs = sleepMs;
        this.carStorage = carStorage;
        this.logger = logger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepMs);
                Car car = carStorage.unload();

                if (logger != null) {
                    logger.info("Dealer " + id + ": " + car);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
