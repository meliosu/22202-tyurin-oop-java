package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.BadArithmeticException;
import org.nsu.oop.task2.exceptions.BadContextException;
import org.nsu.oop.task2.exceptions.BadOperandsException;
import org.nsu.oop.task2.exceptions.InstructionException;

import java.util.EmptyStackException;

public abstract class BinaryOperation extends Instruction {
    protected abstract Double performOperation(Double lhs, Double rhs) throws BadArithmeticException;

    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        if (operands.length != 0) {
            throw new BadOperandsException("no operands expected", this);
        }

        Double lhs, rhs;
        try {
            lhs = context.pop();
            rhs = context.pop();
        } catch (EmptyStackException exception) {
            throw new BadContextException("not enough values in stack", this);
        }

        Double res = performOperation(lhs, rhs);
        context.push(res);
    }
}
