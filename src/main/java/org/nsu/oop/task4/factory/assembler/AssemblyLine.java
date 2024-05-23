package org.nsu.oop.task4.factory.assembler;

import org.nsu.oop.task4.factory.parts.*;
import org.nsu.oop.task4.factory.storage.Storage;
import org.nsu.oop.task4.threadpool.ThreadPool;

public class AssemblyLine extends ThreadPool {
    private final int sleepMs;

    private final Storage<Car> carStorage;

    private final Storage<CarPart> engineStorage;
    private final Storage<CarPart> trunkStorage;
    private final Storage<CarPart> accessoryStorage;

    public AssemblyLine(
            int size,
            int sleepMs,
            Storage<CarPart> trunkStorage,
            Storage<CarPart> engineStorage,
            Storage<CarPart> accessoryStorage,
            Storage<Car> carStorage
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
