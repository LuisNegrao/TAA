package trees;
//https://www.sanfoundry.com/java-program-implement-splay-tree/

import com.sun.istack.internal.NotNull;
import trees.node.Node;

public class SplayTree<T extends Comparable<T>> extends Tree<T> {

    private Node<T> root;
    private int size;

    public SplayTree() {
        size = 0;
    }

    public SplayTree(T value) {

        this.root = new Node<>(value);
        this.size = 1;
    }

    public SplayTree(Node<T> value) {
        root = value;
        size = 1;
    }

    private Node<T> recursiveInsert(Node<T> current, Node<T> parent, Node<T> value) {
        if (current == null) {
            size++;
            value.setParent(parent);
            return value;
        }

        if (current.isGreaterThan(value)) {
            current.setLeft(recursiveInsert(current.getLeft(), current, value));
        } else if (current.isLowerThan(value)) {
            current.setRight(recursiveInsert(current.getRight(), current, value));
        } else {
            return current;
        }

        return current;
    }

    @Override
    public void insert(@NotNull Node<T> value) {
        if (root == null) {
            this.size++;
            this.root = value;
        } else {
            this.root = recursiveInsert(this.root, null, value);
            splay(find(value));
        }
    }

    /**
     * Rotate right
     *
     * @param node   node to rotate
     * @param parent parent of the node
     */
    private void zag(Node<T> node, Node<T> parent) {

        if (parent.getParent() == null) {

            parent.setLeft(node.getRight());
            node.setRight(parent);

            node.setParent(null);
            parent.setParent(node);

           if (parent.getLeft() != null) {
               parent.getLeft().setParent(parent);
           }

        } else {

            if (parent.isGreaterThan(parent.getParent())) {
                //System.out.println("hello");
                parent.getParent().setRight(node);
            } else {
                //System.out.println("hello twice");
                parent.getParent().setLeft(node);
            }

            parent.setLeft(node.getRight());

            if (parent.getLeft() != null) {
                parent.getLeft().setParent(parent);
            }

            node.setRight(parent);

            node.setParent(parent.getParent());
            //System.out.println(node.getParent().getInfo());
            //System.out.println(parent.getParent().getInfo());
            parent.setParent(node);
            //System.out.println(parent.getParent().getInfo());
            //System.out.println(node.getInfo());

        }

    }

    /**
     * Rotate left
     *
     * @param node   node to rotate
     * @param parent parent of the node
     */
    private void zig(Node<T> node, Node<T> parent) {

        if (parent.getParent() == null) {

            parent.setRight(node.getLeft());
            node.setLeft(parent);

            node.setParent(null);
            parent.setParent(node);

            if (parent.getRight() != null) {
                parent.getRight().setParent(parent);
            }


        } else {

            if (parent.isGreaterThan(parent.getParent())) {
                parent.getParent().setRight(node);
            } else {
                parent.getParent().setLeft(node);
            }

            parent.setRight(node.getLeft());

            if (parent.getRight() != null) {
                parent.getRight().setParent(parent);
            }

            node.setLeft(parent);

            node.setParent(parent.getParent());
            parent.setParent(node);

        }


    }


    public void splay(Node<T> node) {

        while (node.getParent() != null) {

            Node<T> parent = node.getParent();
            Node<T> gParent = parent.getParent();

            if (gParent == null) {

                if (parent.isGreaterThan(node)) {
                    zag(node, parent);
                } else {
                    zig(node, parent);
                }

            } else {

                if (node.isGreaterThan(parent)) {

                    if (parent.isGreaterThan(gParent)) {
                        zig(node, parent);
                        zig(node, node.getParent());

                    } else {
                        zig(node, parent);
                        zag(node, node.getParent());
                    }

                } else {

                    if (parent.isGreaterThan(gParent)) {
                        //System.out.println("hello");
                        zag(node, parent);
                        zig(node, node.getParent());

                    } else {
                       // System.out.println("hello2");
                        zag(node, parent);
                        zag(node, node.getParent());
                    }
                }
            }
        }
        this.root = node;
    }


    private boolean containsRec(Node<T> curr, Node<T> value) {

        if (curr == null)
            return false;

        if (value.getInfo().compareTo(curr.getInfo()) == 0) {
            return true;
        }

        if (value.getInfo().compareTo(curr.getInfo()) > 0) {
            return containsRec(curr.getRight(), value);
        }

        return containsRec(curr.getLeft(), value);

    }

    @Override
    public boolean contains(Node<T> node) {
        return containsRec(this.root, node);
    }

    private Node<T> findRec(Node<T> curr, Node<T> value) {

        if (curr.getRight() == null && curr.getLeft() == null)
            return null;

        if (curr.getInfo().compareTo(value.getInfo()) == 0) {
            return value;
        }

        if (curr.isGreaterThan(value)) {
            return findRec(curr.getRight(), value);
        }

        return findRec(curr.getLeft(), value);
    }

    @Override
    public Node<T> find(Node<T> node, Node<T> current) {
        return super.find(node, current);
    }

    @Override
    public Node<T> find(Node<T> node) {
        return find(node, this.root);
    }

    @Override
    public Node<T> remove(Node<T> node) {

        if (!contains(node)) {
            return null;
        }

        if (this.size == 1) {
            if (node.getInfo().compareTo(this.root.getInfo()) == 0) {
                Node<T> n = this.root;
                this.root = null;
                return n;
            } else return null;
        }
        splay(find(node));
        print();
        Node<T> n = this.root;
        Node<T> rightSide = this.root.getRight();
        Node<T> leftSide = this.root.getLeft();
        if (this.root.getLeft() != null) {
            Node<T> newRoot = findBiggest(leftSide);

            leftSide = removeinst(leftSide);

            newRoot.setRight(rightSide);
            newRoot.setLeft(leftSide);
            this.root = newRoot;
            this.root.setParent(null);

        } else {
            this.root = this.root.getRight();
            this.root.setParent(null);
        }
        return n;
    }

    private Node<T> removeinst(Node<T> side) {

        Node<T> temp = side;

        while (side.getRight().getRight() != null) {
            side = side.getRight();
        }

        side.setRight(null);
        return temp;
    }

    @Override
    public Node<T> findSmallest(Node<T> node) {
        return super.findSmallest(node);
    }

    @Override
    public Node<T> findBiggest(Node<T> node) {
        return super.findBiggest(node);
    }

    @Override
    public void print() {
        System.out.println(this.root);
    }

}

