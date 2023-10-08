package com.prudhvi.dynamic_programming.knapsack;

import java.util.Arrays;

public class Knapsack_0_1 {

	public static void main(String[] args) {
		int[] value= {12,20,15,6,10}; 
		int[] weight= {3,6,5,2,4};
		int capacity=7;
		int maxTabulationScOptimized=knapsackTabSpaceOptimized(value,weight,capacity);
		System.out.println(maxTabulationScOptimized);
		int maxTabulation=knapsackTab(value,weight,capacity);
		System.out.println(maxTabulation);
		int maxMemo=knapsackMemo(value,weight,capacity);
		System.out.println(maxMemo);

	}
	
	private static int knapsackTabSpaceOptimized(int[] v, int[] w, int capacity) {
		int[][] dp=new int[2][capacity+1];
		//base condition i=0 items
		for(int i=0;i<=capacity;i++) {
			dp[0][i]=0;
		}
		for(int i=1;i<=v.length;i++) {
			for(int j=0;j<=capacity;j++) {
					int leave=dp[(i-1)%2][j];
					int pick=0;
					if(j>=w[i-1])
						pick=dp[(i-1)%2][j-w[i-1]]+v[i-1];
					dp[i%2][j]=Math.max(leave, pick);
				
			}
		}
		return dp[(v.length)%2][capacity];
	}

	private static int knapsackTab(int[] v, int[] w,int capacity) {
		int[][] dp=new int[v.length+1][capacity+1];
		for(int i=0;i<dp.length;i++)
			for(int j=0;j<dp[i].length;j++) {
				if(i==0 || j==0) {
					dp[i][j]=0;
				}
				else {
					int leave=dp[i-1][j];
					int pick=0;
					if(j>=w[i-1])
						 pick=dp[i-1][j-w[i-1]]+v[i-1];
					dp[i][j]=Math.max(leave, pick);
				}
			}
		System.out.println(Arrays.deepToString(dp));
		return dp[v.length][capacity];
	}
	//memorization
	private static int knapsackMemo(int[] value, int[] weight, int capacity) {
		int w=weight.length;
		Integer[][] dp=new Integer[w+1][capacity+1];
		knapsack0_1(w-1,weight,value,capacity,dp);
		//return knapsack0_1(w-1,weight,value,capacity,dp);
		return dp[w-1][capacity];
		  
	}

	private static int knapsack0_1(int idx, int[] weight, int[] value, int capacity, Integer[][] dp) {
		if(idx<0|| capacity==0) {
			return 0;
		}
		if(dp[idx][capacity]==null) {
			int dontPick=knapsack0_1(idx-1,weight, value, capacity,  dp);
			if(capacity>=weight[idx]) {
				int pick=value[idx]+knapsack0_1(idx-1, weight, value,capacity-weight[idx],  dp);
				dp[idx][capacity]=Math.max(dontPick, pick);
			}
			else {
				dp[idx][capacity]=dontPick;
			}
			
		}
		return dp[idx][capacity];
	}

}
