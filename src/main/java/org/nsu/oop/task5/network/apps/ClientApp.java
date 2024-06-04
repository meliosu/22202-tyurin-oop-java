package org.nsu.oop.task5.network.apps;

import org.nsu.oop.task5.network.client.Client;
import org.nsu.oop.task5.network.controllers.ClientSideController;
import org.nsu.oop.task5.ui.View;

public class ClientApp {
    public static void main(String[] args) {
        View view = new View();
        Client client = new Client("localhost", 5000);

        ClientSideController controller = new ClientSideController(view, client);
        controller.start();
    }
}
