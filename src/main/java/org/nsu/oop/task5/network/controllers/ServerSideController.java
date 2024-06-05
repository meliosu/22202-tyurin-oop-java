package org.nsu.oop.task5.network.controllers;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.events.online.*;
import org.nsu.oop.task5.game.State;
import org.nsu.oop.task5.game.exceptions.IllegalMoveException;
import org.nsu.oop.task5.game.exceptions.IllegalWallException;
import org.nsu.oop.task5.network.observe.Observer;
import org.nsu.oop.task5.network.server.Server;
import org.nsu.oop.task5.network.server.ServerConnectionHandler;
import org.nsu.oop.task5.network.util.Connection;
import org.nsu.oop.task5.util.Player;

import java.util.HashMap;
import java.util.Map;

public class ServerSideController extends Observer {
    private final State state;
    private final Server server;

    private final Map<Connection, Player> playerMap = new HashMap<>();
    private final Map<Connection, Boolean> ready = new HashMap<>();

    public ServerSideController(State state, Server server) {
        this.state = state;
        this.server = server;

        addHandlers();
        server.addObserver(this);
    }

    public void start() {
        Connection firstClient = server.acceptConnection();
        Connection secondClient = server.acceptConnection();

        playerMap.put(firstClient, Player.First);
        playerMap.put(secondClient, Player.Second);

        ServerConnectionHandler firstClientHandler = new ServerConnectionHandler(firstClient);
        ServerConnectionHandler secondClientHandler = new ServerConnectionHandler(secondClient);

        firstClientHandler.addObserver(this);
        secondClientHandler.addObserver(this);

        firstClientHandler.start();
        secondClientHandler.start();

        firstClientHandler.join();
        secondClientHandler.join();
    }

    private void addHandlers() {
        addHandler(ClientDisconnectedEvent.class, e -> {
            ClientDisconnectedEvent event = (ClientDisconnectedEvent) e;

            Player player = playerMap.get(event.connection);
            playerMap.remove(event.connection);

            server.broadcastEvent(new GameOverNotify(player == Player.First ? Player.Second : Player.First));

            state.reset();
            ready.clear();

            Connection newConnection = server.acceptConnection();
            playerMap.put(newConnection, player);

            ServerConnectionHandler newHandler = new ServerConnectionHandler(newConnection);
            newHandler.addObserver(this);
            newHandler.start();
        });

        addHandler(ClientRequest.class, e -> {
            ClientRequest event = (ClientRequest) e;
            GameEvent gameEvent = event.gameEvent;
            Player sourcePlayer = playerMap.get(event.clientConnection);

            if ((gameEvent instanceof MoveRequest || gameEvent instanceof WallPlacementRequest)
                    && sourcePlayer != state.getCurrentPlayer()) {
                return;
            }

            if (gameEvent instanceof StartGameRequest) {
                if (!ready.containsKey(event.clientConnection)) {
                    ready.put(event.clientConnection, true);
                }
            }

            handleEvent(event.gameEvent);
        });

        addHandler(MoveRequest.class, e -> {
            MoveRequest event = (MoveRequest) e;
            Player currentPlayer = state.getCurrentPlayer();

            try {
                state.move(state.getCurrentPlayer(), event.position);
            } catch (IllegalMoveException exception) {
                System.out.println("illegal move");
                return;
            }

            Player winningPlayer = state.winningPlayer();
            if (winningPlayer != null) {
                server.broadcastEvent(new GameOverNotify(winningPlayer));
                state.reset();
            } else {
                server.broadcastEvent(new MoveNotify(event.position, currentPlayer));
            }
        });

        addHandler(WallPlacementRequest.class, e -> {
            WallPlacementRequest event = (WallPlacementRequest) e;

            Player currentPlayer = state.getCurrentPlayer();
            int wallCount = state.getCurrentPlayerWallCount();

            try {
                state.placeWall(event.wallType, event.wallPosition.x, event.wallPosition.y);
                server.broadcastEvent(new WallPlacementNotify(
                        event.wallPosition, event.wallType, wallCount - 1, currentPlayer));
            } catch (IllegalWallException exception) {
                System.out.println("illegal wall");
            }
        });

        addHandler(StartGameRequest.class, e -> {
            if (ready.size() == 2) {
                server.broadcastEvent(new StartGameNotify());
                ready.clear();
            }
        });
    }
}
