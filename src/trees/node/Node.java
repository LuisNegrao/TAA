package trees.node;

import com.sun.istack.internal.NotNull;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
   //For AVL Splay and BST
    private T info;
    private Node<T> parent;
    private String color;
    private int height;
    private Node<T> left;
    private Node<T> right;
    public int depth;
    /*Constructor*/

    public Node() {
    }

    public Node(T info) {
        this.info = info;
    }

    public Node(T info, Node<T> parent) {
        this.info = info;
        this.parent = parent;
    }

    /*Getters and Setters*/
    public T getInfo() {
        return info;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setLeft(Node<T> left) {
        this.left = left;

    }

    public void setRight(Node<T> right) {
        this.right = right;
        //System.out.println(this.right.toString());
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public int compareTo(@NotNull Node<T> o) {
        return this.getInfo().compareTo(o.getInfo());

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Boolean isGreaterThan(@NotNull Node<T> other) {
        return this.getInfo().compareTo(other.getInfo()) > 0;
    }

    public Boolean isLowerThan(@NotNull Node<T> other) {
        return this.getInfo().compareTo(other.getInfo()) < 0;
    }

    public Node<T> getSibling() {

        Node<T> parent = this.getParent();

        if(parent.getRight().compareTo(this) == 0) {
            return parent.getLeft();
        } else {
            return parent.getRight();
        }


    }

    @Override
    public String toString() {

        if (parent == null) {
            return "Node{" +
                    "info=" + info +
                   // ", parent=" + parent.info + "\n"+
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        } else {
            return "Node{" +
                    "info=" + info +
                    ", parent=" + parent.info + "\n"+
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}