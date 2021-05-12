import probabilistic.BloomFilter.BloomFilter;
import probabilistic.Treaps.Treap;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {
        BloomFilter<Integer> bF = new BloomFilter<>(4000000, 0.6);

        System.out.println(bF.getSize());
        System.out.println(bF.getHashNum());
        Thread.sleep(10000);
        System.out.println("------------------");

        bF.add(4000000);
        //System.out.println(bF.contains(4000000));
        int j = 0;

        while (!bF.contains(j)) {
            j++;
            System.out.println(j);
        }
        System.out.println("We needed " + j  + " iterations to find a collision");
    }

    public static void search(Treap<Integer> t, int value) {
        if (t.search(value)) {
            System.out.println("Value " + value + " was found!");
        } else System.out.println("Value " + value + " was not found!");
    }
}
