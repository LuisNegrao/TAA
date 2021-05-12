package probabilistic.SkipList;

public class SkipNode {
    public int key;
    public SkipNode next;
    public SkipNode prev;
    public SkipNode above;
    public SkipNode bellow;


    public SkipNode getNext() {
        return next;
    }

    public void setNext(SkipNode next) {
        this.next = next;
    }

    public SkipNode getPrev() {
        return prev;
    }

    public void setPrev(SkipNode prev) {
        this.prev = prev;
    }

    public SkipNode getAbove() {
        return above;
    }

    public void setAbove(SkipNode above) {
        this.above = above;
    }

    public SkipNode getBellow() {
        return bellow;
    }

    public void setBellow(SkipNode bellow) {
        this.bellow = bellow;
    }

    public SkipNode(int key) {
        this.key = key;
        this.above = null;
        this.next = null;
        this.prev = null;
        this.bellow = null;
    }

}
