package com.hieunguyen.lab.dynamicprogramming;

/**
 * A robot is located at the top-left corner of a [m x n] grid
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Created by hieunguyen on 2/27/17.
 */
public class UniquePath {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    obstacleGrid[i][j] = 1;
                    continue;
                }
                if (i > 0 && j > 0) {
                    obstacleGrid[i][j] += obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                }

                if (i == 0 && j > 0) {
                    obstacleGrid[i][j] += obstacleGrid[i][j - 1];
                }

                if (j == 0 && i > 0) {
                    obstacleGrid[i][j] += obstacleGrid[i - 1][j];
                }
            }
        }
        return obstacleGrid[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
            {0, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(grid));
    }
}
