package com.rizzutih.significantfigurecalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by h.rizzuti on 15/06/2018.
 */
public class SignificantFigureConverterTest {

    private SignificantFigureConverter significantFigureConverter;

    @Before
    public void setUp() {
        significantFigureConverter =  new SignificantFigureConverter();
    }

    @Test
    public void shouldReturnIndexFiveWhenNonZeroFigureFound() {
        //Given
        final String number = "0.00010";
        //Then
        assertEquals(Integer.valueOf(5), significantFigureConverter.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturnIndexZeroWhenNumberDoesNotContainZeros() {
        //Given
        final String number = "10.123";
        //Then
        assertEquals(Integer.valueOf(0), significantFigureConverter.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturnNullWhenNumberIsAllZeros() {
        //Given
        final String number = "000000";
        //Then
        assertEquals(null, significantFigureConverter.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturn1When101and2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), significantFigureConverter.findImmediateNumberToSignificantFigure("101", 2));
    }

    @Test
    public void shouldReturn1When10dot1WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), significantFigureConverter.findImmediateNumberToSignificantFigure("10.1", 2));
    }

    @Test
    public void shouldReturn1When0dot101WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), significantFigureConverter.findImmediateNumberToSignificantFigure("0.101", 2));
    }

    @Test
    public void shouldReturn1When1010WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), significantFigureConverter.findImmediateNumberToSignificantFigure("101", 2));
    }

    @Test
    public void shouldReturn10When10dot5IsPassedInWith2SignificantFigures() {
        assertEquals("11", significantFigureConverter.resolver("10.5", 2));
    }

    @Test
    public void shouldReturn10When10dot2IsPassedInWith2SignificantFigures() {
        assertEquals("10", significantFigureConverter.resolver("10.2", 2));
    }

    @Test
    public void shouldReturn101When100dot6IsPassedInWith3SignificantFigures() {
        assertEquals("101", significantFigureConverter.resolver("100.6", 3));
    }

    @Test
    public void shouldReturn100When99dot6IsPassedInWith3SignificantFigures() {
        assertEquals("99.6", significantFigureConverter.resolver("99.6", 3));
    }

    @Test
    public void shouldReturn99When99dot4IsPassedInWith2SignificantFigures() {
        assertEquals("99", significantFigureConverter.resolver("99.4", 2));
    }

    @Test
    public void shouldReturn100When99dot4IsPassedInWith1SignificantFigures() {
        assertEquals("100", significantFigureConverter.resolver("99.4", 1));
    }

    @Test
    public void shouldReturn90When94dot7IsPassedInWith1SignificantFigures() {
        assertEquals("90", significantFigureConverter.resolver("94.7", 1));
    }

    @Test
    public void shouldReturn900When944dot7IsPassedInWith1SignificantFigures() {
        assertEquals("900", significantFigureConverter.resolver("944.7", 1));
    }

    @Test
    public void shouldReturn940When944dot7IsPassedInWith2SignificantFigures() {
        assertEquals("940", significantFigureConverter.resolver("944.7", 2));
    }

    @Test
    public void shouldReturn945When944dot7IsPassedInWith3SignificantFigures() {
        assertEquals("945", significantFigureConverter.resolver("944.7", 3));
    }

    @Test
    public void shouldReturn945dot0When945IsPassedInWith4SignificantFigures() {
        assertEquals("945.0", significantFigureConverter.resolver("945", 4));
    }

    @Test
    public void shouldReturn945dot000When945IsPassedInWith6SignificantFigures() {
        assertEquals("945.000", significantFigureConverter.resolver("945", 6));
    }

    @Test
    public void shouldReturn945When945IsPassedIn() {
        assertEquals("945", significantFigureConverter.extractZerosBeforeSignificantFigure("945").get(1));
    }

    @Test
    public void shouldReturn945When0dot945And0dot0945ArePassedIn() {
        assertEquals("945", significantFigureConverter.extractZerosBeforeSignificantFigure("0.945").get(1));
        assertEquals("945", significantFigureConverter.extractZerosBeforeSignificantFigure("0.0945").get(1));
    }

    @Test
    public void shouldReturn0dotAnd0dot0When0dot945And0dot0945ArePassedIn() {
        assertEquals("0.", significantFigureConverter.extractZerosBeforeSignificantFigure("0.945").get(0));
        assertEquals("0.0", significantFigureConverter.extractZerosBeforeSignificantFigure("0.0945").get(0));
    }

    @Test
    public void shouldReturn0Dot045When9446IsPassedInWith6SignificantFigures() {
        assertEquals("0.045", significantFigureConverter.resolver("0.0446", 2));
    }

    @Test
    public void shouldReturn0Dot0446When9446IsPassedInWith3SignificantFigures() {
        assertEquals("0.0446", significantFigureConverter.resolver("0.0446", 3));
    }

    @Test
    public void shouldReturn12Dot1When12Dot1IsPassedInWith3SignificantFigures() {
        assertEquals("12.10", significantFigureConverter.resolver("12.1", 4));
    }

    @Test
    public void shouldReturn110When108Dot6IsPassedInWith2SignificantFigures() {
        assertEquals("110", significantFigureConverter.resolver("108.6", 2));
    }

    @Test
    public void testReturn2Dot00When2Dot000IsPassedInWith3SignificantFigures() {
        assertEquals("2.00",significantFigureConverter.resolver("2.000", 3));
    }

    @Test
    public void testReturn2Dot0000When2Dot000IsPassedInWith5SignificantFigures() {
        assertEquals("2.0000",significantFigureConverter.resolver("2.000", 5));
    }

    @Test
    public void testReturns2Dot11When2Dot105IsPassedInWith3SignificantFigures() {
        assertEquals("2.11", significantFigureConverter.resolver("2.105", 3));
        // 2.105 to 3sf -> 2.11
    }
    @Test
    public void testReturns2Dot101When2Dot1005IsPassedInWith4SignificantFigures() {
        assertEquals("2.101", significantFigureConverter.resolver("2.1005", 4));
    }

    @Test
    public void test() {
        System.out.println(significantFigureConverter.resolver("2.1005", 4));
        // 2.105 to 3sf -> 2.11
    }

}