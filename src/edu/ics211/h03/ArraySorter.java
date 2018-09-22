package edu.ics211.h03;

import java.util.Comparator;

/**
 * Implements the Insertion Sort, Bubble Sort and Selection Sort algorithms
 * to sort arrays of Beer objects by their IBU or ABV.
 *
 * @author Christian Mancha
 */
public class ArraySorter<E> implements SortsArray<E> {
    // is it bad to use instance variables to keep track of method progress?
    private int swapTracker;
    private int compareTracker;
    private long timeTracker;

    /**
     * Sorts the array data using the insertion sort.
     *
     * @param data    the data.
     * @param compare the Comparator that determines order.
     */
    @Override
    public void insertionSort(E[] data, Comparator<E> compare) {
        // # credit: https://en.wikipedia.org/wiki/Insertion_sort
        // # credit: https://www.geeksforgeeks.org/insertion-sort/

        // 0 everything out + start sort time
        swapTracker = 0;
        compareTracker = 0;
        timeTracker = 0;
        long localTimeTracker = System.nanoTime();

        // loop thru the data[]
        for (int i = 1; i < data.length; i++) {
            E save = data[i]; // comparing
            int j = i - 1;

            // while j is greater than 0 and the value in the right (data[j]) is greater than the
            // value in the left (data[i]) aka (save): move the right to the left
            while (j >= 0 && compare.compare(data[j], save) > 0) {
                data[j + 1] = data[j];
                j--;

                // end swap
                swapTracker++;
            }

            // move the value we were comparing (data[i]) to the right
            data[j + 1] = save;

            // end comparison
            compareTracker++;
        }

        // end method
        timeTracker = System.nanoTime() - localTimeTracker;
    }

    /**
     * Sorts the array data using the bubble sort.
     *
     * @param data    the data.
     * @param compare the Comparator that determines order.
     */
    @Override
    public void bubbleSort(E[] data, Comparator<E> compare) {
        // # credit: https://en.wikipedia.org/wiki/Bubble_sort
        // # credit: https://www.geeksforgeeks.org/bubble-sort/

        // 0 everything out + start sort time
        swapTracker = 0;
        compareTracker = 0;
        timeTracker = 0;
        long localTimeTracker = System.nanoTime();

        //implementation yay
        // loop thru the entire array except the last element
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {

            // start at the position before i and go up the array
            for (int j = 0; j < n - i - 1; j++) {

                // check if the value on the right is greater than the left
                // true: switch them
                // false: continue
                if (compare.compare(data[j], data[j + 1]) > 0) {
                    E save = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = save;

                    // end swap
                    swapTracker++;
                }
            }

            // end compare
            compareTracker++;
        }

        // end method
        timeTracker = System.nanoTime() - localTimeTracker;
    }

    /**
     * Uses the selection sort algorithm to sort the data array.
     *
     * @param data    the data to sort.
     * @param compare a Comparator.
     */
    @Override
    public void selectionSort(E[] data, Comparator<E> compare) {
        // # credit: https://en.wikipedia.org/wiki/Selection_sort
        // # credit: https://www.geeksforgeeks.org/selection-sort/

        // 0 everything out + start sort time
        swapTracker = 0;
        compareTracker = 0;
        timeTracker = 0;
        long localTimeTracker = System.nanoTime();

        // implementation
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {

            // saving the index not the value
            int min = i;

            // start after i and keep going
            for (int j = i + 1; j < n; j++) {

                // compare the value of i to the other elements' values
                // if j is less than the minimum, set minimum equal to the index of j
                if (compare.compare(data[j], data[min]) < 0) {
                    min = j;
                }

                // end compare
                compareTracker++;
            }

            // if minimum is equal to i, then there's nothing to do
            if (min != i) {
                E save = data[min];
                data[min] = data[i];
                data[i] = save;

                // end swap
                swapTracker++;
            }
        }

        // end method
        timeTracker = System.nanoTime() - localTimeTracker;
    }

    /**
     * Returns the number of swaps for the last sort.
     *
     * @return the number of swaps for the last sort.
     */
    @Override
    public int getNumberOfSwaps() {
        return swapTracker;
    }

    /**
     * Returns the number of comparisons for the last sort.
     *
     * @return the number of comparisons for the last sort.
     */
    @Override
    public int getNumberOfComparisons() {
        return compareTracker;
    }

    /**
     * Returns the time it took to sort.
     *
     * @return the time it took to sort.
     */
    @Override
    public double getSortTime() {
        return timeTracker;
    }
}
