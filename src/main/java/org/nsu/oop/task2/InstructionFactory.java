package org.nsu.oop.task2;

import org.nsu.oop.task2.exceptions.InstructionFactoryException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InstructionFactory {
    private Map<String, Class<?>> instructionsMap;

    public InstructionFactory(String configPath) {
        instructionsMap = new HashMap<>();

        InputStream stream;
        try {
            stream = InstructionFactory.class.getResourceAsStream(configPath);
        } catch (RuntimeException e) {
            throw new InstructionFactoryException("cannot open instructions config file");
        }

        Scanner configScanner = new Scanner(stream);

        while (configScanner.hasNext()) {
            String[] tokens = configScanner.nextLine().split("=");
            if (tokens.length != 2) {
                throw new InstructionFactoryException("bad instruction factory format");
            }
        }
    }
}
