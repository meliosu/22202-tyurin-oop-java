package org.nsu.oop.task5.controller.events;

import java.net.InetAddress;

public class ClientRequestEvent extends GameEvent {
    public final GameEvent gameEvent;
    public final InetAddress clientAddress;

    public ClientRequestEvent(GameEvent event, InetAddress address) {
        this.gameEvent = event;
        this.clientAddress = address;
    }
}
