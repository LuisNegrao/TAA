package trees;

import trees.node.Node;

public abstract class Tree<T extends Comparable<T>> {

    public abstract void insert(Node<T> node);
    public abstract boolean contains(Node<T> node);
    public abstract Node<T> find(Node<T> node);
    public abstract Node<T> remove(Node<T> node);
    public abstract void print();

    public Node<T> findSmallest(Node<T> node) {
        return node.getLeft() == null ? node : findSmallest(node.getLeft());
    }

}
