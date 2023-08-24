package com.prudhvi.backtracking;



public class UniquePaths_III {
	//All Paths
	public static void main(String[] args) {
		int[][] arr= {{1, 0, 0, 0},
			          {0, 0, 0, 0},
			          {0, 0, 2,-1}};
		int paths=uniquePaths(arr);
		System.out.println(paths);
	}
	/*
	The provided code appears to implement a recursive approach for finding the number of unique paths in a grid, while considering obstacles(-1) and specific start(1) and end points(2). 
	The grid has three types of cells: empty squares, obstacles, and the destination point.
	
	Time Complexity:
			The ways function explores each cell at most once, and the exploration is done in constant time for each cell.
			Since each cell is visited only once, the time complexity of the algorithm is O(m * n), where m is the number of rows and n is the number of columns in the grid.

	Space Complexity:
			The space complexity is determined by the recursion stack, 
			which can go as deep as the total number of cells in the grid. Therefore, the space complexity is O(m * n).
	 */
	public static int uniquePaths(int[][] arr) {
		int[] indexes=start_emptySquares(arr);
		int row=indexes[0];
		int col=indexes[1];
		int emptySquares=indexes[2];
		int ways=ways(arr,row,col,emptySquares+1);
		return ways;
	}
	// Find the start position and count of empty squares
	private static int[] start_emptySquares(int[][] arr) {
		int[]  indexes=new int[3];
		int zerosCount=0;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(arr[i][j]==0) {
					zerosCount++;
				}
				else if(arr[i][j]==1) {
					 indexes[0]=i;
					 indexes[1]=j;
				}
			}
		}
		indexes[2]=zerosCount;
		return indexes;
	}
	// Recursive function to calculate unique paths
	private static int ways(int[][] arr,int row,int col,int emptySquares){
		// Base cases: out of bounds or obstacle
		if(row<0 || col<0 || row>=arr.length || col>=arr[0].length || arr[row][col]==-1){
			return 0;
		}
		// Base case: destination reached
		if(arr[row][col]==2) {
			return emptySquares==0?1:0;
		}
		int temp=arr[row][col];
		arr[row][col]=-1;// Mark current cell as visited
		int totalPaths=0;
		// Recursively explore adjacent cells
	    totalPaths+=ways(arr, row+1, col,  emptySquares-1);
		totalPaths+=ways(arr, row-1, col,  emptySquares-1);
		totalPaths+=ways(arr, row,   col+1,emptySquares-1);
		totalPaths+=ways(arr, row,   col-1,emptySquares-1);
		arr[row][col]=temp;// Reset the cell back
		return totalPaths;
	}

}
