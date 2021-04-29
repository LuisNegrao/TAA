package trees.node;

import com.sun.istack.internal.NotNull;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T info;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;
    public String color;
    private int height;
    /*Constructor*/

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

    public Node<T> setLeft(Node<T> left) {
        return this.left = left;

    }

    public Node<T> setRight(Node<T> right) {
        return this.right = right;
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

    @Override
    public String toString() {
        return "Node{" +
                "info=" + info +
                //", parent=" + parent.getInfo() + "\n"+
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}