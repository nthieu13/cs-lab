package com.kms.techcon.amz;

import java.util.*;

/**
 * Created by hieunguyen on 3/2/17.
 */
public class Test1 {

    public static void main(String[] args) {
        String s = "[{}][]()";

        List<Character> brackets = Arrays.asList('(', ')', '[', ']', '{', '}', '<', '>');

        String ss = "";
        for (Character c : s.toCharArray()) {
            if (brackets.contains(c)) {
                ss += c;
            }
        }

        Stack<Bracket> stack = new Stack<>();
        int i = 0;
        while (i < ss.length()) {
            char c = ss.charAt(i);

            if (!stack.isEmpty()) {
                Bracket x = stack.peek();

                if (!isBegin(c) && isBegin(x.c) && isMatch(x.c, c)) {
                    stack.pop();
                } else {
                    stack.push(new Bracket(c, i));
                }
                i++;
            } else {
                stack.push(new Bracket(c, ++i));
            }
        }

        System.out.println(stack.size());
    }

    public static boolean isBegin(Character c) {
        if (c == '(' || c == '{' || c == '[' || c == '<') {
            return true;
        }
        return false;
    }

    public static boolean isMatch(char a, char b) {
        boolean matched = false;
        if (a == '(' && b == ')') {
            matched = true;
        }
        if (a == '{' && b == '}') {
            matched = true;
        }
        if (a == '[' && b == ']') {
            matched = true;
        }
        if (a == '<' && b == '>') {
            matched = true;
        }
        return matched;
    }

    static class Bracket {
        public char c;
        public int idx;

        public Bracket(Character c, int idx) {
            this.c = c;
            this.idx = idx;
        }

    }
}
