package com.hieunguyen.lab.dfs;


/**
 * Given a binary tree, find the its height (the longest path from the root to a leaf).
 * @author hieunguyen on 3/7/18.
 */
public class TreeHeight {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(5, new Node(2, new Node(1), new Node(4, new Node(3), null)),
                new Node(6, null, new Node(7)));

        System.out.println(height(tree));

        printPreOrder(tree);

        System.out.println();
        printPostOrder(tree);

        System.out.println();
        printMinToMax(tree);
    }

    private static int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private static void printPreOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.print(node.value);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    private static void printPostOrder(Node node) {
        if(node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.value);

    }

    private static void printMinToMax(Node node) {
        if(node == null) {
            return;
        }
        printMinToMax(node.left);
        System.out.print(node.value);
        printMinToMax(node.right);
    }
}
