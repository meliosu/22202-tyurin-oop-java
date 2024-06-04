package org.nsu.oop.task5.network.controllers;

import org.nsu.oop.task5.controller.events.*;
import org.nsu.oop.task5.game.State;
import org.nsu.oop.task5.game.exceptions.IllegalMoveException;
import org.nsu.oop.task5.game.exceptions.IllegalWallException;
import org.nsu.oop.task5.network.pubsub2.Subscriber;
import org.nsu.oop.task5.network.server.Connection;
import org.nsu.oop.task5.network.server.ConnectionHandler;
import org.nsu.oop.task5.network.server.Server;
import org.nsu.oop.task5.util.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerSideController extends Subscriber {
    private final State state;
    private final Server server;

    private final Map<Connection, Player> playerMap = new HashMap<>();
    private final Map<Connection, Boolean> ready = new HashMap<>();

    public ServerSideController(State state, Server server) {
        this.state = state;
        this.server = server;

        server.addSubscriber(this);

        addHandlers();
    }

    public void start() throws IOException {
        Connection firstClient = server.acceptConnection();
        Connection secondClient = server.acceptConnection();

        playerMap.put(firstClient, Player.First);
        playerMap.put(secondClient, Player.Second);

        ConnectionHandler firstClientHandler = new ConnectionHandler(firstClient);
        ConnectionHandler secondClientHandler = new ConnectionHandler(secondClient);

        firstClientHandler.addSubscriber(this);
        secondClientHandler.addSubscriber(this);

        firstClientHandler.start();
        secondClientHandler.start();

        firstClientHandler.join();
        secondClientHandler.join();
    }

    private void addHandlers() {
        addHandler(ClientRequestEvent.class, e -> {
            ClientRequestEvent event = (ClientRequestEvent) e;
            GameEvent gameEvent = event.gameEvent;

            // do nothing if request is from wrong player
            if ((gameEvent instanceof MoveEventRequest || gameEvent instanceof WallPlacementEventRequest )
                    && playerMap.get(event.clientConnection) != state.getCurrentPlayer()) {
                return;
            }

            if (gameEvent instanceof StartGameRequest) {
                if (!ready.containsKey(event.clientConnection)) {
                    ready.put(event.clientConnection, true);
                }
            }

            handleEvent(event.gameEvent);
        });

        addHandler(MoveEventRequest.class, e -> {
            System.out.println("handling move request");

            MoveEventRequest event = (MoveEventRequest) e;

            Player currentPlayer = state.getCurrentPlayer();

            try {
                state.move(state.getCurrentPlayer(), event.position);

                if (state.winningPlayer() != null) {
                    server.broadcastEvent(new GameOverEvent(state.winningPlayer()));
                    state.reset();
                } else {
                    server.broadcastEvent(new MoveNotify(event.position, currentPlayer));
                }
            } catch (IOException exception) {
                System.out.println("io: " + exception.getMessage());
            } catch (IllegalMoveException ignored) {
                System.out.println("illegal");
            } // move is illegal, don't confirm
        });

        addHandler(WallPlacementEventRequest.class, e -> {
            WallPlacementEventRequest event = (WallPlacementEventRequest) e;

            Player currentPlayer = state.getCurrentPlayer();
            int wallCount = state.getCurrentPlayerWallCount();

            try {
                state.placeWall(event.wallType, event.wallPosition.x, event.wallPosition.y);
                server.broadcastEvent(new WallPlacementNotify(
                        event.wallPosition, event.wallType, wallCount - 1, currentPlayer));
            } catch (IllegalWallException | IOException ignored) {} // move is illegal, don't confirm
        });

        addHandler(StartGameRequest.class, e -> {
            if (ready.size() == 2) {
                try {
                    ready.clear();
                    server.broadcastEvent(new StartGameNotify());
                } catch (IOException ignored) {}
            }
        });
    }
}
