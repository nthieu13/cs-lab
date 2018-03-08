package com.hieunguyen.lab.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a number of dollars N, and a list of dollar values for M distinct coins.
 * Find and print the number of different ways you can make change for N dollars if each coin is available in an infinite quantity.
 *
 * Sample input:
 * 4 3
 * 1 2 3
 *
 * Sample output:
 * 4
 *
 * Explain
 * 1: 1 1 1 1
 * 2: 1 1 2
 * 3: 1 3
 * 4: 2 2
 *
 * Created by hieunguyen on 3/12/17.
 */
public class CoinChange {

    /**
     * Recursive implementation.
     * @param sum
     * @param coins
     * @return
     */
    public static int coinRecur(int sum, int[] coins) {
        if (sum == 0) {
            return 1;
        }
        if (sum < 0 || coins.length == 0) {
            return 0;
        }
        int head = coins[0];
        int[] tail = Arrays.copyOfRange(coins, 1, coins.length);

        return coinRecur(sum - head, coins) + coinRecur(sum, tail);
    }

    /**
     * Memoization implementation.
     * @param sum
     * @param coins
     * @return
     */
    public static int coinMemo(int sum, int[] coins) {
        if (sum == 0 || coins.length == 0) {
            return 0;
        }
        int[][] memo = new int[sum + 1][coins.length + 1];
        return coinMemo(sum, coins, memo);
    }

    public static int coinMemo(int sum, int[] coins, int[][] memo) {
        if (sum == 0) {
            return 1;
        }
        if (sum < 0 || coins.length == 0) {
            return 0;
        }

        if (memo[sum][coins.length] == 0) {
            int head = coins[0];
            int[] tail = Arrays.copyOfRange(coins, 1, coins.length);

            memo[sum][coins.length] = coinMemo(sum - head, coins, memo) + coinMemo(sum, tail, memo);
        }

        return memo[sum][coins.length];
    }

    /**
     * Dynamic programming with bottom up.
     * @param n
     * @param coins
     * @return
     */

    public static long coinDp(int n, int[] coins) {

        long[] dp = new long[n + 1];
        dp[0] = 1;

        // Pick all coins one by one and update the dp[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[n];

//        long[][] dp = new long[n + 1][coins.length];
//        Arrays.fill(dp[0], 1);
//
//        for (int i = 1; i <= n; i++) {
//            for(int j = 0; j < coins.length; j++) {
//                long x = i - coins[j] >= 0 ? dp[i - coins[j]][j] : 0;
//                long y = j >= 1 ? dp[i][j - 1] : 0;
//                dp[i][j] = x + y;
//            }
//        }
//
//        return dp[n][coins.length - 1];
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println(coinRecur(n, new int[] {1, 2, 5}));
        System.out.println(coinMemo(n, new int[] {2, 5, 3, 6}));
        System.out.println(coinDp(n, new int[] {1, 2, 5}));

        n = 4;
        //System.out.println(coinRecur(n, new int[] {1, 2, 3}));
        //System.out.println(coinMemo(n, new int[] {1, 2, 3}));
        //System.out.println(coinDp(n, new int[]{1, 2, 3}));

    }
}
