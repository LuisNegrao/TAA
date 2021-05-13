package trees.test;

import org.junit.jupiter.api.Test;
import trees.BST.BinarySearchTree;
import trees.node.Node;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void add() {
        BinarySearchTree<Integer> teste = new BinarySearchTree<>(0);

        teste.insert(new Node<>(5));
        teste.insert(new Node<>(9));
        teste.insert(new Node<>(3));
        teste.insert(new Node<>(10));
        teste.insert(new Node<>(6));

        teste.print();

        assertEquals(9,teste.find(new Node<>(9)).getInfo());
        assertEquals(5,teste.find(new Node<>(5)).getInfo());
        assertEquals(3,teste.find(new Node<>(3)).getInfo());
        assertEquals(10,teste.find(new Node<>(10)).getInfo());
        assertEquals(6,teste.find(new Node<>(6)).getInfo());
    }

    @Test
    void contains() {
        BinarySearchTree<Integer> teste = new BinarySearchTree<>(4);

        teste.insert(new Node<>(10));
        teste.insert(new Node<>(9));
        teste.insert(new Node<>(7));
        teste.insert(new Node<>(3));

        assertTrue(teste.contains(new Node<>(10)));
        assertFalse(teste.contains(new Node<>(13)));
        assertTrue(teste.contains(new Node<>(7)));
        assertTrue(teste.contains(new Node<>(3)));
        assertTrue(teste.contains(new Node<>(9)));



    }

    @Test
    void parent() {
        BinarySearchTree<Integer> teste = new BinarySearchTree<>(4);

        teste.insert(new Node<>(5));
        teste.insert(new Node<>(10));
        teste.insert(new Node<>(9));
        teste.insert(new Node<>(7));
        teste.insert(new Node<>(3));

        //teste.print();

        assertEquals(4, teste.find(new Node<>(5)).getParent().getInfo());
        assertEquals(5, teste.find(new Node<>(10)).getParent().getInfo());
        assertEquals(4, teste.find(new Node<>(3)).getParent().getInfo());


    }
    @Test
    void delete() {
        BinarySearchTree<Integer> teste = new BinarySearchTree<>(4);

        teste.insert(new Node<>(5));
        //teste.insert(new Node<>(9));
//        teste.insert(new Node<>(3));
//        teste.insert(new Node<>(10));
//        teste.insert(new Node<>(6));


//        teste.print();

        teste.remove(new Node<>(5));

        teste.print();

//        assertEquals(9,teste.find(new Node<>(9)).getInfo());
//        assertEquals(5,teste.find(new Node<>(5)).getInfo());
        assertNull(teste.find(new Node<>(5)));
    }
//
    @Test
    void find() {

        BinarySearchTree<Integer> teste = new BinarySearchTree<>(4);

        teste.insert(new Node<>(5));
        teste.insert(new Node<>(9));
        teste.insert(new Node<>(3));
        teste.insert(new Node<>(2));
        teste.insert(new Node<>(4));
        teste.insert(new Node<>(7));
        teste.insert(new Node<>(10));
        teste.insert(new Node<>(6));


        assertEquals(5, teste.find(new Node<>(5)).getInfo());
        assertEquals(9, teste.find(new Node<>(9)).getInfo());
        assertNull(teste.find(new Node<>(13)));
        assertNull(teste.find(new Node<>(13)));
    }
    @Test
    void testToString() {
    }
}