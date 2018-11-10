package edu.ics211.h10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements SearchTree<E>, InOrder<E> {
    // # credit - im sourcing the code from the screencasts

    // comparator
    private Comparator<E> comp;

    // return value from add
    protected boolean addReturn;

    // return value from delete
    protected E deleteReturn;

    /**
     * Creates a new BinarySearchTree.
     * @param c the comparator to use for determining order.
     */
    public BinarySearchTree(Comparator<E> c) {
        this.comp = c;
    }

    // SearchTree<E>
    /**
     * Inserts item into where it belongs in the tree.
     *
     * @param item the item to add.
     * @return true if item is inserted, false if item is already in tree.
     */
    @Override
    public boolean add(E item) {
        root = add(root, item); // need a recursive solution
        return addReturn; // because the second add() will return a Node<E>, it'll also set addReturn and we can return that
    }

    private Node<E> add(Node<E> localRoot, E item) {
        // tree is empty, just insert the item
        // this is also the base case for which we actually add item.
        if (localRoot == null) {
            addReturn = true;
            return new Node<E>(item);
        }

        // if tree isn't empty, we have to find the correct spot for item
        int compareTarget = comp.compare(item, localRoot.data);

        if (compareTarget == 0) { // item is already in tree
            addReturn = false;
            return localRoot;
        } else if (compareTarget < 0) { // it goes in the left side of localRoot, call add() again to find an empty spot
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else { // it goes in the right side of localRoot, call add() again to find an empty spot
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Returns true if the item is in this SearchTree.
     *
     * @param item the item to find.
     * @return true if item is in the tree, false otherwise.
     */
    @Override
    public boolean contains(E item) {
        return find(item) != null;
    }

    /**
     * Returns a reference to the target if it is in this SearchTree or null if it is not.
     *
     * @param target the item to find.
     * @return a reference to the target if found, null if target isn't in the tree.
     */
    @Override
    public E find(E target) {
        return find(root, target); // need a recursive solution
    }

    private E find(Node<E> localRoot, E target) {
        // tree is empty, nothing to find
        // also the base case for which we'll know when we've reached the end
        if (localRoot == null) {
            return null;
        }

        // using our own comparator
        int compareTarget = comp.compare(target, localRoot.data);

        if (compareTarget == 0) { // found it
            return localRoot.data;
        } else if (compareTarget < 0) { // on the left side of the tree
            return find(localRoot.left, target);
        } else { // on the right side of the tree
            return find(localRoot.right, target);
        }
    }

    /**
     * Removes target from the tree.
     *
     * @param target the item to remove.
     * @return a reference to the target if found, null if target isn't in the tree.
     */
    @Override
    public E delete(E target) {
        root = delete(root, target); // need a recursive solution
        return deleteReturn; // same as the add() method, the second delete() method will return a Node<E>, and it'll also set deleteReturn so we can return that.
    }

    private Node<E> delete(Node<E> localRoot, E item) {
        // tree is empty, nothing to delete
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }

        // if the tree isn't empty, we have to find out where item is
        int compareTarget = comp.compare(item, localRoot.data);

        if (compareTarget < 0) { // it's in the left side, call delete() again to find exactly
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compareTarget > 0) { // it's in the right side, call delete() again to find exactly
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else { // found it
            deleteReturn = localRoot.data; // make sure the first delete() method can return the old data

            // on to deleting the node
            if (localRoot.left == null) { // if there's no left child, return the right child
                return localRoot.right;
            } else if (localRoot.right == null) { // if there's no right child, return the left child
                return localRoot.left;
            } else { // left and right child are both present
                if (localRoot.left.right == null) { // if the left child does not have a right child
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left; // replace the left child with its left child
                    return localRoot;
                } else {
                    localRoot.data = findLargestChild(localRoot.left); // find the inorder predecessor and replace it's data with that
                    return localRoot;
                }
            }
        }
    }

    private E findLargestChild(Node<E> parent) {
        if (parent.right.right == null) { // if the right child has no right child, then it's the inorder predecessor
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right); // recursion: keep going to the right until the end
        }
    }

    /**
     * Removes target from the tree.
     *
     * @param target the item to remove.
     * @return true if target was in the tree, false otherwise.
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    // InOrder<E>
    /**
     * Returns the items in sorted order.
     *
     * @return The items in sorted order.
     */
    @Override
    public List<E> inorder() {
        return null;
    }
}
