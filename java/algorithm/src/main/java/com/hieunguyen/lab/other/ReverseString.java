package com.hieunguyen.lab.other;

/**
 * Created by hieunguyen on 12/17/16.
 */
public class ReverseString {

    public static void main(String[] args) {
        String s = "xxhellojpx world";

        char[] c = s.toCharArray();
        int l = c.length;
        int half = l/2;

        for (int i = 0; i < half; i++) {
            char temp = c[i];
            c[i] = c[l - 1 - i];
            c[l - 1 - i] = temp;
        }
        System.out.println(new String(c));
    }
}
