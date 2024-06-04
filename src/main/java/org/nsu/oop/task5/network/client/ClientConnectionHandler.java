package org.nsu.oop.task5.network.client;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.network.pubsub2.Publisher;
import org.nsu.oop.task5.network.server.Connection;

import java.io.IOException;

public class ClientConnectionHandler extends Publisher {
    private final Thread thread;

    public ClientConnectionHandler(Connection connection) {
        this.thread = new Thread(() -> {
            while (true) {
                try {
                    GameEvent event = (GameEvent) connection.in.readObject();
                    publishEvent(event);
                } catch (IOException e) {
                    System.err.println("server error: " + e.getMessage());
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("class not found");
                    System.exit(1);
                }
            }
        });
    }

    public void start() {
        thread.start();
    }
}
