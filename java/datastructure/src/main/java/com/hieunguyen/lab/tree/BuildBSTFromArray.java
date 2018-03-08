package com.hieunguyen.lab.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given a sorted array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
 *
 * Input Array = {3,4,5,6,7,8,9,10,12,13}
 * Expected Output = {7,4,3,5,6,10,8,9,12,13}
 *
 * Created by hieunguyen on 4/5/17.
 */
public class BuildBSTFromArray {
    class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        public Node(int val, Node parent) {
            this.val = val;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    private Node insert(int val, Node parent) {
        if (parent == null) {
            root = new Node(val, null);
            return root;
        }
        Node node = new Node(val, parent);
        if (val > parent.val) {
            parent.right = node;
        } else if (val < parent.val) {
            parent.left = node;
        }
        return node;
    }

    public void makeBst(int[] array, Node parent) {
        if (array.length == 0) {
            return;
        }
        if (array.length == 1) {
            insert(array[0], parent);
            return;
        }
        int mid = getMidIdx(array.length);
        Node node = insert(array[mid], parent);
        makeBst(Arrays.copyOfRange(array, 0, mid), node);
        makeBst(Arrays.copyOfRange(array, mid + 1, array.length), node);
    }

    private int getMidIdx(int length) {
        int mid = length / 2;
        if (length % 2 == 0) {
            mid --;
        }
        return mid;
    }

    private String dfs(Node node) {
        if (node == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(dfs(node.left));
        builder.append(node.val);
        builder.append(",");
        builder.append(dfs(node.right));
        return builder.toString();
    }

    public String toString() {
        String result = dfs(root);
        return result.substring(0, result.length() - 1);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public int height() {
        return height(root);
    }

    public String print() {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            builder.append(node.val);
            if (node.left == null && node.right == null) {
                continue;
            }
            if (node.right !=  null) {
                stack.push(node.right);
            }
            if (node.left !=  null) {
                stack.push(node.left);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        BuildBSTFromArray bst = new BuildBSTFromArray();
        int[] array = {3, 4, 5, 6, 7, 8, 9, 10, 12, 13};

        bst.makeBst(array, null);

        System.out.println(bst.toString());
        System.out.println(bst.height());
        System.out.println(bst.print());
    }
}
