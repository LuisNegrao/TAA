package trees;

import trees.node.Node;

public class RedBlackTree<T extends Comparable<T>> extends Tree<T> {

    public Node<T> root;
    private int size;

    public RedBlackTree(Node<T> root) {
        this.root = root;
        this.root.setColor("Black");
        this.size = 1;
    }

    public RedBlackTree() {
        this.size = 0;
    }

    public void rotateLeft(Node<T> node) {

        Node<T> rightNode = node.getRight();
        node.setRight(rightNode.getLeft());

        if (rightNode.getLeft() != null) {
            rightNode.getLeft().setParent(node);
        }

        rightNode.setParent(node.getParent());

        if (node.getParent() == null) {
            this.root = rightNode;
        } else if (node.compareTo(node.getParent().getLeft()) == 0) {
            node.getParent().setLeft(rightNode);
        } else {
            node.getParent().setRight(rightNode);
        }
        rightNode.setLeft(node);
        node.setParent(rightNode);
    }

    public void rotateRight(Node<T> node) {

        Node<T> leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());

        if (leftNode.getRight() != null) {
            leftNode.getRight().setParent(node);
        }

        leftNode.setParent(node.getParent());

        if (node.getParent() == null) {
            this.root = leftNode;
        } else if (node.compareTo(node.getParent().getRight()) == 0) {
            node.getParent().setRight(leftNode);
        } else {
            node.getParent().setLeft(node);
        }

        leftNode.setRight(node);
        node.setParent(leftNode);

    }

    private void insertFix(Node<T> node) {

        if (this.root.compareTo(node) == 0) {
            this.root.setColor("Black");
        } else {
            node.setColor("Red");
            Node<T> parent = node.getParent();
            Node<T> grandParent = parent.getParent();

            if(parent.getColor().equals("Red")) {
                Node<T> sibling = node.getSibling();

                if (sibling.getColor().equals("Black")) {

                    if( parent.compareTo(grandParent.getRight()) == 0) {
                        rotateLeft(node);
                    } else {
                        rotateRight(node);
                        parent.setColor("black");
                        grandParent.setColor("Red");
                        node.setColor("Red");
                    }

                } else {
                    parent.setColor("Black");
                    sibling.setColor("Black");
                    grandParent.setColor("Red");
                    insertFix(node);
                }

            }
        }

    }

    private Node<T> insertRec(Node<T> curr, Node<T> parent, Node<T> value) {

        if (curr == null) {
            value.setColor("Black");
            value.setParent(parent);
            return value;
        }

        if (curr.isGreaterThan(value)) {
            curr.setLeft(insertRec(curr.getLeft(), curr, value));
        } else if (curr.isLowerThan(value)) {
            curr.setRight(insertRec(curr.getRight(), curr, value));
        }
        return curr;
    }

    @Override
    public void insert(Node<T> node) {

        if (this.root == null) {
            this.root = node;
            this.root.setColor("Black");
        } else {
            this.root = insertRec(this.root, null, node);
        }

    }

    @Override
    public boolean contains(Node<T> node) {
        return false;
    }

    @Override
    public Node<T> find(Node<T> node, Node<T> current) {
        return super.find(node, current);
    }

    public Node<T> find(Node<T> node) {
        return find(node, this.root);
    }


    @Override
    public Node<T> remove(Node<T> node) {
        return null;
    }

    @Override
    public void print() {

    }
}
