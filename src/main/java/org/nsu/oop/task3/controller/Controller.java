package org.nsu.oop.task3.controller;

import org.nsu.oop.task3.controller.events.*;
import org.nsu.oop.task3.controller.pubsub.Publisher;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.State;
import org.nsu.oop.task3.ui.View;

public class Controller implements Publisher<GameEvent>, Subscriber<GameEvent> {
    private final State state;
    private final View view;

    public Controller(State state, View view) {
        this.state = state;
        this.view = view;
    }

    @Override
    public void publishEvent(GameEvent event) {
        if (event instanceof GameOverEvent) {
            view.
        }
    }

    @Override
    public void handleEvent(GameEvent event) {
        if (event instanceof MoveEvent) {
            handleMoveEvent((MoveEvent) event);
        } else if (event instanceof WallPlacementEvent) {
            handleWallPlacementEvent((WallPlacementEvent) event);
        } else if (event instanceof StartGameEvent) {
            handleStartGameEvent((StartGameEvent) event);
        }
    }

    private void handleMoveEvent(MoveEvent event) {

    }

    private void handleWallPlacementEvent(WallPlacementEvent event) {

    }

    private void handleStartGameEvent(StartGameEvent event) {

    }
}
