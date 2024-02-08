package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Define extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        checkOperandsCount(operands);

        String variable = operands[0];
        String valueString = operands[1];
        try {
            Double value = Double.valueOf(valueString);
            context.variables.put(variable, value);
        } catch (NumberFormatException ignore) {
            String message = valueString + " must be a number ";
            throw new BadOperandsException(message, this);
        }
    }

    @Override
    protected int getRequiredOperandsCount() {
        return 2;
    }
}
