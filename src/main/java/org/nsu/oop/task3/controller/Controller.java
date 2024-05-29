package org.nsu.oop.task3.controller;

import org.nsu.oop.task3.controller.events.*;
import org.nsu.oop.task3.controller.pubsub.Publisher;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;
import org.nsu.oop.task3.game.exceptions.IllegalMoveException;
import org.nsu.oop.task3.game.exceptions.IllegalWallException;
import org.nsu.oop.task3.ui.View;
import org.nsu.oop.task3.ui.game.Wall;

import java.util.ArrayList;

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
        try {
            State.Player currentPlayer = state.getCurrentPlayer();
            state.move(event.position);
            view.movePlayer(event.position, currentPlayer);

            ArrayList<Position> legalMoves = state.legalMoves();
            view.highlightMoves(legalMoves);
        } catch (IllegalMoveException e) {
            System.out.println("illegal move");
        }

        if (state.winningPlayer() != null) {
            view.gameOver(state.winningPlayer());
        }
    }

    private void handleWallPlacementEvent(WallPlacementEvent event) {
        try {
            state.placeWall(event.type, event.position.x, event.position.y);
            view.placeWall(new Wall(event.position, event.type));
        } catch (IllegalWallException ignored) {}
    }

    private void handleStartGameEvent(StartGameEvent event) {
        ArrayList<Position> legalMoves = state.legalMoves();
        System.out.println(legalMoves.size());

        view.highlightMoves(legalMoves);
    }
}
