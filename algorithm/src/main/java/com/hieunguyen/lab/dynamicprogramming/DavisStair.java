package com.hieunguyen.lab.dynamicprogramming;

import java.util.Scanner;

/**
 * Davis has S staircases in his house and he likes to climb each staircase 1, 2, or 3 steps at a time.
 * Being a very precocious child, he wonders how many ways there are to reach the top of the staircase.
 * Given the respective heights for each of the staircases in his house,
 * find and print the number of ways he can climb each staircase on a new line.
 *
 * Input Format
 * The first line contains a single integer, S, denoting the number of staircases in his house.
 * Each line i of the S subsequent lines contains a single integer, n, denoting the height of staircase .
 *
 * Output Format
 * For each staircase, print the number of ways Davis can climb it in a new line.
 *
 * Sample Input
 * 3
 * 1
 * 3
 * 7
 * Sample Output
 * 1
 * 4
 * 44
 *
 * Created by hieunguyen on 3/24/17.
 */
public class DavisStair {
    public static int climbRecur(int n) {
        if (n == 0)  {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        return climbRecur(n - 1) + climbRecur(n - 2) + climbRecur(n - 3);
    }

    public static int climbMemo(int n) {
        if (n < 0) {
            return 0;
        }
        int[] memo = new int[n + 1];
        return climbMemo(n, memo);
    }

    private static int climbMemo(int n, int[] memo) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (memo[n] == 0) {
            memo[n] = climbMemo(n - 1, memo) + climbMemo(n - 2, memo) + climbMemo(n - 3, memo);
        }
        return memo[n];
    }

    public static int climbDp(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    public static int climbDpSpaceEfficient(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }

        // only use array of 3 and shift 3 last results
        int[] dp = {1, 1, 2};

        for (int i = 3; i <= n; i++) {
            int count = dp[2] + dp[1] + dp[0];
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = count;
        }
        return dp[2];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();

        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();

            System.out.println(climbRecur(n));
            System.out.println(climbMemo(n));
            System.out.println(climbDp(n));
            System.out.println(climbDpSpaceEfficient(n));

        }
    }
}
