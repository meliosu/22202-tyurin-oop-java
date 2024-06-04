package org.nsu.oop.task5.controller.events;

import java.net.InetAddress;
import java.net.Socket;

public class ClientRequestEvent extends GameEvent {
    public final GameEvent gameEvent;
    public final Socket clientSocket;

    public ClientRequestEvent(GameEvent event, Socket socket) {
        this.gameEvent = event;
        this.clientSocket = socket;
    }
}
