package trees;

import trees.node.Node;

public class SplayTree<T extends Comparable<T>> extends Tree<T> {

    private Node<T> root;
    private int size;

    public SplayTree(Node<T> root) {
        this.root = root;
        this.size = 1;
    }

    public SplayTree() {
        this.size = 0;
    }

    @Override
    public void insert(Node<T> node) {

        if (this.root == null) {
            this.root = node;
        } else {
            this.root = insertRec(this.root, null, node);
        }

    }

    private Node<T> insertRec(Node<T> curr, Node<T> parent, Node<T> node) {

        if (curr == null) {
            size++;
            node.setParent(parent);
            return node;
        }

        if (curr.isGreaterThan(node)) {
            insertRec(curr.getLeft(), curr, node);
        } else if (curr.isLowerThan(node)) {
            insertRec(curr.getRight(), curr, node);
        }
        return curr;

    }

    private void insertFix(Node<T> node) {

        while (node.getParent() != null) {

            Node<T> parent = node.getParent();
            Node<T> gParent = parent.getParent();

            if (gParent == null) {
                if (node.compareTo(parent.getLeft()) == 0) {
                    makeLeftChild(node, parent);
                } else {
                    makeRightChild(node, parent);
                }
            } else {

                if (node.compareTo(parent.getLeft()) == 0) {

                    if (parent.compareTo(gParent.getLeft()) == 0) {

                        makeLeftChild(parent, gParent);
                        makeRightChild(node, parent);
                    } else {
                        makeLeftChild(node, node.getParent());
                        makeRightChild(node, node.getParent());
                    }
                } else {

                    if (parent.compareTo(gParent.getLeft()) == 0) {
                        makeLeftChild(node, node.getParent());
                        makeRightChild(node, node.getParent());
                    } else {
                        makeLeftChild(parent, gParent);
                        makeRightChild(node, parent);
                    }

                }
            }
        }
        this.root = node;
    }

    private void makeLeftChild(Node<T> node, Node<T> parent) {

        if (parent.getParent() != null) {

            if (parent.compareTo(parent.getParent().getLeft()) == 0) {
                parent.getParent().setLeft(node);
            } else {
                parent.getParent().setRight(node);
            }
        }
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }

        node.setParent(parent.getParent());
        parent.setParent(node);
        parent.setLeft(node.getRight());
        node.setRight(parent);
    }

    private void makeRightChild(Node<T> node, Node<T> parent) {

        if (parent.getParent() != null) {

            if (parent.compareTo(parent.getParent().getLeft()) == 0) {
                parent.getParent().setLeft(node);
            } else {
                parent.getParent().setRight(node);
            }
        }

        if (node.getLeft() != null) {
            node.getLeft().setParent(parent);
        }

        node.setParent(parent.getParent());
        parent.setParent(node);
        parent.setRight(node.getLeft());
        node.setLeft(parent);

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
