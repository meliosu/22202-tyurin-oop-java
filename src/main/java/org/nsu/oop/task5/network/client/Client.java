package org.nsu.oop.task5.network.client;

import org.nsu.oop.task5.network.pubsub2.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final Socket socket;

    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public Client(String addr, int port) throws IOException {
        this.socket = new Socket(addr, port);
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void sendEvent(Event event) {
        try {
            out.writeObject(event);
        } catch (IOException e) {
            System.exit(0);
        }
    }

    public Event recvEvent() {
        Event event = null;
        try {
            event = (Event) in.readObject();
        } catch (IOException ignored) {
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return event;
    }
}
