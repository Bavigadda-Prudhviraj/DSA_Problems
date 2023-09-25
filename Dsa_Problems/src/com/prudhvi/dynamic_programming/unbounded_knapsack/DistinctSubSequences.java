package com.prudhvi.dynamic_programming.unbounded_knapsack;

import java.util.Arrays;
/*
	Problem Description
		Given two sequences A and B, count number of unique ways in sequence A, to form a subsequence that is identical to the sequence B.
		Subsequence : 
			A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
			(i.e, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

	Problem Constraints
		1 <= length(A), length(B) <= 700
 */
public class DistinctSubSequences {
	//note: go through memo and then tabulation
	public static void main(String[] args) {
		String A = "rabbbit" ;
		String B = "rabbit";
		int subSequence=distnctSubsequenceSpaceOpt(A,B);
		System.out.println(subSequence);
		int subSequenceTab=distinctSubSequencesTabulation(A, B);
		System.out.println(subSequenceTab);
		int ways=distinctSubSequences(A,B);
		System.out.println(ways);
	}
	private static int distnctSubsequenceSpaceOpt(String a, String b) {
		int n=b.length();
		int[] dpPrev=new int[n+1];
		int[] dpCur=new int[n+1];
		dpPrev[0]=dpCur[0]=1;
		for(int i=1;i<=a.length();i++) {
			for(int j=1;j<=b.length();j++) {
				if(i>0 && j>0 &&  a.charAt(i-1)==b.charAt(j-1)) {
					dpCur[j]=dpPrev[j-1]+dpPrev[j];
				}
				else {
					if(i>0 && j>0) {
						dpCur[j]=dpPrev[j];
					}
				}
				
			}
			System.arraycopy(dpCur, 0, dpPrev, 0, n+1);
		}
		return dpCur[n];
	}
	/*
	Code calculates the number of distinct subsequences of string a that are equal to string b using dynamic programming and tabulation. 
	A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
	Time Complexity:
		The time complexity of this solution is O(n * m), where n is the length of string a, and m is the length of string b.
	Space Complexity:
		The space complexity is O(n * m) due to the dp array.
	 */

	private static int distinctSubSequencesTabulation(String a, String b) {
		int n=a.length();
		int m=b.length();
		int[][] dp=new int[n+1][m+1];
		
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[i].length;j++) {
				// When the pattern string 'b' is empty, there's exactly one subsequence in string 'a' by removing all characters from 'a'.
				if(j==0) {
					dp[i][j]=1;
				}
				// If the current characters in both strings match, we have two options:
                // 1. Include the current character in both strings, reducing both lengths.
                // 2. Exclude the current character from string 'a' but continue checking with the previous characters in both strings.
				if(i>0 && j>0 && a.charAt(i-1)==b.charAt(j-1)) {
					dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
				}else {
					if(i>0 && j>0) {
						// If the current characters do not match, we can only exclude the current
		                // character from string 'a' and continue checking with the previous characters in both strings.
						dp[i][j]=dp[i-1][j];
					}
				}
			}
		}
		return dp[n][m];
	}

	/*
	 Time Complexity:
		The time complexity of this solution is O(n * m), where n is the length of string a, and m is the length of string b.
	Space Complexity:
		The space complexity is also O(n * m) due to the memoization table dp.
	 */
	private static int distinctSubSequences(String a, String b) {
		int n=a.length();
		int m=b.length();
		int[][] dp=new int[n][m];
		for(int i=0;i<dp.length;i++) {
			Arrays.fill(dp[i],-1);
		}
		subSequenceMemo(n-1,m-1,a,b,dp);
		return dp[n-1][m-1];
	}

	private static int subSequenceMemo(int i, int j, String a, String b, int[][] dp) {
		//If j (the index of string b) becomes less than 0, it means we have successfully matched all characters in string b with a subsequence of string a, so it returns 1 (indicating a successful match).
		if(j<0) {
			return 1;
		}
		//If i (the index of string a) becomes less than 0, it means we have exhausted string a but haven't matched all characters in string b, so it returns 0 (indicating no match).
		if(i<0) {
			return 0;
		}
		//If the dp matrix at the current indices i and j is not -1, it means we have already computed the value for this subproblem, so it returns the stored value.
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}
		// If the characters at positions i in string 'a' and j in string 'b' are the same,
	    // we have two options:
	    // 1. Include the current character in both strings and move to the next characters.
	    // 2. Exclude the current character from string 'a' and continue checking with the previous character in string 'a'.
		if(a.charAt(i)==b.charAt(j)) {
			//subSequenceMemo(i-1, j-1, a, b, dp) represents the case where we match the current characters of both strings.
			//subSequenceMemo(i-1, j, a, b, dp) represents the case where we don't match the current characters of both strings, but we continue matching string b with the remaining characters of string a.
			dp[i][j]=subSequenceMemo(i-1, j-1, a, b, dp)+subSequenceMemo(i-1, j, a, b, dp);
			
		}
		else {
			// If the characters are not the same, we can only exclude the current character from
	        // string 'a' and continue checking with the previous character in string 'a'.
		   dp[i][j]=subSequenceMemo(i-1, j, a, b, dp);
		}
		return dp[i][j];
		
	}

	

}

