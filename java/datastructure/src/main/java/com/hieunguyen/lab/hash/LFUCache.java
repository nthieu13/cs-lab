package com.hieunguyen.lab.hash;


import java.util.*;
import java.util.HashMap;

/**
 * Created by hieunguyen on 4/2/17.
 */
public class LFUCache<K, V> {
    private Map<K, V> data = new HashMap<>();
    private Map<K, Integer> freq = new HashMap<>();
    private Map<Integer, LinkedHashSet<K>> seq = new HashMap<>();

    private int capacity = 10;
    private int min = 0;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        seq.put(0, new LinkedHashSet<>());
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }
        if (data.containsKey(key)) {
            increaseFreq(key);
            data.put(key, value);
            return;
        }
        if (data.size() == capacity) {
            K ek = getEvictKey();
            data.remove(ek);
            int f = freq.get(ek);
            freq.remove(ek);
            seq.get(f).remove(ek);
        }
        data.put(key, value);
        freq.put(key, 0);
        seq.get(0).add(key);
        min = 0;
    }

    private K getEvictKey() {
        return seq.get(min).iterator().next();
    }

    private void increaseFreq(K key) {
        int f = freq.get(key);
        int nf = f + 1;
        freq.put(key, nf);
        seq.get(f).remove(key);
        if (!seq.containsKey(nf)) {
            seq.put(nf, new LinkedHashSet<>());
        }
        seq.get(nf).add(key);
        if (min == f && seq.get(f).isEmpty()) {
            min++;
        }
    }

    public V get(K key) {
        if (data.containsKey(key)) {
            increaseFreq(key);
            return data.get(key);
        }
        return null;
    }

    public static void main(String[] args) {
//        LFUCache<Integer, Integer> cache = new LFUCache<>(2);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // returns 1
//        cache.put(3, 3);    // evicts key 2
//        System.out.println(cache.get(2));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3.
//        cache.put(4, 4);    // evicts key 1.
//        System.out.println(cache.get(1));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3
//        System.out.println(cache.get(4));       // returns 4


        // ["LFUCache","get","put","get","put","put","get","get"]
        // [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
//        LFUCache<Integer, Integer> a = new LFUCache<>(2);
//        System.out.println(a.get(2));
//        a.put(2, 6);
//        System.out.println(a.get(1));
//        a.put(1, 5);
//        a.put(1, 2);
//        System.out.println(a.get(1));
//        System.out.println(a.get(2));

        // ["LFUCache","put","put","put","put","get","get"]
        // [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
//        LFUCache<Integer, Integer> b = new LFUCache<>(2);
//        b.put(2, 1);
//        b.put(1, 1);
//        b.put(2, 3);
//        b.put(4, 1);
//        System.out.println(b.get(1));
//        System.out.println(b.get(2));

        //["LFUCache","put","put","get","get","put","get","get","get"]
        //[[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
//        LFUCache<Integer, Integer> c = new LFUCache<>(2);
//        c.put(2, 1);
//        c.put(3, 2);
//        System.out.println(c.get(3));
//        System.out.println(c.get(2));
//        c.put(4, 3);
//        System.out.println(c.get(2));
//        System.out.println(c.get(3));
//        System.out.println(c.get(4));

        //["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
        //[[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]
        LFUCache<Integer, Integer> d = new LFUCache<>(3);
        d.put(2, 2);
        d.put(1, 1);
        System.out.println(d.get(2));
        System.out.println(d.get(1));
        System.out.println(d.get(2));
        d.put(3, 3);
        d.put(4, 4);
        System.out.println(d.get(3));
        System.out.println(d.get(2));
        System.out.println(d.get(1));
        System.out.println(d.get(4));
    }
}
