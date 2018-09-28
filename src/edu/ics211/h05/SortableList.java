package edu.ics211.h05;

import edu.ics211.h04.IList211;
import edu.ics211.h04.ISortableList;

import java.util.Comparator;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class SortableList<E> implements IList211<E>, ISortableList<E> {
    private DLinkedNode head;
    private int size;
    private int swapTracker;
    private int compareTracker;
    private long timeTracker;

    // this is how the data is going to be held
    private class DLinkedNode {
        E item;
        DLinkedNode next;

        public DLinkedNode(E item, DLinkedNode next) {
            this.item = item;
            this.next = next;
        }
    }

    public SortableList() {
        this.head = null;
        this.size = 0;
    }

    // IList211<E> implementation

    /**
     * Gets the item at the given index.
     *
     * @param index the index.
     * @return the item at the given index.
     */
    @Override
    public E get(int index) {
        // check if index is in range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // traverse thru the list until index
        DLinkedNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        //
        return temp.item;
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
        // check if the index is in range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // traverse thru the list until index
        DLinkedNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        E old = temp.item;
        temp.item = element;
        return old;
    }

    /**
     * Returns the index of the given obj or -1.
     *
     * @param obj the object to find.
     * @return the index of the given obj or -1.
     */
    @Override
    public int indexOf(Object obj) {
        // traverse thru all the list until obj
        DLinkedNode temp = head;
        for (int i = 0; i < size; i++) {
            temp = temp.next;

            if (temp.item.equals(obj)) {
                // found obj + return index
                return i;
            }
        }

        // if you're here, obj wasn't found
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
        // special case: empty list
        if (head == null) {
            head = new DLinkedNode(e, null);

            // increment size + return
            size++;
            return true;
        } else {
            // traverse thru the list to the end
            DLinkedNode temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.next;
            }

            // add the item in it's own DLinkedNode + create a pointer from the Node before to it
            temp.next = new DLinkedNode(e, null);

            // increment size + return
            size++;
            return true;
        }
    }

    /**
     * Adds element to the list at the given index.
     *
     * @param index   the index.
     * @param element the element to add.
     */
    @Override
    public void add(int index, E element) {
        // check if the index is in range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // special case: index is 0
        if (index == 0) {
            // insert Node before head + have it point to head + set it to head
            head = new DLinkedNode(element, head);
            size++;
        } else {
            // traverse thru the list until index
            DLinkedNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }

            // TODO what if the index is at the end?
            // create a new Node after index - 1 + have it point to the Node next to it
            temp.next = new DLinkedNode(element, temp.next);
            size++;
        }
    }

    /**
     * Removes the element at the given index.
     *
     * @param index the index.
     * @return The element removed from the list.
     */
    @Override
    public E remove(int index) {
        // check if the index is in range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index must be in range");
        }

        // special case: removing head
        if (index == 0) {
            // save the old value + set head to head.next + decrement the size + return old value
            E old = head.item;
            head = head.next;
            size--;
            return old;
        } else {
            // traverse thru the list until index - 1
            DLinkedNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }

            // TODO what if index is at the end?
            // save the old value + set the pointer to skip next Node + decrement size + return old value
            E old = temp.item;
            temp.next = temp.next.next;
            size--;
            return old;
        }
    }

    // ISortableList<E> implementation

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
        return compareTracker;
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
