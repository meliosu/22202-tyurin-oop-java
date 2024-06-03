package org.nsu.oop.task5;

import org.nsu.oop.task5.controller.Controller;
import org.nsu.oop.task5.game.State;
import org.nsu.oop.task5.ui.View;

public class App {
    public static void main(String[] args) {
        View view = new View();
        State state = new State();
        Controller controller = new Controller(state, view);

        view.addSubscriber(controller);
        view.start();
    }
}
