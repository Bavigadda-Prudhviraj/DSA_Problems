package com.prudhvi.Arrays.prefix_sum;
/*
Problem Description
	Given a 2D Matrix A of dimensions N*N, we need to return the sum of all possible submatrices.
	Problem Constraints :- 1 <= N <=30
						   0 <= A[i][j] <= 10
	
	Input Format:-Single argument representing a 2-D array A of size N x N.
	Output Format:-Return an integer denoting the sum of all possible submatrices in the given matrix.
	
	
	
	Example Input
	Input 1:
		A = [ [1, 1]
		      [1, 1] ]
	Input 2:
		A = [ [1, 2]
		      [3, 4] ]
	
	Example Output
	Output 1: 16
	Output 2: 40
	
	
	Example Explanation
	Example 1:
		Number of submatrices with 1 elements = 4, so sum of all such submatrices = 4 * 1 = 4
		Number of submatrices with 2 elements = 4, so sum of all such submatrices = 4 * 2 = 8
		Number of submatrices with 3 elements = 0
		Number of submatrices with 4 elements = 1, so sum of such submatrix = 4
		Total Sum = 4+8+4 = 16
	Example 2:
		The submatrices are [1], [2], [3], [4], [1, 2], [3, 4], [1, 3], [2, 4] and [[1, 2], [3, 4]].
	Total sum = 40
 */
public class SumOfAllSubmatrices {

	public static void main(String[] args) {
		int[][] A = {{1, 1},
			     	 {1, 1}};
		System.out.println(solve(A));

	}
	/**
	 * Calculates the sum of products of each element in the matrix
	 * with its frequency in submatrices.
	 *
	 * @param A The input matrix.
	 * @return The sum of products of each element with its frequency.
	 *
	 * @TimeComplexity: O(rows * cols)
	 *   - The two nested loops iterate through all elements of the matrix.
	 *
	 * @SpaceComplexity: O(1)
	 *   - Constant space is used for variables.
	 */
	public static int solve(int[][] A) {
	    int ans = 0;
	    int lengthRow = A.length;
	    int lengthCol = A[0].length;

	    // Iterate through each element in the matrix
	    for (int i = 0; i < lengthRow; i++) {
	        for (int j = 0; j < lengthCol; j++) {
	            // Calculate the frequency of the current element in sub matrices , (i + 1)(J + 1) element present in bottom right, 
	        	//(lengthRow - i) * (lengthCol - j) element present in top right in sub matrix
	            int frequency = ((i + 1) * (j + 1)) * (lengthRow - i) * (lengthCol - j);

	            // Get the current element
	            int element = A[i][j];

	            // Update the sum with the product of frequency and element
	            ans += (frequency * element);
	        }
	    }
	    return ans;
	}


}
