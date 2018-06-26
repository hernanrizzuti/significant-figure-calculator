package com.rizzutih.significantfigurecalculator.exercises;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by h.rizzuti on 22/06/2018.
 */
public class Ch1ArrayAndStringsTest {

    private Ch1ArrayAndStrings ch1ArrayAndStrings;

    @Before
    public void setUp() {
         ch1ArrayAndStrings = new Ch1ArrayAndStrings();
    }

    @Test
    public void testIsUnique() {
        assertEquals(true, ch1ArrayAndStrings.isUnique("herny"));
        assertEquals(false, ch1ArrayAndStrings.isUnique("hernan"));
        assertEquals(false, ch1ArrayAndStrings.isUniqueChar("hernan"));
    }

    @Test
    public void testPermutation() {
        assertTrue(ch1ArrayAndStrings.permutation("herny", "nyhre"));
        assertFalse(ch1ArrayAndStrings.permutation("herny", "nyrqe"));
    }

    @Test
    public void testURLify() {
        // assertEquals("Mr%20Tom%20atoes", ch1ArrayAndStrings.urlify("Mr Tom atoes     ", 12));
    }

}