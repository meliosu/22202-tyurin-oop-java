package org.nsu.oop.task2;

import org.nsu.oop.task2.exceptions.CalculatorException;
import org.nsu.oop.task2.instructions.Instruction;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    private final Context context;
    private final InstructionFactory factory;
    private Logger logger;

    public Calculator() {
        this.context = new Context();
        this.factory = new InstructionFactory("/factory.cfg");
        this.logger = null;
    }

    public static Calculator withLogging() {
        Calculator calculator = new Calculator();
        calculator.logger = LoggerFactory.getLogger(Calculator.class);
        return calculator;
    }

    private void executeFromScanner(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineParsed = line.split(" ");

            String instructionName = lineParsed[0];
            String[] operands = Arrays.copyOfRange(lineParsed, 1, lineParsed.length);

            Instruction instruction = factory.getInstruction(instructionName);

            if (logger != null) {
                logger.debug("executing instruction: " + instruction);
            }

            instruction.execute(operands, context);
        }
    }

    public void executeInstructions(String[] instructions) {
        String instructionsStr = String.join("\n", instructions);
        executeFromScanner(new Scanner(instructionsStr));
    }

    public void executeStdin() {
        Scanner scanner = new Scanner(System.in);
        executeFromScanner(scanner);
    }

    public void executeFile(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new CalculatorException("input file not found");
        }

        executeFromScanner(scanner);
    }
}
