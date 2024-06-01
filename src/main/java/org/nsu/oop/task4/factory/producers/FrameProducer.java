package org.nsu.oop.task4.factory.producers;

import org.nsu.oop.task4.factory.parts.Part;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.factory.storage.Storage;

public class FrameProducer extends Producer {
    public FrameProducer(Storage<Part> storage) {
        super(storage, 10);
    }

    @Override
    public Part producePart() {
        return new Frame(serialNumber++);
    }
}
