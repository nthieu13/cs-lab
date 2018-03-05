package com.hieunguyen.lab.sort;

import java.util.Scanner;

/**
 * Created by hieunguyen on 3/23/17.
 */
public class BubbleSort {

    public static void bubbleSort(int n, int[] a) {
        int totalSwap = 0;
        int lastSorted = n - 1;
        while(true) {
            // Track number of elements swapped during a single array traversal
            int numberOfSwaps = 0;

            for (int j = 0; j < lastSorted; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    numberOfSwaps++;
                }
            }

            // If no elements were swapped during a traversal, array is sorted
            if (numberOfSwaps == 0) {
                break;
            }
            lastSorted--;
            totalSwap += numberOfSwaps;
        }

        System.out.printf("Array is sorted in %d swaps.\n", totalSwap);
        System.out.printf("First Element: %d\n", a[0]);
        System.out.printf("Last Element: %d\n", a[n - 1]);
    }

    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        bubbleSort(n, a);
    }
}
