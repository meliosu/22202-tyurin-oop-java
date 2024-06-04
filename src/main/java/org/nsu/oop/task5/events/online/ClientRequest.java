package org.nsu.oop.task5.events.online;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.network.util.Connection;

public class ClientRequest extends GameEvent {
    public final GameEvent gameEvent;
    public final Connection clientConnection;

    public ClientRequest(GameEvent event, Connection connection) {
        this.gameEvent = event;
        this.clientConnection = connection;
    }
}
