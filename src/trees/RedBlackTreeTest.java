package trees;

import org.junit.jupiter.api.Test;
import trees.node.Node;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {

    @Test
    void rotateLeft() {

        Tree<Integer> tree = new RedBlackTree<Integer>(new Node<>(4));
        tree.insert(new Node<>(3));
        tree.insert(new Node<>(2));

        assertEquals(3, tree.find(new Node<>(4)).getParent().getInfo());


    }

    @Test
    void rotateRight() {
    }

    @Test
    void insert() {
    }

    @Test
    void contains() {
    }

    @Test
    void find() {

        Tree<Integer> tree = new RedBlackTree<Integer>(new Node<>(4));
        tree.insert(new Node<>(5));
        tree.insert(new Node<>(7));

        assertEquals(7, tree.find(new Node<>(7)).getInfo());

    }

    @Test
    void remove() {
    }
}