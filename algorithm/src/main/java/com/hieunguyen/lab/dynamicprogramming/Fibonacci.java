package com.hieunguyen.lab.dynamicprogramming;

/**
 * Created by hieunguyen on 4/18/17.
 */
public class Fibonacci {

    public static int fibRecur(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibRecur(n - 1) + fibRecur(n - 2);
    }

    public static int fibMemo(int n) {
        return fibMemo(n, new int[n + 1]);
    }

    public static int fibMemo(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (memo[n] == 0) {
            memo[n] = fibMemo(n - 2, memo) + fibMemo(n - 1, memo);
        }
        return memo[n];
    }

    public static int fibDp(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(fibRecur(n));
        System.out.println(fibMemo(n));
        System.out.println(fibDp(n));
    }
}
