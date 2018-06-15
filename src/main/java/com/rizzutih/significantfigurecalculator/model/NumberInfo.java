package com.rizzutih.significantfigurecalculator.model;

/**
 * Created by h.rizzuti on 15/06/2018.
 */
public class NumberInfo {

    private Integer firstSignificantFigurePosition;

    private Integer decimalPlacePosition;

    private Integer numberOfDigitsBeforeDecimalPlace;

    private int significantFigureImmediateNumberPosition;

    private char significantFigureImmediateNumber;

    private int numberLength;

    public Integer getFirstSignificantFigurePosition() {
        return firstSignificantFigurePosition;
    }

    public void setFirstSignificantFigurePosition(Integer firstSignificantFigurePosition) {
        this.firstSignificantFigurePosition = firstSignificantFigurePosition;
    }

    public Integer getDecimalPlacePosition() {
        return decimalPlacePosition;
    }

    public void setDecimalPlacePosition(Integer decimalPlacePosition) {
        this.decimalPlacePosition = decimalPlacePosition;
    }

    public Integer getNumberOfDigitsBeforeDecimalPlace() {
        return numberOfDigitsBeforeDecimalPlace;
    }

    public void setNumberOfDigitsBeforeDecimalPlace(Integer numberOfDigitsBeforeDecimalPlace) {
        this.numberOfDigitsBeforeDecimalPlace = numberOfDigitsBeforeDecimalPlace;
    }

    public int getSignificantFigureImmediateNumberPosition() {
        return significantFigureImmediateNumberPosition;
    }

    public void setSignificantFigureImmediateNumberPosition(int significantFigureImmediateNumberPosition) {
        this.significantFigureImmediateNumberPosition = significantFigureImmediateNumberPosition;
    }

    public char getSignificantFigureImmediateNumber() {
        return significantFigureImmediateNumber;
    }

    public void setSignificantFigureImmediateNumber(char significantFigureImmediateNumber) {
        this.significantFigureImmediateNumber = significantFigureImmediateNumber;
    }

    public int getNumberLength() {
        return numberLength;
    }

    public void setNumberLength(int numberLength) {
        this.numberLength = numberLength;
    }

}
