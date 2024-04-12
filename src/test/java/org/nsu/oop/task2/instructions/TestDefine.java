package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadOperandsException;

public class TestDefine extends TestInstruction {
    @Test
    void defineAndPrint() {
        String[] instructions = {"DEFINE VAR 100", "PUSH VAR", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("100.0", outputStream.toString().trim());
    }

    @Test
    void invalidDefine() {
        String[] instructions = {"DEFINE 100 200"};

        Assertions.assertThrows(BadOperandsException.class, () -> calculator.executeInstructions(instructions));
    }
}
