package com.rizzutih.significantfigurecalculator.exceptions;

import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.ConstraintViolation;

/**
 * Created by h.rizzuti on 21/06/2018.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = FieldValidationErrorException.MESSAGE)
public class FieldValidationErrorException extends Exception  implements CustomExceptionResponse, CustomExceptionCode {

    public static final String VALIDATION_ERRORS = "validationErrors";
    public static final String FIELD = "field";
    protected static final String MESSAGE = "Validation errors";

    private final Collection<FieldValidationError> validationErrors;
    private final String customCode;


    public FieldValidationErrorException(final Collection<FieldError> fieldErrors,
                                         final String code) {
        this.customCode = code;
        this.validationErrors = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            validationErrors.add(new FieldValidationError(fieldError));
        }
    }

    public FieldValidationErrorException(final Set<? extends ConstraintViolation<?>> constraintViolations,
                                         final String code) {
        this.customCode = code;
        this.validationErrors = new ArrayList<>(constraintViolations.size());
        constraintViolations.forEach(violation -> validationErrors.add(new FieldValidationError(violation)));
    }

    public FieldValidationErrorException(final Collection<FieldError> fieldErrors) {
        this(fieldErrors, Integer.toString(HttpStatus.BAD_REQUEST.value()));
    }

    public FieldValidationErrorException(final Set<? extends ConstraintViolation<?>> constraintViolations) {
        this(constraintViolations, Integer.toString(HttpStatus.BAD_REQUEST.value()));
    }

    @Override
    public Map<String, Object> getCustomResponse() {
        final Map<String, Object> customResponse = new HashMap<>();
        customResponse.put(VALIDATION_ERRORS, validationErrors);
        return customResponse;
    }

    @Override
    public String getCustomCode() {
        return this.customCode;
    }

    public static class FieldValidationError implements Serializable {
        private final String field;
        private final String message;

        public static FieldValidationError buildFieldValidationError(String object, String field, String message) {
            FieldError fieldError = new FieldError(object, field, message);
            return new FieldValidationError(fieldError);
        }

        protected FieldValidationError(final ConstraintViolation constraintViolation) {
            this.field = constraintViolation.getPropertyPath().toString();
            this.message = constraintViolation.getMessage();
        }

        protected FieldValidationError(final FieldError fieldError) {
            this.field = fieldError.getField();
            this.message = fieldError.getDefaultMessage();
        }

        public FieldValidationError(final String field, final String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
