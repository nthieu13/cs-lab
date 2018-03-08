package com.hieunguyen.lab.dynamicprogramming;

import java.util.Arrays;

/**
 * Created by hieunguyen on 4/10/17.
 */
public class CountAllPalindrome {

    public static int countRecur(String input) {
        if (input == null) {
            return 0;
        }
        int n = input.length();
        if (n == 0 || n == 1) {
            return 0;
        }
        int begin = 0;
        int end = n - 1;
        return countRecur(input, begin, end);
    }

    public static int countRecur(String input, int begin, int end) {
        if (begin == end) {
            return 0;
        }

        if (end == begin + 1) {
            if (input.charAt(begin) == input.charAt(end)) {
                return 1;
            }
            return 0;
        }

        return isPalindrome(input.substring(begin, end)) + countRecur(input, begin + 1, end)
                + countRecur(input, begin, end - 1)
                - countRecur(input, begin + 1, end - 1);
    }

    private static int isPalindrome(String input) {
        int n = input.length();
        for (int i = 0; i < n/2; i++) {
            if (input.charAt(i) != input.charAt(n - 1 - i)) {
                return 0;
            }
        }
        return 1;
    }

    public static int countMemo(String input) {
        if (input == null) {
            return 0;
        }
        int n = input.length();
        if (n == 0 || n == 1) {
            return 0;
        }
        int begin = 0;
        int end = n - 1;

        int[][] p = new int[n][n];
        int[][] memo = new int[n][n];

        for(int[] a : p) {
            Arrays.fill(a, -1);
        }
        for(int[] a : memo) {
            Arrays.fill(a, -1);
        }

        return countMemo(input, begin, end, p, memo);
    }

    public static int countMemo(String input, int begin, int end, int[][] p, int[][] memo) {
        if (begin == end) {
            return 0;
        }

        if (end == begin + 1) {
            if (input.charAt(begin) == input.charAt(end)) {
                p[begin][end] = 1;
                return 1;
            }
            p[begin][end] = 0;
            return 0;
        }

        if (p[begin][end] == -1) {
            p[begin][end] = isPalindrome(input.substring(begin, end));
        }
        if (memo[begin + 1][end] == -1) {
            memo[begin + 1][end] = countMemo(input, begin + 1, end, p, memo);
        }
        if (memo[begin][end - 1] == -1) {
            memo[begin][end - 1] = countMemo(input, begin, end - 1, p, memo);
        }
        if (memo[begin + 1][end - 1] == -1) {
            memo[begin + 1][end - 1] = countMemo(input, begin + 1, end - 1, p, memo);
        }
        return p[begin][end] + memo[begin + 1][end] + memo[begin][end - 1] - memo[begin + 1][end - 1];
    }

    public static int countDp(String input) {
        if (input == null) {
            return 0;
        }
        int n = input.length();
        if (n == 0 || n == 1) {
            return 0;
        }
        int[][] dp = new int[n][n];
        int[][] p = new int[n][n];

        // palindrome of string length = 1
        for (int i = 0; i < n; i++) {
            p[i][i] = 1;
        }

        // palindrome of string length = 2
        for (int i = 0; i < n - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                p[i][i + 1] = 1;
                dp[i][i + 1] = 1;
            }
        }

        // Palindromes of length more then 2. This loop is similar
        // to Matrix Chain Multiplication. We start with a gap of
        // length 2 and fill DP table in a way that gap between
        // starting and ending indexes increases one by one by
        // outer loop.
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < n - i ; j++) {
                int k = i + j;
                if (input.charAt(j) == input.charAt(k) && p[j + 1][k - 1] == 1) {
                    p[j][k] = 1;
                }

                dp[j][k] = p[j][k] + dp[j][k - 1] + dp[j + 1][k] - dp[j + 1][k - 1];
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        //System.out.println(countRecur("abcbabaab"));
        //System.out.println(countMemo("abcbabaab"));
        System.out.println(countDp("abcbabaab"));
    }
}
