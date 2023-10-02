package com.prudhvi.dynamic_programming.catalan_numbers;
/*
	Problem Description
		Given an integer A, how many structurally unique BST's (binary search trees) exist that can store values 1...A?
	Problem Constraints
		1 <= A <=18
 */
public class UniqueBinarySearchTrees_II {

	public static void main(String[] args) {
		int n=5;
		System.out.println(catalanNumber(n));
		

	}

	private static int catalanNumber(int n) {
		int[] dp=new int[n+1];
		dp[0]=1;dp[1]=1;
		for(int i=2;i<=n;i++) {
			int c0=0;
			int cn=i-1;
			while(c0<i ){
				dp[i]+=(dp[c0]*dp[cn]);
				c0++;
				cn--;
			}
		}
		return dp[n];
	}

}
