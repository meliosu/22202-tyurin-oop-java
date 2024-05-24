package org.nsu.oop.task4.factory.storage;

import org.nsu.oop.task4.controller.Controller;
import org.nsu.oop.task4.controller.EventStockChange;
import org.nsu.oop.task4.pubsub.Publisher;

import java.util.ArrayList;
import java.util.List;

public class Storage <T> implements Publisher<EventStockChange> {
    private final int capacity;
    private int count = 0;
    private final List<T> parts;
    private final Class<?> storedType;
    private final Controller controller = Controller.getInstance();

    public Storage(int capacity, Class<?> storedType) {
        this.capacity = capacity;
        this.storedType = storedType;
        this.parts = new ArrayList<>();
    }

    public synchronized void load(T part) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        parts.add(part);
        count++;

        publishChange();
        notifyAll();
    }

    public synchronized T unload() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        T part = parts.remove(0);

        publishChange();
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

    private synchronized void publishChange() {
        publish(new EventStockChange(storedType, getSize(), getTotalCount()));
    }

    @Override
    public void publish(EventStockChange event) {
        controller.onEvent(event);
    }

    public Class<?> getStoredType() {
        return storedType;
    }
}
