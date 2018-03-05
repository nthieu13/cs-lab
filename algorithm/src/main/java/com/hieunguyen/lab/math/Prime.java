package com.hieunguyen.lab.math;

import java.util.Scanner;

/**
 * Suppose n is a whole number, and we want to test it to see if it is prime.
 * First, we take the square root (or the 1/2 power) of n; then we round this number up to the next highest whole number.
 * Call the result m. We must find all of the following quotients:
 *
 * qm = n / m
 * q(m-1) = n / (m-1)
 * q(m-2) = n / (m-2)
 * q(m-3) = n / (m-3)
 * . . .
 * q3 = n / 3
 * q2 = n / 2
 *
 * The number n is prime if and only if none of the q's, as derived above, are whole numbers.
 * Created by hieunguyen on 3/23/17.
 */
public class Prime {

    public static boolean isPrime(int n) {
        // 1 is not a prime
        if (n == 1) {
            return false;
        }
        // 2 is the only even number prime
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        // take square root of n and round up
        int m = ((Double) Math.ceil(Math.sqrt(n))).intValue();
        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++) {
            int n = in.nextInt();
            if (isPrime(n)) {
                System.out.println("Prime");
            } else {
                System.out.println("Not prime");
            }
        }
    }
}
