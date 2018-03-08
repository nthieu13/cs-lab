package com.hieunguyen.lab.backtracking;

import java.util.*;

/**
 * Given a maze, NxN matrix. A rat has to find a path from source to destination.
 * maze[0][0] (left top corner)is the source and maze[N-1][N-1](right bottom corner) is destination.
 * There are few cells which are blocked, means rat cannot enter into those cells.
 * Rat can move in any direction (left, right, up and down).
 * Input: A 2D-matrix with 0’s and 1’s fill in it. 0 means that cell is blocked and 1 means rat can move to that cell.
 *
 * Created by hieunguyen on 3/1/17.
 */
public class RatInMaze {

    static class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row &&
                    col == cell.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static void move(int[][] maze, int n, List<Cell> solutions, Cell cell) {
        if (!isValid(cell, n, maze)) {
            return;
        }

        if (cell.row == n - 1 && cell.col == n - 1) {
            solutions.add(cell);
            printSolution(solutions, n);
            return;
        }

        // try all paths
        List<Cell> moves = next(cell, maze, n, solutions);
        for(Cell c : moves) {
            // clone solution
            List<Cell> s = new ArrayList<>();
            s.addAll(solutions);
            s.add(c);

            move(maze, n, s, c);
        }
    }

    private static void printSolution(List<Cell> solutions, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                boolean found = false;
                for (Cell c : solutions) {
                    if (c.row == i && c.col == j) {

                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
                System.out.print(" ");
                if (j == n - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    private static List<Cell> next(Cell p, int[][] maze, int n, List<Cell> visited) {
        int[] rowMoves = {0, 0, -1, 1};
        int[] colMoves = {-1, 1, 0, 0};

        List<Cell> result = new ArrayList<>();
        for (int i = 0; i < rowMoves.length; i++) {
            Cell next = new Cell(p.row + rowMoves[i], p.col + colMoves[i]);
            if (isValid(next, n, maze) && !visited.contains(next)) {
                result.add(next);
            }
        }
        return result;
    }

    private static boolean isValid(Cell cell, int n, int[][] maze) {
        if (cell.row >= 0 && cell.row < n
                && cell.col >= 0 && cell.col < n
                && maze[cell.row][cell.col] == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = new int[][]{
                {1, 1, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 1, 0},
                {0, 1, 1, 1}
        };

        List<Cell> solutions = new ArrayList<>();
        solutions.add(new Cell(0, 0));

        move(maze, 4, solutions, new Cell(0, 0));
    }
}
