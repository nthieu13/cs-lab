package com.hieunguyen.lab.bitmanipulation;

/**
 * Given an integer n, flip exactly one bit from 0 to 1 to get the longest 1s.
 * Created by hieunguyen on 4/14/17.
 */
public class FlipBit {

    public static int flip(int n) {
        if (~n == 0) {
            return Integer.BYTES * 8;
        }
        int currentLength = 0;
        int previousLength = 0;
        int max = 1;
        while(n > 0) {
            if ((n & 1) == 1) {
                currentLength++;
            } else {
                if ((n & 2) == 0) {
                    previousLength = 0;
                } else {
                    previousLength = currentLength;
                }
                currentLength = 0;
            }
            max = Math.max(max, currentLength + previousLength + 1);
            n >>>= 1;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(flip(Integer.parseInt("11011101110", 2)));
    }
}
