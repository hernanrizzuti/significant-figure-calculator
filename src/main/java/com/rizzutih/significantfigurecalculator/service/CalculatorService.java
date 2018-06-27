package com.rizzutih.significantfigurecalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by h.rizzuti on 15/06/2018.
 */
@Service
public class CalculatorService {

    private Integer firstSignificantFigurePosition;

    private Integer numberOfDigitsBeforeDecimalPlace;

    private int numberLength;

    private String originalNumber;

    public String calculate(final String number,
                            final int numberOfSignificantFigures) {

        final List<String> zerosAndNumber = extractZerosBeforeSignificantFigure(removeLeadingZeros(number));

        final String extractedZeros =  zerosAndNumber.get(0);

        final String strippedNumber =  zerosAndNumber.get(1);

        originalNumber = strippedNumber;

        final Character immediateNumber = findImmediateNumberToSignificantFigure(strippedNumber, numberOfSignificantFigures);

        if(nonNull(numberOfDigitsBeforeDecimalPlace)) {
            if(numberOfSignificantFigures == getNumberLength(strippedNumber)) {
                return strippedNumber;
            }

            if (numberOfSignificantFigures > getNumberLength(strippedNumber)){
                return addTrailingZeros(numberOfSignificantFigures, strippedNumber, 1, getNumberLength(strippedNumber));
            }
        }

        final BigDecimal rounded = getRoundedNumber(numberOfSignificantFigures, extractedZeros, strippedNumber,
                immediateNumber, firstSignificantFigurePosition, numberOfDigitsBeforeDecimalPlace);

        String roundedStr = rounded.toPlainString();

        if (nonNull(numberOfDigitsBeforeDecimalPlace) && numberOfSignificantFigures < numberOfDigitsBeforeDecimalPlace) {
            roundedStr = rounded.subtract(new BigDecimal(rounded.toPlainString().substring(
                    (firstSignificantFigurePosition + numberOfSignificantFigures)))).toPlainString();

        }

        if(!roundedStr.contains(".") && isNull((extractedZeros))) {
            roundedStr = addTrailingZeros(numberOfSignificantFigures, roundedStr, 0, numberLength);
        }

        return nonNull(extractedZeros) ? (extractedZeros + roundedStr): roundedStr;

    }

    private Integer setNumberOfDigitBeforeDecimalPlaces(final String strippedNumber) {

        numberOfDigitsBeforeDecimalPlace = strippedNumber.contains(".") ? strippedNumber.split("\\.")[0].length() : null;

        return numberOfDigitsBeforeDecimalPlace;
    }

    private BigDecimal getRoundedNumber(final int numberOfSignificantFigures,
                                        final String extractedZeros,
                                        final String strippedNumber,
                                        final Character immediateNumber,
                                        final Integer firstSignificantFigurePosition,
                                        final Integer digitBeforeDecimalPoint) {

        final BigDecimal newShortNumber;

        final BigDecimal rounded;

        if(nonNull(digitBeforeDecimalPoint) && numberOfSignificantFigures <= digitBeforeDecimalPoint) {

            if (firstSignificantFigurePosition == 0
                    && numberOfSignificantFigures == digitBeforeDecimalPoint) {
                newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, numberOfSignificantFigures);
            } else if (numberOfSignificantFigures < digitBeforeDecimalPoint) {
                newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, digitBeforeDecimalPoint);
            } else {
                newShortNumber = getShortNumber(strippedNumber, firstSignificantFigurePosition, (numberOfSignificantFigures - 1));
            }
            rounded = roundNumber(strippedNumber, immediateNumber, newShortNumber, numberOfSignificantFigures);
        } else if  (isNull(digitBeforeDecimalPoint) && isNull((extractedZeros))
                && numberLength < numberOfSignificantFigures){
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

        numberLength = length;

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

        firstSignificantFigurePosition = findFirstSignificantFigurePosition(number);

        setNumberOfDigitBeforeDecimalPlaces(number);

        Character immediateNumber = null;

        if(!(numberOfSignificantFigures >= getNumberLength(number))) {
            immediateNumber = number.charAt(firstSignificantFigurePosition + getIndexSuffix(number, numberOfSignificantFigures));

            if (immediateNumber == '.') {
                immediateNumber = number.charAt(firstSignificantFigurePosition + numberOfSignificantFigures + 1);
            }

            return immediateNumber;
        }
        return immediateNumber;
    }

    private int getIndexSuffix(String number, int numberOfSignificantFigures) {
        return number.contains(".")
                && (!Pattern.matches("0+([.]\\d+)?", number)) &&
                numberOfDigitsBeforeDecimalPlace < numberOfSignificantFigures ? numberOfSignificantFigures + 1 : numberOfSignificantFigures;
    }

    private int getNumberLength(final String number) {
        return number.contains(".") ? number.length() -1 : number.length();
    }

    private String addTrailingZeros(final int numberOfSignificantFigures,
                                    final String roundedStr,
                                    final int counter,
                                    final int numberLength) {

        final StringBuilder concatNumber = new StringBuilder(roundedStr);

        if(numberOfSignificantFigures > numberLength){
            int zerosToAdd = numberOfSignificantFigures - numberLength + 1;
            createZeros(counter, concatNumber, zerosToAdd);
        }
        else if(numberOfSignificantFigures < numberLength && !originalNumber.contains(".")){
            int zerosToAdd =  (numberLength - numberOfSignificantFigures) + 1;
            createZeros(1, concatNumber, zerosToAdd);
        }

        return concatNumber.toString();
    }

    private void createZeros(int counter, StringBuilder concatNumber, int zerosToAdd) {
        for(int i = counter; i < zerosToAdd  ;i++)
            if(i==0) {
                concatNumber.append(".");
            } else {
                concatNumber.append("0");
            }
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
                final String[] newShortNumberSplit = newShortNumber.toPlainString().split("\\.");
                final int zerosToAddBeforeDecimalPlaces = newShortNumberSplit[0].length() ;
                final int zerosToAddAfterDecimalPlaces = newShortNumberSplit[1].length() -1 ;
                final StringBuilder zerosBefore = new StringBuilder();

                createZeros(1, zerosBefore, zerosToAddBeforeDecimalPlaces + 1);
                createZeros(0, zerosBefore, zerosToAddAfterDecimalPlaces + 1);

                rounded = newShortNumber.add(new BigDecimal(zerosBefore.append("1").toString()));
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

    public String removeLeadingZeros(String number) {
        if (number.length() <= 1) {
            return number;
        }

        char c = number.charAt(0);
        if (c != '0') {
            return number;
        } else {
            return number.charAt(1) == '.' ? number : removeLeadingZeros(number.substring(1));
        }
    }

}
