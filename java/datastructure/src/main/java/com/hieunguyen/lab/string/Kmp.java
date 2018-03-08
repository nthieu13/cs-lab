package com.hieunguyen.lab.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Knut-Moris-Patts algorithm to search matching word in a string.
 *
 * Created by hieunguyen on 4/12/17.
 */
public class Kmp {

    public static List<Integer> search(String source, String word) {
        List<Integer> result = new ArrayList<>();

        int m = 0;
        int i = 0;
        int[] t = buildSkipTables(word);

        while(m + i < source.length()) {
            if (word.charAt(i) == source.charAt(m + i)) {
                if (i == word.length() - 1) {
                    result.add(m);
                    m++;
                    i = 0;
                    continue;
                }
                i++;
            } else {
                if (t[i] > -1) {
                    m += i - t[i];
                    i = t[i];
                } else {
                    m++;
                    i = 0;
                }
            }
        }
        return result;
    }

    private static int[] buildSkipTables(String word) {
        int n = word.length();
        int[] table = new int[n];

        table[0] = -1;
        table[1] = 0;

        int pos = 2;
        int cnd = 0;
        while(pos < n) {
            if (word.charAt(pos - 1) == word.charAt(cnd)) {
                table[pos] = cnd + 1;
                cnd++;
                pos++;
            } else if (cnd > 0) {
                cnd = table[cnd];
            } else {
                table[pos] = 0;
                pos++;
            }
        }
        return table;
    }

    public static void main(String[] args) {
        String source = "AABAACAADAABAABA name =\"users\"";
        String word = "name =\"users\"";

        List<Integer> indexes = search(source, word);


        // print results
        System.out.println("text:    " + source);

        // from brute force search method 1
        for (Integer index : indexes) {
            System.out.print("pattern: ");
            for (int i = 0; i < index; i++)
                System.out.print(" ");
            System.out.println(word);
        }
    }
}
