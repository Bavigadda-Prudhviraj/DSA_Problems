package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.ArrayList;
import java.util.List;

/*
	Problem Description
		Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
		Adjacent numbers for jth column of ith row is jth and (j+1)th column of (i + 1)th row
	Problem Constraints
		|A| <= 1000
		A[i] <= 1000
 */
public class MinSumPathInTriangle {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
		arr.add(new ArrayList<>(List.of(9)));
		arr.add(new ArrayList<>(List.of(3 ,8)));
		arr.add(new ArrayList<>(List.of(0 ,2 ,4)));
		arr.add(new ArrayList<>(List.of(8 ,3 ,9 ,0)));
		arr.add(new ArrayList<>(List.of(5 ,2 ,2 ,7 ,3)));
		arr.add(new ArrayList<>(List.of(7 ,9 ,0 ,2 ,3 ,9)));
		arr.add(new ArrayList<>(List.of(9 ,7 ,0 ,3 ,9 ,8 ,6)));
		arr.add(new ArrayList<>(List.of(5 ,7 ,6 ,2 ,7 ,0 ,3 ,9)));
		int minPath=mptTab(arr);
		System.out.println(minPath);
		
	}
	/*
	Time Complexity (mptTab function):
		1.Initialization of dp ArrayList: You create an dp ArrayList of ArrayLists. The initialization step involves adding elements from the first row of the arr ArrayList to the dp ArrayList. This initialization step takes O(n) time, where 'n' is the number of elements in the first row.
		2.Nested Loops: The core of this function consists of two nested loops. The outer loop (for i) runs from 1 to arr.size() - 1, iterating through each row of arr. The inner loop (for j) iterates through each element in the current row (arr.get(i)).
				i.The outer loop runs for 'n-1' iterations.
				ii.The inner loop runs for the number of elements in each row, which could vary but doesn't exceed 'n'.
			Inside the inner loop, you perform constant-time operations.
		So, the total number of iterations is roughly proportional to 'n^2' in the worst case (where 'n' is the number of rows). Therefore, the overall time complexity of the mptTab function is O(n^2).

	Space Complexity (mptTab function):
		dp ArrayList: 
			You create an dp ArrayList of ArrayLists to store the results of subproblems. The size of the dp ArrayList is 'n' (the number of rows in arr), and each inner ArrayList can have up to 'n' elements. This results in a space complexity of O(n^2).
		Other variables and constant space are negligible compared to the dp ArrayList.
	Therefore, the overall space complexity of the mptTab function is O(n^2) due to the dynamic programming table (dp).
In summary, the mptTab function has a time complexity of O(n^2) and a space complexity of O(n^2) due to the dynamic programming table.
	 */
	private static int mptTab(ArrayList<ArrayList<Integer>> arr) {
		//It initializes an ArrayList of ArrayLists (dp) to store intermediate results. 
		//The dp list will have the same dimensions as the input triangle and will store the minimum path sums to each position in the triangle.
		ArrayList<ArrayList<Integer>> dp=new ArrayList<>();
		//The first element of the dp list is initialized with the first element of the input triangle (arr.get(0).get(0)). 
		//This represents the minimum path sum to the top of the triangle.
		dp.add(new ArrayList<>(List.of(arr.get(0).get(0))));
		//It then iterates through the rows of the triangle, starting from the second row (index i = 1). For each row, it calculates the minimum path sum to each element in that row and stores it in the corresponding position in the dp list.
		for(int i=1;i<arr.size();i++) {
			ArrayList<Integer> prevDp=dp.get(i-1);
			ArrayList<Integer> currDp=new ArrayList<>();
			for(int j=0;j<arr.get(i).size();j++) {
				//If the element is the first element in the row (index j = 0), it can only be reached by moving vertically from the element above it. 
				//Therefore, the minimum path sum to the current element is the minimum path sum to the element above it plus the value of the current element.
				if(j==0)
					currDp.add(prevDp.get(0)+arr.get(i).get(j));
				//If the element is the last element in the row (index j = arr.get(i).size() - 1), it can only be reached by moving diagonally from the element diagonally above and to the left of it. 
				//Therefore, the minimum path sum to the current element is the minimum path sum to the element diagonally above and to the left of it plus the value of the current element.
				else if(j==arr.get(i).size()-1)
					currDp.add(prevDp.get(prevDp.size()-1)+arr.get(i).get(j));
				//For all other positions, it considers both paths from the previous dp row (left diagonal and top) and selects the minimum of the two. It then adds the value at the current position in arr to this minimum.
				else {
					int min=Math.min(prevDp.get(j-1), prevDp.get(j));
					currDp.add(arr.get(i).get(j)+min);
				}
			}
			//After processing all rows, the dp list contains the minimum path sums for each element in the triangle.
			dp.add(currDp);	
		}
		//It then finds the minimum path sum in the last row of the dp list, which represents the minimum path sum from the top of the triangle to the bottom.
		ArrayList<Integer> lastRow=dp.get(dp.size()-1);
		int min=Integer.MAX_VALUE;
		for(int i=0;i<lastRow.size();i++) {
			min=Math.min(min, lastRow.get(i));
		}
		return min;
	}

}
