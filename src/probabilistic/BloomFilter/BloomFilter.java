package probabilistic.BloomFilter;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.nio.ByteBuffer;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.BitSet;
//import java.util.function.Function;
//
//import com.sangupta.murmur.Murmur3;
//
//
//public class BloomFilter<T extends Comparable<T>> {
//
//
//    private BitSet bloomFilter;
//    private ArrayList<Hash> hashes;
//
//    public BloomFilter(int size, int hashNum) throws IOException, NoSuchAlgorithmException {
//
//        this.bloomFilter = new BitSet();
//        this.hashes = new ArrayList<Hash>();
//
//        for (int i = 1; i <= hashNum; i++) {
//
//            hashes.add(new Hash(i,"MD5"));
//        }
//
//
//
//    }
//
//    public void add(T value) throws IOException {
//
//        ByteArrayOutputStream ba = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(ba);
//        oos.writeObject(value);
//        oos.flush();
//        //current value converted in bytes
//        byte[] data = ba.toByteArray();
//
////        for (byte b: data) {
////            System.out.println(b);
////        }
//
//        for (Hash h: this.hashes) {
//            //result contains the final obtained hash
//          byte[] result = new byte[data.length+1];
//          int i =0;
//          for(byte b:data) {
//              result[i] = (byte) (b ^ h.getHashvalue()[i++]);
//              System.out.println(result[i]);
//          }
//            System.out.println(Arrays.toString(result));
//
//        }
//    }
//
//    public boolean isPresent(T value) {
//        return true;
//    }
//
//
//    class Hash{
//        public byte[] hashvalue;
//
//        public Hash(int value,String algorithm) throws IOException, NoSuchAlgorithmException {
//            ByteBuffer b = ByteBuffer.allocate(4);
////b.order(ByteOrder.BIG_ENDIAN); // optional, the initial order of a byte buffer is always BIG_ENDIAN.
//            b.putInt(value);
//
//            byte[] result = b.array();
//
//            MessageDigest digest = MessageDigest.getInstance(algorithm);
//
//            this.hashvalue = digest.digest(result);
//        }
//
//        public byte[] getHashvalue() {
//            return hashvalue;
//        }
//    }
//}

/*

 *    Java Program to Implement Bloom Filter

 */



import java.util.*;

import java.security.*;

import java.math.*;

import java.nio.*;



/* Class BloomFilter */

class BloomFilter

{

    private byte[] set;

    private int keySize, setSize, size;

    private MessageDigest md;



    /* Constructor */

    public BloomFilter(int capacity, int k)

    {

        setSize = capacity;

        set = new byte[setSize];

        keySize = k;

        size = 0;

        try

        {

            md = MessageDigest.getInstance("MD5");

        }

        catch (NoSuchAlgorithmException e)

        {

            throw new IllegalArgumentException("Error : MD5 Hash not found");

        }

    }

    /* Function to clear bloom set */

    public void makeEmpty()

    {

        set = new byte[setSize];

        size = 0;

        try

        {

            md = MessageDigest.getInstance("MD5");

        }

        catch (NoSuchAlgorithmException e)

        {

            throw new IllegalArgumentException("Error : MD5 Hash not found");

        }

    }

    /* Function to check is empty */

    public boolean isEmpty()

    {

        return size == 0;

    }

    /* Function to get size of objects added */

    public int getSize()

    {

        return size;

    }

    /* Function to get hash - MD5 */

    private int getHash(int i)

    {

        md.reset();

        byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();

        md.update(bytes, 0, bytes.length);

        return Math.abs(new BigInteger(1, md.digest()).intValue()) % (set.length - 1);

    }

    /* Function to add an object */

    public void add(Object obj)

    {

        int[] tmpset = getSetArray(obj);

        for (int i : tmpset)

            set[i] = 1;

        size++;

    }

    /* Function to check is an object is present */

    public boolean contains(Object obj)

    {

        int[] tmpset = getSetArray(obj);

        for (int i : tmpset)

            if (set[i] != 1)

                return false;

        return true;

    }

    /* Function to get set array for an object */

    private int[] getSetArray(Object obj)

    {

        int[] tmpset = new int[keySize];

        tmpset[0] = getHash(obj.hashCode());

        for (int i = 1; i < keySize; i++)

            tmpset[i] = (getHash(tmpset[i - 1]));

        return tmpset;

    }

}



/* Class BloomFilterTest */

class BloomFilterTest

{

    public static void main(String[] args)

    {

        Scanner scan = new Scanner(System.in);

        System.out.println("Bloom Filter Test\n");



        System.out.println("Enter set capacity and key size");

        BloomFilter bf = new BloomFilter(scan.nextInt() , scan.nextInt());



        char ch;

        /*  Perform bloom filter operations  */

        do

        {

            System.out.println("\nBloomFilter Operations\n");

            System.out.println("1. insert ");

            System.out.println("2. contains");

            System.out.println("3. check empty");

            System.out.println("4. clear");

            System.out.println("5. size");



            int choice = scan.nextInt();

            switch (choice)

            {

                case 1 :

                    System.out.println("Enter integer element to insert");

                    bf.add( new Integer(scan.nextInt()) );

                    break;

                case 2 :

                    System.out.println("Enter integer element to search");

                    System.out.println("Search result : "+ bf.contains( new Integer(scan.nextInt()) ));

                    break;

                case 3 :

                    System.out.println("Empty status = "+ bf.isEmpty());

                    break;

                case 4 :

                    System.out.println("\nBloom set Cleared");

                    bf.makeEmpty();

                    break;

                case 5 :

                    System.out.println("\nSize = "+ bf.getSize() );

                    break;

                default :

                    System.out.println("Wrong Entry \n ");

                    break;

            }



            System.out.println("\nDo you want to continue (Type y or n) \n");

            ch = scan.next().charAt(0);

        } while (ch == 'Y'|| ch == 'y');

    }

}