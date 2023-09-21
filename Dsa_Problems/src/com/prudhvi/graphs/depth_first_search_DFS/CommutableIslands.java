package com.prudhvi.graphs.depth_first_search_DFS;
/*
	Given a grid of size n*m (n is the number of rows and m is the number of columns in the grid) consisting of '0's (Water) and '1's(Land). Find the number of islands.
	Note: An island is either surrounded by water or boundary of grid and is formed by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.
	
	Your Task:
		You don't need to read or print anything. Your task is to complete the function numIslands() which takes the grid as an input parameter and returns the total number of islands.
	Expected Time Complexity: O(n*m)
	Expected Space Complexity: O(n*m)
	Constraints:
		1 ≤ n, m ≤ 500
 */
public class CommutableIslands {

	public static void main(String[] args) {
		int[][] island= {{1,1,0,0,1},
						 {0,1,0,1,0},
				  		 {1,0,0,1,1},
						 {1,1,0,0,0},
						 {1,0,1,1,1},};
		int islandCount=countIslands(island);
		System.out.println(islandCount);

	}
	/*
	Time Complexity:
		The code uses two nested loops to iterate through all cells in the grid, resulting in a time complexity of O(m * n), where m is the number of rows and n is the number of columns in the grid.
		The DFS function is called for each '1' cell, and it explores neighboring cells in all eight directions (four cardinal directions and four diagonals). In the worst case, it explores all adjacent cells for each '1' cell, resulting in constant time operations per cell.
		Therefore, the overall time complexity is O(m * n).
	Space Complexity:
		The space complexity is determined by the call stack during the DFS traversal. In the worst case, when the entire grid consists of a single island, the depth of the call stack can be as large as m * n.
		Each function call in the call stack consumes a constant amount of space, as it stores the current position (i, j) and parameters (m, n).
		Therefore, the space complexity is O(m * n) in the worst case.
	In summary, the provided code efficiently counts the number of islands in a 2D grid using a DFS-based approach with a time complexity of O(m * n) and a space complexity(stack space) of O(m * n) in the worst case.
	 */
	private static int countIslands(int[][] island) {
		int m=island.length;
		int n=island[0].length;
		int count=0;//which will be used to count the number of islands.
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				//When it encounters a land cell (island[i][j] == 1), it starts a depth-first search from that cell to explore the island.
				if(island[i][j]==1) {
					dfs(island,i,j,m,n);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs(int[][] island, int i, int j, int m, int n) {
		//it first checks whether the current cell is out of bounds or if it's a water cell (island[i][j] == 0). If any of these conditions is met, it returns, effectively ending the current branch of the search.
		if(i<0 || j<0 || i>=m ||j>=n || island[i][j]==0) {
			return;
		}
		island[i][j]=0;//If the current cell is valid and a land cell, it marks it as visited by setting island[i][j] to 0 (indicating it's part of an island).
		//The DFS function then recursively calls itself on the adjacent cells in all eight directions (up, down, left, right, and diagonals). This recursive exploration continues until all connected land cells of the island have been visited.
		dfs(island, i+1, j, m, n);//bottom
		dfs(island, i-1, j, m, n);//top
		dfs(island, i, j-1, m, n);//left
		dfs(island, i, j+1, m, n);//right
		//diagonals
		dfs(island, i-1, j-1, m, n);//top left
		dfs(island, i-1, j+1, m, n);//top right
		dfs(island, i+1, j+1, m, n);//bottom right
		dfs(island, i+1, j-1, m, n);//bottom left
		
		
	}

}
