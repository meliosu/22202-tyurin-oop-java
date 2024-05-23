package org.nsu.oop.task4.factory.storage;

import java.util.ArrayList;
import java.util.List;

public class Storage <T> {
    private final int capacity;
    private final List<T> parts;

    public Storage(int capacity) {
        this.capacity = capacity;
        parts = new ArrayList<>();
    }

    public synchronized void loadPart(T part) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        parts.add(part);
        notifyAll();
    }

    public synchronized T takePart() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        T part = parts.remove(0);
        notifyAll();
        return part;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return parts.size();
    }

    public boolean isEmpty() {
        return parts.isEmpty();
    }

    public boolean isFull() {
        return parts.size() == capacity;
    }
}
