package com.hieunguyen.lab.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hieunguyen on 12/17/16.
 */
public class Palindrome {

    public static void main(String[] args) {
        String src = "abababbbabbbaba";

        //String src = "baababa";
        System.out.println(palindrome(src));
    }

    public static int palindrome(String s) {
        List<String> p = new ArrayList<>();
        if (s.length() < 2)
            return 0;

        for (int i = 0; i < s.length(); i++) {
            expand(p, s, i, i + 1);
            expand(p, s, i, i);
        }

        return p.size();
    }

    public static void expand(List<String> p, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (left < right) {
                p.add(s.substring(left, right + 1).intern());
            }
            left--;
            right++;
        }
    }
}
