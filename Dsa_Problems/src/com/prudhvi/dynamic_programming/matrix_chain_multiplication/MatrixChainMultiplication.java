package com.prudhvi.dynamic_programming.matrix_chain_multiplication;

import java.util.Arrays;
/*
	Given a sequence of matrices, find the most efficient way to multiply these matrices together. The efficient way is the one that involves the least number of multiplications.
	The dimensions of the matrices are given in an array arr[] of size N (such that N = number of matrices + 1) where the ith matrix has the dimensions (arr[i-1] x arr[i]).
	Your Task:
		You do not need to take input or print anything. Your task is to complete the function matrixMultiplication() which takes the value N and the array arr[] as input parameters and returns the minimum number of multiplication operations needed to be performed.
		Expected Time Complexity: O(N3)
		Expected Auxiliary Space: O(N2)
 */
public class MatrixChainMultiplication {

	public static void main(String[] args) {
		int noOfMatrix = 5;
		int[] matDimensions = {40, 20, 30, 10, 30};
		int minOp=mcmTab(noOfMatrix,matDimensions);
		System.out.println(minOp);
		int minOperationsMemo=mcmMemo(noOfMatrix,matDimensions);
		System.out.println(minOperationsMemo);

	}
	/*
	Time Complexity (mcmTab function):
				Initialization of dp Array:
				 	You initialize a 2D dp array of size n x n, where n is the length of the values array. This initialization step takes O(n^2) time since you iterate through all elements of the array.
				Nested Loops: The core of this function consists of nested loops. The outer loop (for d) runs from 2 to n-1, and the inner loops (for i and for k) iterate over various combinations of i, j, and k. The innermost calculations are performed in constant time.
						1.The outer loop runs for n-2 iterations.
						2.The for i loop runs for n-d iterations in each outer loop iteration.
						3.The for k loop runs for d-1 iterations in each for i loop iteration.
			So, the total number of iterations is roughly proportional to n^3. Therefore, the overall time complexity of the mcmTab function is O(n^3).
	Space Complexity (mcmTab function):
				dp Array: 
					You create a 2D dp array of size n x n to store the results of subproblems. This array consumes O(n^2) space.
				Other variables and constant space are negligible compared to the dp array.
			Therefore, the overall space complexity of the mcmTab function is O(n^2) due to the dp array.
		In summary, the mcmTab function has a time complexity of O(n^3) and a space complexity of O(n^2) due to the matrix chain multiplication problem and the dynamic programming table.
					
	 */
	private static int mcmTab(int noOfMatrix, int[] values) {
		////The function mcmTab takes two parameters: noOfMatrix, which represents the number of matrices to be multiplied, and values, an array of dimensions of these matrices. 
		//The values array contains the dimensions of the matrices in a way that if you have n matrices to multiply, the dimensions of the matrices are given by values[0] x values[1], values[1] x values[2], ..., values[n-2] x values[n-1].
		int n=values.length;
		////It initializes a 2D array dp of size [n][n], where n is the number of matrices.
		int[][] dp=new int[n][n];
		//The outer loop with variable d represents the length of the chain of matrices to be multiplied. 
		//It starts with d = 2 because the minimum length of the chain required for multiplication is 2 matrices.
		for (int d=2;d<n; d++) {
			//The middle loop with variables i and j iterates through all possible subsequences of matrices of length d within the chain.
	        for (int i=1,j=d;j<n;i++,j++) {
	        	//For each subsequence defined by i and j, it initializes dp[i][j] to a maximum integer value (Integer.MAX_VALUE) as the initial best value for the cost of multiplying this subsequence.
	            dp[i][j] =Integer.MAX_VALUE;
	            //It then iterates through all possible positions k within the subsequence where the multiplication can be split into two subproblems: i to k and k+1 to j. 
	            for (int k = i; k < j; k++) {
	            	//It calculates the cost of multiplying these two subproblems and adds the cost of multiplying the resulting matrices at positions i, k, and j. 
	            	//The formula for the cost is: cost = dp[i][k] + dp[k + 1][j] + (values[i - 1] * values[k] * values[j]), 
	            	//where values[i - 1] represents the number of rows of the matrix at position i, values[k] represents the number of columns of the matrix at position k, and values[j] represents the number of columns of the matrix at position j.
	                int cost = dp[i][k] + dp[k + 1][j] + (values[i - 1] * values[k] * values[j]);//left+right+cost
	                //It updates dp[i][j] with the minimum cost among all possible split positions k.
	                dp[i][j] = Math.min(dp[i][j], cost);
	            }
	        }
	    }
		//After processing all subsequences of length d, the dp[1][n - 1] value contains the minimum cost of multiplying all the matrices optimally, where 1 represents the first matrix and n - 1 represents the last matrix in the chain.
	    return dp[1][n - 1];
	}
	/*
	Time Complexity (mCMMemo function):
				Initialization of dp Array: 
					You initialize a 2D dp array of size n x n, where n is the length of the matDimensions array. This initialization step takes O(n^2) time since you fill the array with -1.
				Recursive Call to mcm Function: 
					You make a recursive call to the mcm function from mCMMemo with initial values i=1 and j=n-1. The recursive mcm function is where most of the work is done.
				mcm Function:
					The mcm function recursively breaks down the matrix chain multiplication problem into subproblems and computes the minimum number of operations required to multiply matrices optimally. It uses memoization to avoid recomputing the same subproblems.
				In the worst case, the mcm function explores all possible combinations of i and j from 1 to n-1, and for each pair, it computes the minimum operations by iterating over k from i to j. This recursive process leads to a time complexity of O(n^3), which is the time complexity of the matrix chain multiplication problem.
		So, the overall time complexity of the mCMMemo function is O(n^3) due to the work done by the mcm function.

	Space Complexity (mCMMemo function):
				dp Array: 
					You create a 2D dp array of size n x n to store the results of subproblems. This array consumes O(n^2) space.
				Other variables and constant space are negligible compared to the dp array.				
			Therefore, the overall space complexity of the mCMMemo function is O(n^2) due to the dp array.
	In summary, the mCMMemo function has a time complexity of O(n^3) and a space complexity of O(n^2) due to the matrix chain multiplication problem and the memoization table.
	 */
	private static int mcmMemo(int noOfMatrix, int[] matDimensions) {
		//The function mcmMemo takes two parameters: noOfMatrix, which represents the number of matrices to be multiplied, and matDimensions, an array of dimensions of these matrices. 
		//The matDimensions array contains the dimensions of the matrices in a way that if you have n matrices to multiply, the dimensions of the matrices are given by matDimensions[0] x matDimensions[1], matDimensions[1] x matDimensions[2], ..., matDimensions[n-2] x matDimensions[n-1].
		int n=matDimensions.length;
		//It initializes a 2D array dp of size [n][n] and fills it with -1. This array will be used for memoization to store previously computed values.
		int[][] dp=new int[n][n];
		for(int i=0;i<dp.length;i++) {
			Arrays.fill(dp[i],-1);
		}
		//The function mcm is a recursive helper function. It takes four parameters: i and j represent the indices of the matrices to be multiplied, values is the array of matrix dimensions, and dp is the memoization array.
		return mcm(1,n-1,matDimensions,dp);
	}

	private static int mcm(int i, int j, int[] values, int[][] dp) {
		//The base case of the recursion is when i is equal to j, which means there's only one matrix, and no multiplication is needed, so it returns 0.
		if(i==j ) {
			return 0;
		}
		//If the result for the given i and j is already computed and stored in dp, it returns that value from dp.
		if(dp[i][j]!=-1)
			return dp[i][j];
		//Otherwise, 
		else {
			//it initializes minOperations to a maximum integer value (Integer.MAX_VALUE) as the initial best value for the cost of multiplying the matrices.
			int minOperations=Integer.MAX_VALUE;
			//It then iterates through all possible positions k within the subsequence [i, j-1] where the multiplication can be split into two subproblems: [i, k] and [k+1, j]. 
			for(int k=i;k<j;k++) {
				//It recursively calculates the minimum number of operations for these two subproblems and adds the cost of multiplying the resulting matrices at positions i, k, and j. 
				int left=mcm(i,k, values,dp);
				int right=mcm(k+1,j, values, dp);
				int cost=values[i-1]*values[k]*values[j];
				//The formula for the cost is: cost = mcm(i, k, values, dp) + mcm(k + 1, j, values, dp) + (values[i - 1] * values[k] * values[j]), 
				//where values[i - 1] represents the number of rows of the matrix at position i, values[k] represents the number of columns of the matrix at position k, and values[j] represents the number of columns of the matrix at position j.
				int totalCost=left+right+cost;
				//It updates minOperations with the minimum cost among all possible split positions k.
				 minOperations=Math.min(minOperations,totalCost );
			}
			//After processing all possible split positions k, it stores minOperations in dp[i][j] for future use and returns minOperations.
			dp[i][j]=minOperations;
		}
		return dp[i][j];
	}

}
