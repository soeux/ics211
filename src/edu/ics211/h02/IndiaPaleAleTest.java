package edu.ics211.h02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the IndiaPaleAle class.
 *
 * @author Christian Mancha
 * Credit: Cam Moore for the testing template.
 */
public class IndiaPaleAleTest {
    /**
     * Test method for {@link edu.ics211.h02.IndiaPaleAle#IndiaPaleAle(java.lang.String)}.
     */
    @Test
    public void testIndiaPaleAleString() {
        IndiaPaleAle india = new IndiaPaleAle("Warrior");
        assertNotNull("Should have created a IndiaPaleAle", india);
        assertEquals("Should have the right name", "Warrior", india.getName());
        assertEquals("Should have the right BeerType", BeerType.INDIA_PALE_ALE, india.getType());
        assertTrue("Should have valid IBU", india.getIbu() > 39 && india.getIbu() < 101);
        assertTrue("Should have valid ABV", india.getAbv() >= 5.0 && india.getAbv() <= 10.0);
    }

    /**
     * Test method for {@link edu.ics211.h02.IndiaPaleAle#IndiaPaleAle(java.lang.String, java.lang.Integer, java.lang.Double)}.
     */
    @Test
    public void testIndiaPaleAleStringIntegerDouble() {
        IndiaPaleAle india = new IndiaPaleAle("Warrior", 55, 8.3);
        assertNotNull("Should have created a IndiaPaleAle", india);
        assertEquals("Should have the right name", "Warrior", india.getName());
        assertTrue("Should have the right IBU", 55 == india.getIbu());
        assertEquals("Should have the right ABV", 8.3, india.getAbv(), 0.0001);
        try {
            india = new IndiaPaleAle("Yikes", 243, 8.0);
            fail("Should not create IndiaPaleAle with that IBU");
        } catch (IllegalArgumentException iae) {
            // ??
        }
        try {
            india = new IndiaPaleAle("Yikes", 44, 12.4);
            fail("Should not create IndiaPaleAle with that ABV");
        } catch (IllegalArgumentException iae) {
            // ??
        }

    }

    /**
     * Test method for {@link edu.ics211.h02.Beer#setName(java.lang.String)}.
     */
    @Test
    public void testSetName() {
        IndiaPaleAle india = new IndiaPaleAle("Yikes India Pale Ale", 78, 7.8);
        assertEquals("Should have the right name", "Yikes India Pale Ale", india.getName());
        india.setName("Warrior");
        assertEquals("Should have the right name", "Warrior", india.getName());
    }

}
