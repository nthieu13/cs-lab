package com.hieunguyen.lab.string;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hieunguyen on 4/8/17.
 */
public class FindAllPalindrome {

    /**
     * O(n^3) solution.
     * @param input
     * @return
     */
    public static Set<String> findPalindrome(String input) {
        Set<String> result = new HashSet<>();
        if (input == null || input.length() == 0) {
            return result;
        }
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                String s = input.substring(i, j);
                if (isPalindrome(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return false;
        }
        int length = s.length();
        int mid = length / 2;
        int left = mid - 1;
        int right = mid + 1;
        if (length % 2 == 0) {
            right = mid;
        }
        for (int i = left, j = right; i >= 0 && j < length; i--, j++) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * O(n^2) solution.
     * @param input
     * @return
     */
    public static Set<String> printAllPalindromes(String input) {
        if (input.length() <= 2) {
            return Collections.emptySet();
        }
        Set<String> out = new HashSet<>();
        int length = input.length();

        for (int i = 1; i <= length; i++) {
            // even length palindrome
            for (int j = i - 1, k = i; j >= 0 && k < length; j--, k++) {
                if (input.charAt(j) == input.charAt(k)) {
                    out.add(input.substring(j, k + 1));
                } else {
                    break;
                }
            }
            // odd length palindrome
            for (int j = i - 1, k = i + 1; j >= 0 && k < length; j--, k++) {
                if (input.charAt(j) == input.charAt(k)) {
                    out.add(input.substring(j, k + 1));
                } else {
                    break;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        Set<String> strings = findPalindrome("sabollolpiplastcruelpopblacksheeparoundioihingerigoroussas");
        strings.forEach(System.out::println);

        //Set<String> s = printAllPalindromes("abcbaaba");
        //s.forEach(System.out::println);
    }
}
