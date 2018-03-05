package com.hieunguyen.lab.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of N coins, their values (V1, V2, … , VN), and the total sum S.
 * Find the minimum number of coins the sum of which is S (we can use as many coins of one type as we want),
 * or report that it’s not possible to select coins in such a way that they sum up to S.
 *
 * Created by hieunguyen on 2/28/17.
 */
public class MinimumCoin {

    public static int minimumCoin(int sum, int[] coins) {
        if (sum == 0 || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= sum; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }
        return dp[sum] == Integer.MAX_VALUE ? 0 : dp[sum];
    }

    public static int minCoin(int sum, int[] coins) {
        if (sum <= 0 || coins.length == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int n = coins.length;
        Map<Integer, Integer> map = new HashMap<>();

        int t = sum;
        boolean found = false;
        for (int i = n - 1; i >= 0; i--) {
            int c = coins[i];
            t -= c;
            if (t == 0) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                found = true;
                break;
            } else if (t > 0) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                while(t >= c) {
                    t -= c;
                    map.put(c, map.get(c) + 1);
                }
            } else {
                t += c;
            }
        }

        if (found) {
            return map.values().stream().mapToInt(x -> x).sum();
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(minimumCoin(7, new int[]{9, 3, 5}));
        System.out.println(minCoin(7, new int[]{9, 3, 5}));
    }
}
