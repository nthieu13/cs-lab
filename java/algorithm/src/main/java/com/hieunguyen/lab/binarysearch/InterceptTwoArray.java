package com.hieunguyen.lab.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Giving two sorted array, find duplicated elements.
 * @author hieunguyen on 3/6/18.
 */
public class InterceptTwoArray {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 6, 8, 9, 11};
        int[] b = new int[]{3, 5, 6, 7, 9};

        System.out.println(Arrays.toString(intercept(a, b)));
    }

    private static int[] intercept(int[] a, int[] b) {
        if (a.length == 0) return b;
        if (b.length == 0) return a;

        int i = 0;
        int j = 0;

        List<Integer> c = new ArrayList<>();

        while(i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                c.add(a[i]);
                i++; j++;
            }
        }
        return c.stream().mapToInt(n -> n).toArray();
    }
}
