package org.nsu.oop.task4.factory;

import org.nsu.oop.task4.factory.assembler.AssemblyLine;
import org.nsu.oop.task4.factory.storage.CarStorage;

public class FactoryController extends Thread {
    private final AssemblyLine assemblyLine;
    private final CarStorage carStorage;

    public FactoryController(AssemblyLine assemblyLine, CarStorage carStorage) {
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
