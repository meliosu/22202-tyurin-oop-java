package org.nsu.oop.task4.factory;

import org.nsu.oop.task4.factory.assembler.AssemblyLine;
import org.nsu.oop.task4.factory.dealer.Dealer;
import org.nsu.oop.task4.factory.parts.*;
import org.nsu.oop.task4.factory.producer.CarAccessoryProducer;
import org.nsu.oop.task4.factory.producer.CarEngineProducer;
import org.nsu.oop.task4.factory.producer.CarTrunkProducer;
import org.nsu.oop.task4.factory.storage.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Factory {
    private final Storage<CarPart> engineStorage;
    private final Storage<CarPart> trunkStorage;
    private final Storage<CarPart> accessoryStorage;

    private final CarEngineProducer engineProducer;
    private final CarTrunkProducer trunkProducer;
    private final CarAccessoryProducer[] accessoryProducers;

    private final AssemblyLine assemblyLine;
    private final Storage<Car> carStorage;
    private final Dealer[] dealers;

    private final FactoryController factoryController;

    public Factory(FactoryConfig config) {
        engineStorage = new Storage<>(config.engineStorageSize);
        trunkStorage = new Storage<>(config.trunkStorageSize);
        accessoryStorage = new Storage<>(config.accessoryStorageSize);
        carStorage = new Storage<>(config.carStorageSize);

        engineProducer = new CarEngineProducer(engineStorage);
        trunkProducer = new CarTrunkProducer(trunkStorage);

        accessoryProducers = new CarAccessoryProducer[config.accessoryProducers];
        for (int i = 0; i < config.accessoryProducers; i++) {
            accessoryProducers[i] = new CarAccessoryProducer(accessoryStorage);
        }

        assemblyLine = new AssemblyLine(
                config.assemblyWorkers,
                10,
                trunkStorage,
                engineStorage,
                accessoryStorage,
                carStorage
        );

        Logger logger = null;
        if (config.enableLogging) {
            logger = Logger.getLogger("Sales Logger");

            try {
                FileHandler fh = new FileHandler("sales.log");
                logger.addHandler(fh);
            } catch (IOException e) {
                throw new RuntimeException("error opening log file: " + e.getMessage());
            }
        }

        dealers = new Dealer[config.carDealers];
        for (int i = 0; i < config.carDealers; i++) {
            dealers[i] = new Dealer(i + 1, 10, carStorage, logger);
        }

        factoryController = new FactoryController(assemblyLine, carStorage);
    }

    public void launch() {
        engineProducer.start();
        trunkProducer.start();

        for (CarAccessoryProducer producer : accessoryProducers) {
            producer.start();
        }

        for (Dealer dealer : dealers) {
            dealer.start();
        }

        factoryController.start();
    }
}
