package com.kms.techcon.ats;

// you can also use imports, for example:
import java.util.*;


public class Solution {

    public int solution(int[] A, int[] B, int M, int X, int Y) {
        int stop = 0;
        int i = 0;
        List<Integer> q = new ArrayList<>();
        while((i = queue(A, X, Y, i, q)) <= A.length) {
            stop += drop(B, M, q);
            if (i == A.length) {
                break;
            }
        }
        return stop;
    }

    public int drop(int[] B, int M, List<Integer> queue) {
        Map<Integer, Integer> des = new HashMap<>();
        for (Integer q : queue) {
            if (q == B.length) {
                q -= 1;
            }
            int f = B[q];
            if (f >= 0 && f <= M) {
                if (des.containsKey(B[q])) {
                    des.put(B[q], des.get(B[q]) + 1);
                } else {
                    des.put(B[q], 1);
                }
            }
        }
        queue.clear();
        return des.size() + 1;
    }

    public int queue(int[] A, int X, int Y, int start, List<Integer> q) {
        int cap = 0;
        int wei = 0;
        int i = start;
        while (i < A.length) {
            wei += A[i];
            cap++;
            if (cap <= X && wei <= Y) {
                q.add(i);
            } else {
                break;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = new int[]{40, 40, 100, 80, 20};
        int[] b = new int[]{3, 3, 2, 2, 3};
        System.out.println(s.solution(a, b, 3, 5, 200));
    }
}