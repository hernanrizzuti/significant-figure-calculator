package com.rizzutih.significantfigurecalculator.web.controller;

import com.rizzutih.significantfigurecalculator.exceptions.FieldValidationErrorException;
import com.rizzutih.significantfigurecalculator.service.CalculatorService;
import com.rizzutih.significantfigurecalculator.validator.SignificantFigureCalculatorValidator;
import com.rizzutih.significantfigurecalculator.web.response.SignificantFigureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by h.rizzuti on 20/06/2018.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SignificantFigureController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private SignificantFigureCalculatorValidator significantFigureCalculatorValidator;

    @ResponseBody
    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public ResponseEntity<SignificantFigureResponse> calculate(@RequestParam("number") String number,
                                                               @RequestParam("significantFigure") String significantFigure)
            throws FieldValidationErrorException {

        significantFigureCalculatorValidator.validate(number, significantFigure);

        final String result = calculatorService.calculate(number, Integer.parseInt(significantFigure));

        return ResponseEntity.ok(SignificantFigureResponse.newBuilder()
                .withOriginalNumber(number)
                .withSignificantFigure(significantFigure)
                .withResult(result)
                .build());
    }

}
