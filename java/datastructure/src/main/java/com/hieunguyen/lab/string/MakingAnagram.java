package com.hieunguyen.lab.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by hieunguyen on 3/22/17.
 */
public class MakingAnagram {
    public static int numberNeeded(String first, String second) {
        char[] f = first.toCharArray();
        char[] s = second.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (Character c : f) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int count = 0;
        for (Character c : s) {
            if (!map.containsKey(c)) {
                count++;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return count + map.entrySet()
                .stream()
                .filter(e -> e.getValue() != 0)
                .mapToInt(e -> Math.abs(e.getValue())).sum();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
