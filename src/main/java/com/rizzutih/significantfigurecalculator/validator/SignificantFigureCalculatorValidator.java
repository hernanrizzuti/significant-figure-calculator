package com.rizzutih.significantfigurecalculator.validator;

import com.rizzutih.significantfigurecalculator.exceptions.FieldValidationErrorException;
import com.rizzutih.significantfigurecalculator.validator.rule.NumberValidationRule;
import com.rizzutih.significantfigurecalculator.validator.rule.SignificantNumberValidationRule;
import org.springframework.stereotype.Component;

/**
 * Created by h.rizzuti on 21/06/2018.
 */
@Component
public class SignificantFigureCalculatorValidator extends BaseValidator {

    public void validate(final String number,
                         final String significantFigure) throws FieldValidationErrorException {

        validate(new NumberValidationRule("number", number),
                new SignificantNumberValidationRule("significantFigure", significantFigure));
    }
}
