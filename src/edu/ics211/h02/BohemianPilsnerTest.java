package edu.ics211.h02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Represents a BohemianPilsnerTest.
 *
 * @author Cam Moore
 *
 */
public class BohemianPilsnerTest {

    /**
     * Tests the BohemianPilsner(String name) constructor.
     */
    @Test
    public void testBohemianPilsnerString() {
        BohemianPilsner pils = new BohemianPilsner("Bohemian Rapsody");
        assertNotNull("Should have created a BohemianPilsner", pils);
        assertEquals("Should have the right name", "Bohemian Rapsody", pils.getName());
        assertEquals("Should have the right BeerType", BeerType.BOHEMIAN_PILSNER, pils.getType());
        assertTrue("Should have valid ibu", pils.getIbu() > 34 && pils.getIbu() < 46);
        assertTrue("Should have valid abv", pils.getAbv() >= 4.2 && pils.getAbv() <= 5.4);

    }

    /**
     * Tests the BohemianPilsner(String name, Integer ibu, Double abv) constructor.
     */
    @Test
    public void testPilsnerStringIntegerDouble() {
        Integer ibu = 40;
        Double abv = 5.0;
        BohemianPilsner pils = new BohemianPilsner("My Monday Pils", ibu, abv);
        assertNotNull("Should have created a Pilsner", pils);
        assertEquals("Wrong name", "My Monday Pils", pils.getName());
        assertEquals("Wrong BeerType", BeerType.BOHEMIAN_PILSNER, pils.getType());
        assertEquals("Wrong ibu", ibu, pils.getIbu());
        assertEquals("Wrong abv", abv, pils.getAbv(), 0.0001);
        try {
            ibu = 24; // too low
            pils = new BohemianPilsner("Bad, Bad, Bad", ibu, abv);
            fail("Should not create a Pilsner with low IBU");
        } catch (IllegalArgumentException iae) {
            // this should happen.
        }
        try {
            ibu = 35; // low edge
            pils = new BohemianPilsner("Edge case", ibu, abv);
            assertNotNull("Should have created a Pilsner", pils);
            assertEquals("Wrong BeerType", BeerType.BOHEMIAN_PILSNER, pils.getType());
            assertEquals("Wrong ibu", ibu, pils.getIbu());
            assertEquals("Wrong abv", abv, pils.getAbv(), 0.0001);
        } catch (IllegalArgumentException iae) {
            fail("IBU 35 is ok for Pilsner");
        }
        try {
            ibu = 46; // too high
            pils = new BohemianPilsner("Bad, Bad, Bad", ibu, abv);
            fail("Should not create a Pilsner with high IBU");
        } catch (IllegalArgumentException iae) {
            // this should happen.
        }
        try {
            ibu = 45; // high edge
            pils = new BohemianPilsner("Edge case", ibu, abv);
            assertNotNull("Should have created a Pilsner", pils);
            assertEquals("Wrong BeerType", BeerType.BOHEMIAN_PILSNER, pils.getType());
            assertEquals("Wrong ibu", ibu, pils.getIbu());
            assertEquals("Wrong abv", abv, pils.getAbv(), 0.0001);
        } catch (IllegalArgumentException iae) {
            fail("IBU 45 is ok for Pilsner");
        }
        try {
            ibu = 35; // ok
            abv = 4.1; // too low
            pils = new BohemianPilsner("Bad, Bad, Bad", ibu, abv);
            fail("Should not create a Pilsner with low ABV");
        } catch (IllegalArgumentException iae) {
            // this should happen.
        }
        try {
            abv = 4.2; // low edge
            pils = new BohemianPilsner("Edge case", ibu, abv);
            assertNotNull("Should have created a Pilsner", pils);
            assertEquals("Wrong BeerType", BeerType.BOHEMIAN_PILSNER, pils.getType());
            assertEquals("Wrong ibu", ibu, pils.getIbu());
            assertEquals("Wrong abv", abv, pils.getAbv(), 0.0001);
        } catch (IllegalArgumentException iae) {
            fail("ABV 4.2 is ok for Pilsner");
        }
        try {
            abv = 5.5; // too high
            pils = new BohemianPilsner("Bad, Bad, Bad", ibu, abv);
            fail("Should not create a Pilsner with high ABV");
        } catch (IllegalArgumentException iae) {
            // this should happen.
        }
        try {
            abv = 5.4; // high edge
            pils = new BohemianPilsner("Edge case", ibu, abv);
            assertNotNull("Should have created a Pilsner", pils);
            assertEquals("Wrong BeerType", BeerType.BOHEMIAN_PILSNER, pils.getType());
            assertEquals("Wrong ibu", ibu, pils.getIbu());
            assertEquals("Wrong abv", abv, pils.getAbv(), 0.0001);
        } catch (IllegalArgumentException iae) {
            fail("ABV 6.0 is ok for Pilsner");
        }
    }

    /**
     * Test method for {@link edu.ics211.h02.Beer#setName(java.lang.String)}.
     */
    @Test
    public void testSetName() {
        BohemianPilsner pils = new BohemianPilsner("My Poorly Named Pils", 35, 5.0);
        assertEquals("Should have the right name", "My Poorly Named Pils", pils.getName());
        pils.setName("Best Pils");
        assertEquals("Should have the right name", "Best Pils", pils.getName());
    }


}