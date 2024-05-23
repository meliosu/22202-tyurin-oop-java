package org.nsu.oop.task4.factory.assembler;

import org.nsu.oop.task4.factory.parts.Car;
import org.nsu.oop.task4.factory.parts.CarAccessory;
import org.nsu.oop.task4.factory.parts.CarEngine;
import org.nsu.oop.task4.factory.parts.CarTrunk;
import org.nsu.oop.task4.factory.storage.CarAccessoryStorage;
import org.nsu.oop.task4.factory.storage.CarEngineStorage;
import org.nsu.oop.task4.factory.storage.CarStorage;
import org.nsu.oop.task4.factory.storage.CarTrunkStorage;
import org.nsu.oop.task4.threadpool.ThreadPool;

public class AssemblyLine extends ThreadPool {
    private final int sleepMs;

    private final CarStorage carStorage;

    private final CarEngineStorage engineStorage;
    private final CarTrunkStorage trunkStorage;
    private final CarAccessoryStorage accessoryStorage;

    public AssemblyLine(
            int size,
            int sleepMs,
            CarTrunkStorage trunkStorage,
            CarEngineStorage engineStorage,
            CarAccessoryStorage accessoryStorage,
            CarStorage carStorage
    ) {
        super(size);
        this.sleepMs = sleepMs;
        this.trunkStorage = trunkStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void assembleCar() {
        enqueue(() -> {
            try {
                CarEngine engine = (CarEngine) engineStorage.takePart();
                CarTrunk trunk = (CarTrunk) trunkStorage.takePart();
                CarAccessory accessory = (CarAccessory) accessoryStorage.takePart();

                Thread.sleep(sleepMs);
                Car car = new Car(engine, trunk, accessory);

                carStorage.loadPart(car);
            } catch (InterruptedException ignored) {}
        });
    }
}
