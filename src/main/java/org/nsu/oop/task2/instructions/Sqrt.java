package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

import java.util.EmptyStackException;

public class Sqrt extends Instruction {
    @Override
    public void execute(String[] operands, Context context) {
        Double value;
        try {
            value = context.pop();
        } catch (EmptyStackException exception) {
            throw new BadContextException("no value in stack", this);
        }

        try {
            Double root = Math.sqrt(value);
            context.push(root);
        } catch (ArithmeticException exception) {
            throw new BadArithmeticException(exception.getMessage(), this);
        }
    }
}
