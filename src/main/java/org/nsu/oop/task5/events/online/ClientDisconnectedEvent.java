package org.nsu.oop.task5.events.online;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.network.util.Connection;

public class ClientDisconnectedEvent extends GameEvent {
    public final Connection connection;

    public ClientDisconnectedEvent(Connection connection) {
        this.connection = connection;
    }
}
