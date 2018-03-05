package com.hieunguyen.lab.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hieunguyen on 4/17/17.
 */
public class RunMaze {
    static class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] xMoves = {1, 0, -1, 0};
    static final int[] yMoves = {0, 1, 0, -1};

    private static boolean isValid(int[][] maze, Cell c) {
        int n = maze.length;
        if (c.x >= 0 && c.x < n && c.y >= 0 && c.y < n && maze[c.x][c.y] == 1) {
            return true;
        }
        return false;
    }

    private static List<Cell> findNext(int[][] maze, int[][] solution, Cell current) {
        List<Cell> result = new ArrayList<>();
        for (int i = 0; i < xMoves.length; i++) {
            Cell c = new Cell(current.x + xMoves[i], current.y + yMoves[i]);
            if (isValid(maze, c) && solution[c.x][c.y] == 0) {
                result.add(c);
            }
        }
        return result;
    }

    private static boolean isEnd(int[][] maze, Cell current) {
        if (current.x == maze.length - 1 && current.y == maze[0].length - 1) {
            return true;
        }
        return false;
    }

    public static boolean run(int[][] maze, int[][] solution, Cell current) {
        if (!isValid(maze, current)) {
            return false;
        }
        solution[current.x][current.y] = 1;
        if (isEnd(maze, current)) {
            printSolution(solution);
            return true;
        }
        List<Cell> next = findNext(maze, solution, current);
        for(Cell c : next) {
            if (!run(maze, solution, c)) {
                solution[c.x][c.y] = 0;
            }
        }
        return false;
    }

    private static void printSolution(int[][] solution) {
        int n = solution.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(solution[i][j]);
                System.out.print(" ");
                if (j == n - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] maze = new int[][]{
                {1, 1, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 1, 1, 1}
        };

        int[][] solution = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        run(maze, solution, new Cell(0, 0));
    }
}
