package com.hieunguyen.lab.hash;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by hieunguyen on 4/2/17.
 */
public class HashMap<K, V> {
    class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) &&
                    Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private LinkedList<Pair>[] buckets;
    private int bucketSize = 10;
    private int size = 0;
    private static final double COLLISION_CHANCE = 0.3;

    public HashMap() {
        buckets = new LinkedList[10];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hashCode(K key, int bucketSize) {
        return (key.hashCode()) % bucketSize;
    }

    public boolean put(K key, V value) {
        int hash = hashCode(key, bucketSize);
        LinkedList<Pair> bucket = buckets[hash];
        for (Pair p : bucket) {
            if (p.key.equals(key)) {
                p.value = value;
                return false;
            }
        }
        bucket.push(new Pair(key, value));
        if (((double) size / bucketSize) > COLLISION_CHANCE) {
            resize();
        }
        size++;
        return true;
    }

    public V get(K key) {
        int hash = hashCode(key, bucketSize);
        LinkedList<Pair> bucket = buckets[hash];
        for (Pair p : bucket) {
            if (p.key.equals(key)) {
                return (V) p.value;
            }
        }
        return null;
    }

    private void resize() {
        int newBucketSize = bucketSize * 2;
        LinkedList<Pair>[] newBuckets = new LinkedList[newBucketSize];

        for (int i = 0; i < newBucketSize; i++) {
            newBuckets[i] = new LinkedList<>();
        }

        for (int i = 0; i < bucketSize; i++) {
            for (Pair p : buckets[i]) {
                int hash = hashCode((K) p.key, newBucketSize);
                newBuckets[hash].push(p);
            }
        }
        bucketSize = newBucketSize;
        buckets = newBuckets;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "testA");
        map.put("b", "testB");

        System.out.println(map.size);
        System.out.println(map.bucketSize);
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));

        map.put("c", "testC");
        map.put("d", "testD");
        map.put("e", "testE");

        System.out.println(map.size);
        System.out.println(map.bucketSize);
        System.out.println(map.get("a"));
        System.out.println(map.get("d"));

        map.put("c", "testCC");

        System.out.println(map.size);
        System.out.println(map.get("c"));
    }
}
