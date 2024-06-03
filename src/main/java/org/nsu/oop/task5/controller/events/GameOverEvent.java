package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Player;

public class GameOverEvent extends GameEvent {
    public final Player winningPlayer;

    public GameOverEvent(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }
}
