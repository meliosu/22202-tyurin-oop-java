package org.nsu.oop.task5.network.server;

import org.nsu.oop.task5.game.State;
import org.nsu.oop.task5.network.controllers.ServerSideController;

import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) {
        State state = new State();

        Server server;
        try {
            server = new Server(5000);
        } catch (IOException e) {
            System.err.println("error creating server: " + e.getMessage());
            return;
        }

        ServerSideController controller = new ServerSideController(state, server);
        controller.start();
    }
}
