package edu.ics211.h10;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
// # credit - im sourcing the code from the screencasts.
// do i need to be using serializable?
public class BinaryTree<E> {
    // the top of the tree
    protected Node<E> root;

    // BinaryTree will be based upon Nodes, this is how the data will be held and the rest of the tree together.
    protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return this.data.toString();
        }
    }

    // default constructor - creates an empty tree
    public BinaryTree() {
        root = null;
    }

    // creates a tree with given node as root
    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    // creates a tree with root data and the left and right trees
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);

        if (leftTree != null) {
            root.left = leftTree.root;
        }

        if (rightTree != null) {
            root.right = rightTree.root;
        }
    }

    /**
     * Returns the left subtree.
     *
     * @return the left subtree, if any.
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return new BinaryTree<E>(null);
        }
    }

    /**
     * Returns the right subtree.
     *
     * @return the right subtree, if any.
     */
    public BinaryTree<E> getRightSubTree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return new BinaryTree<E>(null);
        }
    }

    /**
     * Returns data at root.
     *
     * @return data at root, if any.
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Checks if the tree is a leaf.
     *
     * @return true if it is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return root == null || (root.left == null && root.right == null);
    }

    /**
     * Returns a string representation of tree.
     *
     * @return string representation of tree, if any.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Builds the preorder traversal of the binary tree in sb
     *
     * @param node the root of the tree.
     * @param depth the level of the node, for indentation purposes.
     * @param sb the StringBuilder object.
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append(" ");
        }

        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

}
