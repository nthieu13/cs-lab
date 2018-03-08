package com.hieunguyen.lab.tree;

/**
 * Created by hieunguyen on 3/8/17.
 */
public class Trie {
    int count = 0;
    Trie[] nodes = new Trie[26];

    public void add(String name) {
        Trie t = this;

        for (char c : name.toCharArray()) {
            int i = c - 'a';
            if (t.nodes[i] == null) {
                t.nodes[i] = new Trie();
            }
            Trie n = t.nodes[i];
            n.count++;
            t = n;
        }
    }

    public int find(String partial) {
        Trie t = this;
        for (char c : partial.toCharArray()) {
            int i = c - 'a';
            if (t.nodes[i] == null) {
                return 0;
            }
            t = t.nodes[i];
        }
        return t.count;
    }

    public static void main(String[] args) {

        Trie root = new Trie();

//        root.add("hacker");
//        root.add("hackerrank");
//
//        System.out.println(root.find("hac"));
//        System.out.println(root.find("hak"));

        root.add("s");
        root.add("ss");
        root.add("sss");
        root.add("ssss");
        root.add("sssss");

        System.out.println(root.find("s"));
        System.out.println(root.find("ss"));
        System.out.println(root.find("sss"));
        System.out.println(root.find("ssss"));
        System.out.println(root.find("sssss"));
        System.out.println(root.find("ssssss"));
    }
}
