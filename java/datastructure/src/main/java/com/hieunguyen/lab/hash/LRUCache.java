package com.hieunguyen.lab.hash;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by hieunguyen on 4/2/17.
 */
public class LRUCache<K, V> implements AutoCloseable {
    class CleanUpTask implements Runnable {
        private LRUCache cache;
        private boolean running;

        public CleanUpTask(LRUCache cache) {
            this.cache = cache;
            this.running = true;
        }

        @Override
        public void run() {
            while(running) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    cache.cleanUp();
                } catch (InterruptedException e) {
                    if (!running) {
                        break;
                    }
                }
            }
        }

        public void stop() {
            running = false;
        }
    }

    class Node<K, V> {
        K key;
        V value;
        long ttl;
        LocalDateTime lastAccessDateTime;
        Node<K, V> prev, next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }

    private int capacity;
    private Map<K, Node<K, V>> cache = new ConcurrentHashMap<>();
    private Node<K, V> head;
    private Node<K, V> tail;
    private Thread cleanupThread;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cleanupThread = new Thread(new CleanUpTask(this));
        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            Node<K, V> n = cache.get(key);
            update(n);
            n.lastAccessDateTime = LocalDateTime.now();
            return n.value;
        }
        return null;
    }

    public void update(Node<K, V> n) {
        // if n is tail, do nothing
        if (n.next == null) {
            return;
        }
        // if n is head, update head
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }
        // link prev and next of n together
        n.next.prev = n.prev;

        // update tail to n
        n.prev = tail;
        n.next = null;

        if (tail != null) {
            tail.next = n;
        }
        tail = n;
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }
        if (cache.containsKey(key)) {
            Node<K, V> n = cache.get(key);
            n.value = value;
            update(n);
            return;
        }
        if (cache.size() == capacity) {
            // remove head
            Node<K, V> n = head.next;
            if (n != null) {
                n.prev = null;
            }
            cache.remove(head.key);
            head = n;
        }
        // create new node
        Node<K, V> n = new Node<>(key, value);
        if (head == null) {
            head = n;
            tail = head;
        } else {
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
        cache.put(key, n);
    }

    public void cleanUp() {
        LocalDateTime now = LocalDateTime.now();
        for (Map.Entry<K, Node<K, V>> entry : cache.entrySet()) {
            Node<K, V> node = entry.getValue();
            cache.get(entry.getKey());
            long elapsedTime = ChronoUnit.SECONDS.between(node.lastAccessDateTime, now);
            if (elapsedTime > node.ttl) {
                cache.remove(entry.getKey());
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.cleanupThread.interrupt();
    }

    public static void main(String[] args) {
//        LRUCache<Integer, Integer> a = new LRUCache<>(2);
//        a.put(1, 1);
//        a.put(2, 2);
//        System.out.println(a.get(1));       // returns 1
//        a.put(3, 3);    // evicts key 2
//        System.out.println(a.get(2));       // returns -1 (not found)
//        a.put(4, 4);    // evicts key 1
//        System.out.println(a.get(1));       // returns -1 (not found)
//        System.out.println(a.get(3));       // returns 3
//        System.out.println(a.get(4));       // returns 4

        // ["LRUCache","put","get","put","get","get"]
        // [[1],[2,1],[2],[3,2],[2],[3]]
//        LRUCache<Integer, Integer> b = new LRUCache<>(1);
//        b.put(2, 1);
//        System.out.println(b.get(2));
//        b.put(3, 2);
//        System.out.println(b.get(2));
//        System.out.println(b.get(3));

        // ["LRUCache","put","put","put","put","get","get"]
        // [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        LRUCache<Integer, Integer> c = new LRUCache<>(2);
        c.put(2, 1);
        c.put(1, 1);
        c.put(2, 3);
        c.put(4, 1);
        System.out.println(c.get(1));
        System.out.println(c.get(2));
    }
}
