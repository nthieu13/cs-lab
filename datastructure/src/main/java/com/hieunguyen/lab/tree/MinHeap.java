package com.hieunguyen.lab.tree;

/**
 * Created by hieunguyen on 4/13/17.
 */
public class MinHeap {

    int arr[];
    int size;

    public MinHeap(int initSize) {
        this.arr = new int[initSize];
        this.size = 0;
    }

    private void resize() {
        int[] newArr = new int[arr.length * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void push(int x) {
        if (size >= arr.length) {
            resize();
        }
        arr[size] = x;
        size++;

        int idx = size - 1;
        int parent = (idx - 1) / 2;

        while(idx > 0 && arr[idx] < arr[parent]) {
            swap(idx, parent);
            idx = parent;
            parent = (idx - 1) / 2;
        }
    }

    public int pop() {
        if (size == 0) {
            return 0;
        }

        int ret = arr[0];
        arr[0] = arr[size - 1];
        arr[--size] = -1;

        if (size == 2) {
            if (arr[0] > arr[1]) {
                swap(0, 1);
            }
        } else {
            sink(0);
        }

        return ret;
    }

    private void sink(int idx) {
        while(idx < size) {
            int left = idx * 2 + 1;
            int right = idx * 2 + 2;
            if (left < size && right < size) {
                // has both children
                if (arr[left] < arr[right] && arr[left] < arr[idx]) {
                    swap(left, idx);
                    idx = left;
                } else if (arr[right] <= arr[left] && arr[right] < arr[idx]) {
                    swap(right, idx);
                    idx = right;
                } else {
                    break;
                }
            } else if (left < size) {
                // has only left
                swap(left, idx);
                idx = left;
            } else if (right < size) {
                // has only right
                swap(right, idx);
                idx = right;
            } else {
                // has no child
                break;
            }
        }
    }

    public void heapify(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
        for(int i = 0; i < Math.floor(arr.length / 2.0); i++) {
            sink(i);
        }
        sink(0);
    }

    public static void main(String[] args) {
        MinHeap maxHeap = new MinHeap(10);
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(4);
        maxHeap.push(3);

        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());

        maxHeap = new MinHeap(7);
        maxHeap.heapify(new int[]{1, 5, 8, 11, 99, 4, 134});

        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
    }
}
