package com.hieunguyen.lab.bfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a grid of numbers where 0 means empty space and 1 means a wall,
 * find the number of rooms and the perimeter of each room.
 * The border of the grid is guaranteed to be walls.
 * @author hnguyen
 */
public class RoomCount {
    static class Cell {
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

            if (x != cell.x) return false;
            return y == cell.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 6;
        int[][] matrix = new int[][]{
                {1,1,1,1,1,1},
                {1,1,0,0,0,1},
                {1,0,1,1,0,1},
                {1,1,0,0,0,1},
                {1,1,1,1,1,1},
        };
        findRooms(matrix, n, m);
    }

    private static void findRooms(int[][] matrix, int n, int m) {
        int room = 0;
        Map<Integer, Integer> rooms = new HashMap<>();
        boolean[][]  visited = new boolean[n][m];

        Deque<Cell> q = new ArrayDeque<>();
        // search from 1-1 because borders are wall
        q.add(new Cell(1, 1));

        while(!q.isEmpty()){
            Cell c = q.poll();

            // skip if visited
            if (visited[c.x][c.y]) {
                continue;
            }

            // find next cells and added to queue
            List<Cell> adjacentCells = findUnvisitedCells(findAdjacentCells(c, n, m), visited, n, m);
            q.addAll(adjacentCells);

            // if open cells
            if (matrix[c.x][c.y] == 0) {
                room++;
                // find opened cells
                Deque<Cell> oq = new ArrayDeque<>();
                oq.add(c);

                while(!oq.isEmpty()) {
                    Cell o = oq.poll();

                    // skip if visited
                    if (visited[o.x][o.y]) {
                        continue;
                    }
                    visited[o.x][o.y] = true;

                    // find surround cells to calculate perimeter
                    List<Cell> surroundCells = findAdjacentCells(o, n, m);

                    // find next open cells and add to inner queue
                    List<Cell> openedCells = findUnvisitedCells(findOpenedCells(surroundCells, matrix), visited, n, m);
                    oq.addAll(openedCells);

                    // find unvisited cells and add to outer queue
                    List<Cell> unvisitedCells = findUnvisitedCells(surroundCells, visited, n, m);
                    q.addAll(unvisitedCells);

                    rooms.put(room, rooms.getOrDefault(room, 0) + findWalls(surroundCells, matrix).size());
                }
            }
            visited[c.x][c.y] = true;
        }

        System.out.println("Rooms: " + rooms.size());
        rooms.forEach((key, value) -> System.out.println("Room: " + key + " | Perimeter: " + value));
    }

    private static List<Cell> findAdjacentCells(Cell c, int n, int m) {
        List<Cell> results = new ArrayList<>();

        Cell left = new Cell(c.x - 1, c.y);
        results.add(left);

        Cell right = new Cell(c.x + 1, c.y);
        results.add(right);

        Cell top = new Cell(c.x, c.y - 1);
        results.add(top);

        Cell down = new Cell(c.x, c.y + 1);
        results.add(down);
        return results;
    }

    private static List<Cell> findOpenedCells(List<Cell> cells, int[][] matrix) {
        return cells.stream().filter(c -> matrix[c.x][c.y] == 0).collect(Collectors.toList());
    }

    private static List<Cell> findWalls(List<Cell> cells, int[][] matrix) {
        return cells.stream().filter(c -> matrix[c.x][c.y] == 1).collect(Collectors.toList());
    }

    private static List<Cell> findUnvisitedCells(List<Cell> cells, boolean[][] visited, int n, int m) {
        return cells.stream()
                .filter(a -> a.x != 0 && a.x != n && a.y != 0 && a.y != m)
                .filter(a -> !visited[a.x][a.y])
                .collect(Collectors.toList());
    }
}
