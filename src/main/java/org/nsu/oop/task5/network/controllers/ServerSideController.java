package org.nsu.oop.task5.network.controllers;

import com.sun.tools.javac.util.Pair;
import org.nsu.oop.task5.controller.events.*;
import org.nsu.oop.task5.game.State;
import org.nsu.oop.task5.game.exceptions.IllegalMoveException;
import org.nsu.oop.task5.game.exceptions.IllegalWallException;
import org.nsu.oop.task5.network.pubsub2.Event;
import org.nsu.oop.task5.network.pubsub2.Subscriber;
import org.nsu.oop.task5.network.server.Server;
import org.nsu.oop.task5.util.Player;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerSideController extends Subscriber {
    private final State state;
    private final Server server;

    private final Map<InetAddress, Player> playerMap = new HashMap<>();

    public ServerSideController(State state, Server server) {
        this.state = state;
        this.server = server;

        server.addSubscriber(this);

        addHandlers();
    }

    public void start() throws IOException {
        server.acceptConnections(2);

        InetAddress firstClient = server.getClient(0);
        InetAddress secondClient = server.getClient(1);

        playerMap.put(firstClient, Player.First);
        playerMap.put(secondClient, Player.Second);

        while (true) {}
    }

    private void addHandlers() {
        addHandler(ClientRequestEvent.class, e -> {
            ClientRequestEvent event = (ClientRequestEvent) e;


        });

        addHandler(MoveEventRequest.class, e -> {
            MoveEventRequest event = (MoveEventRequest) e;

            try {
                state.move()
            } catch (IllegalMoveException ignored) {} // move is illegal, don't confirm
        });

        addHandler(WallPlacementEventRequest.class, e -> {
            WallPlacementEventRequest event = (WallPlacementEventRequest) e;

            try {

            } catch (IllegalWallException ignored) {} // move is illegal, don't confirm
        });
    }
}
