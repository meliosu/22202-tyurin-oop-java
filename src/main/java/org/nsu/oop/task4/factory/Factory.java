package org.nsu.oop.task4.factory;

import org.nsu.oop.task4.factory.assembler.AssemblyLine;
import org.nsu.oop.task4.factory.dealer.Dealer;
import org.nsu.oop.task4.factory.parts.*;
import org.nsu.oop.task4.factory.producers.*;
import org.nsu.oop.task4.factory.storage.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Factory {
    private final Storage<Part> engineStorage;
    private final Storage<Part> frameStorage;
    private final Storage<Part> accessoryStorage;

    private final EngineProducer engineProducer;
    private final FrameProducer frameProducer;
    private final AccessoryProducer[] accessoryProducers;

    private final AssemblyLine assemblyLine;
    private final Storage<Car> carStorage;
    private final Dealer[] dealers;

    private final FactoryController factoryController;

    public Factory(FactoryConfig config) {
        engineStorage = new Storage<>(config.engineStorageSize);
        frameStorage = new Storage<>(config.trunkStorageSize);
        accessoryStorage = new Storage<>(config.accessoryStorageSize);
        carStorage = new Storage<>(config.carStorageSize);

        engineProducer = new EngineProducer(engineStorage);
        frameProducer = new FrameProducer(frameStorage);

        accessoryProducers = new AccessoryProducer[config.accessoryProducers];
        for (int i = 0; i < config.accessoryProducers; i++) {
            accessoryProducers[i] = new AccessoryProducer(accessoryStorage);
        }

        assemblyLine = new AssemblyLine(
                config.assemblyWorkers,
                10,
                frameStorage,
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
        frameProducer.start();

        for (AccessoryProducer producer : accessoryProducers) {
            producer.start();
        }

        for (Dealer dealer : dealers) {
            dealer.start();
        }

        factoryController.start();
    }

    public int getEngineCount() {
        return engineStorage.getTotalCount();
    }

    public int getFrameCount() {
        return frameStorage.getTotalCount();
    }

    public int getAccessoryCount() {
        return accessoryStorage.getTotalCount();
    }

    public void setEngineSpeed(int sleepMs) {
        engineProducer.setSpeed(sleepMs);
    }

    public void setFrameSpeed(int sleepMs) {
        frameProducer.setSpeed(sleepMs);
    }

    public void setAccessorySpeed(int sleepMs) {
        for (AccessoryProducer producer : accessoryProducers) {
            producer.setSpeed(sleepMs);
        }
    }
}
