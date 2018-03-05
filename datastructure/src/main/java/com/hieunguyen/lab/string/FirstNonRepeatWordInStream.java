package com.hieunguyen.lab.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hieunguyen on 4/8/17.
 */
public class FirstNonRepeatWordInStream {

    public interface Stream {
        char getNext();
        boolean hasNext();
    }

    public static String firstWord(Stream input) {
        LinkedHashMap<String, Integer> wordMap = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();
        while(input.hasNext()) {
            char c = input.getNext();
            if (Character.isSpaceChar(c)) {
                String word = builder.toString().toLowerCase();
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                builder = new StringBuilder();
                continue;
            }
            if (Character.isLetterOrDigit(c)) {
                builder.append(c);
            }
        }
        // last word
        if (builder.length() > 0) {
            String word = builder.toString().toLowerCase();
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> word : wordMap.entrySet()) {
            if (word.getValue() == 1) {
                return word.getKey();
            }
        }
        return "";
    }

    static class SimpleStream implements Stream {

        private String source;
        private int index;

        public SimpleStream(String source) {
            this.source = source;
            this.index = 0;
        }

        @Override
        public char getNext() {
            char c = source.charAt(index);
            index++;
            return c;
        }

        @Override
        public boolean hasNext() {
            if (index < source.length()) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        SimpleStream stream = new SimpleStream("The angry dog was red. And the cat was also angry.");
        System.out.println(firstWord(stream));

    }
}
