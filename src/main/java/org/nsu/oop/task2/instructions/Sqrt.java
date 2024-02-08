package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Sqrt extends Instruction {
    @Override
    public void execute(String[] operands, Context context) {
        checkOperandsCount(operands);
        checkStackSize(context);

        Double value = context.stack.pop();
        try {
            Double root = Math.sqrt(value);
            context.stack.push(root);
        } catch (ArithmeticException exception) {
            throw new BadArithmeticException(exception.getMessage(), this);
        }
    }

    @Override
    protected int getMinimumStackSize() {
        return 1;
    }
}
