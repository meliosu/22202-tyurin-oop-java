package org.nsu.oop.task2;

public class InstructionException extends CalculatorException {
    public InstructionException(String message, Instruction instruction) {
        super("while executing" + instruction.toString() + "instruction: " + message);
    }
}
