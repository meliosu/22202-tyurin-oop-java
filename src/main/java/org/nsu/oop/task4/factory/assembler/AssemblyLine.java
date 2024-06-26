package org.nsu.oop.task4.factory.assembler;

import org.nsu.oop.task4.factory.parts.*;
import org.nsu.oop.task4.factory.storage.Storage;
import org.nsu.oop.task4.threadpool.ThreadPool;

public class AssemblyLine extends ThreadPool {
    private static final int sleepMs = 10;

    private final Storage<Car> carStorage;

    private final Storage<Part> engineStorage;
    private final Storage<Part> frameStorage;
    private final Storage<Part> accessoryStorage;

    public AssemblyLine(
            int size,
            Storage<Part> frameStorage,
            Storage<Part> engineStorage,
            Storage<Part> accessoryStorage,
            Storage<Car> carStorage
    ) {
        super(size);
        this.frameStorage = frameStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void assembleCar() {
        enqueue(() -> {
            try {
                Engine engine = (Engine) engineStorage.unload();
                Frame frame = (Frame) frameStorage.unload();
                Accessory accessory = (Accessory) accessoryStorage.unload();

                Thread.sleep(sleepMs);
                Car car = new Car(engine, frame, accessory);

                carStorage.load(car);
            } catch (InterruptedException ignored) {}
        });
    }
}
