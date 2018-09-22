package edu.ics211.h04;

import edu.ics211.h02.Beer;

import java.util.Comparator;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class SortableBeerList implements IList211<Beer> {
    private SortableList<Beer> beerList = new SortableList<Beer>();
    private Comparator<Beer> compare;

    public SortableBeerList(Comparator<Beer> compare) {
        this.compare = compare;
    }

    /**
     * Gets the item at the given index.
     *
     * @param index the index.
     * @return the item at the given index.
     */
    @Override
    public Beer get(int index) {
        return beerList.get(index);
    }

    /**
     * Sets the item at the given index.
     *
     * @param index   the index.
     * @param element the new element to set.
     * @return the old element at index.
     */
    @Override
    public Beer set(int index, Beer element) {
        return beerList.set(index, element);
    }

    /**
     * Returns the index of the given obj or -1.
     *
     * @param obj the object to find.
     * @return the index of the given obj or -1.
     */
    @Override
    public int indexOf(Object obj) {
        return beerList.indexOf(obj);
    }

    /**
     * Returns the size of this list.
     *
     * @return the size of this list.
     */
    @Override
    public int size() {
        return beerList.size();
    }

    /**
     * Adds e to the end of the list.
     *
     * @param beer the item to add.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean add(Beer beer) {
        boolean cond = beerList.add(beer);

        // resort the array
        beerList.bubbleSort(compare);

        return cond;
    }

    /**
     * Adds element to the list at the given index.
     *
     * @param index   the index.
     * @param element the element to add.
     */
    @Override
    public void add(int index, Beer element) {
        // add
        beerList.add(index, element);
        // resort
        beerList.bubbleSort(compare);
    }

    /**
     * Removes the element at the given index.
     *
     * @param index the index.
     * @return The element removed from the list.
     */
    @Override
    public Beer remove(int index) {
        return beerList.remove(index);
    }
}
