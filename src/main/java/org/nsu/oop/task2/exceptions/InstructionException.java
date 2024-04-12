package org.nsu.oop.task2.exceptions;

import org.nsu.oop.task2.instructions.Instruction;

public class InstructionException extends CalculatorException {
    public InstructionException(String message, Instruction instruction) {
        super("while executing " + instruction.getClass() + "instruction: " + message);
    }
}
