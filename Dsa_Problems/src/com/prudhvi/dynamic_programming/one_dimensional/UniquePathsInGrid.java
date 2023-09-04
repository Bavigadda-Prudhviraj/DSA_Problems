package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

public class UniquePathsInGrid {
	/*
	Problem Description
		Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m). 
		At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).
			Now consider if some obstacles are added to the grids. 
			Return the total number unique paths from (1, 1) to (n, m).
		Note: An obstacle is marked as 1 and empty space is marked 0 respectively in the grid.
	Problem Constraints
		1 <= n, m <= 100
		A[i][j] = 0 or 1
	 */
	public static void main(String[] args) {
		int[][] arr= {{0,0},{0,0},{0,0},{1,0},{0,0}};
		int paths=tabulatedUnique(arr);
		System.out.println(paths);
		int paths1=uniquePathsMemo(arr);
		System.out.println(paths1);
	}
	//Tabulation
	private static int tabulatedUnique(int[][] arr) {
	    int m = arr.length;
	    int n = arr[0].length;
	    // Create a table dp with the same dimensions as arr
	    int[][] dp = new int[m][n];
	    // Initialize the last cell if it's not blocked
	    if (arr[m - 1][n - 1] != 1) 
	        dp[m - 1][n - 1] = 1;
	    // Fill the last row and last column
	    for (int i = m - 2; i >= 0; i--) 
	        if (arr[i][n - 1] != 1) 
	            dp[i][n - 1] = dp[i + 1][n - 1];
	  
	    for (int j = n - 2; j >= 0; j--) 
	        if (arr[m - 1][j] != 1) 
	            dp[m - 1][j] = dp[m - 1][j + 1];
	    // Fill the rest of the table
	    for (int i = m - 2; i >= 0; i--) 
	        for (int j = n - 2; j >= 0; j--) 
	            if (arr[i][j] != 1) 
	                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
	            
	  
	    return dp[0][0];
	}

	//Memo working
	private static int uniquePathsMemo(int[][] arr) {
		int[][] dp=new int[arr.length][arr[0].length];
		for(int[] row:dp) {
			Arrays.fill(row, -1);
		}
		int paths=memoUnique(arr,dp,0,0);
		return paths;
	}
	private static int memoUnique(int[][] arr, int[][] dp, int i, int j) {
		if(i>=arr.length || j>=arr[0].length || arr[i][j]==1) {
			return 0;
		}
		if(i==arr.length-1 && j==arr[0].length-1) {
			dp[i][j]=1;
			return 1;
		}
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}
		int left=memoUnique(arr, dp, i+1, j);
		int right=memoUnique(arr, dp, i, j+1);
		dp[i][j]=left+right;
		return dp[i][j];
	}
	


}
