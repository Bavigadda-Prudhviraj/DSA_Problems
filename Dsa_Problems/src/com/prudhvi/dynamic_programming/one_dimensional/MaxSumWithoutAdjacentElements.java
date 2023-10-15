package com.prudhvi.dynamic_programming.one_dimensional;



public class MaxSumWithoutAdjacentElements {

	public static void main(String[] args) {
		int[][] arr= {{1,2,3,4,5,6,7},
				      {2,1,4,6,3,5,9}};
		int maxSum=maxSumWithourAdjacentEle2D(arr);
		System.out.println(maxSum);

	}

	private static int maxSumWithourAdjacentEle2D(int[][] arr) {
		if(arr[0].length<=2) {
			int max=Integer.MIN_VALUE;
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[0].length;j++) {
					if(arr[i][j]>max) {
						max=arr[i][j];
					}
				}
			}
			return max;
		}
		int col=arr[0].length;
		int[] dp=new int[col];
		dp[0]=Math.max(arr[0][0], arr[1][0]);
	    dp[1]=Math.max(dp[0],Math.max(arr[0][1], arr[1][1]));
	    for(int i=2;i<col;i++) {
	    	dp[i]=Math.max(dp[i-1],dp[i-2]+Math.max(arr[0][i], arr[1][i]) );
	    }
		
		return dp[col-1];
	}

}
/*
This code is designed to find the maximum sum of non-adjacent elements from a 2D array. Let's break down the code and then analyze its time and space complexity.

**Explanation**:

1. The function `maxSumWithourAdjacentEle2D` takes a 2D array `arr` as input.

2. It first checks whether the number of columns in the array is less than or equal to 2. If this is the case, it iterates through the entire array to find the maximum element. The reason is that when there are only two columns, you can choose at most one element from each row, making it impossible to select two adjacent elements. So, the maximum element is the result.

3. If the number of columns is greater than 2, it initializes an array `dp` to store the maximum sum for each column. The array `dp` has the same number of columns as the input array.

4. It initializes `dp[0]` as the maximum value between `arr[0][0]` and `arr[1][0]`. This is because you can choose either the first element of the first row or the first element of the second row, but not both.

5. It initializes `dp[1]` as the maximum value between `dp[0]` (the maximum sum so far) and the maximum value between `arr[0][1]` and `arr[1][1]`. This ensures that no two adjacent columns are selected.

6. It then iterates from the third column to the last column, updating `dp[i]` as the maximum value between the previous maximum `dp[i-1]` and the sum of `dp[i-2]` (skipping one column) and the maximum value between `arr[0][i]` and `arr[1][i]`. This guarantees that no two adjacent columns are selected while maximizing the sum.

7. Finally, the function returns `dp[col-1]`, which represents the maximum sum of non-adjacent elements.

**Time Complexity**:
- The code iterates through each column of the 2D array exactly once, performing constant time operations at each step. Therefore, the time complexity is O(col), where `col` is the number of columns in the array.

**Space Complexity**:
- The code uses an array `dp` with `col` elements to store the maximum sums for each column. Therefore, the space complexity is O(col).

In summary, this code efficiently finds the maximum sum of non-adjacent elements in a 2D array. Its time complexity is O(col), and its space complexity is also O(col).
 */
