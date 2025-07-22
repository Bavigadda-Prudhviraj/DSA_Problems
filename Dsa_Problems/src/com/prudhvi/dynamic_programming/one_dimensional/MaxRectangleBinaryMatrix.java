package com.prudhvi.dynamic_programming.one_dimensional;

/*
 	Problem Description
		Given a 2-D binary matrix A of size N x M filled with 0's and 1's, find the largest rectangle containing only ones and return its area.
	Problem Constraints
		1 <= N, M <= 100
 */
public class MaxRectangleBinaryMatrix {
	public static void main(String[] args) {
		int[][] arr= {{1, 1, 1},
		              {0, 1, 1},
		              {1, 0, 0}};
		int maxArea=maxRectangle(arr);
		System.out.println(maxArea);
	}
	/*
	The code is designed to find the maximum rectangular area in a binary matrix. It uses dynamic programming to preprocess the matrix and then iterates through the matrix to find the maximum area. Let's analyze its time and space complexity:
	
	**Time Complexity**:
	- The initial loop that iterates through the matrix and updates the elements in the matrix is a nested loop. The outer loop iterates through the rows, and the inner loop iterates through the columns. Both loops run in O(rows * columns) time, where "rows" and "columns" are the dimensions of the matrix (number of rows and columns).
	- After the preprocessing, the code iterates through the matrix once again. In the worst case, it will explore the area formed by each element, considering it as the upper-left corner of a rectangle. This exploration takes O(1) time for each element since the loops have a constant number of iterations. Therefore, the second part of the code also runs in O(rows * columns) time.
	- So, the overall time complexity of the code is O(rows * columns).
	
	**Space Complexity**:
	- The code uses additional space to store the updated matrix. It overwrites the original matrix with the modified values, so the space complexity is O(1) in terms of extra space.
	In summary, the code has a time complexity of O(rows * columns) and a space complexity of O(1). It efficiently finds the maximum rectangular area in the binary matrix.
	 */
	private static int maxRectangle(int[][] arr) {
	    // Check if the input array is valid
	    if (arr == null || arr.length == 0 || arr[0].length == 0) {
	        return 0;
	    }

	    // Step 1: Convert each row into a histogram
	    // For each cell with value 1, compute the number of consecutive 1s to its left (including itself)
	    for (int i = 0; i < arr.length; i++) {
	        for (int j = 0; j < arr[i].length; j++) {
	            // Skip the first column (j == 0) and the first cell (i == 0, j == 0)
	            // as they are already initialized or need no modification
	            if ((i == 0 && j == 0) || j == 0) {
	                continue;
	            }
	            // If the current cell is 1, add 1 to the count of consecutive 1s from the left
	            if (arr[i][j] == 1) {
	                arr[i][j] = arr[i][j - 1] + 1;
	            }
	            // If the cell is 0, it remains 0 (no consecutive 1s)
	        }
	    }

	    // Step 2: Calculate the maximum rectangle area
	    int maxArea = 0; // Initialize the maximum area
	    for (int i = 0; i < arr.length; i++) {
	        for (int j = 0; j < arr[i].length; j++) {
	            // Process only cells with a positive width (i.e., cells that were originally 1)
	            if (arr[i][j] > 0) {
	                int height = 1; // Initialize height to 1 for the current row
	                int width = arr[i][j]; // Width is the number of consecutive 1s in the current row
	                // Update maxArea with the rectangle formed by the current row
	                maxArea = Math.max(maxArea, height * width);
	                
	                // Check rows above the current row to form larger rectangles
	                int k = i - 1;
	                while (k >= 0 && arr[k][j] > 0) {
	                    height++; // Increment height for each valid row above
	                    width = Math.min(width, arr[k][j]); // Update width to the minimum width of all rows considered
	                    maxArea = Math.max(maxArea, width * height); // Update maxArea if the new rectangle is larger
	                    k--; // Move to the row above
	                }
	            }
	        }
	    }

	    // Return the maximum area found
	    return maxArea;
	}
	/*
	The  code is used to find the maximum area of a rectangle in a binary matrix (`arr`). This code efficiently calculates the maximum rectangle area by maintaining the width of rectangles in each row. Let's break down the code step by step:
	
	1. The code begins by iterating through each element in the matrix using two nested loops:
	   - It starts by initializing the matrix `arr`, where each element in a row (except for the first column) stores the count of consecutive '1's up to that position. In other words, `arr[i][j]` represents the count of '1's in a row `i` up to column `j`.
	   - If the element at `arr[i][j]` is equal to 1, it calculates the width of the rectangle ending at that position by adding 1 to the width of the previous position in the same row (`arr[i][j-1]`).
	
	2. After calculating the width of rectangles in each row, the code enters a second loop to find the maximum rectangle area:
	   - For each element in the matrix, if the element is greater than 0, it means that a rectangle can potentially be formed starting from that position.
	   - It initializes the `height` as 1 and `width` as the value of `arr[i][j]` (the current width). The `area` variable is also initialized to 0.
	   - The code calculates the maximum area of the rectangle ending at the current position and updates the `area` if the calculated area is larger.
	   - Then, it checks for possible rectangles that start from the current row and go up. For this, it decrements `k` (starting from the current row - 1) and calculates the width as the minimum of the current width and the width in the previous row (`arr[k][j]`).
	   - It calculates the area for each such potential rectangle and updates the `area` if a larger area is found.
	
	3. Finally, the code returns the maximum area found.
	
	In summary, this code efficiently calculates the maximum rectangle area within a binary matrix by maintaining the width of rectangles in each row and exploring potential rectangles that start from each row and go up. The time complexity of this algorithm is O(m * n), where m and n are the number of rows and columns in the matrix, respectively. The space complexity is O(1), as the code modifies the input matrix in place and only uses a few additional variables.
	 */

}
