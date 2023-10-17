package com.prudhvi.dynamic_programming.strings;

import java.util.Arrays;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		String str="abba";
		int lenTab=LPSStab(str);
		System.out.println(lenTab);
		int lenMemo=LPSSmemo(str);
		System.out.println(lenMemo);
		

	}
	/*
	Time Complexity:
		The code iterates through two nested loops, one for each character of the str and revStr. In the worst case, it compares every character pair. Therefore, the time complexity is O(n * m), where n is the length of str, and m is the length of revStr.
	Space Complexity:
		The code uses a 2D array dp to store the results for different substrings, with a size of n x m. Therefore, the space complexity is O(n * m).
	
	In summary, this code effectively finds the length of the Longest Palindromic Subsequence (LPS) of a given string using dynamic programming. Its time complexity is O(n * m), and its space complexity is also O(n * m).
	 */
	private static int LPSStab(String str) {
		StringBuilder revStr=new StringBuilder(str);
		revStr.reverse();
		int n=str.length();
		int m=revStr.length();
		int[][] dp=new int[n][m];
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[i].length;j++) {
				if(i==0 && j==0) {
					dp[i][j]=str.charAt(i)==revStr.charAt(j)?1:0;
				}else if(i==0 && j>0) {
					dp[i][j]=str.charAt(i)==revStr.charAt(j)?1:dp[i][j-1];
				}else if (j==0 && i>0){
					dp[i][j]=str.charAt(i)==revStr.charAt(j)?1:dp[i-1][j];
				}else {
					if(str.charAt(i)==revStr.charAt(j)) {
						dp[i][j]=dp[i-1][j-1]+1;
					}else {
						dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
					}
					
				}
			}
		}
		return dp[n-1][m-1];
	}
	/*

**Explanation**:

	1. The function `LPSStab` takes a string `str` as input.
	
	2. It initializes a `StringBuilder` called `revStr`, which is a reversed version of the input string `str`. This is used to compare the characters of the string with its reverse.
	
	3. It determines the length of the input string `str` and the reversed string `revStr`, and initializes a 2D array `dp` of size `[n][m]`, where `n` is the length of `str`, and `m` is the length of `revStr`.
	
	4. The 2D array `dp` is used to store the length of the Longest Palindromic Subsequence (LPS) for different substrings of `str` and `revStr`.
	
	5. It then iterates through both `str` and `revStr` using a nested loop. For each pair of characters `str[i]` and `revStr[j]`, it compares them and updates `dp[i][j]` accordingly.
	
	6. If `i` and `j` are both 0, it checks whether the characters are the same, and if they are, it sets `dp[i][j]` to 1, indicating a palindromic subsequence of length 1.
	
	7. If `i` is 0 and `j` is greater than 0, it checks whether the characters are the same and updates `dp[i][j]` based on the previous cell in the same row.
	
	8. If `j` is 0 and `i` is greater than 0, it checks whether the characters are the same and updates `dp[i][j]` based on the previous cell in the same column.
	
	9. For other cases, if `str[i]` is the same as `revStr[j]`, it sets `dp[i][j]` to `dp[i-1][j-1] + 1`, indicating that the length of the LPS increases by 1.
	
	10. If the characters are not the same, it takes the maximum of `dp[i][j-1]` (the previous cell in the same row) and `dp[i-1][j]` (the previous cell in the same column).
	
	11. The final cell `dp[n-1][m-1]` contains the length of the Longest Palindromic Subsequence for the entire string.
	
	12. The function returns `dp[n-1][m-1]` as the result.

*/
	/*
	**Time Complexity**:
		- The memoization process ensures that each unique substring of `str` and `revStr` is calculated only once. The function iterates through two nested loops, one for each character of the `str` and `revStr`, but with memoization, many of these calculations are skipped. In the worst case, it calculates every unique substring. Therefore, the time complexity is O(n * m), where `n` is the length of `str`, and `m` is the length of `revStr`.
	
	**Space Complexity**:
		- The code uses a 2D array `dp` for memoization, with a size of `n` x `m`. Therefore, the space complexity is also O(n * m).
	
	In summary, this code efficiently finds the length of the Longest Palindromic Subsequence (LPS) of a given string using dynamic programming with memoization. Its time complexity is O(n * m), and its space complexity is also O(n * m).
	 
	 */
	private static int LPSSmemo(String str) {
		StringBuilder revStr=new StringBuilder(str);
		revStr.reverse();
		int n=str.length();
		int m=revStr.length();
		int[][] dp=new int[n][m];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i],-1);
		lpsMemo(n-1,str,m-1,revStr,dp);
		return dp[n-1][m-1];
	}
	private static int lpsMemo(int i, String str, int j, StringBuilder revStr, int[][] dp) {
		if(i==-1 || j==-1) {
			return 0;
		}
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}else{
			if(str.charAt(i)==revStr.charAt(j)) {
				dp[i][j]=lpsMemo(i-1, str, j-1, revStr, dp)+1;
			}else {
				int excludeCharStr=lpsMemo(i-1, str, j, revStr, dp);
				int excludeCharRevStr=lpsMemo(i, str, j-1, revStr, dp);
				dp[i][j]=Math.max(excludeCharStr, excludeCharRevStr);
			}
		}
		return dp[i][j];
		
	}
	/*
**Explanation**:

	1. The function `LPSSmemo` takes a string `str` as input.
	
	2. It initializes a `StringBuilder` called `revStr`, which is a reversed version of the input string `str`. This is used to compare the characters of the string with its reverse.
	
	3. It determines the length of the input string `str` and the reversed string `revStr`, and initializes a 2D array `dp` of size `[n][m]`, where `n` is the length of `str`, and `m` is the length of `revStr`.
	
	4. The 2D array `dp` is used to store the length of the Longest Palindromic Subsequence (LPS) for different substrings of `str` and `revStr`. The array is initialized with `-1` to indicate that no values have been calculated yet.
	
	5. It then calls the `lpsMemo` function with `i = n-1`, `j = m-1`, and the memoization array `dp`.
	
	6. The `lpsMemo` function is a recursive function that calculates the LPS length for a given substring.
	
	7. If `i` or `j` is -1, it means one of the substrings is empty, and the LPS length is 0. So, it returns 0.
	
	8. If `dp[i][j]` is not -1, it means that the value has already been calculated, so it returns the stored value from the memoization array.
	
	9. If the characters at position `i` in `str` and `j` in `revStr` are the same, it calls `lpsMemo` recursively with `i-1` and `j-1` and adds 1 to the result, indicating that the length of the LPS increases by 1. The result is stored in `dp[i][j]`.
	
	10. If the characters are not the same, it calculates two scenarios: one where `i` is excluded, and one where `j` is excluded. It takes the maximum of these two scenarios and stores it in `dp[i][j]`.
	
	11. The function returns `dp[n-1][m-1]`, which contains the length of the Longest Palindromic Subsequence for the entire string.

*/


}
