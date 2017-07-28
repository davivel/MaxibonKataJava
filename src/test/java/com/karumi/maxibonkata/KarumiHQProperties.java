package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

/**
 * Created by David A. Velasco on 28/7/17.
 */


@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperties {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        karumiHQs = new KarumiHQs(chat);
    }

    KarumiHQs karumiHQs;

    @Mock
    Chat chat;

    @Property public void theNumberOfMaxibonsIsGreaterThanTwo(
        @From(DevelopersGenerator.class) Developer developer
    ) {
        System.out.println("-----------------> " + developer);

        karumiHQs.openFridge(developer);

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property public void theNumberOfMaxibonsIsGreaterThanTwoGroup(
        List<@From(DevelopersGenerator.class) Developer> developers
    ) {
        System.out.println("-----------------> " + developers.size());

        karumiHQs.openFridge(developers);

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property public void aMessageIsSentThroughChatEverytimeTheNumberOfMaxibonsIsBelowTwo(
        @From(HungryDevelopersGenerator.class) Developer developer
    ) {
        System.out.println("-----------------> " + developer);

        karumiHQs.openFridge(developer);

        verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

    /*
    @Property public void aMessageIsSentThroughChatEverytimeTheNumberOfMaxibonsIsBelowTwoGroup(
        List<@From(HungryDevelopersGenerator.class) Developer> developers
    ) {
        System.out.println("-----------------> " + developers.size());

        karumiHQs.openFridge(developers);

        verify(chat, atLeastOnce()).sendMessage(anyString());
    }
    */


    @Property public void aMessageIsNeverSentIfTheNumberOfMaxibonsIsGreaterThanTwo(
        @From(NotSoHungryDevelopersGenerator.class) Developer developer
    ) {
        System.out.println("-----------------> " + developer);

        karumiHQs.openFridge(developer);

        verify(chat, never()).sendMessage(anyString());
    }

    @Test
    public void shouldBe7LeftWhenPedroOpensTheFridge() {
        Developer developer = Karumies.PEDRO;

        karumiHQs.openFridge(developer);

        assertEquals(7, karumiHQs.getMaxibonsLeft());
    }

    @Test
    public void shouldBeLeft12WhenJorgeGrabs8() {
        Developer developer = new Developer(Karumies.JORGE.getName(), 8);

        karumiHQs.openFridge(developer);

        assertEquals(12, karumiHQs.getMaxibonsLeft());
    }

    @Test
    public void shouldBeLeft3WhenAlbertoGrabs7() {
        Developer developer = new Developer(Karumies.ALBERTO.getName(), 7);

        karumiHQs.openFridge(developer);

        assertEquals(3, karumiHQs.getMaxibonsLeft());
    }

    @Test
    public void shouldBeLeft10WhenDavideGrabs0() {
        Developer developer = Karumies.DAVIDE;

        karumiHQs.openFridge(developer);

        assertEquals(10, karumiHQs.getMaxibonsLeft());
    }

    @Test
    public void shouldBeLeft10WhenSergioGrabs10() {
        Developer developer = new Developer(Karumies.SERGIO.getName(), 10);

        karumiHQs.openFridge(developer);

        assertEquals(10, karumiHQs.getMaxibonsLeft());
    }
}
