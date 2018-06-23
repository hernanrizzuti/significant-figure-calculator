package com.rizzutih.significantfigurecalculator.validator.rule;

import com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by h.rizzuti on 21/06/2018.
 */
public class NumberValidationRule extends ValidationRule<String> {

    public NumberValidationRule(String fieldName, String fieldValue) {
        super(fieldName, fieldValue);
    }

    @Override
    protected Optional<List<FieldError>> validate() {
        final String regEx = "\\d+([.]\\d+)?";

        if (!Pattern.matches(regEx, fieldValue)){
            final List<FieldError> errors = new ArrayList<>();
            errors.add(new FieldError(fieldName, fieldName, ErrorMessage.NOT_A_NUMBER.getMessage()));
            return Optional.of(errors);
        }
        return Optional.empty();
    }
}
