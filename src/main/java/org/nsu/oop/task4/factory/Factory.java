package org.nsu.oop.task4.factory;

import org.nsu.oop.task4.factory.assembler.AssemblyLine;
import org.nsu.oop.task4.factory.dealer.Dealer;
import org.nsu.oop.task4.factory.producer.CarAccessoryProducer;
import org.nsu.oop.task4.factory.producer.CarEngineProducer;
import org.nsu.oop.task4.factory.producer.CarTrunkProducer;
import org.nsu.oop.task4.factory.storage.CarAccessoryStorage;
import org.nsu.oop.task4.factory.storage.CarEngineStorage;
import org.nsu.oop.task4.factory.storage.CarStorage;
import org.nsu.oop.task4.factory.storage.CarTrunkStorage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Factory {
    private final CarEngineStorage engineStorage;
    private final CarTrunkStorage trunkStorage;
    private final CarAccessoryStorage accessoryStorage;

    private final CarEngineProducer engineProducer;
    private final CarTrunkProducer trunkProducer;
    private final CarAccessoryProducer[] accessoryProducers;

    private final AssemblyLine assemblyLine;
    private final CarStorage carStorage;
    private final Dealer[] dealers;

    private final FactoryController factoryController;

    public Factory(FactoryConfig config) {
        engineStorage = new CarEngineStorage(config.engineStorageSize);
        trunkStorage = new CarTrunkStorage(config.trunkStorageSize);
        accessoryStorage = new CarAccessoryStorage(config.accessoryStorageSize);
        carStorage = new CarStorage(config.carStorageSize);

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
