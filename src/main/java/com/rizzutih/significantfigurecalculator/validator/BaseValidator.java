package com.rizzutih.significantfigurecalculator.validator;

import com.rizzutih.significantfigurecalculator.exceptions.FieldValidationErrorException;
import com.rizzutih.significantfigurecalculator.validator.rule.ValidationRule;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by h.rizzuti on 21/06/2018.
 */
public class BaseValidator {

    private void validate(final BindingResult bindingResult) throws FieldValidationErrorException {
        if (bindingResult.hasErrors()) {
            throw new FieldValidationErrorException(bindingResult.getFieldErrors());
        }
    }

    protected void validate(final BindingResult bindingResult,
                            final ValidationRule... validationRules) throws FieldValidationErrorException {

        final List<ValidationRule> validations = Arrays.asList(validationRules);
        validations.forEach((ValidationRule validation) -> {
            final Optional<List<FieldError>> validate = validation.execute();
            validate.ifPresent(v -> v.forEach(bindingResult::addError));
        });
        validate(bindingResult);
    }

    protected void validate(final ValidationRule... validationRules) throws FieldValidationErrorException {
        final List<ValidationRule> validations = Arrays.asList(validationRules);
        final List<FieldError> errors = new ArrayList<>();
        validations.forEach((ValidationRule validation) -> {
            final Optional<List<FieldError>> validate = validation.execute();
            validate.ifPresent(errors::addAll);
        });
        if (!errors.isEmpty()) {
            throw new FieldValidationErrorException(errors);
        }
    }
}
