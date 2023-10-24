package com.prudhvi.dynamic_programming.one_dimensional;
/*
	There is a row of N houses, each house can be painted with one of the three colors: red, blue or green. 
	The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color. 
	Find the minimum cost to paint all houses.
	
	The cost of painting each house in red, blue or green colour is given in the array r[], g[] and b[] respectively.
 */
public class DistinctColoring {

	public static void main(String[] args) {
		int n=3;
		int[] r= {1,1,1};
		int[] g= {2,2,2};
		int[] b= {3,3,3};
		long ans=distinctColoring(n, r, g, b);
		System.out.println(ans);

	}
	public static long distinctColoring(int N, int[]r, int[]g, int[]b){
		//It initializes a 2D array dp of size N by 3. 
		//The first dimension represents the objects to be colored (from 0 to N-1), and the second dimension represents the three color choices (0 for red, 1 for green, and 2 for blue).
        long[][] dp=new long[N][3];
        //It sets the base cases for the first object (index 0) by directly assigning the costs for coloring it with each of the three colors:
        dp[0][0] = r[0];
        dp[0][1] = g[0];
        dp[0][2] = b[0];
        //It enters a loop that iterates from i = 1 to N-1. In each iteration, it calculates the minimum cost for coloring the current object with each color choice based on the costs of the previous object:
        for(int i=1;i<N;i++){
        	//dp[i][0] represents the minimum cost of coloring the current object with red, which is the cost of red for the current object (r[i]) plus the minimum of the costs of coloring the previous object with green (dp[i-1][1]) and blue (dp[i-1][2]).
            dp[i][0]=r[i]+Math.min(dp[i-1][1],dp[i-1][2]);
            ////Similarly, dp[i][1] and dp[i][2] represent the minimum costs for coloring the current object with green and blue, respectively.
            dp[i][1]=g[i]+Math.min(dp[i-1][0],dp[i-1][2]);
            dp[i][2]=b[i]+Math.min(dp[i-1][1],dp[i-1][0]);
        }
        //After the loop is completed, the function returns the minimum cost among the three possibilities:
        return Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2]));
    }
	/*
	this code calculates the minimum cost of coloring N objects with three different colors, considering the costs of coloring each object with one of the three colors and minimizing the total cost. 
	The dynamic programming approach allows for efficient computation of the minimum cost. 
	The time complexity of this algorithm is O(N), where N is the number of objects, and the space complexity is O(N) due to the 2D dp array.
	 */
	
}
