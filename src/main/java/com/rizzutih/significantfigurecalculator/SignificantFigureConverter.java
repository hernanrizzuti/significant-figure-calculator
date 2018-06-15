package com.rizzutih.significantfigurecalculator;

import com.rizzutih.significantfigurecalculator.model.NumberInfo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by h.rizzuti on 15/06/2018.
 */
public class SignificantFigureConverter {

    private NumberInfo numberInfo;

    public SignificantFigureConverter() {
        numberInfo = new NumberInfo();
    }

    public String resolver(final String number,
                           final int numberOfSignificantFigures) {

        final List<String> zerosAndNumber = extractZerosBeforeSignificantFigure(number);

        final String extractedZeros =  zerosAndNumber.get(0);

        final String strippedNumber =  zerosAndNumber.get(1);

        final Integer digitBeforeDecimalPoint = getNumberOfDigitBeforeDecimalPlaces(strippedNumber);

        final Character immediateNumber = findImmediateNumberToSignificantFigure(strippedNumber, numberOfSignificantFigures);

        final Integer firstSignificantFigurePosition = numberInfo.getFirstSignificantFigurePosition();


        if(nonNull(digitBeforeDecimalPoint)) {
            if(numberOfSignificantFigures == getNumberLength(strippedNumber)) {
                return strippedNumber;
            }

            if (numberOfSignificantFigures > getNumberLength(strippedNumber)){
                return addTrailingZeros(numberOfSignificantFigures, strippedNumber, 1);
            }
        }

        final BigDecimal rounded = getRoundedNumber(numberOfSignificantFigures, extractedZeros, strippedNumber,
                immediateNumber, firstSignificantFigurePosition, digitBeforeDecimalPoint);

        String roundedStr = rounded.toPlainString();

        if (nonNull(digitBeforeDecimalPoint) && numberOfSignificantFigures < digitBeforeDecimalPoint) {
            roundedStr = rounded.subtract(new BigDecimal(rounded.toPlainString().substring(
                    (firstSignificantFigurePosition + numberOfSignificantFigures)))).toPlainString();

        }

        if(!roundedStr.contains(".") && isNull((extractedZeros))) {
            roundedStr = addTrailingZeros(numberOfSignificantFigures, roundedStr, 0);
        }

        return nonNull(extractedZeros) ? (extractedZeros + roundedStr): roundedStr;

    }

    private Integer getNumberOfDigitBeforeDecimalPlaces(final String strippedNumber) {

        final Integer digitBeforeDecimalPoint = strippedNumber.contains(".") ? strippedNumber.split("\\.")[0].length() : null;
        numberInfo.setNumberOfDigitsBeforeDecimalPlace(digitBeforeDecimalPoint);

        return digitBeforeDecimalPoint;
    }

    private BigDecimal getRoundedNumber(final int numberOfSignificantFigures,
                                        final String extractedZeros,
                                        final String strippedNumber,
                                        final Character immediateNumber,
                                        final Integer firstSignificantFigurePosition,
                                        final Integer digitBeforeDecimalPoint) {

        final BigDecimal newShortNumber;

        final BigDecimal rounded;

        if(nonNull(digitBeforeDecimalPoint)
                && numberOfSignificantFigures <= digitBeforeDecimalPoint) {

            if (numberInfo.getFirstSignificantFigurePosition() == 0
                    && numberOfSignificantFigures == digitBeforeDecimalPoint) {
                newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, numberOfSignificantFigures);
            } else if (numberOfSignificantFigures < digitBeforeDecimalPoint) {
                newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, digitBeforeDecimalPoint);
            } else {
                newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, (numberOfSignificantFigures - 1));
            }
            rounded = roundNumber(strippedNumber, immediateNumber, newShortNumber, numberOfSignificantFigures);
        } else if  (isNull(digitBeforeDecimalPoint) && isNull((extractedZeros))
                && numberInfo.getNumberLength() < numberOfSignificantFigures){
            rounded = new BigDecimal(strippedNumber);
        }
        else {
            newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, numberOfSignificantFigures);
            rounded = roundNumber(strippedNumber, immediateNumber, newShortNumber, numberOfSignificantFigures);
        }

        return rounded;
    }

    public Integer findFirstSignificantFigurePosition(final String number) {
        final int length = number.length();

        int index = length;

        numberInfo.setNumberLength(length);

        for(int i =0; i< number.length(); i++){

            char numberChar = number.charAt(i);

            if(numberChar != '0' && numberChar != '.'){
                index = i;
                break;
            }
        }
        return (index != number.length()) ? index : null;
    }

    public Character findImmediateNumberToSignificantFigure(final String number,
                                                            final int numberOfSignificantFigures) {

        final Integer firstSignificantFigurePosition = findFirstSignificantFigurePosition(number);

        numberInfo.setFirstSignificantFigurePosition(firstSignificantFigurePosition);

        Character immediateNumber = null;


        if(!(numberOfSignificantFigures >= getNumberLength(number))) {
            immediateNumber = number.charAt(firstSignificantFigurePosition + getIndexSuffex(number, numberOfSignificantFigures));

            if (immediateNumber == '.') {
                immediateNumber = number.charAt(firstSignificantFigurePosition + numberOfSignificantFigures + 1);
                numberInfo.setDecimalPlacePosition(firstSignificantFigurePosition + numberOfSignificantFigures);
                numberInfo.setSignificantFigureImmediateNumberPosition(firstSignificantFigurePosition + numberOfSignificantFigures + 1);
            } else {
                numberInfo.setSignificantFigureImmediateNumberPosition(firstSignificantFigurePosition + getIndexSuffex(number, numberOfSignificantFigures));
            }

            numberInfo.setSignificantFigureImmediateNumber(immediateNumber);

            return immediateNumber;
        }
        return immediateNumber;
    }

    private int getIndexSuffex(String number, int numberOfSignificantFigures) {
        return number.contains(".")
                && (!number.contains("0.")) &&
                numberInfo.getNumberOfDigitsBeforeDecimalPlace() < numberOfSignificantFigures ? numberOfSignificantFigures + 1 : numberOfSignificantFigures;
    }

    private int getNumberLength(final String number) {
        return number.contains(".") ? number.length() -1 : number.length();
    }

    private String addTrailingZeros(final int numberOfSignificantFigures,
                                    final String roundedStr,
                                    final int counter) {

        final StringBuilder concatNumber = new StringBuilder(roundedStr);

        final int numberLength = getNumberLength(roundedStr);

        if(numberOfSignificantFigures > numberLength){
            int zerosToAdd = numberOfSignificantFigures - numberLength + 1;
            for(int i = counter; i < zerosToAdd  ;i++)
                if(i==0) {
                    concatNumber.append(".");
                } else {
                    concatNumber.append("0");
                }
        }

        return concatNumber.toString();
    }

    private BigDecimal roundNumber(final String strippedNumber,
                                   final Character immediateNumber,
                                   final BigDecimal newShortNumber,
                                   final int numberOfSignificantFigures) {

        final BigDecimal rounded;

        if(Objects.nonNull(immediateNumber) && Character.getNumericValue(immediateNumber) >= 5) {

            if (numberOfSignificantFigures < getNumberLength(newShortNumber.toPlainString())) {
                rounded = newShortNumber.round(new MathContext(numberOfSignificantFigures));

            } else if(newShortNumber.toPlainString().contains(".")) {
                rounded = newShortNumber.add(new BigDecimal("0.01")); // dynamically the zeros to add in front the 1.
            } else {
                rounded = newShortNumber.add(BigDecimal.ONE);
            }

        } else if (isNull(immediateNumber) && !strippedNumber.contains(".")) {
            rounded = new BigDecimal(strippedNumber);
        }
        else {
            rounded = newShortNumber;
        }

        return rounded;
    }

    private BigDecimal getShortNumber(final String number,
                                      final int firstSignificantFigurePosition,
                                      final int lastFigurePosition) {

        return new BigDecimal(number.substring(firstSignificantFigurePosition,
                (firstSignificantFigurePosition + (number.contains(".") ? (lastFigurePosition + 1) : lastFigurePosition))));
    }

    public List<String> extractZerosBeforeSignificantFigure(final String fullNumber) {

        final String extractedNumber;

        String extractedZeros = null;

        if(fullNumber.contains("0.")){

            final Integer firstSignificantFigurePosition = findFirstSignificantFigurePosition(fullNumber);

            extractedNumber = fullNumber.substring(firstSignificantFigurePosition, fullNumber.length());

            extractedZeros = fullNumber.substring(0, firstSignificantFigurePosition);

        } else {
            extractedNumber = fullNumber;
        }

        return Arrays.asList(extractedZeros, extractedNumber);

    }
}
