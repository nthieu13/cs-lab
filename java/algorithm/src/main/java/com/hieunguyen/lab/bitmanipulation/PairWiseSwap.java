package com.hieunguyen.lab.bitmanipulation;

/**
 * Swap even and odd bit with as few instruction as possible.
 *
 * Created by hieunguyen on 4/15/17.
 */
public class PairWiseSwap {

    public static int swapOddEvenBit(int x) {
        int maskRight = Integer.parseUnsignedInt("10101010", 2);
        int maskLeft = Integer.parseUnsignedInt("01010101", 2);

        System.out.println(Integer.toBinaryString(x & maskRight >>> 1));
        System.out.println(Integer.toBinaryString(x & maskLeft << 1));
        return (((x & maskRight) >>> 1) | ((x & maskLeft) << 1));
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(swapOddEvenBit(Integer.parseUnsignedInt("11100100", 2))));
    }
}
