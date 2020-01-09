package com.horstmann.corejava.v1ch09.sieve;

import java.util.Arrays;
import java.util.BitSet;

public class MyBitSetTest {

    public static void main(String[] args) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) bits1.set(i);
            if ((i % 5) != 0) bits2.set(i);
        }

        long[] words1 = bits1.toLongArray();
        System.out.println("bits1 array: " + Arrays.toString(words1));
        System.out.println("bits1 binary string: " + Long.toBinaryString(words1[0]));

        long[] words2 = bits2.toLongArray();
        System.out.println("\nbits2 array: " + Arrays.toString(words2));
        System.out.println("bits2 binary string: " + Long.toBinaryString(words2[0]));

        System.out.println("\nInitial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        System.out.println("\nbits1 nextSetBit from 0: " + bits1.nextSetBit(0));
        System.out.println("bits1 nextClearBit from 0: " + bits1.nextClearBit(0));

        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);


        System.out.println("bits1.get(0): " + bits1.get(0));
        System.out.println("bits1.get(1): " + bits1.get(1));
        bits1.set(1);
        System.out.println("bits1.get(1) after bits1.set(1): " + bits1.get(1));
        bits1.clear(1);
        System.out.println("bits1.get(1) after bits1.clear(1): " + bits1.get(1));

    }
}
