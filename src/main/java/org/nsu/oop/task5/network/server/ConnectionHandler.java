package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.controller.events.ClientRequestEvent;
import org.nsu.oop.task5.controller.events.GameEvent;
import org.nsu.oop.task5.network.pubsub2.Publisher;

import java.io.IOException;

public class ConnectionHandler extends Publisher {
    private final Connection connection;
    private final Thread thread;

    public ConnectionHandler(Connection connection) {
        this.connection = connection;
        this.thread = new Thread(() -> {
            while (true) {
                try {
                    GameEvent event = (GameEvent) connection.in.readObject();
                    publishEvent(new ClientRequestEvent(event, connection));
                } catch (IOException e) {
                    System.err.println("e: " + e.getMessage());
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("class not found!");
                    System.exit(1);
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("interrupt");
        }
    }
}
