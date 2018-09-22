//package edu.ics211.h04;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
///**
// * what does it do?
// *
// * @author Christian Mancha
// */
//public class SortableList<E> implements ISortableList<E>, IList211<E> {
//    private E[] data;
//    private int size;
//
//    /**
//     * uhhhh
//     */
//    @SuppressWarnings("unchecked");
//    public SortableList() {
//        data = (E[]) new Object[10];
//        size = 0;
//    }
//
//    /**
//     * Gets the item at the given index.
//     *
//     * @param index the index.
//     * @return the item at the given index.
//     */
//    @Override
//    public E get(int index) {
//        // does this index even exist?
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException("index must be in range");
//        }
//
//        return data[index];
//    }
//
//    /**
//     * Sets the item at the given index.
//     *
//     * @param index   the index.
//     * @param element the new element to set.
//     * @return the old element at index.
//     */
//    @Override
//    public E set(int index, E element) {
//        // does this index even exist?
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException("index must be in range");
//        }
//
//        // save old E here
//        E save = data[index];
//
//        // overwrite old E with new E
//        data[index] = element;
//
//        // is it worth resorting now?
//
//        // return old E.
//        return save;
//    }
//
//    /**
//     * Returns the index of the given obj or -1.
//     *
//     * @param obj the object to find.
//     * @return the index of the given obj or -1.
//     */
//    @Override
//    public int indexOf(Object obj) {
//        // loop thru the list until you find the obj
//        for (int i = 0; i < size - 1; i++) {
//            if (data[i].equals(obj)) {
//                return i;
//            }
//        }
//
//        // if unsuccessful -> -1
//        return -1;
//    }
//
//    /**
//     * Returns the size of this list.
//     *
//     * @return the size of this list.
//     */
//    @Override
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Adds e to the end of the list.
//     *
//     * @param e the item to add.
//     * @return true if successful, false otherwise.
//     */
//    @Override
//    public boolean add(E e) {
//        // check if data[] is big enough -> if not, make it twice as big
//        if (size == data.length) {
//            data = Arrays.copyOf(data, data.length * 2);
//        }
//
//        // add E at the end of the array
//        data[size + 1] = e;
//
//        // increment size
//        size++;
//
//    }
//
//    /**
//     * Adds element to the list at the given index.
//     *
//     * @param index   the index.
//     * @param element the element to add.
//     */
//    @Override
//    public void add(int index, E element) {
//        // does index even exist?
//
//        // check if data[] is big enough -> if not, make it bigger
//
//        // move values after index to the right, 1
//
//        // add E to index
//
//        // resort?
//    }
//
//    /**
//     * Removes the element at the given index.
//     *
//     * @param index the index.
//     * @return The element removed from the list.
//     */
//    @Override
//    public E remove(int index) {
//        // save the value at index
//
//        // remove value at index
//
//        // move everything to the left, 1
//
//        // return what was removed from the list
//        return null;
//    }
//
//    // ISortableList
//    /**
//     * Sorts the list using the insertion sort.
//     *
//     * @param compare a Comparator that determines order.
//     */
//    @Override
//    public void insertionSort(Comparator<E> compare) {
//
//    }
//
//    /**
//     * Sorts the list using the bubble sort.
//     *
//     * @param compare a Comparator that determines order.
//     */
//    @Override
//    public void bubbleSort(Comparator<E> compare) {
//
//    }
//
//    /**
//     * Sorts the list using the selection sort.
//     *
//     * @param compare a Comparator that determines order.
//     */
//    @Override
//    public void selectionSort(Comparator<E> compare) {
//
//    }
//
//    /**
//     * Returns the number of swaps for the last sort.
//     *
//     * @return the number of swaps for the last sort.
//     */
//    @Override
//    public int getNumberOfSwaps() {
//        return 0;
//    }
//
//    /**
//     * Returns the number of comparisons for the last sort.
//     *
//     * @return the number of comparisons for the last sort.
//     */
//    @Override
//    public int getNumberOfComparisons() {
//        return 0;
//    }
//
//    /**
//     * Returns the time it took to sort the list.
//     *
//     * @return the time it took to sort the list.
//     */
//    @Override
//    public double getSortTime() {
//        return 0;
//    }
//}
