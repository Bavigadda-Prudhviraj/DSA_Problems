package com.prudhvi.Arrays.prefix_sum;
/*
Problem Description
	Given a matrix of integers A of size N x M and multiple queries Q, for each query, find and return the submatrix sum.
	Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.
	
	NOTE:
		Rows are numbered from top to bottom, and columns are numbered from left to right.
		The sum may be large, so return the answer mod 109 + 7.
		Also, select the data type carefully, if you want to store the addition of some elements.
		Indexing given in B, C, D, and E arrays is 1-based.
		Top Left 0-based index = (B[i] - 1, C[i] - 1)
		Bottom Right 0-based index = (D[i] - 1, E[i] - 1)
	Problem Constraints
		1 <= N, M <= 1000
		-100000 <= A[i] <= 100000
		1 <= Q <= 100000
		1 <= B[i] <= D[i] <= N
		1 <= C[i] <= E[i] <= M
	Input Format
		The first argument given is the integer matrix A.
		The second argument given is the integer array B.
		The third argument given is the integer array C.
		The fourth argument given is the integer array D.
		The fifth argument given is the integer array E.
		(B[i], C[i]) represents the top left corner of the i'th query.
		(D[i], E[i]) represents the bottom right corner of the i'th query.
	
	Output Format
		Return an integer array containing the sub matrix sum for each query.
	Example Input
	Input 1:
	
	 A = [   [1, 2, 3]
	         [4, 5, 6]
	         [7, 8, 9]   ]
	 B = [1, 2]
	 C = [1, 2]
	 D = [2, 3]
	 E = [2, 3]
	
	Example Output
	Output 1:[12, 28]
	
	
	Example Explanation
	Explanation 1:
	
	 For query 1: Submatrix contains elements: 1, 2, 4 and 5. So, their sum is 12.
	 For query 2: Submatrix contains elements: 5, 6, 8 and 9. So, their sum is 28.
	Explanation 2:
	
	 For query 1: Submatrix contains elements: 5, 17, 0 and 0. So, their sum is 22.
	 For query 2: Submatrix contains elements: 11 and 8. So, their sum is 19.
	 
 */
public class SubMatrixSumQueries {

	public static void main(String[] args) {
		 int[][] A = {{1, 2, 3},
		         	  {4, 5, 6},
		         	  {7, 8, 9}  };
		 int[] B = {1, 2};
		 int[] C = {1, 2};
		 int[] D = {2, 3};
		 int[] E = {2, 3};
		 System.out.println(solve(A, B, C, D, E));

	}

	/**
	 * Calculates the values based on the given queries and input matrices.
	 *
	 * @param A1 The input matrix.
	 * @param B  Array representing the x1 coordinates of queries.
	 * @param C  Array representing the y1 coordinates of queries.
	 * @param D  Array representing the x2 coordinates of queries.
	 * @param E  Array representing the y2 coordinates of queries.
	 * @return An array containing the calculated values for each query.
	 *
	 * @TimeComplexity: O(rows * cols + queries)
	 *   - The method iterates through the input matrix to preprocess cumulative sums.
	 *   - Then, it processes each query in constant time.
	 *
	 * @SpaceComplexity: O(rows * cols)
	 *   - The cumulative sum matrix is stored in additional space.
	 */
	public static int[] solve(int[][] A1, int[] B, int[] C, int[] D, int[] E) {
		int mod = 1000000007;
		long[][] A = new long[A1.length][A1[0].length];
		// Step 1: Compute prefix sum of A1 array and store it in A array.
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (j != 0)
					A[i][j] = (A[i][j - 1] + A1[i][j]);
				else 
					A[i][j] = A1[i][j];
			}
		}

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (i != 0) {
					A[i][j] = (A[i - 1][j] + A[i][j]);
				}
			}
		}
		// Step 2: Calculate the sum of subarrays using prefix sum and update finalArr.
		int[] finalArr = new int[B.length];
		for (int i = 0; i < B.length; i++) {
			int x1 = B[i] - 1;
			int y1 = C[i] - 1;
			int x2 = D[i] - 1;
			int y2 = E[i] - 1;
			long sum = A[x2][y2];
			if (y1 > 0) 
				sum = sum - A[x2][y1 - 1];
			if (x1 > 0) 
				sum = sum - A[x1 - 1][y2];
			if (x1 > 0 && y1 > 0) 
				sum = (sum + A[x1 - 1][y1 - 1]);
			// Ensure sum is non-negative and take modulus to prevent overflow.
			while (sum < 0) 
				sum += mod;
			
			finalArr[i] = (int) (sum % mod);
		}
		return finalArr;
	}

}
