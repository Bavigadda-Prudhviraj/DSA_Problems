package com.prudhvi.dynamic_programming.matrix_chain_multiplication;

//https://www.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1

public class BracketsInMatrixChainMultiplication {

	public static void main(String[] args) {
		int n = 5;
		int	p[] = {40, 20, 30, 10, 30};
		System.out.println(matrixChainOrder(p, n));
		
	}
	/**
	 * Finds the optimal parenthesization of matrices to minimize the number of scalar multiplications.
	 *
	 * @param arr An array representing the dimensions of matrices.
	 * @param n The number of matrices.
	 * @return The optimal parenthesization as a string.
	 * @TimeComplexity : O(n^3), where n is the number of matrices, as the function uses a bottom-up dynamic programming approach.
	 * @SpaceComplexity : O(n^2), as the function uses a 2D table to store computed results.
	 */
	public static String matrixChainOrder(int arr[], int n) {
	    // 2D table to store optimal values and parenthesizations
	    Pair[][] dp = new Pair[n - 1][n - 1];
	    // Loop through all possible subchains of matrices
	    for (int d = 0; d < n - 1; d++) {
	        for (int i = 0, j = d; j < n - 1; i++, j++) {
	            if (d == 0) {
	                // Base case: a single matrix, no multiplication needed
	                int val = 0;
	                String string = "" + (char) ('A' + i);
	                dp[i][j] = new Pair(val, string);
	            } 
	            else if (d == 1) {
	                // Base case: two matrices, multiply them and parenthesize
	                int val = arr[i] * arr[j] * arr[j + 1];
	                String string = "(" + (char) ('A' + i) + (char) ('A' + j) + ")";
	                dp[i][j] = new Pair(val, string);
	            } 
	            else {
	                // General case: find optimal parenthesization for the subchain
	                int min = Integer.MAX_VALUE;
	                String string = "";
	                for (int k = i; k < j; k++) {
	                    int lc = dp[i][k].val; // Left cost
	                    int rc = dp[k + 1][j].val; // Right cost
	                    int mc = arr[i] * arr[k + 1] * arr[j + 1]; // Multiplication cost
	                    int total = lc + rc + mc; // Total cost
	                    // Update minimum cost and corresponding parenthesization
	                    if (total < min) {
	                        min = total;
	                        string = "(" + dp[i][k].string + dp[k + 1][j].string + ")";
	                    }
	                }
	                dp[i][j] = new Pair(min, string);
	            }
	        }
	    }
	    // Return the optimal parenthesization for the entire chain of matrices
	    return dp[0][n - 2].string;
	}
	



}
/**
 * A helper class representing a pair of values (cost and parenthesization).
 */
class Pair {
    int val;
    String string;

    public Pair(int val, String string) {
        this.val = val;
        this.string = string;
    }
}
