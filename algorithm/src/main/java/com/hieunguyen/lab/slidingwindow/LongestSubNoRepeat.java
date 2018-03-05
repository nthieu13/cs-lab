package com.hieunguyen.lab.slidingwindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a string, find the length of the longestIncreasingListMemo substring without repeating characters.
 *   Examples:
 *   Given "abcabcbb", the answer is "abc", which the length is 3.
 *   Given "bbbbb", the answer is "b", with the length of 1.
 *   Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Created by hieunguyen on 2/26/17.
 */
public class LongestSubNoRepeat {
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;

        if (s == null) {
            return result;
        }

        int begin = 0;
        int end = 0;

        Map<Character, Integer> map = new HashMap<>();
        boolean repeat = false;

        while(end < s.length()) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) {
                repeat = true;
            }
            end++;
            while(repeat) {
                char x = s.charAt(begin);
                map.put(x, map.get(x) - 1);
                if (map.get(x) == 1) {
                    repeat = false;
                }
                begin++;
            }
            result = Math.max(result, end - begin);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("c"));
        System.out.println(lengthOfLongestSubstring("aa"));
        System.out.println(lengthOfLongestSubstring("au"));
    }
}
