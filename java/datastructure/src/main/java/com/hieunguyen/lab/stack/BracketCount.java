package com.hieunguyen.lab.stack;

import java.util.*;

/**
 * @author hnguyen
 */
public class BracketCount {
    public static void main(String[] args) {
        String s = "[{}][[<>]]<(>)";

        List<Character> brackets = Arrays.asList('(', ')', '[', ']', '{', '}', '<', '>');

        // keep only brackets in string
        String str = s.codePoints()
                .filter(c -> brackets.stream().anyMatch(b -> b == c))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Deque<Character> stack = new ArrayDeque<>();
        int count = 0;
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            i++;

            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }

            Character x = stack.peek();
            if (isPair(x, c)) {
                stack.pop();
                count++;
            } else {
                stack.push(c);
            }
        }

        System.out.println(count);
    }

    private static boolean isPair(char a, char b) {
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
}
