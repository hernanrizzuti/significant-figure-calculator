package com.rizzutih.significantfigurecalculator.validator;

import com.rizzutih.significantfigurecalculator.validator.rule.NumberValidationRule;
import com.rizzutih.significantfigurecalculator.validator.rule.SignificantNumberValidationRule;
import org.junit.Test;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

import static com.rizzutih.significantfigurecalculator.exceptions.ErrorMessage.NOT_A_NUMBER;
import static org.junit.Assert.*;
/**
 * Created by h.rizzuti on 21/06/2018.
 */
public class NumberValidationRuleTest {

    @Test
    public void shouldFailWhenLettersAndNumbersArePassedIn() {
        validateNumber("123a");
    }

    @Test
    public void shouldFailWhenTwoDotsArePassedIn() {
        validateNumber("123.12.0");
    }

    @Test
    public void shouldFailWhenALetterIsPassedIn() {
        validateNumber("a");
    }

    @Test
    public void shouldPassWhenANumberIsPassedIn() {
        //Given
        final String fieldName = "number";

        //When
        final Optional<List<FieldError>> result = new NumberValidationRule(fieldName, "123").execute();

        //Then
        assertFalse(result.isPresent());
    }

    @Test
    public void shouldPassWhenANumberWithDecimalPlaceIsPassedIn() {
        //Given
        final String fieldName = "number";

        //When
        final Optional<List<FieldError>> result = new NumberValidationRule(fieldName, "123.01").execute();

        //Then
        assertFalse(result.isPresent());
    }

    private void validateNumber(String number) {
        final String fieldName = "number";
        final Optional<List<FieldError>> result = new NumberValidationRule(fieldName, number).execute();
        final List<FieldError> fieldErrors = result.get();

        assertEquals(1, fieldErrors.size());
        assertEquals(fieldErrors.get(0).getDefaultMessage(), NOT_A_NUMBER.getMessage());
        assertEquals(fieldErrors.get(0).getObjectName(), fieldName);
        assertEquals(fieldErrors.get(0).getField(), fieldName);
    }

}