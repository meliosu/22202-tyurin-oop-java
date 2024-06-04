package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.network.pubsub2.Event;
import org.nsu.oop.task5.network.pubsub2.Publisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Publisher {
    private final ServerSocket socket;
    private final ArrayList<Connection> connections;

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
        this.connections = new ArrayList<>();
    }

    public Connection acceptConnection() {
        while (true) {
            try {
                Socket clientSocket = socket.accept();
                Connection connection = new Connection(clientSocket);
                connections.add(connection);
                return connection;
            } catch (IOException ignored) {}
        }
    }

    public void broadcastEvent(Event event) {
        for (Connection conn : connections) {
            try {
                conn.out.writeObject(event);
            } catch (IOException ignored) {}
        }
    }
}
