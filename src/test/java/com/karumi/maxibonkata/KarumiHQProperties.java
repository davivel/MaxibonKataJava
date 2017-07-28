package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by David A. Velasco on 28/7/17.
 */


@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperties {

    @Property public void theNumberOfMaxibonsIsGreaterThanTwo(
        @From(DevelopersGenerator.class) Developer developer
    ) {
        System.out.println("-----------------> " + developer);

        KarumiHQs hq = new KarumiHQs();
        hq.openFridge(developer);

        assertTrue(hq.getMaxibonsLeft() > 2);
    }

    @Property public void theNumberOfMaxibonsIsGreaterThanTwo(
        List<@From(DevelopersGenerator.class) Developer> developers
    ) {
        System.out.println("-----------------> " + developers.size());

        KarumiHQs hq = new KarumiHQs();
        hq.openFridge(developers);

        assertTrue(hq.getMaxibonsLeft() > 2);
    }

}
