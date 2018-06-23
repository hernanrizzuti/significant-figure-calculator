package com.rizzutih.significantfigurecalculator.validator.rule;

import com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage;
import org.springframework.validation.FieldError;

import java.util.*;

/**
 * Created by h.rizzuti on 20/06/2018.
 */
public abstract class ValidationRule<T> {

    protected final String fieldName;
    protected final T fieldValue;

    protected ValidationRule(final String fieldName,
                             final T fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    protected abstract Optional<List<FieldError>> validate();

    public Optional<List<FieldError>> execute() {
        if (Objects.isNull(fieldValue)) {
            final List<FieldError> errors = new ArrayList<>();
            errors.add(new FieldError(fieldName, fieldName, ErrorMessage.PARAMETER_CANNOT_BE_NULL_OR_EMPTY.getMessage()));
            return Optional.of(errors);
        }
        return validate();
    }

}
