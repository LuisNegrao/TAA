import trees.BST.BinarySearchTree;
import trees.node.Node;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        for (int k = 1; k < 1000000; k *= 10) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            ArrayList<Integer> inputList = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                inputList.add(i);
            }

            for (int i = 0; i < k; i++) {
                int a = inputList.get(i);
                tree.insert(new Node<>(a));
            }
            System.out.println("normal insertion height : " + (k-1));

            tree.clear();
            ArrayList<Integer> n = (ArrayList<Integer>) inputList.clone();
            while (!n.isEmpty()) {
                int value = new Random().nextInt(n.size());
                int a = n.get(value);
                tree.insert(a);
                n.remove(value);

            }
            System.out.println("randomized insertion height : " + tree.height());
            }
    }
}
