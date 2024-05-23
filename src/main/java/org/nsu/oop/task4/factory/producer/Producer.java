package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.storage.CarPartStorage;

public abstract class Producer extends Thread {
    protected int sleepMs;
    protected CarPartStorage storage;

    public Producer(CarPartStorage storage, int sleepMs) {
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
                // simulate working on a part
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
