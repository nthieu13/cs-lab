package com.hieunguyen.lab.hash;


import java.util.Map;
import java.util.TreeMap;

/**
 * Implement a hashmap using BST
 * Created by hieunguyen on 4/2/17.
 */
public class HashMapBST<K, V> {

    private TreeMap<K, V>[] buckets;
    int bucketSize = 10;
    int size = 0;
    private static final double COLLISION_CHANCE = 0.3;

    public HashMapBST() {
        buckets = new TreeMap[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new TreeMap<>();
        }
    }

    private int hash(K key, int bucketSize) {
        return key.hashCode() % bucketSize;
    }

    private void resize() {
        int newBucketSize = bucketSize * 2;
        TreeMap<K, V>[] newBuckets = new TreeMap[newBucketSize];

        for (int i = 0; i < newBucketSize; i++) {
            newBuckets[i] = new TreeMap<>();
        }

        for (int i = 0; i < bucketSize; i++) {
            TreeMap<K, V> bucket = buckets[i];
            for (Map.Entry<K, V> entry : bucket.entrySet()) {
                int newHash =  hash(entry.getKey(), newBucketSize);
                newBuckets[newHash].put(entry.getKey(), entry.getValue());
            }
        }

        bucketSize = newBucketSize;
        buckets = newBuckets;
    }

    public boolean put(K key, V value) {
        int hash = hash(key, bucketSize);
        TreeMap<K, V> bucket = buckets[hash];

        if (bucket.containsKey(key)) {
            bucket.put(key, value);
            return false;
        }

        bucket.put(key, value);
        if (((double) size / bucketSize) > COLLISION_CHANCE) {
            resize();
        }
        size++;
        return true;
    }

    public V get(K key) {
        int hash = hash(key, bucketSize);
        TreeMap<K, V> bucket = buckets[hash];
        return bucket.get(key);
    }

    public static void main(String[] args) {
        HashMapBST<String, String> map = new HashMapBST<>();
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
