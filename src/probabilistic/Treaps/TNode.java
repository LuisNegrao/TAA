package probabilistic.Treaps;

import com.sun.istack.internal.NotNull;

import java.util.Random;

public class TNode<T extends Comparable<T>> {
    private T key;
    private int priority;
    public TNode<T> left;
    public TNode<T> right;


    /*Constructor*/

    public TNode() {
        this.key = null;
        this.priority = Integer.MAX_VALUE;
        this.left = this;
        this.right = this;
    }
    public TNode(T key){
        this.key = key;
        this.priority= new Random().nextInt(100);
    }

    public TNode(T key , TNode<T> left,TNode<T> right){
        this.key= key;
        this.priority= new Random().nextInt(100);
        this.left = left ;
        this.right = right;
    }

    /*Getters and Setters*/

    public T getKey() {
        return key;
    }

    public int getPriority() {
        return priority;
    }

    public TNode<T> getLeft() {
        return left;
    }

    public void setLeft(TNode<T> left) {
        this.left = left;
    }

    public TNode<T> getRight() {
        return right;
    }

    public void setRight(TNode<T> right) {
        this.right = right;
    }

    /*Comparison methods*/
    public int compareTo(@NotNull TNode<T> o) {
        return this.getKey().compareTo(o.getKey());

    }

    public Boolean isGreaterThan(@NotNull TNode<T> other) {
        return this.getKey().compareTo(other.getKey()) > 0;
    }

    public Boolean isLowerThan(@NotNull TNode<T> other) {
        return this.getKey().compareTo(other.getKey()) < 0;
    }

    public Boolean isGreaterThan(@NotNull T other) {
        return this.getKey().compareTo(other) > 0;
    }

    public Boolean isLowerThan(@NotNull T other) {
        return this.getKey().compareTo(other) < 0;
    }


    public Boolean isEqualTo(@NotNull T other) {
        return this.getKey().compareTo(other) == 0;
    }

    @Override
    public String toString() {
        return "TNode{" +
                "key=" + key +
                ", priority=" + priority +
                "\n, left=" + left +
                ", right=" + right +
                "}";
    }
}