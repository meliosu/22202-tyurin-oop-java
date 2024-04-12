package org.nsu.oop.task2;

import org.nsu.oop.task2.exceptions.CalculatorException;
import org.nsu.oop.task2.instructions.Instruction;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    private final Context context;
    private final InstructionFactory factory;

    public Calculator() {
        this.context = new Context();
        this.factory = new InstructionFactory("/factory.cfg");
    }

    private void scanInstructions(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineParsed = line.split(" ");

            String instructionName = lineParsed[0];
            String[] operands = Arrays.copyOfRange(lineParsed, 1, lineParsed.length);

            Instruction instruction = factory.getInstruction(instructionName);
            instruction.execute(operands, context);
        }
    }

    public void executeStdin() {
        Scanner scanner = new Scanner(System.in);
        this.scanInstructions(scanner);
    }

    public void executeFile(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new CalculatorException("input file not found");
        }

        this.scanInstructions(scanner);
    }
}
