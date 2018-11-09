package edu.ics211.h10;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
// # credit - im sourcing the code from the screencasts.
// do i need to be using serializable?

public class BinaryTree<E> {
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

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);

        if (leftTree != null) {
            root.left = leftTree.root;
        }

        if (rightTree != null) {
            root.right = rightTree.root;
        }
    }

    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return new BinaryTree<E>(null);
        }
    }

    public BinaryTree<E> getRightSubTree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return new BinaryTree<E>(null);
        }
    }

    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    public boolean isLeaf() {
        return root == null || (root.left == null && root.right == null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

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
