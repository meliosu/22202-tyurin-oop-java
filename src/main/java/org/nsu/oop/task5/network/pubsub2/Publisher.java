package org.nsu.oop.task5.network.pubsub2;

import java.util.ArrayList;
import java.util.List;

public abstract class Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void publishEvent(Event event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.handleEvent(event);
        }
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}
