package com.rizzutih.significantfigurecalculator.web.controller;

import com.rizzutih.significantfigurecalculator.exceptions.FieldValidationErrorException;
import com.rizzutih.significantfigurecalculator.model.NumberInfo;
import com.rizzutih.significantfigurecalculator.service.CalculatorService;
import com.rizzutih.significantfigurecalculator.validator.SignificantFigureCalculatorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
    public String calculate(@RequestParam("number") String number,
                            @RequestParam("significantFigure") String significantFigure)
            throws FieldValidationErrorException {

        significantFigureCalculatorValidator.validate(number, significantFigure);

        final String result = calculatorService.calculate(number, Integer.parseInt(significantFigure), new NumberInfo());

        return "{\"result\": \"" + result+ "\"}";
    }

}
