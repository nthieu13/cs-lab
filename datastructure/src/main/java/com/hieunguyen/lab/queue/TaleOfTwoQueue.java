package com.hieunguyen.lab.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Implement a stack using two queues.
 * Created by hieunguyen on 4/17/17.
 */
public class TaleOfTwoQueue {

    static class MyStack<E> {
        private Deque<E> in = new ArrayDeque<>();
        private Deque<E> out = new ArrayDeque<>();

        public void push(E value) {
            in.add(value);
        }

        public E pop() {
            E ret = null;
            if (out.isEmpty()) {
                while (in.size() > 1) {
                    out.add(in.poll());
                }
                ret = in.peek();
                in = out;
                out = new ArrayDeque<>();
            }
            return ret;
        }

        public E peek() {
            E ret = null;
            if (out.isEmpty()) {
                while (in.size() > 1) {
                    out.add(in.poll());
                }
                ret = in.peek();
                out.add(in.poll());
                in = out;
                out = new ArrayDeque<>();
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                stack.push(scan.nextInt());
            } else if (operation == 2) { // dequeue
                stack.pop();
            } else if (operation == 3) { // print/peek
                System.out.println(stack.peek());
            }
        }
        scan.close();
    }
}
