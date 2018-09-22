package edu.ics211.h04;

import java.util.Arrays;
import java.util.Comparator;

/**
 * implements IList211<E> and ISortableList<E>
 *
 * @author Christian Mancha
 */
public class SortableList<E> implements IList211<E>, ISortableList<E> {
    private E[] data;
    private int size;
    private int swapTracker;
    private int comparTracker;
    private long timeTracker;

    // TODO figure out how to do the sorting thing with this one


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
        for (int i = 0; i < size - 1; i++) {
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
        data[size + 1] = e;

        // increment the size
        size++;

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

        // TODO are the numbers right lol
        // traverse thru data[] and move everything right 1
        for (int i = size - 1; i > index; i--) {
            data[i] = data[i + 1];
        }

        // add the new element
        data[index] = element;

        // increment the size
        size++;
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
    /**
     * Sorts the list using the insertion sort.
     *
     * @param compare a Comparator that determines order.
     */
    @Override
    public void insertionSort(Comparator<E> compare) {

    }

    /**
     * Sorts the list using the bubble sort.
     *
     * @param compare a Comparator that determines order.
     */
    @Override
    public void bubbleSort(Comparator<E> compare) {

    }

    /**
     * Sorts the list using the selection sort.
     *
     * @param compare a Comparator that determines order.
     */
    @Override
    public void selectionSort(Comparator<E> compare) {

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
        return comparTracker;
    }

    /**
     * Returns the time it took to sort the list.
     *
     * @return the time it took to sort the list.
     */
    @Override
    public double getSortTime() {
        return timeTracker;
    }
}
