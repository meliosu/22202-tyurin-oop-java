package org.nsu.oop.task5.network.client;

import org.nsu.oop.task5.network.pubsub2.Event;
import org.nsu.oop.task5.network.server.Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private final InetSocketAddress serverAddress;

    private Connection connection;

    public Client(String addr, int port) {
        this.serverAddress = new InetSocketAddress(addr, port);
    }

    public void connect() throws IOException {
        Socket socket = new Socket(serverAddress.getHostName(), serverAddress.getPort());
        this.connection = new Connection(socket);
    }

    public void sendEvent(Event event) {
        try {
            connection.out.writeObject(event);
        } catch (IOException e) {
            System.err.println("error sending event: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
