package com.hieunguyen.lab.bitmanipulation;

/**
 * Given an integer n, find next smallest and next largest integer has the same 1s.
 * Created by hieunguyen on 4/15/17.
 */
public class NextNumber {

    public static int getNext(int n) {
        int c = count(n);

        int t = n + 1;
        while(true) {
            if (count(t) == c) {
                break;
            }
            t++;
        }
        return t;
    }

    public static int getPrev(int n) {
        int c = count(n);

        int t = n - 1;
        while(true) {
            if (count(t) == c) {
                break;
            }
            t--;
        }
        return t;
    }

    private static int count(int x) {
        int count = 0;
        int t = x;
        while (t > 0) {
            if ((t & 1) == 1) {
                count++;
            }
            t >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = Integer.parseUnsignedInt("11011001111100", 2);
        System.out.println(n + "|" + Integer.toBinaryString(n));

        int next = getNext(n);
        int prev = getPrev(n);
        System.out.println(next + "|" + Integer.toBinaryString(next));
        System.out.println(prev + "|" + Integer.toBinaryString(prev));
    }
}
