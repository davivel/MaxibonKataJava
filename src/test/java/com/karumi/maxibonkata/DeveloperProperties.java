package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Created by David A. Velasco on 28/7/17.
 */


@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    private static final String ANY_NAME = "Pedro";
    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    @Property public void theNumberOfMaxibonsAssignedIsPositiveOrZero(int numberOfMaxibons) {
        System.out.println("-----------------> " + numberOfMaxibons);

        Developer developer = new Developer(ANY_NAME, numberOfMaxibons);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

}
