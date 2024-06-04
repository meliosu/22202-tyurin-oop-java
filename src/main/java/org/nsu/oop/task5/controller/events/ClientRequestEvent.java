package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.network.server.Connection;

import java.net.InetAddress;
import java.net.Socket;

public class ClientRequestEvent extends GameEvent {
    public final GameEvent gameEvent;
    public final Connection clientConnection;

    public ClientRequestEvent(GameEvent event, Connection connection) {
        this.gameEvent = event;
        this.clientConnection = connection;
    }
}
