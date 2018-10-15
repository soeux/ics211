package edu.ics211.h08;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class CircularArrayQueue<E> implements Queue211<E> {
    // class vars


    // Queue211 implementation

    /**
     * Adds e to the end of the queue. May throw an IllegalStateException if the queue is full.
     *
     * @param e the item to add.
     * @return true if successful.
     */
    @Override
    public boolean add(E e) {
        return false;
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
        return null;
    }

    /**
     * Adds e to the end of the queue. Returns false if the queue is full.
     *
     * @param e the item to add.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean offer(E e) {
        return false;
    }

    /**
     * Returns the front of this queue without removing the front item.
     *
     * @return The front of this queue.
     */
    @Override
    public E peek() {
        return null;
    }

    /**
     * Removes and returns the front of this queue.
     *
     * @return The front of this queue, removing that item.
     */
    @Override
    public E poll() {
        return null;
    }

    /**
     * Removes and returns the front of this queue.
     *
     * @return The front of this queue, removing that item.
     */
    @Override
    public E remove() {
        return null;
    }

    /**
     * Returns the size of this queue.
     *
     * @return The size of this queue.
     */
    @Override
    public int size() {
        return 0;
    }
}
