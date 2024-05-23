package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.storage.Storage;

public abstract class Producer extends Thread {
    private int sleepMs;
    private final Storage<CarPart> storage;
    protected int serialNumber = 0;

    public Producer(Storage<CarPart> storage, int sleepMs) {
        this.sleepMs = sleepMs;
        this.storage = storage;
    }

    public int getSpeed() {
        return sleepMs;
    }

    public void changeSpeed(int newSleepMs) {
        sleepMs = newSleepMs;
    }

    @Override
    public void run() {
        while (true) {
            CarPart part;

            try {
                Thread.sleep(sleepMs);

                part = producePart();
                storage.loadPart(part);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public abstract CarPart producePart();
}
