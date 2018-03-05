package com.kms.techcon.ats;

import java.util.*;

/**
 * Created by hieunguyen on 12/5/16.
 */
public class LookAndSay {

    static String lookAndSay(String number) {
        StringBuilder result = new StringBuilder();
        char [] chars = number.toCharArray();
        int currentNum = -1;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            int n = Character.getNumericValue(chars[i]);
            if (currentNum == -1) {
                currentNum = n;
                count++;
            } else {
                if (currentNum == n) {
                    count++;
                } else {
                    result.append(count).append(currentNum);
                    currentNum = n;
                    count = 1;
                }
            }
        }
        result.append(count).append(currentNum);
        return result.toString();
    }

    public static void main(String [] args) {
        System.out.println(lookAndSay("111221"));
    }
}
