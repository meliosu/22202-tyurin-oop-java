package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.game.State;
import org.nsu.oop.task5.network.controllers.ServerSideController;

import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        State state = new State();
        Server server = new Server(5000);

        ServerSideController controller = new ServerSideController(state, server);
        controller.start();
    }
}
