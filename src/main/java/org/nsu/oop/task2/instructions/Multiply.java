package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.exceptions.*;

public class Multiply extends ArithmeticOperation {
    @Override
    protected Double performOperation(Double lhs, Double rhs) throws ArithmeticException {
        return lhs * rhs;
    }
}
