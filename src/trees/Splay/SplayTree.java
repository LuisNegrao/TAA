package trees.Splay;
//https://www.sanfoundry.com/java-program-implement-splay-tree/

import com.sun.istack.internal.NotNull;
import trees.Tree;
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
            this.root = node;


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
            this.root = node;

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
        } else if (curr.isLowerThan(value)) {
            return containsRec(curr.getRight(), value);
        } else
            return containsRec(curr.getLeft(), value);

    }

    @Override
    public boolean contains(Node<T> node) {
        Node<T> curr = this.root;
        return containsRec(curr, node);
    }


    @Override
    public Node<T> find(Node<T> cur, Node<T> value) {

        while (cur != null) {
           // System.out.println(cur);
            if (cur.isGreaterThan(value)) {
                cur = cur.getLeft();
            } else if (cur.isLowerThan(value)) {
                cur = cur.getRight();
            } else {
                return cur;
            }
        }
        return null;
    }

    @Override
    public Node<T> find(Node<T> node) {
        Node<T> curr = this.root;
        return find(curr, node);

    }

    @Override
    public Node<T> remove(Node<T> node) {

//        if (!contains(node)) {
//            return null;
//        }


        if (node.getInfo().compareTo(this.root.getInfo()) == 0) {
            //System.out.println(this.root);
            this.root = null;
            size = 0;
            return null;
        }


        //Find required nodes
        Node<T> n = find(node);
        if (n == null) {
            return null;
        }
        splay(n);
        //Select the new root


        if (this.root.getLeft() != null) {
            Node<T> newRoot = findAndDeleteBiggest(this.root.getLeft());
            //In case our first left side element is the "rightmost element" on the right side
            if (newRoot.getLeft() != null) {
                newRoot.setRight(this.root.getRight());
                if (this.root.getRight() != null)
                    this.root.getRight().setParent(newRoot);

                this.root = newRoot;

            } else {
                //in case we found an actual rightmost element in the left side
                newRoot.setRight(this.root.getRight());
                newRoot.setLeft(this.root.getLeft());
                if (this.root.getRight() != null)
                    this.root.getRight().setParent(newRoot);
                if (this.root.getLeft() != null)
                    this.root.getLeft().setParent(newRoot);
                this.root = newRoot;
            }
            return newRoot;
        } else {

            Node<T> newRoot = this.root;
            if (newRoot.getRight() != null)
                newRoot = newRoot.getRight();

            newRoot.setParent(null);

            return newRoot;
        }
    }

    @Override
    public Node<T> findSmallest(Node<T> node) {
        return super.findSmallest(node);
    }

    @Override
    public Node<T> findBiggest(Node<T> node) {
        return super.findBiggest(node);
    }

    private Node<T> findAndDeleteBiggest(Node<T> cur) {

        while (cur.getRight() != null) {
            cur = cur.getRight();
        }
        //Since we always go to the right, we know that the parent is on the right
        if (cur.getParent() != null) {
            if (cur.getParent().isGreaterThan(cur)) {
                cur.getParent().setLeft(null);
            } else {
                cur.getParent().setRight(null);
            }
        }
        cur.setParent(null);
        //System.out.println(cur);
        return cur;
    }

    @Override
    public void print() {
        System.out.println(this.root);
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }
}

