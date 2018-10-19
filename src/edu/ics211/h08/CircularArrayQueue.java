package edu.ics211.h08;

import java.util.NoSuchElementException;

/**
 * Implements Queue211 as a Circular Array
 *
 * @author Christian Mancha
 */
public class CircularArrayQueue<E> implements Queue211<E> {
    // # credit: this entire implementation came from the "Queue ADT Continued" video on the class website
    // instance variables
    private E[] data;
    private int front;
    private int back;
    private int size;

    public CircularArrayQueue() {
        this.data = (E[]) new Object[10];
        this.front = 0;
        this.back = data.length - 1;
        this.size = 0;
    }

    // Queue211 implementation
    /**
     * Adds e to the end of the queue. May throw an IllegalStateException if the queue is full.
     *
     * @param e the item to add.
     * @return true if successful.
     */
    @Override
    public boolean add(E e) {
        // check if data isn't full
        if (size == data.length) {
            reallocate();
        }

        // updates back + makes it circular
        back = (back + 1) % data.length;

        // add e + increment size + return true
        data[back] = e;
        size++;
        return true;
    }

    /**
     * Adds e to the end of the queue. Returns false if the queue is full.
     *
     * @param e the item to add.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean offer(E e) {
        // add() without the exception
        try {
            return add(e);
        } catch (IllegalStateException e) {
            return false;
        }
    }

    /**
     * Retrieves, but doesn't remove the head of the queue.
     * Throws NoSuchElementException if queue is empty.
     *
     * @return the head of the queue.
     * @throws NoSuchElementException if queue is empty.
     */
    @Override
    public E element() {
        // check if there's anything in data
        if (size == 0) {
            throw new NoSuchElementException();
        }

        // if we're here, then there's stuff in data and we can return the first element
        return data[front];
    }

    /**
     * Returns the front of this queue without removing the front item.
     *
     * @return The front of this queue.
     */
    @Override
    public E peek() {
        // element() without the exception
        try {
            return element();
        } catch (NoSuchElementException) {
            return null;
        }
    }

    /**
     * Removes and returns the front of this queue.
     *
     * @return The front of this queue, removing that item.
     */
    @Override
    public E remove() {
        // check if there's stuff in data
        if (size == 0) {
            throw new NoSuchElementException();
        }

        // remember the old value + update front + decrement size + return value
        E value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    /**
     * Removes and returns the front of this queue.
     *
     * @return The front of this queue, removing that item.
     */
    @Override
    public E poll() {
        // remove() without the exception
        try {
            return remove();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Returns the size of this queue.
     *
     * @return The size of this queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Doubles capacity of data and reallocates the data
     */
    private void reallocate() {
        E[] newData = (E[]) new Object[2 * data.length];

        // front can be anywhere in the array at this point
        // we have to follow it circularly in order to copy the order to the new array
        int j = front;
        for (int i = 0; i < size; i++) {
            newData[i] = data[j];
            j = (j + 1) % data.length;
        }

        // reset front + set back size - 1 + set newData to data
        front = 0;
        back = size - 1;
        data = newData;
    }
}
