package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.BeforeEach;
import org.nsu.oop.task2.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestInstruction {
    Calculator calculator;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
}
