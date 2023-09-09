package com.prudhvi.dynamic_programming.strings;

import java.util.Arrays;
/*
	Problem Description
		Given two strings A and B. Find the longest common subsequence ( A sequence which does not need to be contiguous),which is common in both the strings.
		You need to return the length of such longest common subsequence.
	Problem Constraints
		1 <= Length of A, B <= 1005
 */
public class LongestCommonSubsequence {
	public static void main(String[] args) {
		String str1="abbcdgf";
		String str2="bbadcgf";
		int subSeqMemo=LCSMemo(str1,str2);
		System.out.println(subSeqMemo);
		int subSeqTab=LCSTab(str1,str2);
		System.out.println(subSeqTab);
	}
	/*
	Longest Common Subsequence (LCS) Problem:
	The LCS problem seeks to find the length of the longest subsequence that is common to two given strings, str1 and str2. 
	A subsequence of a string is a sequence of characters that appears in the same order as in the original string, but not necessarily consecutively.
	
	Time Complexity:
		The time complexity of this dynamic programming solution is O(n*m), where n and m are the lengths of str1 and str2, respectively. 
		This is because you iterate through both strings in a nested loop, filling in the dp array.
	Space Complexity:
		The space complexity is O(n*m) as well because you create a 2D dp array with dimensions n and m, which store the results of subproblems.
	 */
	private static int LCSTab(String str1, String str2) {
		int n=str1.length();
		int m=str2.length();
		//It creates a 2D array dp of size n x m to store the length of the LCS for different substrings of str1 and str2. dp[i][j] will represent the length of the LCS for the substrings str1[0...i] and str2[0...j].
		int[][] dp=new int[n][m];
		
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[i].length;j++) {
				//If both i and j are 0, it checks if the characters at str1[0] and str2[0] are the same. If they are the same, it sets dp[0][0] to 1 (indicating a common character), otherwise, it sets it to 0.
				if(i==0 && j==0) 
					dp[i][j]=str1.charAt(i)==str2.charAt(j)?1:0;
				//If i is 0 and j is greater than 0, it checks if the character at str1[0] is the same as the character at str2[j]. If they are the same, it sets dp[i][j] to 1, otherwise, it sets it to the value of dp[i][j-1] (the LCS length without considering str1[i]).
				else if(i==0 && j>0)
					dp[i][j]=str1.charAt(i)==str2.charAt(j)?1:dp[i][j-1];
				//If j is 0 and i is greater than 0, it does the same comparison but for str2[0] and str1[i].
				else if(j==0 && i>0)
					dp[i][j]=str1.charAt(i)==str2.charAt(j)?1:dp[i-1][j];
				//For all other cases (when both i and j are greater than 0),  
				else
					//it compares str1[i] with str2[j].If they are the same, it adds 1 to the LCS length from the diagonal element (dp[i-1][j-1]), indicating that a common character has been found.
					if(str1.charAt(i)==str2.charAt(j)) 
						dp[i][j]=dp[i-1][j-1]+1;
					//Otherwise, it takes the maximum of the LCS length without considering str1[i] (dp[i-1][j]) and without considering str2[j] (dp[i][j-1]).
					else 
						dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
			}	
		}
		return dp[n-1][m-1];
	}
	private static int LCSMemo(String str1, String str2) {
		int n=str1.length();
		int m=str2.length();
		//dp[i][j] will represent the length of the LCS for the substrings str1[0...i] and str2[0...j]. 
		int[][] dp=new int[n][m];
		for(int i=0;i<dp.length;i++)
			//Initially, all elements of dp are set to -1 to indicate that they have not been calculated yet.
			Arrays.fill(dp[i], -1);
		//The code then calls the lcsRecMemo function with the following arguments: i (initially set to n-1), str1, j (initially set to m-1), str2, and dp.
		lcsRecMemo(n-1,str1,m-1,str2,dp);
		return dp[n-1][m-1];
	}
	private static int lcsRecMemo(int i, String str1, int j, String str2, int[][] dp) {
		//If either i or j becomes less than 0, it returns 0 because there are no characters left to compare.
		if(i==-1 || j==-1)
			return 0;
		//If dp[i][j] is not -1, it means that the length of the LCS for the current substrings has already been calculated, so it returns dp[i][j].
		if(dp[i][j]!=-1) 
			return dp[i][j];
		else {
			//If the characters at str1[i] and str2[j] are the same, it recursively calls lcsRecMemo with updated indices (i-1 and j-1) and adds 1 to the result. 
			//This represents the case when the current characters are part of the LCS.
			if(str1.charAt(i)==str2.charAt(j)) {
				dp[i][j]=lcsRecMemo(i-1,str1,j-1,str2,dp)+1;			
			}else{
				//If the characters are not the same, it calculates two possibilities: 
				//			one by excluding the character at str1[i](by calling lcsRecMemo(i-1, str1, j, str2, dp)) and the 
				//	      other by excluding the character at str2[j](by calling lcsRecMemo(i, str1, j-1, str2, dp)). 
				//It then takes the maximum of these two possibilities and stores it in dp[i][j].
				int excludeCharStr1=lcsRecMemo(i-1,str1,j,str2,dp);
				int excludeCharStr2=lcsRecMemo(i,str1,j-1,str2,dp);
				dp[i][j]=Math.max(excludeCharStr1,excludeCharStr2);
			}
		}
		//Finally, the LCSMemo function returns dp[n-1][m-1], which represents the length of the LCS between the entire str1 and str2.
		return dp[i][j];
	}
}
