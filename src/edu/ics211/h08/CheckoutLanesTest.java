package edu.ics211.h08; // 20181015 - h07 -> h08

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Represents a CheckoutLanesTest.
 *
 * @author Cam Moore
 */
public class CheckoutLanesTest {

    /**
     * Test no express lane.
     */
    @Test
    public void testNoExpress() {
        System.out.println("\nTest No Express");
        CheckoutLanes lanes = new CheckoutLanes(0, 1);
        lanes.enterLane(0, new Shopper(5));
        lanes.enterLane(0, new Shopper(10));
        lanes.enterLane(0, new Shopper(15));
        List<Shopper> shoppers = lanes.simulateCheckout();
        assertTrue(shoppers.get(0).getNumberOfItems() == 5);
        assertTrue(shoppers.get(1).getNumberOfItems() == 10);
        assertTrue(shoppers.get(2).getNumberOfItems() == 15);
    }

    /**
     * Test one express lane.
     */
    @Test
    public void testOneExpress() {
        System.out.println("\nTest One Express");
        CheckoutLanes lanes = new CheckoutLanes(1, 1);
        lanes.enterLane(0, new Shopper(15));
        lanes.enterLane(0, new Shopper(10));
        lanes.enterLane(0, new Shopper(5));
        lanes.enterLane(1, new Shopper(25));
        List<Shopper> shoppers = lanes.simulateCheckout();
        assertEquals("Wrong number of shoppers", 4, shoppers.size());
        assertTrue(shoppers.get(0).getNumberOfItems() == 25);
        assertTrue(shoppers.get(1).getNumberOfItems() == 10);
        assertTrue(shoppers.get(2).getNumberOfItems() == 15);
        assertTrue(shoppers.get(3).getNumberOfItems() == 5);
    }

    /**
     * One express two regular.
     */
    @Test
    public void testOneTwo() {
        System.out.println("\nTest One Express, Two Regular");
        CheckoutLanes checkout = new CheckoutLanes(1, 2);
        checkout.enterLane(0, new Shopper(15));
        checkout.enterLane(0, new Shopper(3));
        checkout.enterLane(1, new Shopper(20));
        checkout.enterLane(2, new Shopper(17));
        List<Shopper> shoppers = checkout.simulateCheckout();
        assertEquals("Wrong number of shoppers", 4, shoppers.size());
        assertTrue(shoppers.get(0).getNumberOfItems() == 20);
        assertTrue(shoppers.get(1).getNumberOfItems() == 17);
        assertTrue(shoppers.get(2).getNumberOfItems() == 3);
        assertTrue(shoppers.get(3).getNumberOfItems() == 15);
    }

    // added in methods from the email sent out friday morning

    /**
     * One Express, Zero Regular.
     */
    @Test
    public void testOneZero() {
        System.out.println("\nTest One Express, Zero Regular");
        CheckoutLanes checkout = null;
        try {
            checkout = new CheckoutLanes(1, 0);
            fail("IllegalArgumentException should be thrown.");
        } catch (IllegalArgumentException e) {
            // this is expected
        }
    }

    /**
     * Two Express, Two Regular.
     */
    @Test
    public void testTwoTwo() {
        System.out.println("\nTest Two Express, Two Regular");
        CheckoutLanes checkout = new CheckoutLanes(2,2);
        checkout.enterLane(0, new Shopper(15));
        checkout.enterLane(2, new Shopper(3));
        checkout.enterLane(1, new Shopper(20));
        checkout.enterLane(3, new Shopper(17));
        checkout.enterLane(2, new Shopper(54));
        checkout.enterLane(0, new Shopper(2));
        checkout.enterLane(1, new Shopper(6));
        checkout.enterLane(3, new Shopper(32));
        checkout.enterLane(2, new Shopper(9));
        checkout.enterLane(0, new Shopper(23));
        List<Shopper> shoppers = checkout.simulateCheckout();
        assertEquals("Wrong number of shoppers.", 10, shoppers.size());
        assertTrue(shoppers.get(0).getNumberOfItems() == 3);
        assertTrue(shoppers.get(1).getNumberOfItems() == 17);
        assertTrue(shoppers.get(2).getNumberOfItems() == 2);
        assertTrue(shoppers.get(3).getNumberOfItems() == 6);
        assertTrue(shoppers.get(4).getNumberOfItems() == 54);
        assertTrue(shoppers.get(5).getNumberOfItems() == 32);
        assertTrue(shoppers.get(6).getNumberOfItems() == 9);
        assertTrue(shoppers.get(7).getNumberOfItems() == 15);
        assertTrue(shoppers.get(8).getNumberOfItems() == 20);
        assertTrue(shoppers.get(9).getNumberOfItems() == 23);
    }

}