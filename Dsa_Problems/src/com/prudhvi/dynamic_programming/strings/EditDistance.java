package com.prudhvi.dynamic_programming.strings;


import java.util.Arrays;

/*
	Problem Description
		Given two strings A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)
		You have the following 3 operations permitted on a word:
			Insert a character
			Delete a character
			Replace a character
	Problem Constraints
		1 <= length(A), length(B) <= 450
 */
public class EditDistance {
	public static void main(String[] args) {
		//convert into a to b
		String a="abad";
		String b="abac";
		int setpsTab=editDistanceTab(a,b);
		System.out.println(setpsTab);
		int steps=editDistance(a,b);
		System.out.println(steps);
		
	}

	private static int editDistanceTab(String a, String b) {
		 int n = a.length();
		 int m = b.length();
		//It creates a 2D array dp of size (n+1) x (m+1) to store the edit distance between different substrings of a and b. 
		//dp[i][j] will represent the edit distance between the substrings a[0...i-1] and b[0...j-1]. 
		//The extra row and column are used for the base cases when one of the strings is empty.
		 int[][] dp = new int[n + 1][m + 1];
		 for (int i = 0; i <= n; i++) {
			 for (int j = 0; j <= m; j++) {
			     if (i == 0 && j == 0) {
			         dp[i][j] = 0; // Both strings are empty
			     } else if (i == 0) {
			         dp[i][j] = j; // Insertion: Transforming an empty string to b[0...j-1]
			     } else if (j == 0) {
			         dp[i][j] = i; // Deletion: Transforming a[0...i-1] to an empty string
			     }else if (a.charAt(i - 1) == b.charAt(j - 1)) {
			         dp[i][j] = dp[i - 1][j - 1]; // No operation needed
			     } else {
			         int delete = dp[i - 1][j] + 1;     // Deletion
			         int insert = dp[i][j - 1] + 1;     // Insertion
			         int replace = dp[i - 1][j - 1] + 1; // Replacement
			         dp[i][j] = Math.min(delete, Math.min(insert, replace));
			     }
			}
		 }
		 for(int i=0;i<dp.length;i++) {
			 System.out.println(Arrays.toString(dp[i]));
		 }
		 return dp[n][m];
	}

	private static int editDistance(String a, String b) {
		int n=a.length();
		int m=b.length();
		int[][] dp=new int[n][m];
		for(int i=0;i<dp.length;i++) {
			Arrays.fill(dp[i],-1);
		}
		editDiestanceMemo(n-1,a,m-1,b,dp);
		return dp[n-1][m-1];
	}

	private static int editDiestanceMemo(int i, String a, int j, String b, int[][] dp) {
		//convert into a to b
		if(i==-1) {
			return j+1; // Insert all characters from b into a
		}
		if(j==-1) {
			return i+1;// Delete all characters from a
		}
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}else {
			if(a.charAt(i)==b.charAt(j)) {
				dp[i][j]=editDiestanceMemo(i-1, a, j-1, b, dp);// No operation needed
			}else {
				int delete=editDiestanceMemo(i-1, a, j, b, dp)+1;// Deletion
				int insert=editDiestanceMemo(i, a, j-1, b, dp)+1;// Insertion
				int replace=editDiestanceMemo(i-1, a, j-1, b, dp)+1;// Replacement
				dp[i][j]=Math.min(replace, Math.min(delete, insert));
			}
		}
		return dp[i][j];
	}

}
