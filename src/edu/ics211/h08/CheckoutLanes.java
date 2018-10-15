package edu.ics211.h08;

import java.util.List;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class CheckoutLanes implements ICheckoutLanes {

    /**
     *
     * @param numExpress
     * @param numRegular
     */
    public CheckoutLanes(int numExpress, int numRegular) {}

    /**
     * Adds the shopper to the checkout lane.
     *
     * @param laneNumber the checkout lane number.
     * @param shopper    the shopper.
     */
    @Override
    public void enterLane(int laneNumber, Shopper shopper) {

    }

    /**
     * Simulates the checkout process.
     *
     * @return A list of shoppers in the order they are finished.
     */
    @Override
    public List<Shopper> simulateCheckout() {
        return null;
    }
}
