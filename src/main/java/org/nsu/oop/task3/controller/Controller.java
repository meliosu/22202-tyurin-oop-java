package org.nsu.oop.task3.controller;

import org.nsu.oop.task3.controller.events.*;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Player;
import org.nsu.oop.task3.util.Position;
import org.nsu.oop.task3.game.State;
import org.nsu.oop.task3.game.exceptions.IllegalMoveException;
import org.nsu.oop.task3.game.exceptions.IllegalWallException;
import org.nsu.oop.task3.ui.View;
import org.nsu.oop.task3.ui.game.Wall;

import java.util.ArrayList;

public class Controller implements Subscriber<GameEvent> {
    private final State state;
    private final View view;

    public Controller(State state, View view) {
        this.state = state;
        this.view = view;
    }

    @Override
    public void handleEvent(GameEvent event) {
        if (event instanceof MoveEvent) {
            handleMoveEvent((MoveEvent) event);
        } else if (event instanceof WallPlacementEvent) {
            handleWallPlacementEvent((WallPlacementEvent) event);
        } else if (event instanceof StartGameEvent) {
            handleStartGameEvent();
        } else if (event instanceof BackToMenuEvent) {
            handleBackToMenuEvent();
        }
    }

    private void handleMoveEvent(MoveEvent event) {
        try {
            Player currentPlayer = state.getCurrentPlayer();
            state.move(event.position);
            view.movePlayer(event.position, currentPlayer);
            highlightLegalMoves();
        } catch (IllegalMoveException ignored) {}

        if (state.winningPlayer() != null) {
            view.gameOver(state.winningPlayer());
        }
    }

    private void handleWallPlacementEvent(WallPlacementEvent event) {
        int wallCount = state.getCurrentPlayerWallCount();
        Player player = state.getCurrentPlayer();

        try {
            state.placeWall(event.wallType, event.wallPosition.x, event.wallPosition.y);
            view.placeWall(new Wall(event.wallPosition, event.wallType));
            view.updateWallCount(player, wallCount - 1);
            highlightLegalMoves();
        } catch (IllegalWallException e) {
            System.err.println("unable to place wall: " + e.getMessage());
        }
    }

    private void handleStartGameEvent() {
        state.reset();
        view.reset();
        view.showMainView();
        highlightLegalMoves();
    }

    private void handleBackToMenuEvent() {
        view.showMenu();
    }

    private void highlightLegalMoves() {
        ArrayList<Position> legalMoves = state.legalMoves();
        view.highlightMoves(legalMoves);
    }
}
