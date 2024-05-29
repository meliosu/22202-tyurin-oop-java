package org.nsu.oop.task3.controller.events;

import org.nsu.oop.task3.game.State;

public class GameOverEvent extends GameEvent {
    private final State.Player winningPlayer;

    public GameOverEvent(State.Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public State.Player getWinningPlayer() {
        return winningPlayer;
    }
}
