package com.hieunguyen.lab.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Giving two sorted array, union them.
 * @author hieunguyen on 3/6/18.
 */
public class UnionTwoArray {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 6, 8, 9, 11};
        int[] b = new int[]{3, 5, 6, 7, 9};

        System.out.println(Arrays.toString(union(a, b)));
    }

    private static int[] union(int[] a, int[] b) {
        if (a.length == 0) return b;
        if (b.length == 0) return a;

        int i = 0;
        int j = 0;

        List<Integer> c = new ArrayList<>();

        while(i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c.add(a[i]);
                i++;
            } else if (a[i] > b[j]) {
                c.add(b[j]);
                j++;
            } else {
                c.add(a[i]);
                i++; j++;
            }
        }
        if (i < a.length) {
            for(int x = i; x < a.length; x++) {
                c.add(a[x]);
            }
        }
        if (j < b.length) {
            for(int x = j; x < b.length; x++) {
                c.add(b[x]);
            }
        }
        return c.stream().mapToInt(n -> n).toArray();
    }
}
