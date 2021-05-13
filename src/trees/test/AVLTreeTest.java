package trees.test;

import org.junit.jupiter.api.Test;
import trees.AVLTree;
import trees.node.Node;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void insert() {

        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        long startTime = System.nanoTime();
        // Time for 100000000 odes = Java Heap Space fucked up
        /*
        * 10 nodes = 0 ms
        * 100 nodes = 1 ms
        * 1000 nodes = 3 ms
        * 10000 nodes = 11 ms
        * 100000 nodes = 33 ms
        * 1000000 nodes = 204 ms
        * 10000000 nodes = 4398 ms
        * 50000000 nodes = 24557 ms
        *
         * */
        for (int i = 0; i < 10000000; i++) {
            tree.insert(new Node<>(i));
        }

        long time = System.nanoTime() - startTime;

        System.out.println(time/1000000);

    }

    @Test
    void contains() {

        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        for (int i = 0; i < 10000000; i++) {
            tree.insert(new Node<>(i));
        }

        //TODO contains left/right most node and contains a non existing node

        long startTime = System.nanoTime();

        tree.contains(new Node<>(-1));

        long time = System.nanoTime() - startTime;

        System.out.println((float) time/1000000);

        /*
         * with 10000000 nodes search for the Right/Left most node 0.0556 ms
         * with 10000000 nodes search for a non existing node 0.09025
         *
         */

    }

    @Test
    void find() {

        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        tree.insert(new Node<>(5));
        tree.insert(new Node<>(6));
        tree.insert(new Node<>(7));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(2));

        assertEquals(5, tree.find(new Node<>(5)).getInfo());
        assertEquals(8, tree.find(new Node<>(8)).getInfo());
        assertEquals(2, tree.find(new Node<>(2)).getInfo());

    }

    @Test
    void remove() {
        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        int nodes = 10000;

        for (int i = 0; i < nodes; i++) {
            tree.insert(new Node<>(i));
        }

        long startTime = System.nanoTime();

        System.out.println(tree.remove(new Node<>(5)));

        long time = System.nanoTime() - startTime;

        System.out.println((float) time/1000000);

        /*/
        * remove Left/Right most node with 10 0.0260 ms
        * remove Left/Right most node with 100 0.167 ms
        * remove Left/Right most node with 1000 0.0223 ms
        * remove Left/Right most node with 10000 0.1180 ms
        * remove Left/Right most node with 100000 0.081895 ms
        * remove Left/Right most node with 1000000 0.094498
        * remove Left/Right most node with 10000000 0.082877
        *
         */

    }
}