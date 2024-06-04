package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.controller.events.ClientRequestEvent;
import org.nsu.oop.task5.controller.events.GameEvent;
import org.nsu.oop.task5.network.pubsub2.Publisher;

import java.io.IOException;
import java.net.InetAddress;
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

    public void acceptConnections(int numConns) throws IOException {
        while (connections.size() < numConns) {
            Socket sock = socket.accept();

            System.out.println("connected!");

            Connection conn = new Connection(sock);

            connections.add(conn);

            new Thread(() -> {
                while (true) {
                    GameEvent event = null; // will not be null after try-catch ?

                    try {
                        event = (GameEvent) conn.in.readObject();
                    } catch (IOException e) {
                        System.err.println("worker in server: " + e.getMessage());
                    } catch (ClassNotFoundException e) {
                        System.err.println("class not found!");
                        System.exit(1);
                    }

                    publishEvent(new ClientRequestEvent(event, sock.getInetAddress()));
                }
            }).start();
        }
    }

    public void broadcastEvent(GameEvent event) throws IOException {
        for (Connection conn : connections) {
            conn.out.writeObject(event);
        }
    }

    public InetAddress getClient(int idx) {
        return connections.get(idx).socket.getInetAddress();
    }
}
