package com.rizzutih.significantfigurecalculator.validator;

import com.rizzutih.significantfigurecalculator.validator.rule.SignificantNumberValidationRule;
import org.junit.Test;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

import static com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage.PARAMETER_CANNOT_BE_NULL_OR_EMPTY;
import static com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage.SIGNIFICANT_FIGURE_NEEDS_TO_BE_GREATER_THAN_ZERO;
import static com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage.WHOLE_NUMBERS_ONLY;
import static org.junit.Assert.*;

/**
 * Created by h.rizzuti on 20/06/2018.
 */
public class SignificantNumberValidationRuleTest {

    @Test
    public void shouldFailWhenSignificantFigureIsZero() {
        final String fieldName = "significantFigure";
        final Optional<List<FieldError>> result = new SignificantNumberValidationRule(fieldName, "0").execute();
        final List<FieldError> fieldErrors = result.get();

        assertEquals(1, fieldErrors.size());
        assertEquals(fieldErrors.get(0).getDefaultMessage(), SIGNIFICANT_FIGURE_NEEDS_TO_BE_GREATER_THAN_ZERO.getMessage());
        assertEquals(fieldErrors.get(0).getObjectName(), fieldName);
        assertEquals(fieldErrors.get(0).getField(), fieldName);
    }

    @Test
    public void shouldFailWhenSignificantFigureContainsADot() {
        final String fieldName = "significantFigure";
        final Optional<List<FieldError>> result = new SignificantNumberValidationRule(fieldName, "1.1").execute();
        final List<FieldError> fieldErrors = result.get();

        assertEquals(1, fieldErrors.size());
        assertEquals(fieldErrors.get(0).getDefaultMessage(), WHOLE_NUMBERS_ONLY.getMessage());
        assertEquals(fieldErrors.get(0).getObjectName(), fieldName);
        assertEquals(fieldErrors.get(0).getField(), fieldName);
    }

    @Test
    public void shouldFailWhenSignificantFigureIsBelowZero() {
        final String fieldName = "significantFigure";
        final Optional<List<FieldError>> result = new SignificantNumberValidationRule(fieldName, "-5").execute();
        final List<FieldError> fieldErrors = result.get();

        assertEquals(1, fieldErrors.size());
        assertEquals(fieldErrors.get(0).getDefaultMessage(), WHOLE_NUMBERS_ONLY.getMessage());
        assertEquals(fieldErrors.get(0).getObjectName(), fieldName);
        assertEquals(fieldErrors.get(0).getField(), fieldName);
    }

    @Test
    public void shouldFailWhenCreditTypeIsNull() {
        //Given
        final String fieldName = "significantFigure";

        //When
        final Optional<List<FieldError>> result = new SignificantNumberValidationRule(fieldName, null).execute();

        //Then
        assertTrue(result.isPresent());
        final List<FieldError> fieldErrors = result.get();
        assertEquals(1, fieldErrors.size());
        assertEquals(fieldErrors.get(0).getDefaultMessage(), PARAMETER_CANNOT_BE_NULL_OR_EMPTY.getMessage());
        assertEquals(fieldErrors.get(0).getObjectName(), fieldName);
        assertEquals(fieldErrors.get(0).getField(), fieldName);
    }


    @Test
    public void shouldFailWhenSignificantFigureIsNotANumber() {
        final String fieldName = "significantFigure";
        final Optional<List<FieldError>> result = new SignificantNumberValidationRule(fieldName, "aaaa").execute();
        final List<FieldError> fieldErrors = result.get();

        assertEquals(1, fieldErrors.size());
        assertEquals(fieldErrors.get(0).getDefaultMessage(), WHOLE_NUMBERS_ONLY.getMessage());
        assertEquals(fieldErrors.get(0).getObjectName(), fieldName);
        assertEquals(fieldErrors.get(0).getField(), fieldName);
    }

    @Test
    public void shouldPassWhenNumberIsPassedIn() {
        //Given
        final String fieldName = "significantFigure";
        final Optional<List<FieldError>> result = new SignificantNumberValidationRule(fieldName, "3").execute();
       //When
        assertFalse(result.isPresent());

    }

}