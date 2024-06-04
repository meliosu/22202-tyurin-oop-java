package org.nsu.oop.task5.network.client;

import org.nsu.oop.task5.network.controllers.ClientSideController;
import org.nsu.oop.task5.ui.View;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        View view = new View();
        Client client = new Client("localhost", 5000);

        System.out.println("huh");

        ClientSideController controller = new ClientSideController(view, client);
        view.start();
        controller.start();
    }
}
