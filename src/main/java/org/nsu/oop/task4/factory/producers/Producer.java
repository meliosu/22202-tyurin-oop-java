package org.nsu.oop.task4.factory.producers;

import org.nsu.oop.task4.factory.parts.Part;
import org.nsu.oop.task4.factory.storage.Storage;

public abstract class Producer extends Thread {
    private int sleepMs;
    private final Storage<Part> storage;
    protected int serialNumber = 0;

    public Producer(Storage<Part> storage, int sleepMs) {
        this.sleepMs = sleepMs;
        this.storage = storage;
    }

    public int getSpeed() {
        return sleepMs;
    }

    @Override
    public void run() {
        while (true) {
            Part part;

            try {
                Thread.sleep(sleepMs);

                part = producePart();
                storage.load(part);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void setSpeed(int sleepMs) {
        this.sleepMs = sleepMs;
    }

    public abstract Part producePart();
}
