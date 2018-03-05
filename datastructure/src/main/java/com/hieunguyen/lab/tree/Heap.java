package com.hieunguyen.lab.tree;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by hieunguyen on 3/11/17.
 */
public class Heap {

    PriorityQueue<Integer> pq;

    public Heap(Comparator<Integer> comparator) {
        pq = new PriorityQueue<>(comparator);
    }

    public static void median(int e, Heap min, Heap max) {
        double z;
        if (min.pq.size() == max.pq.size()) {
            max.pq.add(e);
            swap(min, max);
            z = (double) max.pq.peek();
        } else {
            min.pq.add(e);
            swap(min, max);
            z = (double) (min.pq.peek() + max.pq.peek()) / 2;
        }
        System.out.printf("%.1f%n", z);
    }

    public static void swap(Heap min, Heap max) {
        if (!min.pq.isEmpty() && !max.pq.isEmpty() && min.pq.peek() > max.pq.peek()) {
            int a = min.pq.poll();
            int b = max.pq.poll();
            min.pq.add(b);
            max.pq.add(a);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{12, 4, 5, 3, 8, 7};

        // all elements in min heap will less than all elements in max heap
        Heap min = new Heap((x, y) -> y - x);
        Heap max = new Heap((x, y) -> x - y);
        for(int i = 0; i < a.length; i++) {
            median(a[i], min, max);
        }
    }
}
