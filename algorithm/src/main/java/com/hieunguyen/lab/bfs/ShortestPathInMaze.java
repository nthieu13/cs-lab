package com.hieunguyen.lab.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a MxN matrix where each element can either be 0 or 1.
 * We need to find the shortest path between a given source cell to a destination cell.
 * The path can only be created out of a cell if its value is 1.
 * If no path to return -1.
 *
 * Created by hieunguyen on 3/1/17.
 */
public class ShortestPathInMaze {

    public static int shortestPath(int[][] maze, Pos src, Pos des) {
        if (maze == null) {
            return -1;
        }

        int n = maze.length;
        int m = maze[0].length;

        // validate source and destination
        if (maze[src.x][src.y] != 1 || maze[des.x][des.y] != 1 || !isValidPos(src, n, m) || !isValidPos(des, n, m)) {
            return -1;
        }

        // boolean array equals to maze to track visited position
        // mark source position as visited
        boolean[][] visited = new boolean[n][m];
        visited[src.x][src.y] = true;

        // queue to store node, node is a position with distance to source position
        // add source position as root node of queue
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(src, 0);
        queue.add(root);

        // loop through queue until it empty, it is bfs
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            Pos curPos = node.p;

            // reach destination, return the shortest path
            if (curPos.x == des.x && curPos.y == des.y) {
                return node.distance;
            }

            // look around (top, left, bottom, right) for next position
            // then add it to queue and mark as visited
            Pos top = new Pos(curPos.x - 1, curPos.y);
            Pos left = new Pos(curPos.x, curPos.y - 1);
            Pos bottom = new Pos(curPos.x + 1, curPos.y);
            Pos right = new Pos(curPos.x, curPos.y + 1);

            if (isValidPos(top, n, m) && maze[top.x][top.y] == 1 && !visited[top.x][top.y]) {
                queue.add(new Node(top, ++node.distance));
                visited[top.x][top.y] = true;
            }
            if (isValidPos(left, n, m) && maze[left.x][left.y] == 1 && !visited[left.x][left.y]) {
                queue.add(new Node(left, ++node.distance));
                visited[left.x][left.y] = true;
            }
            if (isValidPos(bottom, n, m) && maze[bottom.x][bottom.y] == 1 && !visited[bottom.x][bottom.y]) {
                queue.add(new Node(bottom, ++node.distance));
                visited[bottom.x][bottom.y] = true;
            }
            if (isValidPos(right, n, m) && maze[right.x][right.y] == 1 && !visited[right.x][right.y]) {
                queue.add(new Node(right, ++node.distance));
                visited[right.x][right.y] = true;
            }
        }

        return -1;
    }

    public static boolean isValidPos(Pos p, int n, int m) {
        if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < m) {
            return true;
        }
        return false;
    }

    public static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Node {
        public Pos p;
        public int distance;

        public Node(Pos p, int distance) {
            this.p = p;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        int[][] maze = new int[][]{
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
            {1, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
        };
        Pos src = new Pos(0, 5);
        Pos des = new Pos(3, 4);

        System.out.println(shortestPath(maze, src, des));
    }
}
