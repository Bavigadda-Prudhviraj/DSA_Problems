package com.prudhvi.stack;



import java.util.Stack;



public class MaximumRectangle {
	/*
	Given a 2D binary matrix of integers A containing 0's and 1's of size N x M.
	Find the largest rectangle containing only 1's and return its area.
		Note: 
			Rows are numbered from top to bottom and columns are numbered from left to right.
	
	The provided code is for finding the maximum area of rectangles formed with ones in a 2D matrix array.
	The matrix represents a binary image where 1s represent black pixels, and the goal is to find the largest possible rectangle that can be formed with consecutive black pixels.
	 */
	public static void main(String[] args) {
		int[][] arr= {{0,1,1},
					  {1,0,0},
					  {1,0,0},
					  {1,0,0},
					  {1,0,0},
					  {1,1,1},
					  {0,1,0}};
		int answer=maxAreaOFRectanglesWithOnes(arr);
		System.out.println(answer);
	}
	/*
	
	Time Complexity:
			The time complexity of the maxAreaOFRectanglesWithOnes function is O(row * column) since it iterates through each element of the 2D matrix once.
			The time complexity of the areaCalculationOfEachArray function is O(n), where n is the number of elements in the input array.
	
	Space Complexity:
			The space complexity of both functions is O(column) since they use additional arrays (e.g., nSLI, nSRI, array) 
			and stacks whose size depends on the number of columns in the 2D matrix array.
	 */
	private static int maxAreaOFRectanglesWithOnes(int[][] arr) {
		//The function starts by getting the number of rows (row) and columns (columns) of the 2D matrix array..
		int rows=arr.length;
		int columns=arr[0].length;
		//it iterates through the matrix starting from the second row (i=1) and the first column (j=0)
		for(int i=1;i<rows;i++) { //why i=1 reason commended by end of the code
			for(int j=0;j<columns;j++) {
				// For each element arr[i][j], it checks if the element above (arr[i-1][j]) is greater than or equal to 1 (indicating a consecutive black pixel) and the current element (arr[i][j]) is 1. 
				//If these conditions are met, it updates the current element with the value of the element above plus 1. This is done to count the consecutive ones forming a rectangle in the matrix.
				if(arr[i-1][j]>=1 && arr[i][j]==1) {
					int ele=arr[i-1][j];
					arr[i][j]=ele+1;
				}
			}
		}
		//After updating the matrix, it proceeds to calculate the area for each row using the areaCalculationOfEachArray function. 
		//It iterates through each row of the matrix and calculates the area using the "Largest Rectangle in Histogram" concept. 
		//It does this by finding the nearest smaller element index on the left (nSLI) and the nearest smaller element index on the right (nSRI) for each element in the row using a stack.
		int maxArea=Integer.MIN_VALUE;
		for(int i=0;i<rows;i++) {
			//The areaCalculationOfEachArray function takes an integer array as input and returns the area of the largest rectangle that can be formed using consecutive elements in the input array.
			int area=areaCalculationOfEachArray(arr[i]);
			maxArea=Math.max(maxArea, area);
		}
		return maxArea;
	}
	private static int areaCalculationOfEachArray(int[] arr) {
		//It first creates an array nSLI to store the nearest smaller element index on the left for each element. 
		//It then uses a stack to find the nearest smaller element index on the left for each element in the array.
		int[] nSLI=new int[arr.length];
		Stack<Integer> stack=new Stack<>();
		for(int i=0;i<arr.length;i++) {
			while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){
				stack.pop();
			}
			if(!stack.isEmpty()) {
				nSLI[i]=stack.peek();
			}else {
				nSLI[i]=-1;
			}
			stack.push(i);
		}
		stack.clear();
		//Next, it creates another array nSRI to store the nearest smaller element index on the right for each element.
		//Again, it uses a stack to find the nearest smaller element index on the right for each element in the array.
		int[] nSRI=new int[arr.length];
		for(int i=arr.length-1;i>=0;i--) {
			while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){
				stack.pop();
			}
			if(!stack.isEmpty()) {
				nSRI[i]=stack.peek();
			}else {
				nSRI[i]=arr.length;
			}
			stack.push(i);
		}
		//Finally, it calculates the area for each element in the array by taking the height (the element's value) and width (the difference between the nearest smaller element index on the right and the nearest smaller element index on the left minus one).	
		int area=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			int height=arr[i];
			int width=nSRI[i]-nSLI[i]-1;
			int currentArea=height*width;
			//It keeps track of the maximum area seen so far and returns it as the result.
			area=Math.max(area,currentArea);
		}
		return area;
	}
}
/*
 	The iteration starts from i = 1 in the maxAreaOFRectanglesWithOnes function because the logic within the loop depends on the value of the element above the current element.
 	For the first row (i.e., i = 0), there is no element above it (arr[i - 1][j]), and checking it will result in an index out of bounds exception. 
 	Therefore, the loop starts from the second row (i = 1) to avoid this issue.

	In other words, starting from i = 1 ensures that the logic inside the loop can correctly compare the current element with the element above it, 
	which is required for calculating consecutive ones forming a rectangle in the matrix.
	
	By starting the first iteration from i = 1, the code correctly processes the elements of the matrix, 
	updating the consecutive ones count for each element and thus enabling the subsequent calculation of the maximum area of rectangles formed with ones.
 */
