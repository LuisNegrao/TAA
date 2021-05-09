package trees;

import trees.node.Node;

public class AVLTree<T extends Comparable<T>> extends Tree<T> {

    private Node<T> root;
    private int size;

    public AVLTree() {

        size = 0;

    }

    public AVLTree(Node<T> root) {
        this.root = root;
    }

    private void updateHeight(Node<T> node) {

        if (node != null) {

            int height1 = 0, height2 = 0;

            if (node.getLeft() != null) {
                height1 = node.getLeft().getHeight();
            }

            if (node.getRight() != null) {
                height2 = node.getRight().getHeight();
            }

            int height = Math.max(height1, height2);

            node.setHeight(1 + height);
        }


    }

    private int height(Node<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    private int getBalance(Node<T> node) {
        return (node == null) ? 0 : height(node.getRight()) - height(node.getLeft());
    }


    private Node<T> rotateRight(Node<T> node) {
        Node<T> rightNode = node.getLeft();
        Node<T> leftNode = rightNode.getRight();
        rightNode.setRight(node);
        node.setLeft(leftNode);
        updateHeight(node);
        updateHeight(leftNode);
        return rightNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRight();
        Node<T> leftNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(leftNode);
        updateHeight(node);
        updateHeight(leftNode);
        return rightNode;
    }

    private Node<T> rebalance(Node<T> node) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (height(node.getRight().getRight()) > height(node.getRight().getLeft())) {
                node = rotateLeft(node);
            } else {
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        } else if (balance < -1) {
            if (height((node.getLeft().getLeft())) > height(node.getLeft().getRight())) {
                node = rotateRight(node);
            } else {
                node.setLeft(node.getLeft());
                node = rotateRight(node);
            }
        }
        return node;
    }

    private Node<T> insertRec(Node<T> curr, Node<T> parent, Node<T> value) {

        if (curr == null) {
            size++;
            value.setParent(parent);
            return value;
        }

        if (curr.isGreaterThan(value)) {
            curr.setLeft(insertRec(curr.getLeft(), curr, value));
        } else if (curr.isLowerThan(value)) {
            curr.setRight(insertRec(curr.getRight(), curr, value));
        } else {
            return curr;
        }

        return rebalance(curr);
    }

    @Override
    public void insert(Node<T> node) {
        this.root = insertRec(this.root, null, node);
    }

    private boolean containsRec(Node<T> curr, Node<T> value) {

        if (curr == null)
            return false;

        if (curr.getInfo().compareTo(value.getInfo()) == 0) {
            return true;
        } else if (curr.getInfo().compareTo(value.getInfo()) > 0) {
            return containsRec(curr.getLeft(), value);
        } else {
            return containsRec(curr.getRight(), value);
        }

    }

    @Override
    public boolean contains(Node<T> node) {
        return containsRec(this.root, node);
    }

    private Node<T> findRec(Node<T> curr, Node<T> value) {

        if (curr == null) {
            return null;
        }

        if (curr.getInfo().compareTo(value.getInfo()) == 0) {
            return curr;
        } else if (curr.getInfo().compareTo(value.getInfo()) > 0) {
            return findRec(curr.getLeft(), value);
        } else {
            return findRec(curr.getRight(), value);
        }
    }

    @Override
    public Node<T> find(Node<T> node) {
        return findRec(this.root, node);
    }

    private Node<T> removeRec(Node<T> curr, Node<T> value) {

        if (curr == null) {
            return null;
        }

        if (curr.getInfo().compareTo(value.getInfo()) > 0) {
            curr.setLeft(removeRec(curr.getLeft(), value));
        } else if (curr.getInfo().compareTo(value.getInfo()) < 0) {
            curr.setRight(removeRec(curr.getRight(), value));
        } else {
            if (curr.getLeft() == null || curr.getRight() == null) {
                curr = curr.getLeft() == null ? curr.getRight() : curr.getLeft();
            } else {
                Node<T> smallestNode = findSmallest(curr.getRight());
                curr.setInfo(smallestNode.getInfo());
                curr.setRight(removeRec(curr.getRight(), curr));
            }
        }

        if (curr != null) {
            curr = rebalance(curr);
        }

        return curr;
    }

    @Override
    public Node<T> remove(Node<T> node) {
        return this.root = removeRec(this.root, node);
    }

    @Override
    public void print() {
        System.out.println(this.root);
    }
}
