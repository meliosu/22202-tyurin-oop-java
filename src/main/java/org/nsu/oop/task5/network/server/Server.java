package org.nsu.oop.task5.network.server;

import com.sun.tools.javac.util.Pair;
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
    private final ArrayList<Pair<Socket, Connection>> connections;

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
        this.connections = new ArrayList<>();
    }

    public void acceptConnections(int numConns) throws IOException {
        while (connections.size() < numConns) {
            Socket sock = socket.accept();
            Connection conn = new Connection(sock);

            connections.add(new Pair<>(sock, conn));

            new Thread(() -> {
                while (true) {
                    GameEvent event = null; // will not be null after try-catch ?

                    try {
                        event = (GameEvent) conn.in.readObject();
                    } catch (IOException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.err.println("class not found!");
                        System.exit(0);
                    }

                    publishEvent(new ClientRequestEvent(event, sock.getInetAddress()));
                }
            });
        }
    }

    public InetAddress getClient(int idx) {
        return connections.get(idx).fst.getInetAddress();
    }
}
