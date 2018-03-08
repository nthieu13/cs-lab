package com.hieunguyen.lab.binarysearch;

/**
 * Created by hieunguyen on 8/14/17.
 */
public class SortedSearch {


    public static int countNumbers(int[] sortedArray, int lessThan) {
        if (sortedArray[sortedArray.length - 1] < lessThan) {
            return sortedArray.length;
        }

        if (sortedArray[0] >= lessThan) {
            return 0;
        }

        int index = 1;
        while(index < sortedArray.length && sortedArray[index] < lessThan) {
            index *= 2;
        }
        return search(sortedArray, lessThan, index / 2, index);
    }

    public static int search(int[] sortedArray, int lessThan, int min, int max) {
        int mid = -1;
        while(min <= max) {
            mid = (min + max) / 2;
            if (mid >= sortedArray.length) {
                max = mid - 1;
            } else {
                int midVal = sortedArray[mid];
                if (midVal > lessThan) {
                    max = mid - 1;
                } else if (midVal < lessThan) {
                    min = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { -9, -9, 0, -1, -1, 0, 4, 7,  9, 99}, -1));
    }
}
