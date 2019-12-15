package com.netcracker.interviewschedulerauthorization.unit.combinealgorithms;

import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DenyIfNotPermitted {

    CombineAlgorithm algorithm = CombineAlgorithm.DENY_IF_NOT_PERMITTED;

    @Test
    public void nullArrayTest() {
        assertFalse(algorithm.combine(null));
    }

    @Test
    public void emptyArrayTest() {
        assertFalse(algorithm.combine(Arrays.asList()));
    }

    @Test
    public void allFalseArrayTest() {
        assertFalse(algorithm.combine(Arrays.asList(false, false, false)));
    }

    @Test
    public void allTrueArrayTest() {
        assertTrue(algorithm.combine(Arrays.asList(true, true, true)));
    }

    @Test
    public void trueAndFalseArrayTest(){
        assertTrue(algorithm.combine(Arrays.asList(true, false, true)));
    }

}
