package com.kenzie.breadthfirstsearch.countingislands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Counts the number of islands for a map.
 */
public class IslandCounter {
    private final int width;
    private final int height;
    private final int[][]map;

    public IslandCounter(int width, int height, int[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    /**
     * Main method for manual testing.
     * @param args - unused
     */
    public static void main(String[] args) {
        IslandCounter islandCounter = new IslandCounter(5, 5, SamplesMaps.FIVE_ISLAND_MAP);
        int islandCount = islandCounter.countIslands();
        System.out.println(String.format("Found %s islands.", islandCount));
    }

    /**
     * Counts the number of islands for the map.
     * @return the number of islands for the map.
     */
    public int countIslands() {


        if (this.map == null || this.height == 0) {
            return 0;
        }

        int nr = this.height;
        int nc = this.width;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (this.map[r][c] == 1) {
                    ++num_islands;
                    this.map[r][c] = 0; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && this.map[row-1][col] == 1) {
                            neighbors.add((row-1) * nc + col);
                            this.map[row-1][col] = 0;
                        }
                        if (row + 1 < nr && this.map[row+1][col] == 1) {
                            neighbors.add((row+1) * nc + col);
                            this.map[row+1][col] = 0;
                        }
                        if (col - 1 >= 0 && this.map[row][col-1] == 1) {
                            neighbors.add(row * nc + col-1);
                            this.map[row][col-1] = 0;
                        }
                        if (col + 1 < nc && this.map[row][col+1] == 1) {
                            neighbors.add(row * nc + col+1);
                            this.map[row][col+1] = 0;
                        }
                    }
                }
            }
        }

        return num_islands;

//        return 0;
    }
}
