package com.hieunguyen.lab.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * Created by hieunguyen on 2/26/17.
 */
public class MinimumWindow {

    public static String minWindow(String s, String t) {
        String result = "";
        if (t == null || s == null || s.length() < t.length()) {
            return result;
        }
        int begin = 0;
        int end = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(Character c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();

        while(end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    count--;
                }
            }
            end++;
            while(count == 0) {
                char x = s.charAt(begin);
                if (map.containsKey(x)) {
                    map.put(x, map.get(x) + 1);
                    if (map.get(x) > 0) {
                        count++;
                    }
                }

                String temp = s.substring(begin, end);
                if ("".equals(result) || result.length() > temp.length()) {
                    result = temp;
                }

                begin++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String minw = minWindow("ADOBECODEBANC","ABC");
        System.out.println(minw);
    }
}
