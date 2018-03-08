package com.hieunguyen.lab.binarysearch;

/**
 * Calculate square root with x precision using binary search.
 * @author hieunguyen on 3/6/18.
 */
public class SquareRoot {

    public static void main(String[] args) {
        System.out.println(sqrt(77, 0.0001));

        System.out.println(floorSqrt(77));
    }

    private static double sqrt(int n, double precision) {
        if (n < 0) return -1;
        if (n == 0  || n == 1) return 1;

        double min = 1;
        double max = n;

        while(max - min > precision) {
            double mid = (min + max) / 2;
            double dif = (mid * mid) - n;
            // System.out.println("mid: " + mid + " | dif: " + dif);
            if (Math.abs(dif) <= precision) {
                return (double) Math.round(mid * 10000d) / 10000d;
            }

            if (dif > 0) {
                max = mid;
            } else {
                min = mid;
            }
        }
        return (double) Math.round(min * 10000d) / 10000d;
    }

    private static int floorSqrt(int n) {
        if (n < 0) return -1;
        if (n == 0  || n == 1) return 1;

        int min = 1;
        int max = n;

        while(min <= max) {
            int mid = (min + max) / 2;
            int x = mid * mid;
            int y = (mid + 1) * (mid + 1);

            if (x == n) {
                return mid;
            }
            if (y == n) {
                return mid + 1;
            }
            if (x < n && y > n) {
                return mid;
            }
            if (x < n && y < n) {
                min = mid;
            }  else {
                max = mid;
            }
        }
        return -1;
    }
}
