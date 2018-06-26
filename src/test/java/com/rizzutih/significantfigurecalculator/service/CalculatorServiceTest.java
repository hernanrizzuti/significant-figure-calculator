package com.rizzutih.significantfigurecalculator.service;

import com.rizzutih.significantfigurecalculator.model.NumberInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by h.rizzuti on 15/06/2018.
 */
public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @Before
    public void setUp() {

        calculatorService =  new CalculatorService();
    }

    @Test
    public void shouldReturnIndexFiveWhenNonZeroFigureFound() {
        //Given
        final String number = "0.00010";
        //Then
        assertEquals(Integer.valueOf(5), calculatorService.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturnIndexZeroWhenNumberDoesNotContainZeros() {
        //Given
        final String number = "10.123";
        //Then
        assertEquals(Integer.valueOf(0), calculatorService.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturnNullWhenNumberIsAllZeros() {
        //Given
        final String number = "000000";
        //Then
        assertEquals(null, calculatorService.findFirstSignificantFigurePosition(number));
    }

    @Test
    public void shouldReturn1When101and2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculatorService.findImmediateNumberToSignificantFigure("101", 2));
    }

    @Test
    public void shouldReturn1When10dot1WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculatorService.findImmediateNumberToSignificantFigure("10.1", 2));
    }

    @Test
    public void shouldReturn1When0dot101WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculatorService.findImmediateNumberToSignificantFigure("0.101", 2));
    }

    @Test
    public void shouldReturn1When1010WithDPAnd2SFarePAssedIn() {
        assertEquals(Character.valueOf('1'), calculatorService.findImmediateNumberToSignificantFigure("101", 2));
    }

    @Test
    public void shouldReturn10When10dot5IsPassedInWith2SignificantFigures() {
        assertEquals("11", calculatorService.calculate("10.5", 2));
    }

    @Test
    public void shouldReturn10When10dot2IsPassedInWith2SignificantFigures() {
        assertEquals("10", calculatorService.calculate("10.2", 2));
    }

    @Test
    public void shouldReturn101When100dot6IsPassedInWith3SignificantFigures() {
        assertEquals("101", calculatorService.calculate("100.6", 3));
    }

    @Test
    public void shouldReturn100When99dot6IsPassedInWith3SignificantFigures() {
        assertEquals("99.6", calculatorService.calculate("99.6", 3));
    }

    @Test
    public void shouldReturn99When99dot4IsPassedInWith2SignificantFigures() {
        assertEquals("99", calculatorService.calculate("99.4", 2));
    }

    @Test
    public void shouldReturn100When99dot4IsPassedInWith1SignificantFigures() {
        assertEquals("100", calculatorService.calculate("99.4", 1));
    }

    @Test
    public void shouldReturn90When94dot7IsPassedInWith1SignificantFigures() {
        assertEquals("90", calculatorService.calculate("94.7", 1));
    }

    @Test
    public void shouldReturn900When944dot7IsPassedInWith1SignificantFigures() {
        assertEquals("900", calculatorService.calculate("944.7", 1));
    }

    @Test
    public void shouldReturn940When944dot7IsPassedInWith2SignificantFigures() {
        assertEquals("940", calculatorService.calculate("944.7", 2));
    }

    @Test
    public void shouldReturn945When944dot7IsPassedInWith3SignificantFigures() {
        assertEquals("945", calculatorService.calculate("944.7", 3));
    }

    @Test
    public void shouldReturn945dot0When945IsPassedInWith4SignificantFigures() {
        assertEquals("945.0", calculatorService.calculate("945", 4));
    }

    @Test
    public void shouldReturn945dot000When945IsPassedInWith6SignificantFigures() {
        assertEquals("945.000", calculatorService.calculate("945", 6));
    }

    @Test
    public void shouldReturn945When945IsPassedIn() {
        assertEquals("945", calculatorService.extractZerosBeforeSignificantFigure("945").get(1));
    }

    @Test
    public void shouldReturn945When0dot945And0dot0945ArePassedIn() {
        assertEquals("945", calculatorService.extractZerosBeforeSignificantFigure("0.945").get(1));
        assertEquals("945", calculatorService.extractZerosBeforeSignificantFigure("0.0945").get(1));
    }

    @Test
    public void shouldReturn0dotAnd0dot0When0dot945And0dot0945ArePassedIn() {
        assertEquals("0.", calculatorService.extractZerosBeforeSignificantFigure("0.945").get(0));
        assertEquals("0.0", calculatorService.extractZerosBeforeSignificantFigure("0.0945").get(0));
    }

    @Test
    public void shouldReturn0Dot045When9446IsPassedInWith6SignificantFigures() {
        assertEquals("0.045", calculatorService.calculate("0.0446", 2));
    }

    @Test
    public void shouldReturn0Dot0446When9446IsPassedInWith3SignificantFigures() {
        assertEquals("0.0446", calculatorService.calculate("0.0446", 3));
    }

    @Test
    public void shouldReturn12Dot1When12Dot1IsPassedInWith3SignificantFigures() {
        assertEquals("12.10", calculatorService.calculate("12.1", 4));
    }

    @Test
    public void shouldReturn110When108Dot6IsPassedInWith2SignificantFigures() {
        assertEquals("110", calculatorService.calculate("108.6", 2));
    }

    @Test
    public void testReturn2Dot00When2Dot000IsPassedInWith3SignificantFigures() {
        assertEquals("2.00", calculatorService.calculate("2.000", 3));
    }

    @Test
    public void testReturn2Dot0000When2Dot000IsPassedInWith5SignificantFigures() {
        assertEquals("2.0000", calculatorService.calculate("2.000", 5));
    }

    @Test
    public void testReturns2Dot11When2Dot105IsPassedInWith3SignificantFigures() {
        assertEquals("2.11", calculatorService.calculate("2.105", 3));
    }
    @Test
    public void testReturns2Dot101When2Dot1005IsPassedInWith4SignificantFigures() {
        assertEquals("2.101", calculatorService.calculate("2.1005", 4));
    }

    @Test
    public void testReturns1500When1529IsPassedInWith2SignificantFigures() {
        assertEquals("1500", calculatorService.calculate("1529", 2));
    }


    @Test
    public void testReturns1521When1529IsPassedInWith3SignificantFigures() {
        assertEquals("1530", calculatorService.calculate("1529", 3));
    }

    @Test
    public void testReturnsCorrectNumbersWhenDifferentSignificantFigureArePassedIn() {
        //assertEquals("0", calculatorService.calculate("305.459", 0));
        assertEquals("300", calculatorService.calculate("305.459", 1));
        assertEquals("310", calculatorService.calculate("305.459", 2));
        assertEquals("305", calculatorService.calculate("305.459", 3));
        assertEquals("305.5", calculatorService.calculate("305.459", 4));
        assertEquals("305.46", calculatorService.calculate("305.459", 5));
        assertEquals("305.459", calculatorService.calculate("305.459", 6));
    }

    @Test
    public void test() {
        System.out.println(calculatorService.calculate("1529", 1));
        //2000?
    }

    @Test
    public void testReturns1When001IsPassedIn() {

        assertEquals("1", calculatorService.removeLeadingZeros("001"));
    }

    @Test
    public void testReturns0Dot1When0Dot01IsPassedIn() {
        assertEquals("0.1", calculatorService.removeLeadingZeros("00.1"));
    }

    @Test
    public void testReturns1Dot1When01Dot1IsPassedIn() {
        assertEquals("1.1", calculatorService.removeLeadingZeros("01.1"));
    }

    //O(n^2)
    public void printUnorderedPairs( int[] array) {

        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                System.out.println(array[i]+ ","+array[j]);
            }
            System.out.println("");
        }
    }

    //O(n!)
    void permutation(String str, String prefix){
        if(str.length()==0){
            System.out.println(prefix);
        } else{

        }for (int i=0;i<str.length();i++){
            String rem = str.substring(0,i) +str.substring(i+1);
            permutation(rem,prefix+str.charAt(i));
        }
    }
    //O(log n)
    public int powerOf2(int n){
        if(n < 1){
            return 0;
        } else if (n == 1){
            System.out.println(1);
            return 1;
        } else {
            int prev = powerOf2(n/2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }
    }

    @Test
    public void should() {
        int[] array = {0,1,2,3,4,5,6,7};
     //printUnorderedPairs(array);
        //permutation("hello","world");
        powerOf2(1000000000);//50 -> 4ms, 100 -> 2ms, 1000 -> 2ms,10000 -> 3ms 1000000 -> 16ms

    }


}