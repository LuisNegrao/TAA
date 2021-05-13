package probabilistic.BloomFilter;

import com.github.eprst.murmur3.MurmurHash3;
import sun.security.util.BitArray;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BloomFilter<T extends Comparable<T>> {


    private BitArray bloomFilter;
    private MessageDigest hasher;

    private int maxSize;
    //size of the bloom filter
    private int size;

    //number of hashes recommended
    private int hashNum;

    private MurmurHash3 hash;

    private long seed;

    public BloomFilter(int numberOfElements) throws IOException, NoSuchAlgorithmException {

        this.maxSize = numberOfElements;

        this.hashNum = (int) ((-1.44) * (Math.log(0.01) / Math.log(2))) ;

        this.size = numberOfElements*hashNum*11;

        this.bloomFilter = new BitArray(this.getSize());

        this.seed = new Random().nextLong();

        try {
            this.hasher = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //System.out.println("Algorithm does not exist");
        }


    }

    //---------------


    public int getSize() {
        return size;
    }

    public int getHashNum() {
        return hashNum;
    }

    public void emptyBloomFilter() {
      this.bloomFilter= new BitArray(this.getSize());
    }

    public void add(T value) {
        for (int i : getHashedValue(value)) {
            if (!this.bloomFilter.get(i)) {
                this.bloomFilter.set(i,true);
            }
        }
    }

    public Boolean contains(T value) {
        for (int i : getHashedValue(value)) {
            if (!this.bloomFilter.get(i))
                return false;
        }
        return true;
    }

    private List<Integer> getHashedValue(T value) {

        //List with converted indexes
        ArrayList<Integer> results = new ArrayList<>();

        int firstHashCode = value.hashCode();

        //Convert resulting hashed byte array into new int value
        byte[] convertedHash = hashedByteArray(firstHashCode, this.hasher);

        int a = Math.abs(MurmurHash3.murmurhash3_x86_32(convertedHash, 0 & 0x03, convertedHash.length, (int) this.seed));
        ////System.out.println(a);
        a = a % this.getSize();
        ////System.out.println(a);
        results.add(a);
        ////System.out.println(a);
        byte[] current;

        for (int i = 1; i < hashNum; i++) {
            current = hashedByteArray(results.get(i - 1), this.hasher);
            a = Math.abs(MurmurHash3.murmurhash3_x86_32(current, 0 & 0x03, current.length, (int) this.seed));
            a = a % this.getSize();
            results.add(a);
        }

        //return list of positions to change
        return results;
    }


    public static int byteArrayToInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF));
    }

    private byte[] hashedByteArray(int value, MessageDigest hash) {
        //hash.reset();

        byte[] bytes = ByteBuffer.allocate(this.hashNum).putInt(value).array();

        //hash.update(bytes, 0, bytes.length);
        ////System.out.println();
        return bytes;
    }

}
//
//package probabilistic.BloomFilter;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
//
//public class BloomFilter<T extends Comparable<T>> {
//
//
//    private Boolean[] bloomFilter;
//    private MessageDigest hasher;
//
//    private int maxSize;
//    //size of the bloom filter
//    private int size;
//
//    //number of hashes recommended
//    private int hashNum;
//
//    public BloomFilter(int n) throws IOException, NoSuchAlgorithmException {
//
//        this.maxSize = n;
//        this.size =n^2;
//
//        this.hashNum =2;
//
//        this.bloomFilter = new Boolean[this.getSize()];
//
//        Arrays.fill(bloomFilter,false);
//
//        try {
//            this.hasher = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            //System.out.println("Algorithm does not exist");
//        }
//
//
//    }
//
//    //---------------
//
//
//    public int getSize() {
//        return size;
//    }
//
//    public int getHashNum() {
//        return hashNum;
//    }
//
//    public void emptyBloomFilter(){
//        Arrays.fill(bloomFilter,false);
//    }
//    public void add(T value) {
//        for (int i : getHashedValue(value)) {
//            if (!this.bloomFilter[i]) {
//                this.bloomFilter[i]=true;
//            }
//        }
//    }
//
//    public Boolean contains(T value) {
//        for (int i : getHashedValue(value)) {
//            if(!this.bloomFilter[i])
//                return false;
//        }
//        return true;
//    }
//
//    private List<Integer> getHashedValue(T value) {
//
//        //List with converted indexes
//        ArrayList<Integer> results = new ArrayList<>();
//
//        int firstHashCode = value.hashCode();
//        //System.out.println(value);
//        //Convert resulting hashed byte array into new int value
//        int convertedHash = Math.abs(byteArrayToInt(hashedByteArray(firstHashCode, this.hasher)));
//        ////System.out.println(convertedHash % this.getSize());
//        ////System.out.println(convertedHash);
//        results.add(convertedHash % this.getSize());
//
//        byte[] current;
//
//        for (int i = 1; i < hashNum; i++) {
//            //System.out.println(results.get(i - 1));
//            current = hashedByteArray(results.get(i - 1), this.hasher);
//            ////System.out.println(Math.abs(byteArrayToInt(current)));
//            results.add(Math.abs(byteArrayToInt(current)) % this.getSize());
//        }
//
//        //return list of positions to change
//        return results;
//    }
//
//    public static int byteArrayToInt(byte[] bytes) {
//        return ((bytes[0] & 0xFF) << 24) |
//                ((bytes[1] & 0xFF) << 16) |
//                ((bytes[2] & 0xFF) << 8) |
//                ((bytes[3] & 0xFF));
//    }
//
//    private byte[] hashedByteArray(int value, MessageDigest hash) {
//        hash.reset();
//
//        byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
//
//        hash.update(bytes, 0, bytes.length);
//
//        return hash.digest();
//    }
//
//}