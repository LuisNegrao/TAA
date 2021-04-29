package trees.node;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest implements Runnable {

    @Override
    public void run() {
        testInfo();
        testParent();
        testLeft();
        testRight();
        testCompareTo();
        testIsGreaterThan();
        testIsLowerThan();
    }

    @Test
    @Description("Test if info getter and setter are working correctly")
    void testInfo() {
        Node<Integer> node = new Node<Integer>(42);
        assertEquals(42,node.getInfo());

        node.setInfo(41);
        assertEquals(41,node.getInfo());

    }

    @Test
    @Description("Test if parent getter and setter are working correctly")
    void testParent() {
        Node<Integer> parent = new Node<Integer>(42);
        Node<Integer> son_left = new Node<Integer>(21);
        Node<Integer> son_right = new Node<Integer>(21);

        son_left.setParent(parent);
        son_right.setParent(parent);

        assertEquals(parent,son_left.getParent());
        assertEquals(parent,son_right.getParent());

    }

    @Test
    @Description("Test if left node getter and setter are working correctly")
    void testLeft() {
        Node<Integer> parent = new Node<Integer>(42);
        Node<Integer> son_left = new Node<Integer>(21);

        parent.setLeft(son_left);

        assertEquals(son_left,parent.getLeft());

    }

    @Test
    @Description("Test if right node getter and setter are working correctly")
    void testRight() {
        Node<Integer> parent = new Node<Integer>(42);
        Node<Integer> son_right = new Node<Integer>(21);

        parent.setRight(son_right);

        assertEquals(son_right,parent.getRight());

    }

    @Test
    @Description("Comparing Strings and Integers ")
    void testCompareTo() {
        //String Comparison
        Node<String> n1 = new Node<String>("Hello");
        Node<String> n2 = new Node<String>("World");

        assertFalse(n1.compareTo(n2) == 0);
        n2.setInfo("Hello");
        assertTrue(n1.compareTo(n2) == 0);

        //Integer Comparison
        Node<Integer> n1i = new Node<Integer>(1);
        Node<Integer> n2i = new Node<Integer>(2);

        assertFalse(n1i.compareTo(n2i) == 0);
        n2i.setInfo(1);
        assertTrue(n1i.compareTo(n2i) == 0);


    }

    @Test
    void testIsGreaterThan() {
        //String Comparison
        Node<String> n1 = new Node<String>("A");
        Node<String> n2 = new Node<String>("B");

        assertFalse(n1.isGreaterThan(n2));
        assertTrue(n2.isGreaterThan(n1));

        //Integer Comparison
        Node<Integer> n1i = new Node<Integer>(1);
        Node<Integer> n2i = new Node<Integer>(2);

        assertFalse(n1i.isGreaterThan(n2i));
        assertTrue(n2i.isGreaterThan(n1i));
    }

    @Test
    void testIsLowerThan() {
        //String Comparison
        Node<String> n1 = new Node<String>("A");
        Node<String> n2 = new Node<String>("B");

        assertFalse(n2.isLowerThan(n1));
        assertTrue(n1.isLowerThan(n2));

        //Integer Comparison
        Node<Integer> n1i = new Node<Integer>(1);
        Node<Integer> n2i = new Node<Integer>(2);

        assertFalse(n2i.isLowerThan(n1i));
        assertTrue(n1i.isLowerThan(n2i));
    }
}