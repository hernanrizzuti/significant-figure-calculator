package com.rizzutih.significantfigurecalculator.validator.rule;

import com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by h.rizzuti on 20/06/2018.
 */
public class SignificantNumberValidationRule extends ValidationRule<String> {

    public SignificantNumberValidationRule(String fieldName, String fieldValue) {
        super(fieldName, fieldValue);
    }

    @Override
    protected Optional<List<FieldError>> validate() {

        final List<FieldError> errors = new ArrayList<>();

        if(!Pattern.matches("\\d+", fieldValue) || fieldValue.contains(".")){
            errors.add(new FieldError(fieldName, fieldName, ErrorMessage.WHOLE_NUMBERS_ONLY.getMessage()));

            return Optional.of(errors);
        }

        if(Integer.parseInt(fieldValue) < 1){
            errors.add(new FieldError(fieldName, fieldName,
                    ErrorMessage.SIGNIFICANT_FIGURE_NEEDS_TO_BE_GREATER_THAN_ZERO.getMessage()));
        }

        if(!errors.isEmpty()) {
            return Optional.of(errors);
        }

        return Optional.empty();
    }
}
