package probabilistic.test;

import org.junit.jupiter.api.Test;
import probabilistic.Treaps.Treap;

class TreapTest {

    Treap<Integer> t = new Treap<>();
    @Test
    void insert() {
        long start = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            t.insert(i);
        }

        long end = System.nanoTime()-start;

        System.out.println("Tempo de execução "+end/1000000);
    }

    @Test
    void print() {
    }

    @Test
    void delete() {
        for (int i = 0; i < 100000; i++) {
            t.insert(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            t.delete(i);
        }

        long end = System.nanoTime()-start;

        System.out.printf("Tempo de execução "+end/1000000);
    }

    @Test
    void search() {
    }

    @Test
    void split() {
    }

    @Test
    void merge() {
    }

    @Test
    void clearTree(){

    }
}