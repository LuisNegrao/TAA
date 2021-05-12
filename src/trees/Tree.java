package trees;

import trees.node.Node;

public abstract class Tree<T extends Comparable<T>> {

    //AVL BST and Splay Tree Methods
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
    public Node<T> findBiggest(Node<T> node) {
        return node.getRight() == null ? node : findBiggest(node.getRight());
    }

}
