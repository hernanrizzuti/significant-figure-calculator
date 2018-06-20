package com.rizzutih.significantfigurecalculator;

import com.rizzutih.significantfigurecalculator.model.NumberInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by h.rizzuti on 15/06/2018.
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {

        calculator =  new Calculator(new NumberInfo());
    }

    @Test
    public void shouldReturnIndexFiveWhenNonZeroFigureFound() {
        //Given
        final String number = "0.00010";
        //Then
        assertEquals(Integer.valueOf(5), calculator.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturnIndexZeroWhenNumberDoesNotContainZeros() {
        //Given
        final String number = "10.123";
        //Then
        assertEquals(Integer.valueOf(0), calculator.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturnNullWhenNumberIsAllZeros() {
        //Given
        final String number = "000000";
        //Then
        assertEquals(null, calculator.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturn1When101and2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculator.findImmediateNumberToSignificantFigure("101", 2));
    }

    @Test
    public void shouldReturn1When10dot1WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculator.findImmediateNumberToSignificantFigure("10.1", 2));
    }

    @Test
    public void shouldReturn1When0dot101WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculator.findImmediateNumberToSignificantFigure("0.101", 2));
    }

    @Test
    public void shouldReturn1When1010WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculator.findImmediateNumberToSignificantFigure("101", 2));
    }

    @Test
    public void shouldReturn10When10dot5IsPassedInWith2SignificantFigures() {
        assertEquals("11", calculator.resolver("10.5", "2"));
    }

    @Test
    public void shouldReturn10When10dot2IsPassedInWith2SignificantFigures() {
        assertEquals("10", calculator.resolver("10.2", "2"));
    }

    @Test
    public void shouldReturn101When100dot6IsPassedInWith3SignificantFigures() {
        assertEquals("101", calculator.resolver("100.6", "3"));
    }

    @Test
    public void shouldReturn100When99dot6IsPassedInWith3SignificantFigures() {
        assertEquals("99.6", calculator.resolver("99.6", "3"));
    }

    @Test
    public void shouldReturn99When99dot4IsPassedInWith2SignificantFigures() {
        assertEquals("99", calculator.resolver("99.4", "2"));
    }

    @Test
    public void shouldReturn100When99dot4IsPassedInWith1SignificantFigures() {
        assertEquals("100", calculator.resolver("99.4", "1"));
    }

    @Test
    public void shouldReturn90When94dot7IsPassedInWith1SignificantFigures() {
        assertEquals("90", calculator.resolver("94.7", "1"));
    }

    @Test
    public void shouldReturn900When944dot7IsPassedInWith1SignificantFigures() {
        assertEquals("900", calculator.resolver("944.7", "1"));
    }

    @Test
    public void shouldReturn940When944dot7IsPassedInWith2SignificantFigures() {
        assertEquals("940", calculator.resolver("944.7", "2"));
    }

    @Test
    public void shouldReturn945When944dot7IsPassedInWith3SignificantFigures() {
        assertEquals("945", calculator.resolver("944.7", "3"));
    }

    @Test
    public void shouldReturn945dot0When945IsPassedInWith4SignificantFigures() {
        assertEquals("945.0", calculator.resolver("945", "4"));
    }

    @Test
    public void shouldReturn945dot000When945IsPassedInWith6SignificantFigures() {
        assertEquals("945.000", calculator.resolver("945", "6"));
    }

    @Test
    public void shouldReturn945When945IsPassedIn() {
        assertEquals("945", calculator.extractZerosBeforeSignificantFigure("945").get(1));
    }

    @Test
    public void shouldReturn945When0dot945And0dot0945ArePassedIn() {
        assertEquals("945", calculator.extractZerosBeforeSignificantFigure("0.945").get(1));
        assertEquals("945", calculator.extractZerosBeforeSignificantFigure("0.0945").get(1));
    }

    @Test
    public void shouldReturn0dotAnd0dot0When0dot945And0dot0945ArePassedIn() {
        assertEquals("0.", calculator.extractZerosBeforeSignificantFigure("0.945").get(0));
        assertEquals("0.0", calculator.extractZerosBeforeSignificantFigure("0.0945").get(0));
    }

    @Test
    public void shouldReturn0Dot045When9446IsPassedInWith6SignificantFigures() {
        assertEquals("0.045", calculator.resolver("0.0446", "2"));
    }

    @Test
    public void shouldReturn0Dot0446When9446IsPassedInWith3SignificantFigures() {
        assertEquals("0.0446", calculator.resolver("0.0446", "3"));
    }

    @Test
    public void shouldReturn12Dot1When12Dot1IsPassedInWith3SignificantFigures() {
        assertEquals("12.10", calculator.resolver("12.1", "4"));
    }

    @Test
    public void shouldReturn110When108Dot6IsPassedInWith2SignificantFigures() {
        assertEquals("110", calculator.resolver("108.6", "2"));
    }

    @Test
    public void testReturn2Dot00When2Dot000IsPassedInWith3SignificantFigures() {
        assertEquals("2.00", calculator.resolver("2.000", "3"));
    }

    @Test
    public void testReturn2Dot0000When2Dot000IsPassedInWith5SignificantFigures() {
        assertEquals("2.0000", calculator.resolver("2.000", "5"));
    }

    @Test
    public void testReturns2Dot11When2Dot105IsPassedInWith3SignificantFigures() {
        assertEquals("2.11", calculator.resolver("2.105", "3"));
        // 2.105 to 3sf -> 2.11
    }
    @Test
    public void testReturns2Dot101When2Dot1005IsPassedInWith4SignificantFigures() {
        assertEquals("2.101", calculator.resolver("2.1005", "4"));
    }

    @Test
    public void testReturns1500When1529IsPassedInWith2SignificantFigures() {
        assertEquals("1500", calculator.resolver("1529", "2"));
    }


    @Test
    public void testReturns1521When1529IsPassedInWith3SignificantFigures() {
        assertEquals("1530", calculator.resolver("1529", "3"));
    }

    @Test
    public void testReturnsCorrectNumbersWhenDifferentSignificantFigureArePassedIn() {
        //assertEquals("0", calculator.resolver("305.459", 0));
        assertEquals("300", calculator.resolver("305.459", "1"));
        assertEquals("310", calculator.resolver("305.459", "2"));
        assertEquals("305", calculator.resolver("305.459", "3"));
        assertEquals("305.5", calculator.resolver("305.459", "4"));
        assertEquals("305.46", calculator.resolver("305.459", "5"));
        assertEquals("305.459", calculator.resolver("305.459", "6"));
    }

    @Test
    public void test() {
        System.out.println(calculator.resolver("1529", "1"));
        //2000?
    }

    @Test
    public void testReturns1When001IsPassedIn() {

        assertEquals("1", calculator.removeLeadingZeros("001"));
    }

    @Test
    public void testReturns0Dot1When0Dot01IsPassedIn() {
        assertEquals("0.1", calculator.removeLeadingZeros("00.1"));
    }

    @Test
    public void testReturns1Dot1When01Dot1IsPassedIn() {
        assertEquals("1.1", calculator.removeLeadingZeros("01.1"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenLettersAndNumbersArePassedIn() {
        calculator.checkNumber("123a");
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenTwoDotsArePassedIn() {
        calculator.checkNumber("123.12.0");
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenALetterIsPassedIn() {
        calculator.parseSignificantFigure("a");
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSignificantFigureIsZero() {
        calculator.parseSignificantFigure("0");
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSignificantFigureContainsADot() {
        calculator.parseSignificantFigure("1.1");
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSignificantFigureIsBelowZero() {
        calculator.parseSignificantFigure("-5");
    }

    @Test
    public void shouldReturnParseStringToInteger() {
        assertEquals(1, calculator.parseSignificantFigure("1"));
    }

    @Test
    public void shouldPassWhenNumberIsPassedIn() {
        calculator.checkNumber("123");
        calculator.checkNumber("123.01");
    }

    public void printUnorderedPairs( int[] array) {

        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                System.out.println(array[i]+ ","+array[j]);
            }
            System.out.println("");
        }
    }

    void permutation(String str, String prefix){
        if(str.length()==0){
            System.out.println(prefix);
        } else{

        }for (int i=0;i<str.length();i++){
            String rem = str.substring(0,i) +str.substring(i+1);
            permutation(rem,prefix+str.charAt(i));
        }
    }

    //@Test
    public void should() {
        int[] array = {0,1,2,3,4,5,6,7};
     //printUnorderedPairs(array);
        permutation("hello","world");

    }


}