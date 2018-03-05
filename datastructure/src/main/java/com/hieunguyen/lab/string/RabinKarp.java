package com.hieunguyen.lab.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hieunguyen on 4/13/17.
 */
public class RabinKarp {

    private static long hash(String str, int length, int prime, int base) {
        long h = 0;
        for (int i = 0; i < length; i++) {
            h = (h * base + str.charAt(i)) % prime;
        }
        return h;
    }

    private static boolean check(String txt, String pat, int startPos) {
        for (int i = 0; i < pat.length(); i++) {
            if ((startPos + i >= txt.length()) || txt.charAt(startPos + i) != pat.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int search(String txt, String pat) {
        int prime = 101;
        int base = 256;

        int n = txt.length();
        int m = pat.length();

        long h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % prime;
        }

        long patHash = hash(pat, m, prime, base);
        long txtHash = hash(txt, m, prime, base);

        for (int i = 0; i <= n - m; i++) {
            if (patHash == txtHash && check(txt, pat, i)) {
                return i;
            }
            if (i + m >= n) {
                break;
            }
            txtHash = (txtHash + prime - (h * txt.charAt(i)) % prime) % prime;
            txtHash = (txtHash * base + txt.charAt(i + m)) % prime;
        }
        return -1;
    }

    public static Map<String, Integer> multipleSearch(String txt, Set<String> pats) {
        Map<String, Integer> result = new HashMap<>();
        int prime = 101;
        int base = 256;

        int n = txt.length();
        int m = minLength(pats);

        long h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % prime;
        }

        Map<Long, String> hSet = new HashMap<>();
        for (String pat : pats) {
            hSet.put(hash(pat, m, prime, base), pat);
        }
        long txtHash = hash(txt, m, prime, base);

        for (int i = 0; i <= n - m; i++) {
            if (hSet.containsKey(txtHash) && check(txt, hSet.get(txtHash), i)) {
                result.put(hSet.get(txtHash), i);
            }
            if (i + m >= n) {
                break;
            }
            txtHash = (txtHash + prime - (h * txt.charAt(i)) % prime) % prime;
            txtHash = (txtHash * base + txt.charAt(i + m)) % prime;
        }
        return result;
    }

    private static int minLength(Set<String> pats) {
        int minLength = Integer.MAX_VALUE;
        for (String pat : pats) {
            if (pat.length() < minLength) {
                minLength = pat.length();
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        String txt = "abacadabrabracabracadabrabrabracad";
        String pat = "abracadabra";

        int offset = search(txt, pat);

        // print results
        System.out.println("text:    " + txt);

        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++)
            System.out.print(" ");
        System.out.println(pat);

        System.out.println("--------------------------------------");

        Set<String> pats = new HashSet<>();
        pats.add("abracadabra");
        pats.add("bacadabrab");

        Map<String, Integer> offsets = multipleSearch(txt, pats);
        for (Map.Entry<String, Integer> entry : offsets.entrySet()) {
            System.out.print("pattern: ");
            for (int i = 0; i < entry.getValue(); i++)
                System.out.print(" ");
            System.out.println(entry.getKey());
        }
    }
}
