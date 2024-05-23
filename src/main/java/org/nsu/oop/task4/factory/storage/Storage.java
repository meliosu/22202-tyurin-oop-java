package org.nsu.oop.task4.factory.storage;

import java.util.ArrayList;
import java.util.List;

public class Storage <T> {
    private final int capacity;
    private int count = 0;
    private final List<T> parts;

    public Storage(int capacity) {
        this.capacity = capacity;
        parts = new ArrayList<>();
    }

    public synchronized void load(T part) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        parts.add(part);
        count++;
        notifyAll();
    }

    public synchronized T unload() throws InterruptedException {
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

    public int getTotalCount() {
        return count;
    }
}
