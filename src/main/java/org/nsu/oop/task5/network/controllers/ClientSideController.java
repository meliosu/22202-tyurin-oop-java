package org.nsu.oop.task5.network.controllers;

import org.nsu.oop.task5.events.*;
import org.nsu.oop.task5.events.online.*;
import org.nsu.oop.task5.events.ui.BackToMenuEvent;
import org.nsu.oop.task5.events.ui.StartGameEvent;
import org.nsu.oop.task5.network.client.Client;
import org.nsu.oop.task5.network.client.ClientConnectionHandler;
import org.nsu.oop.task5.network.pubsub2.Subscriber;
import org.nsu.oop.task5.ui.View;
import org.nsu.oop.task5.ui.game.Wall;

import java.io.IOException;

public class ClientSideController extends Subscriber implements org.nsu.oop.task5.pubsub.Subscriber<GameEvent> {
    private final View view;
    private final Client client;

    public ClientSideController(View view, Client client) {
        this.view = view;
        this.client = client;

        view.addSubscriber(this);
        addHandlers();
    }

    public void start() {
        view.start();
    }

    public void connectToServer() throws IOException {
        client.connect();
        ClientConnectionHandler handler = new ClientConnectionHandler(client.getConnection());
        handler.addSubscriber(this);
        handler.start();
    }

    private void addHandlers() {
        addHandler(BackToMenuEvent.class, e -> {
            view.showMenu();
        });

        addHandler(WallPlacementNotify.class, e -> {
            WallPlacementNotify event = (WallPlacementNotify) e;

            view.placeWall(new Wall(event.wallPosition, event.wallType));
            view.updateWallCount(event.player, event.wallCount);
        });

        addHandler(MoveNotify.class, e -> {
            MoveNotify event = (MoveNotify) e;

            view.movePlayer(event.position, event.player);
        });

        addHandler(MoveRequest.class, e -> {
            client.sendEvent(e);
        });

        addHandler(WallPlacementRequest.class, e -> {
            client.sendEvent(e);
        });

        addHandler(GameOverNotify.class, e -> {
            GameOverNotify event = (GameOverNotify) e;

            view.gameOver(event.winningPlayer);
        });

        addHandler(StartGameNotify.class, e -> {
            view.reset();
            view.showMainView();
        });

        addHandler(StartGameEvent.class, e -> {
            if (client.getConnection() == null) {
                try {
                    connectToServer();
                } catch (IOException exception) {
                    System.err.println("error connecting to server: " + exception.getMessage());
                    return;
                }
            }

            client.sendEvent(new StartGameRequest());
        });
    }

    @Override
    public void onEvent(GameEvent event) {
        handleEvent(event);
    }
}
