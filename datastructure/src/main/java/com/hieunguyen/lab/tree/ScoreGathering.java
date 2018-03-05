package com.hieunguyen.lab.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by hieunguyen on 4/6/17.
 */
public class ScoreGathering {
    class Node {
        int score;
        int count;
        Node parent;
        Node left;
        Node right;

        public Node(int score, Node parent) {
            this.score = score;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.count = 1;
        }

        public String toString() {
            return score + ":" + count;
        }
    }

    Node root;
    int size = 0;

    public Node find(int score) {
        if (root == null) {
            return null;
        }
        Node temp = root;
        while(temp != null) {
            if (score > temp.score) {
                temp = temp.right;
            } else if (score < temp.score) {
                temp = temp.left;
            } else {
                return temp;
            }
        }
        return null;
    }

    public void insert(int score) {
        if (root == null) {
            root = new Node(score, null);
            return;
        }
        Node existedNode = find(score);
        if (existedNode != null) {
            existedNode.count++;
            return;
        }
        Node temp = root;
        while(temp != null) {
            Node node = new Node(score, temp);
            if (score > temp.score) {
                if (temp.right == null) {
                    temp.right = node;
                    break;
                } else {
                    temp = temp.right;
                }
            }  else {
                if (temp.left == null) {
                    temp.left = node;
                    break;
                } else {
                    temp = temp.left;
                }
            }
        }
        size++;
    }

    private String bfs(Node node) {
        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return builder.toString();
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        builder.append(node.toString()).append(",");

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (n.left == null && n.right == null) {
                continue;
            }
            if (n.left != null) {
                builder.append(n.left.toString()).append(",");
                queue.add(n.left);
            } else {
                builder.append(",");
            }
            if (n.right != null) {
                builder.append(n.right.toString()).append(",");
                queue.add(n.right);
            } else {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public String toString() {
        String result = bfs(root);
        return result.substring(0, result.length() - 1);
    }

    public static void main(String[] args) {
        int[] source = {4, 2, 5, 5, 6, 1, 4};
        ScoreGathering sg = new ScoreGathering();
        for (int s : source) {
            sg.insert(s);
        }
        System.out.println(sg.toString());
    }
}
