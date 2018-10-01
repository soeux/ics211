package edu.ics211.h04;

import edu.ics211.h03.ArraySorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * implements IList211<E> & ISortableList<E>
 *
 * @author Christian Mancha
 */
public class SortableList<E> implements IList211<E>, ISortableList<E> {
    private E[] data;
    private int size;
    private int swapTracker;
    private int compareTracker;
    private long timeTracker;
    //private ArraySorter<E> sorter = new ArraySorter<E>();

    // make a default list of 10 empty spaces
    //@SuppressWarnings("unchecked");
    public SortableList() {
        data = (E[]) new Object[10];
        size = 0;
    }

    /**
     * Gets the item at the given index.
     *
     * @param index the index.
     * @return the item at the given index.
     */
    @Override
    public E get(int index) {
        // check if index is in range
        if (index < 0 || index > size) { // TODO does it matter if i use >= or > for size?
            throw new IndexOutOfBoundsException("index must be in range");
        }

        return data[index];
    }

    /**
     * Sets the item at the given index.
     *
     * @param index   the index.
     * @param element the new element to set.
     * @return the old element at index.
     */
    @Override
    public E set(int index, E element) {
        // check if index is in range
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // save the old element + replace with new element
        E save = data[index];
        data[index] = element;

        return save;
    }

    /**
     * Returns the index of the given obj or -1.
     *
     * @param obj the object to find.
     * @return the index of the given obj or -1.
     */
    @Override
    public int indexOf(Object obj) {
        // traverse through the array to see if obj exists + return it's index when found
        for (int i = 0; i < size; i++) {
            if (obj.equals(data[i])) { // TODO do these objs need .equals overridden?
                return i;
            }
        }

        // if you're here, the obj wasn't found
        return -1;
    }

    /**
     * Returns the size of this list.
     *
     * @return the size of this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds e to the end of the list.
     *
     * @param e the item to add.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean add(E e) {
        // check if data[] is big enough
        if (size >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }

        // add e to the end
        data[size] = e;

        // increment the size
        size++;

        // resort the array

        // ?
        return true;
    }

    /**
     * Adds element to the list at the given index.
     *
     * @param index   the index.
     * @param element the element to add.
     */
    @Override
    public void add(int index, E element) {
        // check if index is in range
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // check if data[] is big enough
        if (size >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }

        // traverse thru data[] and move everything right 1
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        // add the new element
        data[index] = element;

        // increment the size
        size++;

        // resort the array
    }

    /**
     * Removes the element at the given index.
     *
     * @param index the index.
     * @return The element removed from the list.
     */
    @Override
    public E remove(int index) {
        // check if index is in range
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // save the old element
        E save = data[index];

        // traverse thru data[] and move everything left 1
        // this is where the old element gets deleted bc overwritten
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        // decrement the size
        size--;

        // return the old element
        return save;
    }

    // ISortableList
    // i would have implemented this through delegation, but because we're using List the
    // size of the list is actually different from the size of data[].
    // that can't be taken into account without introducing a new parameter
    // to bubbleSort() in ArraySorter<E>
    /**
     * Sorts the list using the insertion sort.
     * Implementation from {@link edu.ics211.h03.ArraySorter#insertionSort(Object[], Comparator)}.
     *
     * @param compare a Comparator that determines order.
     */
    @Override
    public void insertionSort(Comparator<E> compare) {
        // 0 everything out + start time tracker
        swapTracker = 0;
        compareTracker = 0;
        timeTracker = 0;
        long localTimeTracker = System.nanoTime();

        // implementation
        // traverse thru data[]
        for (int i = 1; i < size; i++) {
            E save = data[i]; // what's gna be compared
            int j = i - 1;

            // 1: we can't get a value at index -1
            // 2: check if the value at j is less than the value at i -> ascending order
            while (j >= 0 && compare.compare(data[j], save) > 0) {
                data[j + 1] = data[j];
                j--;

                // end swap
                swapTracker++;
            }

            // move the value we were comparing to the right
            data[j + 1] = save;

            // end compare
            compareTracker++;
        }

        // end method
        timeTracker = System.nanoTime() - localTimeTracker;
    }

    /**
     * Sorts the list using the bubble sort.
     * Implementation from {@link edu.ics211.h03.ArraySorter#bubbleSort(Object[], Comparator)}.
     *
     * @param compare a Comparator that determines order.
     */
    @Override
    public void bubbleSort(Comparator<E> compare) {
        // 0 everything out + start sort time
        swapTracker = 0;
        compareTracker = 0;
        timeTracker = 0;
        long localTimeTracker = System.nanoTime();

        // implementation
        // traverse thru data[], except for the last element
        for (int i = 0; i < size - 1; i++) {

            // start at the position before i and go up the array
            for (int j = 0; j < size - i - 1; j++) {

                // check if the value of j is less than the value at j+1 -> ascending order
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
     * Sorts the list using the selection sort.
     * Implementation from {@link edu.ics211.h03.ArraySorter#selectionSort(Object[], Comparator)}.
     *
     * @param compare a Comparator that determines order.
     */
    @Override
    public void selectionSort(Comparator<E> compare) {
        // 0 everything out + start sort time
        swapTracker = 0;
        compareTracker = 0;
        timeTracker = 0;
        long localTimeTracker = System.nanoTime();

        // implementation
        int n = size;
        for (int i = 0; i < n - 1; i++) {
            // save index
            int min = i;

            // start after i and keep going
            for (int j = i + 1; j < n; j++) {
                // if value of i is less than value of j, set min to j
                if (compare.compare(data[j], data[min]) > 0) {
                    min = j;
                }

                // end compare
                compareTracker++;
            }

            // if min is equal to i, then there's nothing to swap
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
     * Returns the time it took to sort the list.
     *
     * @return the time it took to sort the list.
     */
    @Override
    public double getSortTime() {
        return (double) timeTracker;
    }
}
