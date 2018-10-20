package edu.ics211.h08;

import java.util.ArrayList;
import java.util.List;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class CheckoutLanes implements ICheckoutLanes {
    private ArrayList<CircularArrayQueue<Shopper>> expressLanes = new ArrayList<CircularArrayQueue<Shopper>>();
    private ArrayList<CircularArrayQueue<Shopper>> regularLanes = new ArrayList<CircularArrayQueue<Shopper>>();
    private int express;
    private int regular;

    /**
     * Constructor for CheckoutLanes.
     *
     * @param numExpress The number of express lanes.
     * @param numRegular The number of regular lanes.
     */
    public CheckoutLanes(int numExpress, int numRegular) {
        this.express = numExpress;
        this.regular = numRegular;

        // create CircularArrayQueue<Shopper> for every lane that's put into the constructor
        for (int i = 0; i < numExpress; i++) {
            expressLanes.add(new CircularArrayQueue<Shopper>());
        }

        for (int i = 0; i < numRegular; i++) {
            regularLanes.add(new CircularArrayQueue<Shopper>());
        }
    }

    /**
     * Adds the shopper to the checkout lane.
     *
     * @param laneNumber the checkout lane number.
     * @param shopper    the shopper.
     */
    @Override
    public void enterLane(int laneNumber, Shopper shopper) {
        // express lanes come before regular lanes
        // check if laneNumber is an express or regular lane
        if (laneNumber >= 0 && laneNumber < express) {
            // because we're storing x amount of CircularQueue<Shopper>'s inside an ArrayList,
            // we have to grab the CircularQueue<Shopper> that corresponds to the lane and
            // add the shopper to that queue
            expressLanes.get(laneNumber).add(shopper);
        } else if (laneNumber >= express) {
            // regularLanes is going to be 0 based, so we have to subtract express
            regularLanes.get(laneNumber - express).add(shopper);
        }
    }

    /**
     * Simulates the checkout process.
     *
     * @return A list of shoppers in the order they are finished.
     */
    @Override
    public List<Shopper> simulateCheckout() {
        // List is abstract
        // # credit: https://stackoverflow.com/a/858590
        List<Shopper> checkedOut = new ArrayList<Shopper>();

        // keep running until all the checkout lanes are empty
        while (!expressEmpty() || !regularEmpty()) {
            // traverse thru all the checkout lanes
            for (int j = 0; j < express + regular; j++) {
                // check if i is in express lane range
                // special case, if we have express lanes, then the Shopper can only have x < 11 items
                if (j < express) {
                    // if the lane we're at now is empty, move on
                    if (expressLanes.get(j).size() == 0) {
                        continue;
                    }

                    // the shopper has more than 10 items, move them to a random regular lane
                    if (expressLanes.get(j).peek().getNumberOfItems() > 11) {
                        // # credit: https://stackoverflow.com/a/363732
                        // pick a number between express and regular to pick a random regular lane
                        int randomLane = express + (int)(Math.random() * ((regular - express) + 1));

                        // grab the Shopper that has too many items
                        Shopper shopperToMove = expressLanes.get(j).poll();

                        // add the Shopper that had too many items to the end of a regular line
                        // regularLanes is 0 based, so we have to subtract express
                        regularLanes.get(randomLane - express).offer(shopperToMove);

                        // print to console
                        System.out.println("Express Lane Shopper with " + shopperToMove.getNumberOfItems() + " items moved to lane " + randomLane + ".");
                    } else { // the Shopper has less than 10 items
                        // remove the Shopper from the queue
                        Shopper checked = expressLanes.get(j).poll();

                        // add the Shopper to the List that's returned
                        checkedOut.add(checked);

                        // print to console
                        System.out.println("Express Lane " + j + ", shopper had " + checked.getNumberOfItems() + " items.");
                    }
                } else { // no express lanes
                    // all regular lanes are 0 based, have to subtract express
                    int counter = j - express;

                    // if the lane we're at now is empty, move on
                    if (regularLanes.get(counter).size() == 0) {
                        continue;
                    }

                    // remove the Shopper from the queue
                    Shopper checked = regularLanes.get(counter).poll();

                    // add the Shopper to the List that's returend
                    checkedOut.add(checked);

                    // print to console
                    System.out.println("Regular Lane " + (counter) + ", shopper had " + checked.getNumberOfItems() + " items.");
                }
            }
        }

        return checkedOut;
    }

    private boolean expressEmpty() {
        // do we even have expressLanes?
        if (express == 0) {
            return true;
        }

        boolean empty = false;

        // check if any of the CircularArrayQueue's still have Shoppers waiting to be checked out
        for (int i = 0; i < express; i++) {
            // if there's nothing in side of Queue at i
            if (expressLanes.get(i).size() == 0) {
                empty = true;
            } else { // if there's Shoppers inside Queue at i
                return false;
            }
        }

        return empty;
    }

    private boolean regularEmpty() {
        boolean empty = false;

        // check if any of the CircularArrayQueue's still have Shoppers waiting to be checked out
        for (int i = 0; i < regular; i++) {
            // if there's nothing inside of Queue at i
            if (regularLanes.get(i).size() == 0) {
                empty = true;
            } else { // if there's Shoppers inside Queue at i
                return false;
            }
        }

        return empty;
    }
}
