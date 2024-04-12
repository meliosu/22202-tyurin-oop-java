package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadArithmeticException;
import org.nsu.oop.task2.exceptions.BadContextException;

public class TestSqrt extends TestInstruction {
    @Test
    void simple() {
        String[] instructions = {"PUSH 25", "SQRT", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("5.0", outputStream.toString().trim());
    }

    @Test
    void negative() {
        String[] instructions = {"PUSH -10", "SQRT"};

        Assertions.assertThrows(BadArithmeticException.class, () -> calculator.executeInstructions(instructions));
    }
}
