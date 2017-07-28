package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by David A. Velasco on 28/7/17.
 */


@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperties {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Chat chat;

    @Property public void theNumberOfMaxibonsIsGreaterThanTwo(
        @From(DevelopersGenerator.class) Developer developer
    ) {
        System.out.println("-----------------> " + developer);

        KarumiHQs hq = new KarumiHQs();
        hq.openFridge(developer);

        assertTrue(hq.getMaxibonsLeft() > 2);
    }

    @Property public void theNumberOfMaxibonsIsGreaterThanTwoGroup(
        List<@From(DevelopersGenerator.class) Developer> developers
    ) {
        System.out.println("-----------------> " + developers.size());

        KarumiHQs hq = new KarumiHQs();
        hq.openFridge(developers);

        assertTrue(hq.getMaxibonsLeft() > 2);
    }

    @Property public void aMessageIsSentThroughChatEverytimeTheNumberOfMaxibonsIsBelowTwo(
        @From(HungryDevelopersGenerator.class) Developer developer
    ) {
        System.out.println("-----------------> " + developer);

        KarumiHQs hq = new KarumiHQs(chat);
        hq.openFridge(developer);

        verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

    /*
    @Property public void aMessageIsSentThroughChatEverytimeTheNumberOfMaxibonsIsBelowTwoGroup(
        List<@From(HungryDevelopersGenerator.class) Developer> developers
    ) {
        System.out.println("-----------------> " + developers.size());

        KarumiHQs hq = new KarumiHQs(chat);
        hq.openFridge(developers);

        verify(chat, atLeastOnce()).sendMessage(anyString());
    }
    */



}
