package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.controller.events.ClientRequestEvent;
import org.nsu.oop.task5.controller.events.GameEvent;
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

    public Connection acceptConnection() throws IOException {
        Socket clientSocket = socket.accept();
        Connection connection = new Connection(clientSocket);
        connections.add(connection);
        return connection;
    }

    public void broadcastEvent(GameEvent event) throws IOException {
        for (Connection conn : connections) {
            conn.out.writeObject(event);
        }
    }
}
