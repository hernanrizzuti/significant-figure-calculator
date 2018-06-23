package com.rizzutih.significantfigurecalculator.exceptions;

/**
 * Created by h.rizzuti on 20/06/2018.
 */
public enum ErrorMessage implements Message {

    PARAMETER_CANNOT_BE_NULL_OR_EMPTY("1-0", "Parameter cannot be null or empty"),
    WHOLE_NUMBERS_ONLY("2-0", "Whole numbers only."),
    SIGNIFICANT_FIGURE_NEEDS_TO_BE_GREATER_THAN_ZERO("3-0", "Significant figure needs to be greater than zero."),
    NOT_A_NUMBER("4-0", "Not a number.");

    private final String code;
    private final String message;

    ErrorMessage(final String specificCode,
                 final String message) {

        this.code = specificCode;
        this.message = message;
    }

    @Override
    public Object getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
