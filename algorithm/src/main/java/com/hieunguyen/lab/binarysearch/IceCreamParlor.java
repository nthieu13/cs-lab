package com.hieunguyen.lab.binarysearch;

import java.util.Arrays;

/**
 * Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together M dollars for ice cream.
 * On any given day, the parlor offers a line of flavors.
 *
 * Given the value of M and the cost of each flavor for their trips to the Ice Cream Parlor,
 * help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money M during each visit.
 * For each trip to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line.
 * You must print the smaller ID first and the larger ID second.
 *
 * Sample input:
 * 2 -- two trips
 * 4 -- first trip M dollars
 * 5 -- number of flavors N
 * 1 4 5 3 2 -- flavors and cost, id of flavor from 1 to N
 * 4
 * 4
 * 2 2 4 3
 *
 * Sample output:
 * 1 4
 * 1 2
 *
 * Created by hieunguyen on 3/16/17.
 */
public class IceCreamParlor {

    /**
     * Complexity: sort(log(n)) + outer_loop(n) * binary_search(log(n-1)) = log(n)*(n + 1) = n*log(n)
     * @param s
     * @param n
     * @param flavors
     */
    public static void search(int s, int n, int[] flavors) {
        int[] t = flavors.clone();
        Arrays.sort(t);
        int a = -1;
        int b = -1;
        for (int i = 0; i < n; i++) {
            a = t[i];
            if (a >= s) {
                continue;
            }
            int min = i + 1;
            int max = n - 1;
            while (min <= max) {
                int mid = (min + max) / 2;
                int sum = a + t[mid];
                if (sum == s) {
                    b = t[mid];
                    break;
                }
                if (sum < s) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }
            if (b != -1) {
                break;
            }
        }
        String result = "";
        for(int i = 1; i <= n; i++) {
            if (flavors[i - 1] == a || flavors[i - 1] == b) {
                result += i + " ";
            }
        }
        System.out.print(result.trim());
    }

    public static void main(String[] args) {
        int t = 1;
        for(int a0 = 0; a0 < t; a0++){
            int m = 4;
            int n = 5;
            int a[] = new int[]{1, 4, 5, 3, 2};

            search(m, n, a);
            System.out.println("");
        }
    }
}
