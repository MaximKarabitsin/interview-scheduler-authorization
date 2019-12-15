package com.netcracker.interviewschedulerauthorization.unit.combinealgorithms;

import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PermitIf2PermittedTest {

    CombineAlgorithm algorithm = CombineAlgorithm.PERMIT_IF_2_PERMITTED;

    @Test
    public void nullArrayTest() {
        assertFalse(algorithm.combine(null));
    }

    @Test
    public void emptyArrayTest() {
        assertFalse(algorithm.combine(Arrays.asList()));
    }

    @Test
    public void arrayOfFalseTest() {
        assertFalse(algorithm.combine(Arrays.asList(false, false, false)));
    }

    @Test
    public void arrayOf1TrueTest() {
        assertFalse(algorithm.combine(Arrays.asList(true)));
    }

    @Test
    public void arrayOf2TrueTest(){
        assertTrue(algorithm.combine(Arrays.asList(true, true)));
    }

    @Test
    public void arrayOfMoreThan2TrueTest(){
        assertTrue(algorithm.combine(Arrays.asList(true, true, true, false)));
    }

}
