package trees.test;

import org.junit.jupiter.api.Test;
import trees.SplayTree;
import trees.node.Node;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {

    @Test
    void insert() {

        SplayTree<Integer> tree = new SplayTree<Integer>();

        for (int i = 0; i < 10; i++) {
            tree.insert(new Node<>(new Random().nextInt()));
        }
        tree.clear();
        for (int i = 0; i < 100; i++) {
            tree.insert(new Node<>(new Random().nextInt()));
        }
        tree.clear();
        for (int i = 0; i < 1000000; i++) {
            tree.insert(new Node<>(new Random().nextInt()));
        }

    }

    @Test
    void contains() {
    }

    @Test
    void find() {
    }

    @Test
    void testFind() {
    }

    @Test
    void remove() {

        SplayTree<Integer> tree = new SplayTree<Integer>();

        for (int i = 0; i < 1000; i++) {
            tree.insert(new Node<>(new Random().nextInt(10000)));
        }
        //tree.print();
        System.out.println("--------------------");
        for (int i = 0; i < 1000; i++) {
            tree.remove(new Node<Integer>(new Random().nextInt(10000)));
        }
        tree.print();
    }


    @Test
    void removeAux() {
    }

    @Test
    void print() {
    }

    @Test
    void findSmallest() {
    }
}