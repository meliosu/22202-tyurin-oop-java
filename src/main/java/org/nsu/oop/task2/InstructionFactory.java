package org.nsu.oop.task2;

import org.nsu.oop.task2.exceptions.InstructionFactoryException;
import org.nsu.oop.task2.instructions.Instruction;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InstructionFactory {
    private final Map<String, Class<Instruction>> instructionsMap;

    public InstructionFactory(String configPath) {
        instructionsMap = new HashMap<>();

        InputStream stream;
        try {
            stream = InstructionFactory.class.getResourceAsStream(configPath);
        } catch (RuntimeException e) {
            throw new InstructionFactoryException("cannot find instructions config file");
        }

        if (stream == null) {
            throw new InstructionFactoryException("cannot open instructions config file");
        }

        Scanner configScanner = new Scanner(stream);

        while (configScanner.hasNext()) {
            String[] tokens = configScanner.nextLine().split("=");
            if (tokens.length != 2) {
                throw new InstructionFactoryException("bad instruction factory config format");
            }

            try {
                instructionsMap.put(tokens[0], (Class<Instruction>) Class.forName(tokens[1]));
            } catch (ClassNotFoundException exception) {
                throw new InstructionFactoryException("unknown class in factory config");
            }

        }
    }

    public Instruction getInstruction(String instructionName) {
        if (!instructionsMap.containsKey(instructionName)) {
            throw new InstructionFactoryException("unknown command");
        }

        try {
            return instructionsMap.get(instructionName).getDeclaredConstructor().newInstance();
        } catch (Exception exception) {
            throw new InstructionFactoryException(
                    "error creating class instance for an instruction " + instructionName + exception.getMessage()
            );
        }
    }
}
