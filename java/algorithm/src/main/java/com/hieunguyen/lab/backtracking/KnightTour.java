package com.hieunguyen.lab.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The knight is placed on the first block of an empty board and, moving according to the rules of chess, must visit each square exactly once.
 *
 * Created by hieunguyen on 3/29/17.
 */
public class KnightTour {

    static class Block {
        int x;
        int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            return x == block.x &&
                    y == block.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static boolean ride(int[][] board, List<Block> solutions, Block block) {
        if (solutions.size() == 64) {
            printSolution(solutions);
            return true;
        }
        List<Block> next = nextMoves(board, block, solutions);

        for (Block b : next) {
            solutions.add(b);
            if (ride(board, solutions, b)) {
                return true;
            } else  {
                solutions.remove(b);
            }
        }
        return false;
    }

    private static boolean isValid(int[][] board, Block block) {
        if (block.x >= 0 && block.x < board[0].length
                && block.y >= 0 && block.y < board[0].length) {
            return true;
        }
        return false;
    }

    private static void printSolution(List<Block> solutions) {
        int[][] board = new int[8][8];
        for (int i = 0; i < solutions.size(); i++) {
            Block b = solutions.get(i);
            board[b.x][b.y] = i;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int k = board[i][j];
                System.out.print((k < 10 ? ("0" + k) : k) + " ");
            }
            System.out.println();
        }
    }

    private static List<Block> nextMoves(int[][] board, Block current, List<Block> solutions) {
        int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};

        List<Block> result = new ArrayList<>();
        for (int i = 0; i < xMoves.length; i++) {
            Block block = new Block(current.x + xMoves[i], current.y + yMoves[i]);
            if(isValid(board, block) && !solutions.contains(block)) {
                result.add(block);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] board = new int[8][8];

        Block first = new Block(0, 0);

        List<Block> solutions = new ArrayList<>();
        solutions.add(first);

        ride(board, solutions, first);
    }
}
