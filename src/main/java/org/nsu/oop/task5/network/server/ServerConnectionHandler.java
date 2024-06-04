package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.events.online.ClientDisconnectedEvent;
import org.nsu.oop.task5.events.online.ClientRequest;
import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.network.pubsub2.Publisher;

import java.io.IOException;

public class ServerConnectionHandler extends Publisher {
    private final Thread thread;

    public ServerConnectionHandler(Connection connection) {
        this.thread = new Thread(() -> {
            while (true) {
                try {
                    GameEvent event = (GameEvent) connection.in.readObject();
                    publishEvent(new ClientRequest(event, connection));
                } catch (IOException e) {
                    publishEvent(new ClientDisconnectedEvent(connection));
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
