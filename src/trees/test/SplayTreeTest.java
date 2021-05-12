package trees.test;

import org.junit.jupiter.api.Test;
import trees.SplayTree;
import trees.node.Node;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {

    @Test
    void insert() {

        SplayTree<Integer> tree = new SplayTree<Integer>(new Node<>(5));

        tree.insert(new Node<>(15));
        tree.insert(new Node<>(14));
        tree.insert(new Node<>(1));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(12));
        tree.insert(new Node<>(2));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(9));
        System.out.println("--");
        tree.print();
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

        SplayTree<Integer> tree = new SplayTree<Integer>(new Node<>(10));

        tree.insert(new Node<>(5));
        tree.insert(new Node<>(7));
        tree.insert(new Node<>(1));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(12));
        tree.insert(new Node<>(2));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(9));

        tree.remove(new Node<>(9));

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