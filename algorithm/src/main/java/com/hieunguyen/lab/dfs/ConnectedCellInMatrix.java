package com.hieunguyen.lab.dfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Print the number of cells in the largest region in the given matrix.
 *
 * Sample input
 *  4
 *  4
 *  1 1 0 0
 *  0 1 1 0
 *  0 0 1 0
 *  1 0 0 0
 * Sample output:
 *  5
 *
 * Created by hieunguyen on 3/21/17.
 */
public class ConnectedCellInMatrix {

    public static List<Cell> findNext(Cell c, int n, int m, int[][] grid) {
        List<Cell> nextCells = new ArrayList<>();
        int[] xd = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] yd = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < xd.length; i++) {
            Cell next = new Cell(c.x + xd[i], c.y + yd[i]);
            if (isValidCell(next,  n, m, grid)) {
                nextCells.add(next);
            }
        }

        return nextCells;
    }

    public static boolean isValidCell(Cell c, int n, int m, int[][] grid) {
        return c.x >= 0 && c.x < n && c.y >= 0 && c.y < m && isFilledCell(c, grid);
    }

    public static boolean isFilledCell(Cell c, int[][] grid) {
        return grid[c.x][c.y] == 1;
    }

    public static int findConnectedCell(int[][] grid, int n, int m) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int result = 0;
        List<Cell> visited = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Cell current = new Cell(i, j);
                if (visited.contains(current)) {
                    continue;
                }
                if (!isFilledCell(current, grid)) {
                    visited.add(current);
                    continue;
                }
                Deque<Cell> s = new ArrayDeque<>();
                s.push(current);

                int count = 0;
                while(!s.isEmpty()) {
                    Cell c = s.pop();
                    visited.add(c);
                    count++;
                    // find next connected cell
                    List<Cell> cells = findNext(c, n, m, grid)
                            .stream()
                            .filter(e -> !visited.contains(e) && !s.contains(e))
                            .collect(Collectors.toList());

                    cells.forEach(s::push);
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for (int grid_i = 0; grid_i < n; grid_i++) {
            for (int grid_j = 0; grid_j < m; grid_j++) {
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(findConnectedCell(grid, n, m));
    }
}

class Cell {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
