package probabilistic.test;

import org.junit.jupiter.api.Test;
import probabilistic.BloomFilter.BloomFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {

    @Test
    void emptyBloomFilter() {
    }

    @Test
    void add() throws IOException, NoSuchAlgorithmException {

        BloomFilter<Integer> filter = new BloomFilter<Integer>(1000, 0.001);

        for (int i = 0; i < 100; i++) {
            if (filter.contains(i)) {
                System.out.println("colisao");
            }else {
                filter.add(i);
            }
        }

    }

    @Test
    void contains() {
    }
}