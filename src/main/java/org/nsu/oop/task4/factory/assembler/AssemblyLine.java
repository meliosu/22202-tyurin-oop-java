package org.nsu.oop.task4.factory.assembler;

import org.nsu.oop.task4.factory.parts.*;
import org.nsu.oop.task4.factory.storage.Storage;
import org.nsu.oop.task4.threadpool.ThreadPool;

public class AssemblyLine extends ThreadPool {
    private final int sleepMs;

    private final Storage<Car> carStorage;

    private final Storage<Part> engineStorage;
    private final Storage<Part> frameStorage;
    private final Storage<Part> accessoryStorage;

    public AssemblyLine(
            int size,
            int sleepMs,
            Storage<Part> frameStorage,
            Storage<Part> engineStorage,
            Storage<Part> accessoryStorage,
            Storage<Car> carStorage
    ) {
        super(size);
        this.sleepMs = sleepMs;
        this.frameStorage = frameStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void assembleCar() {
        enqueue(() -> {
            try {
                Engine engine = (Engine) engineStorage.takePart();
                Frame frame = (Frame) frameStorage.takePart();
                Accessory accessory = (Accessory) accessoryStorage.takePart();

                Thread.sleep(sleepMs);
                Car car = new Car(engine, frame, accessory);

                carStorage.loadPart(car);
            } catch (InterruptedException ignored) {}
        });
    }
}
