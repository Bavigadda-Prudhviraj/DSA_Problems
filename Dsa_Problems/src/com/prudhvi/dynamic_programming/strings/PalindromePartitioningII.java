package com.prudhvi.dynamic_programming.strings;



/*
	Problem Description
		Given a string A, partition A such that every substring of the partition is a palindrome.
		Return the minimum cuts needed for a palindrome partitioning of A.
	Problem Constraints
		1 <= length(A) <= 501
 */
public class PalindromePartitioningII {

	public static void main(String[] args) {
		String str="ababb";//1
		int minCuts=palindromePartitioningII(str);
		System.out.println(minCuts);

	}
	/*
	Time Complexity:
		Palindrome Detection (palindromeDp): The first nested loop constructs a 2D array palindromeDp to detect palindromes within the given string str. It iterates through all possible substrings of str, and for each substring, it checks if it is a palindrome. The nested loop has a time complexity of O(n^2), where 'n' is the length of the input string.
		Dynamic Programming (dp array): The second loop constructs a dynamic programming array dp of length 'n' and calculates the minimum number of palindrome partitions for each substring. It uses the palindromeDp array to determine if a substring is a palindrome and finds the optimal solution using dynamic programming. This loop also has a time complexity of O(n^2).
		Overall, the dominant factor for time complexity is the nested palindrome detection loop, which is O(n^2). Therefore, the overall time complexity of the function is O(n^2).
	Space Complexity:
		Palindrome Detection (palindromeDp): The function creates a 2D array palindromeDp to store whether substrings are palindromes or not. This array has dimensions 'n' by 'n', resulting in a space complexity of O(n^2).
		Dynamic Programming (dp array): The function creates a 1D array dp of length 'n' to store intermediate results of the minimum number of palindrome partitions. This array consumes O(n) space.
		Other variables and constants used in the function consume constant space and do not depend on the input size.
		The dominant factor for space complexity is the palindromeDp array, which is O(n^2). Therefore, the overall space complexity of the function is O(n^2).
	In summary, the time complexity of the palindromePartitioningII function is O(n^2), and the space complexity is O(n^2).
	 */
	private static int palindromePartitioningII(String str) {
		int n=str.length();
		//It first calculates a boolean 2D array palindromeDp, where palindromeDp[i][j] represents whether the substring of str from index i to index j is a palindrome.
		boolean[][] palindromeDp=new boolean[n][n];
		//The outer loop with variable d iterates through all possible substrings of str, where d represents the length of the substring.
		for(int d=0;d<n;d++) {
			//The inner loops with variables i and j iterate through the characters of the substring, with i starting at 0 and j starting at d.
			for(int i=0,j=d; j < n; i++,j++){
				//If the length of the substring (d) is 1, it's considered a palindrome.
				if(d==0) {
					palindromeDp[i][j]=true;
				}
				//If the length of the substring (d) is 2 and the characters at indices i and j are the same, it's considered a palindrome.
				else if(d==1 && str.charAt(i)==str.charAt(j)){
					palindromeDp[i][j]=true;
				}
				//For substrings of length greater than 2, it checks if the characters at indices i and j are the same and if the substring between i and j is also a palindrome (palindromeDp[i+1][j-1]).
				else{
					if(str.charAt(i)==str.charAt(j) && palindromeDp[i+1][j-1]) {
						palindromeDp[i][j]=true;
					}
				}
				
			}
		}
		//After calculating palindromeDp, it initializes an integer array dp of the same length as str (i.e., n) to keep track of the minimum number of cuts needed to partition the string.
		int[] dp=new int[n];
		dp[0]=0;//It initializes dp[0] to 0 since no cuts are needed for an empty string
		//It then iterates through the string characters starting from index 1. 
		for(int i=1;i<n;i++) {
			//For each character at index i, it initializes ans to i, which represents the worst-case scenario where each character is cut individually.
			int ans=i;
			//It checks if the entire substring from index 0 to i is a palindrome (palindromeDp[0][i]). If it's a palindrome, no cuts are needed (ans = 0).
			if(palindromeDp[0][i]) 
				ans=0;
			//If the substring is not a palindrome, 
			else {
				//it iterates through the substring from index i to 1 in reverse order (variable j). 
				for(int j=i;j>=1;j--) {
					//For each j, it checks if the substring from j to i is a palindrome (palindromeDp[j][i]). 
					if(palindromeDp[j][i]) {
						//If it's a palindrome, it updates ans to the minimum of its current value and dp[j-1] + 1. 
						//This means that if we cut the string at index j, we add 1 to the number of cuts needed for the remaining substring (dp[j-1]).
						ans=Math.min(ans,dp[j-1]+1);
					}
				}
			}
			//After finding the minimum ans for the current index i, it sets dp[i] to ans.
			dp[i]=ans;
			
		}
		//Finally, the function returns dp[n-1], where n is the length of the input string str. This value represents the minimum number of cuts needed to partition the string into palindromic substrings.
		return dp[n-1];
	}

}
