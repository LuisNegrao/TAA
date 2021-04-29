package trees;

import trees.node.Node;

public class RedBlackTree<T extends Comparable<T>> extends Tree<T> {

    private Node<T> root;
    private int size;

    public RedBlackTree(Node<T> root) {
        this.root = root;
        this.size = 1;
    }

    public RedBlackTree() {
        this.size = 0;
    }

    @Override
    public void insert(Node<T> node) {

    }

    @Override
    public boolean contains(Node<T> node) {
        return false;
    }

    @Override
    public Node<T> find(Node<T> node) {
        return null;
    }

    @Override
    public Node<T> remove(Node<T> node) {
        return null;
    }

    @Override
    public void print() {

    }
}
