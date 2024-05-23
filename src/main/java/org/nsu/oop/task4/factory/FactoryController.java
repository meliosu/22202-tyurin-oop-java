package org.nsu.oop.task4.factory;

import org.nsu.oop.task4.factory.assembler.AssemblyLine;
import org.nsu.oop.task4.factory.parts.Car;
import org.nsu.oop.task4.factory.storage.Storage;

public class FactoryController extends Thread {
    private final AssemblyLine assemblyLine;
    private final Storage<Car> carStorage;

    public FactoryController(AssemblyLine assemblyLine, Storage<Car> carStorage) {
        this.assemblyLine = assemblyLine;
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        while (true) {
            if (carStorage.getSize() < carStorage.getCapacity() / 2) {
                assemblyLine.assembleCar();
            }

            synchronized (carStorage) {
                try {
                    carStorage.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
