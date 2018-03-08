package com.hieunguyen.lab.slidingwindow;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * For example, given:
 *      s: "barfoothefoobarman"
 *      words: ["foo", "bar"]
 * You should return the indices: [0,9]
 * Created by hieunguyen on 2/26/17.
 */
public class SubStrConcatAllWord {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s == null || words == null || words.length == 0 || words[0].length() > s.length()) {
            return result;
        }
        int len = words[0].length();

        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        Map<String, Integer> currentFound = new HashMap<>();
        String str = "";
        String tmp = "";

        for (int i = 0; i < len; i++) {
            int count = 0;
            int begin = i;

            // move the window by step = word length
            for (int j = begin; j + len <= s.length(); j += len) {
                str = s.substring(j, j + len);
                // if a match found, put it to current found and proceed
                // otherwise reset the current found, the count and increase the pointer by word length
                if (map.containsKey(str)) {
                    currentFound.put(str, currentFound.getOrDefault(str, 0) + 1);

                    // found one word with count equals to word count in map
                    if (currentFound.get(str) <= map.get(str)) {
                        count++;
                    }

                    // move forward if found the duplicate word in string
                    while(currentFound.get(str) > map.get(str)) {
                        tmp = s.substring(begin, begin + len);
                        currentFound.put(tmp, currentFound.get(tmp) - 1);
                        begin += len;
                        if (currentFound.get(tmp) < map.get(tmp)) {
                            count--;
                        }
                    }

                    if (count == words.length) {
                        result.add(begin);
                        tmp = s.substring(begin, begin + len);
                        currentFound.put(tmp, currentFound.get(tmp) - 1);
                        begin += len;
                        count--;
                    }
                } else {
                    currentFound.clear();
                    count = 0;
                    begin = j + len;
                }
            }
            currentFound.clear();
        }
        return result;
    }
    public static void main(String[] args) {
        List<Integer> result = findSubstring("barfoobarbarfoothefoobarmanbarbartfoobar", new String[]{"foo", "bar"});
        result.forEach(System.out::println);
    }
}
