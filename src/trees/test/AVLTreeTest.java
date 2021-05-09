package trees.test;

import org.junit.jupiter.api.Test;
import trees.AVLTree;
import trees.node.Node;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void insert() {

        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        tree.insert(new Node<>(5));
        tree.insert(new Node<>(6));
        tree.insert(new Node<>(7));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(2));

        tree.print();

        assertEquals(7, tree.find(new Node<>(5)).getRight().getInfo());
        assertEquals(3, tree.find(new Node<>(5)).getLeft().getInfo());
        assertEquals(6, tree.find(new Node<>(7)).getLeft().getInfo());
        assertEquals(8, tree.find(new Node<>(7)).getRight().getInfo());
        assertEquals(2, tree.find(new Node<>(3)).getLeft().getInfo());
        assertEquals(4, tree.find(new Node<>(3)).getRight().getInfo());



    }

    @Test
    void contains() {

        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        tree.insert(new Node<>(5));
        tree.insert(new Node<>(6));
        tree.insert(new Node<>(7));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(2));

        assertTrue(tree.contains(new Node<>(5)));
        assertTrue(tree.contains(new Node<>(8)));
        assertFalse(tree.contains(new Node<>(80)));

    }

    @Test
    void find() {

    }

    @Test
    void remove() {
        AVLTree<Integer> tree = new AVLTree<>(new Node<>(4));

        tree.insert(new Node<>(5));
        tree.insert(new Node<>(6));
        tree.insert(new Node<>(7));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(2));

        tree.remove(new Node<>(3));

        tree.print();

        //assertEquals(2, tree.find(new Node<>(5)).getLeft().getInfo());



    }
}