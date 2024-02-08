package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.BadArithmeticException;
import org.nsu.oop.task2.exceptions.InstructionException;

public abstract class ArithmeticOperation extends Instruction {
    protected abstract Double performOperation(Double lhs, Double rhs) throws ArithmeticException;

    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        checkOperandsCount(operands);
        checkStackSize(context);

        Double lhs = context.stack.pop();
        Double rhs = context.stack.pop();
        try {
            Double res = performOperation(lhs, rhs);
            context.stack.push(res);
        } catch (ArithmeticException exception) {
            throw new BadArithmeticException(exception.getMessage(), this);
        }
    }

    @Override
    protected int getMinimumStackSize() {
        return 2;
    }
}
