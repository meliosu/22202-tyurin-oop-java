package org.nsu.oop.task4.factory.storage;

import org.nsu.oop.task4.controller.EventStockChange;
import org.nsu.oop.task4.controller.FactoryEvent;
import org.nsu.oop.task4.pubsub.Publisher;
import org.nsu.oop.task4.pubsub.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Storage <T> implements Publisher<EventStockChange> {
    private final int capacity;
    private int count = 0;
    private final List<T> parts;
    private final Class<?> storedType;
    private Subscriber<FactoryEvent> subscriber = null;

    public Storage(int capacity, Class<?> storedType) {
        this.capacity = capacity;
        this.storedType = storedType;
        this.parts = new ArrayList<>();
    }

    public void addSubscriber(Subscriber<FactoryEvent> subscriber) {
        this.subscriber = subscriber;
    }

    public synchronized void load(T part) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        parts.add(part);
        count++;

        if (subscriber != null) publishChange();

        notifyAll();
    }

    public synchronized T unload() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        T part = parts.remove(0);

        if (subscriber != null) publishChange();

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
        subscriber.onEvent(event);
    }

    public Class<?> getStoredType() {
        return storedType;
    }
}
