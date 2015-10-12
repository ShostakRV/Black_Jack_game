package com.my.application;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
public class Test1 {

    private static Collection<Integer> values;

    @BeforeClass
    public static void initCollection() {
        values = new LinkedList<>();
    }

    @Before
    public void fillValues() {
        values.add(1);
        values.add(2);
        values.add(3);
    }

    @Test
    public void testSizeIsThree() {
        assertTrue(values.size() == 3);
    }

    @Test
    public void testSizeIsSmallerThanFive() {
        assertTrue(values.size() <= 5);
    }

}
