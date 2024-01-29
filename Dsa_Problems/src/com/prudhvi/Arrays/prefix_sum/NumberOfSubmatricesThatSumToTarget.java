package com.prudhvi.Arrays.prefix_sum;

import java.util.HashMap;

public class NumberOfSubmatricesThatSumToTarget {

	public static void main(String[] args) {
		int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
		int target = 0;
		System.out.println(numSubmatrixSumTarget(matrix, target));

	}
	/**
     * Counts the number of submatrices whose sum equals the target.
     *
     * @param matrix The input matrix.
     * @param target The target sum.
     * @return The number of sub matrices whose sum equals the target.
     *
     * @TimeComplexity: O(cols^2 * rows)
     *   - The two nested loops iterate through all possible sub matrices.
     *   - For each sub matrix, there is an additional loop through the rows.
     *
     * @SpaceComplexity: O(rows)
     *   - The space used by the HashMap to store cumulative sums and their frequencies.
     */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Preprocess the matrix to calculate cumulative sums in each row
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++)
                matrix[i][j] += matrix[i][j - 1];
        }

        int subMatrixCount = 0;

        // Iterate through all possible sub matrices
        for (int colStart = 0; colStart < cols; colStart++) {
            for (int currCol = colStart; currCol < cols; currCol++) {
                // Map to store cumulative sums and their frequencies
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(0, 1); // Initialize with the sum 0
                int cumSum = 0;

                // Iterate through each row
                for (int row = 0; row < rows; row++) {
                    // Calculate cumulative sum for the current sub matrix
                    cumSum += matrix[row][currCol] - (colStart > 0 ? matrix[row][colStart - 1] : 0);

                    // Calculate the remaining sum needed to reach the target
                    int remainingSum = cumSum - target;

                    // Update the count of sub matrices with the remaining sum
                    subMatrixCount += map.getOrDefault(remainingSum, 0);

                    // Update the frequency of the current cumulative sum
                    map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
                }
            }
        }

        return subMatrixCount;
    }

}
