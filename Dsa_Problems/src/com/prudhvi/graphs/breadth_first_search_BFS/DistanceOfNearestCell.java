package com.prudhvi.graphs.breadth_first_search_BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Description:
 * Given a binary matrix A (containing 0s and 1s), for each cell,
 * find the minimum Manhattan distance to the nearest cell that contains a 1.
 *
 * Manhattan Distance: |x1 - x2| + |y1 - y2|
 * There is at least one 1 present in the matrix.
 *
 * Time Complexity  : O(N * M)
 * Space Complexity : O(N * M)
 */
public class DistanceOfNearestCell {
	// Optional: main method to test with input
    public static void main(String[] args) {
        DistanceOfNearestCell solution = new DistanceOfNearestCell();

        int[][] matrix = {
            {0, 0, 0, 1},
            {0, 0, 1, 1},
            {0, 1, 1, 0}
        };

        int[][] result = solution.solve(matrix);

        // Print result
        for (int[] row : result) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Directions: Up, Down, Left, Right
    private static final int[][] DIRECTIONS = {
        {-1, 0}, // up
        {1, 0},  // down
        {0, -1}, // left
        {0, 1}   // right
    };

    /**
     * Computes a matrix where each cell holds the shortest distance
     * to the nearest 1 in the original matrix.
     *
     * @param inputMatrix Binary matrix containing only 0s and 1s
     * @return Distance matrix with shortest distances to nearest 1
     */
    public int[][] solve(int[][] inputMatrix) {
        int rows = inputMatrix.length;
        int cols = inputMatrix[0].length;

        // Matrix to store result distances
        int[][] distanceMatrix = new int[rows][cols];

        // To track visited cells and prevent revisiting
        boolean[][] isVisited = new boolean[rows][cols];

        // Queue for BFS (multi-source)
        Queue<int[]> bfsQueue = new LinkedList<>();

        // Step 1: Initialize BFS queue with all 1s in the matrix
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (inputMatrix[row][col] == 1) {
                    bfsQueue.offer(new int[]{row, col});
                    isVisited[row][col] = true;
                    distanceMatrix[row][col] = 0; // Distance to itself is 0
                }
            }
        }

        // Step 2: Perform BFS traversal from all 1s simultaneously
        while (!bfsQueue.isEmpty()) {
            int[] currentCell = bfsQueue.poll();
            int currRow = currentCell[0];
            int currCol = currentCell[1];

            // Visit 4 directions
            for (int[] dir : DIRECTIONS) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];

                // Boundary and visit check
                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    !isVisited[newRow][newCol]) {

                    // Mark visited
                    isVisited[newRow][newCol] = true;

                    // Set distance as 1 more than current cell
                    distanceMatrix[newRow][newCol] = distanceMatrix[currRow][currCol] + 1;

                    // Add to BFS queue for further traversal
                    bfsQueue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return distanceMatrix;
    }

    
}
