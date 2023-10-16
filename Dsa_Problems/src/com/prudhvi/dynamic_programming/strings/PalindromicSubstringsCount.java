package com.prudhvi.dynamic_programming.strings;

/*
	Given a string A consisting of lowercase English alphabets. Your task is to find how many substrings of A are palindrome.
	The substrings with different start indexes or end indexes are counted as different substrings even if they consist of same characters.
	Return the count of palindromic substrings.
	Note: A string is palindrome if it reads the same from backward and forward.
 */

public class PalindromicSubstringsCount {

	public static void main(String[] args) {
		String str="ababa";
		int palindromeCnt=pSCTab(str);
		System.out.println(palindromeCnt);

	}

	private static int pSCTab(String str) {
		int n=str.length();
		//2D boolean array dp of size n x n. The dp[i][j] value will be true if the substring from index i to index j in the string str is a palindrome; otherwise, it will be false.
		boolean[][] dp=new boolean[n][n];
		int palindromeCnt=0;//This variable will be used to count the number of palindromic substrings found in the string.
		//The outer loop (d loop) represents the difference between the end and start indices of the substring being considered. It ranges from 0 to n-1, where d represents the length of the substring minus one.
		for(int d=0;d<n;d++) {
			//The inner loops (i and j loops) iterate through all possible substrings of length d within the string str.
			for(int i=0,j=d;j<n;j++,i++) {
				//If i is equal to j, it means the substring has only one character, which is always a palindrome. In this case, dp[i][j] is set to true, and palindromeCnt is incremented.
				if(i==j) {
					dp[i][j]=true;
					palindromeCnt++;
				}
				//If j is equal to i + 1, it means the substring has two characters.  or we can check as d==1 it will also will work
				else if(j==(i+1)) {
					//The code checks if these two characters are the same. If they are, dp[i][j] is set to true, and palindromeCnt is incremented.
					if(str.charAt(i)==str.charAt(j)) {
						dp[i][j]=true;
						palindromeCnt++;
					}
				}
				//the code checks if the characters at the current positions i and j are the same. If they are, it checks if the substring within these positions (i.e., from i+1 to j-1) is also a palindrome (i.e., dp[i+1][j-1] is true). 
				else {
					if(str.charAt(i)==str.charAt(j)) {
						  //If both conditions are met, dp[i][j] is set to true, and palindromeCnt is incremented.
							dp[i][j]=dp[i+1][j-1]?true:false;
							palindromeCnt=dp[i][j]?palindromeCnt+1:palindromeCnt;
					}
				}
			}
		}
		return palindromeCnt;
	}
	public static int pSCMemo(String str) {
	    int n = str.length();
	    boolean[][] dp = new boolean[n][n];
	    int cnt = 0;
	    
	    for (int d = 0; d < n; d++) {
	        for (int i = 0, j = d; j < n; j++, i++) {
	            if (isPalindrome(str, i, j, dp)) {
	                cnt++;
	            }
	        }
	    }
	    
	    return cnt;
	}

	private static boolean isPalindrome(String str, int i, int j, boolean[][] dp) {
	    if (i == j) {
	        dp[i][j] = true;
	    } else if (j == (i + 1)) {
	        dp[i][j] = (str.charAt(i) == str.charAt(j));
	    } else {
	        if (str.charAt(i) == str.charAt(j)) {
	            dp[i][j] = dp[i + 1][j - 1];
	        }
	    }
	    return dp[i][j];
	}


}
