package trees;

import trees.node.Node;

public class SplayTree<T extends Comparable<T>> extends Tree<T>{

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

    @Override
    public Node<T> findSmallest(Node<T> node) {
        return super.findSmallest(node);
    }
}
