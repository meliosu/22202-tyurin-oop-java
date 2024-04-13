package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Define extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        String variable, valueString;
        try {
            variable = operands[0];
            valueString = operands[1];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new BadOperandsException("not enough operands provided", this);
        }

        if (Character.isDigit(variable.charAt(0))) {
            throw new BadOperandsException("invalid variable name", this);
        }

        try {
            Double value = Double.valueOf(valueString);
            context.insertVariable(variable, value);
        } catch (NumberFormatException exception) {
            String message = valueString + " must be a number ";
            throw new BadOperandsException(message, this);
        }
    }
}
