package org.nsu.oop.task5.network.controllers;

import org.nsu.oop.task5.controller.events.*;
import org.nsu.oop.task5.network.client.Client;
import org.nsu.oop.task5.network.pubsub2.Event;
import org.nsu.oop.task5.network.pubsub2.Subscriber;
import org.nsu.oop.task5.ui.View;
import org.nsu.oop.task5.ui.game.Wall;
import org.nsu.oop.task5.util.Player;

public class ClientSideController extends Subscriber {
    private final View view;
    private final Client client;

    public ClientSideController(View view, Client client) {
        this.view = view;
        this.client = client;

        // view.addSubscriber(this);

        addHandlers();
    }

    public void start() {
        while (true) {
            Event event = client.recvEvent();
            handleEvent(event);
        }
    }

    private void addHandlers() {
        // maybe unnecessary
        addHandler(ServerHello.class, e -> {
            ServerHello event = (ServerHello) e;
            view.setPlayer(event.player);
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

        addHandler(MoveEventRequest.class, e -> {
            client.sendEvent(e);
        });

        addHandler(WallPlacementEventRequest.class, e -> {
            client.sendEvent(e);
        });

//        addHandler(MoveEventResponse.class, e -> {
//            MoveEventResponse response = (MoveEventResponse) e;
//            MoveEventRequest request = response.request;
//
//            if (response.isLegal) {
//                view.movePlayer(request.position, view.getPlayer()); // fix
//            }
//        });
//
//        addHandler(WallPlacementResponse.class, e -> {
//            WallPlacementResponse response = (WallPlacementResponse) e;
//            WallPlacementEventRequest request = response.request;
//
//            if (response.isLegal) {
//                view.placeWall(new Wall(request.wallPosition, request.wallType));
//                view.updateWallCount(view.getPlayer(), response.wallCount);
//            }
//        });
    }
}
