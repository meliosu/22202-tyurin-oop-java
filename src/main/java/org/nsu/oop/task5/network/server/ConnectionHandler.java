package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.network.pubsub2.Event;
import org.nsu.oop.task5.network.pubsub2.Publisher;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler extends Publisher {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        new Thread(() -> {
            while (true) {
                Event event;

                try {
                    event = (Event) in.readObject();
                } catch (IOException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("class not found");
                    break;
                }

                publishEvent(event);
            }
        });
    }
}
