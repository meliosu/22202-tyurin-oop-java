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

        System.out.println("hello??");

        this.out = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("HUH??");

        this.in = new ObjectInputStream(socket.getInputStream());
        System.out.println("bred");
    }

    public void sendEvent(Event event) {
        try {
            out.writeObject(event);
        } catch (IOException e) {
            System.err.println("sendEvent: " + e.getMessage());
        }
    }

    public Event recvEvent() {
        Event event = null;
        try {
            event = (Event) in.readObject();
        } catch (IOException e) {
            System.err.println("recvEvent: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return event;
    }
}
