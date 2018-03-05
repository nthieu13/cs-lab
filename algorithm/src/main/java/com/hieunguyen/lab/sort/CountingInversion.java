package com.hieunguyen.lab.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by hieunguyen on 3/25/17.
 */
public class CountingInversion {

    static long swapCount = 0;

    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        int leftIdx = 0;
        int rightIdx = 0;

        List<Integer> result = new ArrayList<>();
        while(leftIdx < left.size() && rightIdx < right.size()) {
            if (left.get(leftIdx) <= right.get(rightIdx)) {
                result.add(left.get(leftIdx));
                leftIdx++;
            } else {
                result.add(right.get(rightIdx));
                swapCount++;
                rightIdx++;
            }
        }

        if (leftIdx < left.size()) {
            result.addAll(left.subList(leftIdx, left.size()));
        }
        if (rightIdx < right.size()) {
            result.addAll(right.subList(rightIdx, right.size()));
        }
        return result;
    }

    public static List<Integer> sort(List<Integer> list) {
        int size = list.size();
        if (size <= 1) {
            return list;
        }
        int mid = size / 2;
        List<Integer> left = sort(list.subList(0, mid));
        List<Integer> right = sort(list.subList(mid, size));
        return merge(left, right);
    }

    public static long countInversion(int[] arr, int n) {
        if (n <= 1) {
            return 0;
        }

        sort(IntStream.of(arr).boxed().collect(Collectors.toList()));

        return swapCount;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int arr_i=0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            System.out.println(countInversion(arr, n));
        }
    }
}
