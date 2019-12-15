package com.netcracker.interviewschedulerauthorization.unit.combinealgorithms;

import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PermitIfAllPermittedTest {

    CombineAlgorithm algorithm = CombineAlgorithm.PERMIT_IF_ALL_PERMITTED;

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
    public void arrayOfTrueTest() {
        assertTrue(algorithm.combine(Arrays.asList(true, true, true)));
    }

    @Test
    public void arrayOfTrueAndFalseTest(){
        assertFalse(algorithm.combine(Arrays.asList(true, false, true)));
    }

}
