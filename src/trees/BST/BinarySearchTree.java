package trees.BST;


import com.sun.istack.internal.NotNull;
import trees.Tree;
import trees.node.Node;

public class BinarySearchTree<T extends Comparable<T>> extends Tree<T> {


    public Node<T> root;

    public int getSize() {
        return size;
    }

    private int size;

    public BinarySearchTree() {
        size = 0;
    }

    public BinarySearchTree(T value) {

        this.root = new Node<>(value);
        this.size = 1;
    }

    public BinarySearchTree(Node<T> value) {
        root = value;
        size = 1;
    }

    private Node<T> recursiveInsert(Node<T> current, Node<T> parent, Node<T> value) {
        if (current == null) {
            size++;
            value.setParent(parent);
            value.depth = value.getParent().depth+1;
            //System.out.println(value.depth);
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

    public void insert(T value){
        insert(new Node<>(value));
    }
    @Override
    public void insert(@NotNull Node<T> value) {
        if (root == null) {
            this.size++;
            this.root = value;
        } else {
            this.root = recursiveInsert(this.root, null, value);
        }
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


    public int height() {
        Node<T> cur = this.root;
        return Math.max(height(cur.getLeft(), 0), height(cur.getRight(), 0));
    }

    private int height(Node<T> node, int i) {
        if (node == null)
            return i;
        else if (node.getRight() != null && node.getLeft() != null)
            return Math.max(height(node.getRight(), i + 1), height(node.getLeft(), i + 1));
        else if (node.getRight() != null) {
            return height(node.getRight(), i + 1);
        } else if (node.getLeft() != null)
            return height(node.getLeft(), i + 1);
        else return i;
    }

    private Node<T> removeRec(Node<T> curr, Node<T> value) {
        //System.out.println((curr));
        if (curr == null) {
            return null;
        }

        if (value.getInfo() == curr.getInfo()) {

            if (curr.getRight() == null) {
                return curr.getLeft();
            }

            if (curr.getLeft() == null) {
                return curr.getRight();
            }

            Node<T> node = findSmallest(curr.getRight());
            //System.out.println(node);
            curr.setInfo(node.getInfo());
            curr.setRight(removeRec(curr.getRight(), node));
            return curr;

        }

        if (curr.isGreaterThan(value)) {
            curr.setLeft(removeRec(curr.getLeft(), value));
            return curr;
        }

        curr.setRight(removeRec(curr.getRight(), value));
        return curr;
    }

    @Override
    public Node<T> remove(Node<T> node) {

        if (!contains(node)) {
            System.out.println("Tree does not contain the expected value");
            return null;
        }
        return this.root = removeRec(root, node);
    }

    @Override
    public void print() {
        System.out.println(this.root);

//        LinkedList<Node<T>> nodes = new LinkedList<>();
//
//        nodes.add(this.root);
//
//        while (!nodes.isEmpty()) {
//
//            Node<T> node = nodes.remove();
//            System.out.println(node);
//
//            if (node.getLeft() != null) {
//                nodes.add(node.getLeft());
//            }
//
//            if (node.getRight() != null) {
//                nodes.add(node.getRight());
//            }
//        }
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }
}