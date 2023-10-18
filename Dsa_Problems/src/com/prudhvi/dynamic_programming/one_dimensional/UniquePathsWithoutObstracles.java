package com.prudhvi.dynamic_programming.one_dimensional;
/*
	There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). 
	The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
	Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
	The test cases are generated so that the answer will be less than or equal to 2 * 109.
 */
public class UniquePathsWithoutObstracles {

	public static void main(String[] args) {
		int row=3;
		int col=7;
		int paths=uniquePaths(row,col);
		System.out.println(paths);
		int path=uniquePathCSC(row,col);
		System.out.println(path);

	}

	private static int uniquePathCSC(int row, int col) {
		long ans=1;
		for(int i=row+col-2,j=1;i>=Math.max(row,col);i--,j++) {
			ans=(ans*i)/j;
		}
		return (int)ans;
	}

	private static int uniquePaths(int row, int col) {
		 int[][] dp=new int[row][col];
	        for(int i=0;i<row;i++){
	            for(int j=0;j<col;j++){
	                if(i==0 && j==0){
	                    continue;
	                }
	                else if( (i==0 && j>0) || (j==0 && i>0)){
	                    dp[i][j]=1;
	                }
	                else{
	                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
	                }
	            }
	        }
	        return dp[row-1][col-1];
	}
	/*
	The code is used to find the number of unique paths from the top-left corner of a grid to the bottom-right corner. The grid has a size of `row` rows and `col` columns, and you can only move right or down. It uses dynamic programming to calculate the number of unique paths. Let's break down the code and analyze its time and space complexity:
	
	**Explanation**:
	
		1. The `uniquePaths` function takes two integer parameters: `row` and `col`, which represent the number of rows and columns in the grid.
		
		2. It initializes a 2D array `dp` of size `[row][col]` to store the number of unique paths for each cell.
		
		3. The nested loops iterate through each cell in the grid. The outer loop iterates over the rows, and the inner loop iterates over the columns.
		
		4. For the cell at the top-left corner (`i = 0` and `j = 0`), there's only one way to reach it (no moves). So, it continues to the next iteration.
		
		5. For the cells in the first row (`i = 0` and `j > 0`) and the first column (`j = 0` and `i > 0`), there's only one way to reach each cell. So, it sets `dp[i][j]` to 1.
		
		6. For the other cells, it calculates the number of unique paths to reach the current cell. The number of unique paths to a cell is the sum of the number of unique paths to the cell immediately above it (`dp[i-1][j]`) and the cell immediately to the left of it (`dp[i][j-1]`).
		
		7. Finally, it returns the value stored in the bottom-right cell of the grid (`dp[row-1][col-1]`), which represents the number of unique paths from the top-left corner to the bottom-right corner.
	
	**Time Complexity**:
	- The code uses a nested loop to iterate through each cell in the grid, resulting in a time complexity of O(row * col).
	
	**Space Complexity**:
	- The code uses a 2D array `dp` to store the number of unique paths for each cell in the grid. The space complexity is O(row * col) since it depends on the size of the grid.
	
	In summary, this code efficiently calculates the number of unique paths from the top-left corner to the bottom-right corner of a grid. Its time complexity is O(row * col), and its space complexity is also O(row * col).
	 */

}
