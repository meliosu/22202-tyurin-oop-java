package org.nsu.oop.task4;

import org.nsu.oop.task4.controller.Controller;
import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.FactoryConfig;
import org.nsu.oop.task4.ui.Menu;

public class App {
    public static void main(String[] args) {
        FactoryConfig factoryConfig = new FactoryConfig();
        Factory factory = new Factory(factoryConfig);

        Menu menu = new Menu();

        Controller controller = Controller
                .getInstance()
                .addMenu(menu)
                .addFactory(factory);

        factory.addSubscriber(controller);
        menu.display();
        factory.launch();
    }
}
