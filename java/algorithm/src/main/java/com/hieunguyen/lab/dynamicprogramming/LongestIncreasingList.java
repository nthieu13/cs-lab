package com.hieunguyen.lab.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a sequence of N numbers – A[1] , A[2] , …, A[N] . Find the length of the longestIncreasingListMemo increasing list.
 *
 * Created by hieunguyen on 2/28/17.
 */
public class LongestIncreasingList {

    public static int longestIncreasingListDp(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int n = arr.length;

        int[] s = new int[n];
        // each single element in array has longestIncreasingListMemo list s[i] = 1
        Arrays.fill(s, 1);

        // sub-problem: s[i] has its last number arr[i]
        // in order to find s[i], we need to find s[j] where j < i and arr[j] <= arr[i]
        // if s[j] is a longer list s[j] + 1 > s[i], we set s[i] = s[j] + 1
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && s[j] + 1 > s[i]) {
                    s[i] = s[j] + 1;
                }
            }
        }
        return s[n - 1];
    }

    public static int longestIncreasingListMemo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[arr.length - 1], 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], 1);
                }
                if (arr[j] > arr[i] && map.get(arr[i]) <= map.get(arr[j])) {
                    map.put(arr[i], 1 + map.get(arr[j]));
                }
            }
        }
        return map.values()
                .stream().sorted((x, y) -> y - x)
                .iterator().next();
    }

    public static void main(String[] args) {
        System.out.println(longestIncreasingListDp(new int[]{5, 3, 4, 8, 6, 7, 2, 1, 9, 2, 3, 4, 4, 5, 6}));
        //System.out.println(longestIncreasingListDp(new int[]{5, 3, 4, 8, 6, 7}));
        //System.out.println(longestIncreasingListDp(new int[]{50, 3, 10, 7, 40, 80}));


        System.out.println(longestIncreasingListMemo(new int[]{5, 3, 4, 8, 6, 7, 2, 1, 9, 2, 3, 4, 4, 5, 6}));
        //System.out.println(longestIncreasingListMemo(new int[]{5, 3, 4, 8, 6, 7}));
        //System.out.println(longestIncreasingListMemo(new int[]{50, 3, 10, 7, 40, 80}));
    }
}
