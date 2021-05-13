package probabilistic.test;

import org.junit.jupiter.api.Test;
import probabilistic.BloomFilter.BloomFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

class BloomFilterTest {


    final int num = 10000;
    BloomFilter<String> bF;
    BloomFilter<Integer> bF2;

    {
        try {
            bF = new BloomFilter<>(num);
            bF2 = new BloomFilter<>(num);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add_and_contain() throws IOException, NoSuchAlgorithmException {
        /**
         *
         * In this test we will verify if it is adding an element, if there exists a colision and if the predefined
         * false positive rating is being achieved ==>0.01%
         *
         * The user may change the num
         *
         * */

        int colisionCounter=0;
        for (int i = 1; i < num; i++) {
            if(!bF.contains(i+"")) {
                bF.add(i+"");
            }else colisionCounter++;}
        System.out.println(colisionCounter+" colisions");


        //Verificamos se o nosso rate de falsos positivos se manteve --> 0.01
        if(colisionCounter<num*0.01)assert(true);
        else assert(false);
    }


}