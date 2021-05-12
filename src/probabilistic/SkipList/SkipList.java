package probabilistic.SkipList;

import java.util.Random;

public class SkipList {

    public SkipNode header;
    public SkipNode tail;

    private int MAX_LEVELS;

    public SkipList(int MAX_LEVELS) {
        this.header = new SkipNode(Integer.MIN_VALUE);
        this.tail = new SkipNode(Integer.MAX_VALUE);
        this.header.next = tail;
        this.tail.prev = header;
        int i = 1;
        this.MAX_LEVELS = MAX_LEVELS;

        SkipNode h = this.header;
        SkipNode t = this.tail;
        while (i < MAX_LEVELS) {
            h.above = new SkipNode(Integer.MIN_VALUE);
            h.above.bellow = h;

            t.above = new SkipNode(Integer.MAX_VALUE);
            t.above.bellow = t;

            t = t.above;
            h = h.above;

            h.next = t;
            t.prev = h;

            i++;
        }
    }

    public void print() {
        SkipNode n = this.header;

        for (int i = 0; i < this.MAX_LEVELS; i++) {
            SkipNode n1 = n;
            while (n1 != null) {
                if (n1.key == Integer.MAX_VALUE) System.out.print("+inf ");
                else if (n1.key == Integer.MIN_VALUE) System.out.print("-inf ");
                else System.out.print(n1.key + " ");
                n1 = n1.next;
            }
            System.out.println();
            n = n.above;
        }
    }

    public Boolean insert(int key) {
        SkipNode[] arr = this.modded_search(key);
        SkipNode n = arr[0];
        int level = 0;

        if (this.contains(key)) return false;

        //Auxiliar Node
        SkipNode aux = n.next;
        //Create the new Node
        SkipNode new_node = new SkipNode(key);

        //Make the insertion for level 0
        n.next = new_node;
        new_node.prev = n;
        new_node.next = aux;
        aux.prev = new_node;
        level++;

        //Start generating new node levels if true until the MAX_LEVELS
        while (new Random().nextBoolean() && level < this.MAX_LEVELS) {
            //create the reference to the new node
            SkipNode new_above_node = new SkipNode(key);
            new_node.above = new_above_node;
            new_above_node.bellow = new_node;

            //Same process as above
            n = arr[level];
            aux = n.next;
            n.next = new_above_node;
            new_above_node.prev = n;
            new_above_node.next = aux;
            aux.prev = new_above_node;

            //Next iteration
            level++;
            new_node = new_above_node;
        }

        return true;
    }

    public void delete(int key) {
        SkipNode n = this.search(key);

        //Node not found return null
        if (n.key != key) return;

        while (n != null) {
            //Remove the old node
            SkipNode previous = n.prev;
            SkipNode next = n.next;
            previous.next = next;
            next.prev = previous;
            n = n.above;
        }

    }

    public Boolean contains(int key) {
        SkipNode n = this.search(key);
        return n.key == key;
    }

    public SkipNode[] modded_search(int key) {
        SkipNode[] arr = new SkipNode[this.MAX_LEVELS];
        SkipNode n = this.header;
        int level = 0;
        while (n.above != null) {
            n = n.above;
            level++;

        }

        while (n != null) {
            SkipNode n1 = n;
            while (n1.next != null && key >= n1.next.key) {
                n1 = n1.next;

            }
            arr[level] = n1;
            level--;
            n = n.bellow;
        }
        return arr;
    }

    public SkipNode search(int key) {
        SkipNode n = this.header;
        SkipNode n1 = null;
        //int level =0;
        while (n.above != null) {
            // level++;
            n = n.above;
        }

        while (n != null) {
            n1 = n;
            while (n1.next != null && key >= n1.next.key) {
                n1 = n1.next;
            }
            //System.out.println("At level :"+level+",value :"+n1.key);
            n = n.bellow;
            // level--;
        }
        return n1;
    }

}