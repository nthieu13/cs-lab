package com.hieunguyen.lab.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by hieunguyen on 3/22/17.
 */
public class TaleOfTwoStacks {
    static class MyQueue<T> {
        Deque<T> in = new ArrayDeque<T>();
        Deque<T> out = new ArrayDeque<T>();

        public void enqueue(T t) {
            in.push(t);
        }

        public void dequeue() {
            if (out.isEmpty()) {
                while(!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            out.pop();
        }

        public T peek() {
            if (out.isEmpty()) {
                while(!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.peek();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
