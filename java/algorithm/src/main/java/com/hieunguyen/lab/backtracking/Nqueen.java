package com.hieunguyen.lab.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hieunguyen on 4/1/17.
 */
public class Nqueen {

    static int[] xMoves = {1, 1, -1, -1};
    static int[] yMoves = {-1, 1, -1, 1};

    static class Block {
        int x;
        int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isValid(int[][] solutions, Block block) {
        int n = solutions[0].length;
        for (int i = 0; i < n; i++) {
            if ((block.y != i && solutions[block.x][i] == 1) || (block.x != i && solutions[i][block.y] == 1)) {
                return false;
            }
            for (int j = 0; j < 4; j++) {
                int x = block.x + xMoves[j]*i;
                int y = block.y + yMoves[j]*i;
                if (x == block.x && y == block.y) {
                    continue;
                }
                if (x >= 0 && x < n && y >= 0 && y < n && solutions[x][y] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Block> next(int[][] solutions) {
        List<Block> result = new ArrayList<>();
        int n = solutions.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Block block = new Block(i, j);
                if (solutions[i][j] == 0 && isValid(solutions, block)) {
                    result.add(block);
                }
            }
        }
        return result;
    }

    private static void nqueen(int[][] solutions, Block block, int n, int count) {
        if (!isValid(solutions, block)) {
            return;
        }
        solutions[block.x][block.y] = 1;
        count++;
        if (n == count) {
            printSolution(solutions);
            System.out.println("------------------------------");
            return;
        }
        //printSolution(solutions);
        List<Block> blocks = next(solutions);
        if (blocks.isEmpty()) {
            solutions[block.x][block.y] = 0;
            return;
        }
        for (Block b: blocks) {
            nqueen(solutions, b, n, count);
        }
    }

    private static void printSolution(int[][] solutions) {
        for (int i = 0; i < solutions.length; i++) {
            for (int j = 0; j < solutions[0].length; j++) {
                System.out.print(solutions[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] boards = new int[4][4];
        for (Block b : next(boards)) {
            int[][] solutions = new int[4][4];
            solutions[b.x][b.y] = 1;
            nqueen(solutions, b, 4, 0);
        }
    }
}
