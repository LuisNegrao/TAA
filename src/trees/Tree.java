package trees;

import trees.node.Node;

public abstract class Tree<T extends Comparable<T>> {

    public abstract void insert(Node<T> node);
    public abstract boolean contains(Node<T> node);

    public Node<T> find(Node<T> node, Node<T> current) {

        while (current != null && current.getInfo().compareTo(node.getInfo()) != 0) {

            if( current.isGreaterThan(node)) {
                current = current.getLeft();
            } else if (current.isLowerThan(node)) {
                current = current.getRight();
            }

        }
        return current;
    }

    public abstract Node<T> find(Node<T> node);

    public abstract Node<T> remove(Node<T> node);
    public abstract void print();

    public Node<T> findSmallest(Node<T> node) {
        return node.getLeft() == null ? node : findSmallest(node.getLeft());
    }

}
