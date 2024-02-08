package org.nsu.oop.task2.exceptions;

import org.nsu.oop.task2.instructions.Instruction;

public class BadOperandsException extends InstructionException {
    public BadOperandsException(String message, Instruction instruction) {
        super("operands error: " + message, instruction);
    }
}
