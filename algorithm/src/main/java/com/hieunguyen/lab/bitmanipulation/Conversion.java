package com.hieunguyen.lab.bitmanipulation;

/**
 * Count number of bits have to flipped to convert integer A to integer B.
 * Created by hieunguyen on 4/15/17.
 */
public class Conversion {

    public static int bitFlipToConvert(int a, int b) {
        int count = 0;
        // to clear least significant bit of c, c = c & (c - 1)
//        for (int  c = a ^ b; c > 0; c = c & (c - 1)) {
//            count++;
//        }

        int c = a ^ b;
        while (c > 0) {
            if ((c & 1) == 1) {
                count++;
            }
            c >>>= 1;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(bitFlipToConvert(Integer.parseInt("110100", 2), Integer.parseInt("111101", 2)));
    }
}
