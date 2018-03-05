package com.hieunguyen.lab.bitmanipulation;

import java.math.BigDecimal;

/**
 * Print a double value between 0 and 1 in binary
 * Created by hieunguyen on 4/14/17.
 */
public class BinaryToString {

    public static String toString(double d) {
        if (d <= 0 || d >= 1) {
            return "ERROR";
        }
        BigDecimal n = BigDecimal.valueOf(d);
        StringBuilder builder = new StringBuilder(".");
        while (n.doubleValue() > 0) {
            if (builder.length() >= 32) {
                return "ERROR";
            }
            // 0.101 = 1 * 1/2 + 0 * 1/4 + 1 * 1/8
            // 0.101 * 2 = 1 + 0 * 1/2 + 1 * 1/4 = 1.01
            // multiply number by two to get the digit after dot. For example: 0.101 * 2 = 1.01
            BigDecimal r = n.multiply(BigDecimal.valueOf(2));
            if (r.doubleValue() >= 1) {
                builder.append(1);
                n = r.subtract(BigDecimal.valueOf(1));
            } else {
                builder.append(0);
                n = r;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(toString(0.675));
    }
}
