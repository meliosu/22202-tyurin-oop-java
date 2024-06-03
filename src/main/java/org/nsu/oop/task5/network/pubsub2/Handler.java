package org.nsu.oop.task5.network.pubsub2;

import java.io.IOException;

public interface Handler {
    void handle(Event event);
}
