package org.nsu.oop.task5.network.controllers;

import org.nsu.oop.task5.controller.events.*;
import org.nsu.oop.task5.network.client.Client;
import org.nsu.oop.task5.network.pubsub2.Event;
import org.nsu.oop.task5.network.pubsub2.Subscriber;
import org.nsu.oop.task5.ui.View;
import org.nsu.oop.task5.ui.game.Wall;
import org.nsu.oop.task5.util.Player;

public class ClientSideController extends Subscriber implements org.nsu.oop.task5.pubsub.Subscriber<GameEvent> {
    private final View view;
    private final Client client;

    public ClientSideController(View view, Client client) {
        this.view = view;
        this.client = client;

        System.out.println("adding");
        view.addSubscriber(this);

        addHandlers();
    }

    public void start() {
        while (true) {
            Event event = client.recvEvent();
            handleEvent(event);
        }
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

        addHandler(MoveEventRequest.class, e -> {
            System.out.println("move request");

            client.sendEvent(e);
        });

        addHandler(WallPlacementEventRequest.class, e -> {
            System.out.println("wall request");

            client.sendEvent(e);
        });

        addHandler(GameOverEvent.class, e -> {
            GameOverEvent event = (GameOverEvent) e;

            view.gameOver(event.winningPlayer);
        });

        addHandler(StartGameNotify.class, e -> {
            System.out.println("start game notify");

            view.reset();
            view.showMainView();
        });

        // probably need to merge events (StartGameEvent / StartGameRequest)
        addHandler(StartGameEvent.class, e -> {
            client.sendEvent(new StartGameRequest());
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

    @Override
    public void onEvent(GameEvent event) {
        handleEvent(event);
    }
}
