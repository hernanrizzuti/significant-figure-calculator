package com.rizzutih.significantfigurecalculator.exercises;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by h.rizzuti on 22/06/2018.
 */
public class Ch1ArrayAndStringsTest {

    @Test
    public void testIsUnique() {
        Ch1ArrayAndStrings ch1ArrayAndStrings = new Ch1ArrayAndStrings();
        assertEquals(false, ch1ArrayAndStrings.isUnique("herny"));
        assertEquals(true, ch1ArrayAndStrings.isUnique("hernan"));
    }

}