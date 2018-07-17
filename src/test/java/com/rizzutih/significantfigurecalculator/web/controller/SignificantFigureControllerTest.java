package com.rizzutih.significantfigurecalculator.web.controller;

import com.rizzutih.significantfigurecalculator.exceptions.FieldValidationErrorException;
import com.rizzutih.significantfigurecalculator.service.CalculatorService;
import com.rizzutih.significantfigurecalculator.validator.SignificantFigureCalculatorValidator;
import com.rizzutih.significantfigurecalculator.web.response.SignificantFigureResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by h.rizzuti on 22/06/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignificantFigureControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @Mock
    private SignificantFigureCalculatorValidator significantFigureCalculatorValidator;

    @InjectMocks
    SignificantFigureController significantFigureController;

    @Test
    public void shouldReturnResponse() throws FieldValidationErrorException {
        //Given
        final String number = "5.32";
        final String significantFigure = "2";
        final String expectedNumber = "5.30";
        //When
        when(calculatorService.calculate(anyString(), anyInt())).thenReturn(expectedNumber);

        final ResponseEntity<SignificantFigureResponse> significantFigureResponse = significantFigureController
                .calculate("5.32", "2");

        //Then
        assertNotNull(significantFigureResponse);
        verify(significantFigureCalculatorValidator).validate(number, significantFigure);
        SignificantFigureResponse significantFigureResponseActual = significantFigureResponse.getBody();
        assertEquals("5.32", significantFigureResponseActual.getOriginalNumber());
        assertEquals("5.30", significantFigureResponseActual.getResult());
        assertEquals("2", significantFigureResponseActual.getSignificantFigure());
    }




}