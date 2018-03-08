package com.hieunguyen.lab.binarysearch;

/**
 * Given a sorted array, find the number of elements between the number A and number B inclusive.
 * Example: 1, 2, 4, 6, 8, 10, 16, 20. Given A=5 and B=15, the number of elements between A and B is 3 (6, 8, 10).
 * @author hieunguyen on 3/6/18.
 */
public class NumberBetween {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 6, 8, 10, 16, 20};
        int a = 5;
        int b = 15;

        System.out.println(between(array, a, b));
    }

    private static int between(int[] array, int a, int b) {
        if (a > array[array.length - 1] || b < array[0]) return 0;
        if (a < array[0] && b > array[array.length - 1]) return array.length;

        int x = 0;
        int y = 0;

        for(int i = 0, j = array.length - 1; i < j; i++, j--) {
            if (array[i] > a) {
                x = i - 1;
            }
            if (array[j] < b) {
                y = j + 1;
            }
        }

        return y - x;
    }
}
