package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Player;

public class ServerHello extends GameEvent {
    public final Player player;

    public ServerHello(Player player) {
        this.player = player;
    }
}
