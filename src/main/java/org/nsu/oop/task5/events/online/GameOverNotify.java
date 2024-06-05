package org.nsu.oop.task5.events.online;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.util.Player;

public class GameOverNotify extends GameEvent {
    public final Player winningPlayer;

    public GameOverNotify(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }
}
