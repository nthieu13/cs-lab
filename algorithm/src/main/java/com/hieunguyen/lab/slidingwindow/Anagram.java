package com.hieunguyen.lab.slidingwindow;

import java.util.*;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Created by hieunguyen on 2/25/17.
 */
public class Anagram {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p == null || s == null || s.length() < p.length()) {
            return result;
        }

        int begin = 0;
        int end = 0;

        // conduct map of character of target string
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();

        // loop through each character in source string
        while(end < s.length()) {
            // move the end pointer, decrease count in map if matched
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                // if count of character sink to zero means the window contain enough this type of character
                if (map.get(c) == 0) {
                    count--;
                }
            }
            end++;

            // move the begin pointer if window contains all types of character
            while(count == 0) {
                char x = s.charAt(begin);
                if (map.containsKey(x)) {
                    map.put(x, map.get(x) + 1);
                    if (map.get(x) > 0) {
                        count++;
                    }
                }
                // save the result
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }
        }

        return result;
    }

    public static void main(String ... args) {
        List<Integer> result = findAnagrams("cbaeadbcabacd", "abc");
        result.stream().forEach(System.out::println);
    }
}
