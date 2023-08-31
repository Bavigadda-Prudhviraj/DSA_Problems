package com.prudhvi.dynamic_programming.one_dimensional;



public class DungeonPrincess {
	/*
	Problem Description
		1.The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. 
		  The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
		2.The knight has an initial health point represented by a positive integer. 
		   If at any point his health point drops to 0 or below, he dies immediately.
		3.Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
		4.In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
		5.Given a 2D array of integers A of size M x N. Find and return the knight's minimum initial health so that he is able to rescue the princess.
	Problem Constraints
		1 <= M, N <= 500
		-100 <= A[i] <= 100
	 */
	public static void main(String[] args) {
		int[][] arr={{-2, -3, 3},
		             {-5, -10, 1},
		             {10, 30, -5}};//7
		int energy=energyRequried(arr);
		System.out.println(energy);
		int energy2=energyRequriedDp(arr);
		System.out.println(energy2);
		int energy1=energyRequiredMemo(arr);
		System.out.println(energy1);
	}
	/*
	Code is to calculate the minimum initial energy required for a character to reach the bottom-right corner of a grid while ensuring that the character's energy never drops to or below 1.
	
	Time Complexity:
		The dynamic programming loop iterates through all cells of the grid once.
		Therefore, the time complexity of this approach is O(row * col), where row is the number of rows and col is the number of columns in the grid.

	Space Complexity:
		The space complexity is O(row * col) due to the dp array used to store the minimum energy values for each cell.
	 */
	private static int energyRequried(int[][] arr) {
		int row=arr.length,col=arr[0].length;//The grid dimensions are determined by row = arr.length and col = arr[0].length.
		int[][] dp=new int[row][col];//Create a 2D array dp of the same dimensions as the grid to store the minimum initial energy required to reach each cell.
		//Iterate through the grid in reverse order (starting from the bottom-right corner and moving towards the top-left corner).
		for(int i=row-1;i>=0;i--) {
			for(int j=col-1;j>=0;j--) {
				//If the current cell is the bottom-right,the minimum initial energy required is calculated as Math.max(1, 1 - arr[i][j]). 
				//This ensures that the character's energy is at least 1 at the end.
				if(i==row-1 && j==col-1) {
					dp[i][j] = Math.max(1,1-arr[i][j]);//dp[i][j]=1-arr[i][j]>0?(1-arr[i][j]):1;
				}
				//If the current cell is in the last row,the minimum initial energy required is calculated as Math.max(1, dp[i][j + 1] - arr[i][j]). 
				//This ensures that the character's energy remains positive while moving from right to left along the bottom row.
				else if(i==row-1) {
					dp[i][j] = Math.max(1,dp[i][j+1]-arr[i][j]);//dp[i][j]=dp[i][j+1]-arr[i][j]>0?dp[i][j+1]-arr[i][j]:1;
				}
				//If the current cell is in the last column,the minimum initial energy required is calculated as Math.max(1, dp[i + 1][j] - arr[i][j]). 
				//This ensures that the character's energy remains positive while moving from bottom to top along the last column.
				else if(j==col-1){
					dp[i][j] = Math.max(1, dp[i+1][j]-arr[i][j]);//dp[i+1][j]-arr[i][j]<=0?1:dp[i+1][j]-arr[i][j];
				}
				//For other cells, the minimum initial energy required is calculated as the minimum of two possible moves: moving right (dp[i][j + 1] - arr[i][j]) and moving down (dp[i + 1][j] - arr[i][j]) Select min among both, while ensuring the energy is at least 1.
				else{
					int min_health=Math.min(dp[i][j+1],dp[i+1][j]);
					dp[i][j]=min_health-(arr[i][j])<=0?1:min_health-(arr[i][j]);
				}
			}
			
		}
		return dp[0][0];
	}
	private static int energyRequriedDp(int[][] arr) {
		int row=arr.length,col=arr[0].length;
		int[][] dp=new int[row][col];
		for(int i=row-1;i>=0;i--) {
			for(int j=col-1;j>=0;j--) {
				if(i==row-1 && j==col-1) {
					dp[i][j]=1-arr[i][j]>0?(1-arr[i][j]):1;
				}else if(i==row-1) {
					dp[i][j]=dp[i][j+1]-arr[i][j]>0?dp[i][j+1]-arr[i][j]:1;
				}
				else if(j==col-1){
					dp[i][j]=dp[i+1][j]-arr[i][j]<=0?1:dp[i+1][j]-arr[i][j];
				}
				else{
					int min_health=Math.min(dp[i][j+1],dp[i+1][j]);
					dp[i][j]=min_health-(arr[i][j])<=0?1:min_health-(arr[i][j]);	
				}
			}
		}
		return dp[0][0];
	}
	//memo
	private static int energyRequiredMemo(int[][] arr) {
	    int row = arr.length;
	    int col = arr[0].length;
	    int[][] dp = new int[row][col];
	    boolean[][] visited = new boolean[row][col];
	    return energyRequired(arr, 0, 0, dp, visited);
	}

	private static int energyRequired(int[][] arr, int i, int j, int[][] dp, boolean[][] visited) {
	    if (i >= arr.length || j >= arr[0].length) {
	        return Integer.MAX_VALUE; // Out of bounds, return a large value
	    }

	    if (i == arr.length - 1 && j == arr[0].length - 1) {
	        return Math.max(1, 1 - arr[i][j]);
	    }

	    if (visited[i][j]) {
	        return dp[i][j];
	    }

	    int rightHealth = Math.max(1, energyRequired(arr, i, j + 1, dp, visited) - arr[i][j]);
	    int bottomHealth = Math.max(1, energyRequired(arr, i + 1, j, dp, visited) - arr[i][j]);
	    dp[i][j] = Math.min(rightHealth, bottomHealth);
	    visited[i][j] = true;

	    return dp[i][j];
	}


}
