package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.exceptions.BadArithmeticException;

public class Divide extends BinaryOperation {
    @Override
    protected Double performOperation(Double lhs, Double rhs) throws BadArithmeticException {
        if (rhs == 0) {
            throw new BadArithmeticException("division by zero", this);
        }

        return lhs / rhs;
    }
}
