package com.prudhvi.dynamic_programming.strings;

import java.util.Arrays;

/*
	Problem Description
		Implement wild card pattern matching with support for ' ? ' and ' * ' for strings A and B.
			' ? ' : Matches any single character.
			' * ' : Matches any sequence of characters (including the empty sequence).
			The matching should cover the entire input string (not partial).
	Problem Constraints
		1<=length(A),length(B) <= 104
 */
public class RegularExpressionMatch {

	public static void main(String[] args) {
		String text= "aaa";
		String pattern= "a*";
		int isMatchTab=isMatch(text, pattern);
		System.out.println(isMatchTab);
		int ismatch=regularExpressionMatch(text,pattern);
		System.out.println(ismatch);

	}
	/*
	Time Complexity: 
			The time complexity of this code is O(n * m) since it uses a nested loop to fill in the dp array of size (n + 1) x (m + 1).
	Space Complexity: 
			The space complexity is also O(n * m) due to the dp array.
	 */
	public static int isMatch(final String text, final String pattern) {
        int n = text.length();
        int m = pattern.length();
        //where dp[i][j] represents whether the first i characters of the text match the first j characters of the pattern.
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;//Set dp[0][0] to true because an empty text and an empty pattern match.
        //Loop through the pattern string using the variable i. If pattern.charAt(i - 1) is '', set dp[0][i] to dp[0][i - 1]. 
        //This is because '' can match zero or more characters, so if the previous pattern character can match an empty string, '*' can also match an empty string.
        for (int i = 1; i < m + 1; i++) {
            if (pattern.charAt(i - 1) == '*'){
                dp[0][i] = dp[0][i-1];
            }
        }
        //Next, loop through the text string using the variable i and the pattern string using the variable j (both starting from 1)
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
            	//For each pair of characters text.charAt(i - 1) and pattern.charAt(j - 1):
            	//If the characters match exactly (text.charAt(i - 1) == pattern.charAt(j - 1)) or the pattern character is '?', set dp[i][j] to dp[i - 1][j - 1]. 
            	//This means that the current characters match, and we continue the matching process with the next characters in both the text and the pattern.
                if(text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?')   
                    dp[i][j] = dp[i-1][j-1];
                //If the pattern character is '*', set dp[i][j] to the result of logical OR (|) of two possibilities:
                else if(pattern.charAt(j-1) == '*')   
                	//dp[i - 1][j]: '*' matches one or more characters in the text, so we continue with the current pattern character but move to the next text character.
                	//dp[i][j - 1]: '*' matches zero characters in the text, so we skip the current pattern character.
                    dp[i][j] = (dp[i-1][j] | dp[i][j-1]);
            }
        }
        //Finally, return dp[n][m], where n is the length of the text and m is the length of the pattern. If dp[n][m] is true, it means the entire text matches the pattern, so the function returns 1. Otherwise, it returns 0.
        return dp[n][m]?1:0;
    }
	//works in leet code gives TLE in interviewBit
	private static int regularExpressionMatch(String text, String pattern) {
		//It initializes two integers, n and m, representing the lengths of the text and pattern strings, respectively.
		int n=text.length();
		int m=pattern.length();
		//It creates a 2D integer array dp of size (n x m), where dp[i][j] stores the result of whether the first i characters of the text match the first j characters of the pattern. 
		int[][] dp=new int[n][m];
		//The array is initialized with -1 to indicate that no computations have been done yet.
		for(int i=0;i<dp.length;i++) {
			Arrays.fill(dp[i],-1);
		}
		//It calls the rfmMemo function with the last indices of the text and pattern strings and the dp array.
		return rfmMemo(n-1,text,m-1,pattern,dp);
		
	}

	private static int rfmMemo(int i, String text, int j, String pattern, int[][] dp) {
		//If both i and j reach -1 (indicating that both the text and pattern have been completely matched), it returns 1, indicating a match.
		if(i==-1 && j==-1) 
			return 1;
		//If j reaches -1 (indicating that the pattern has been completely matched), it returns 0, as the text cannot be matched further.
		if(j==-1) 
			return 0;
		//If i reaches -1 (indicating that the text has been completely matched), it checks if the remaining characters in the pattern are all ''. 
		//If they are, it returns 1 since '' can match zero or more characters.
		if(i==-1) {
			for (int k = 0; k <= j; k++) { 
	            if (pattern.charAt(k) != '*')
	                return 0;
	        }
	        return 1;
		}
		//If dp[i][j] is not -1, it returns the previously computed result to avoid redundant calculations.
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}//If none of the above conditions are met, it checks the current characters in the text and pattern:
		else {
			//If they match or the pattern has a '?', it recursively calls rfmMemo with i-1 and j-1.
			if(text.charAt(i)==pattern.charAt(j) || pattern.charAt(j)=='?') {
				dp[i][j]=rfmMemo(i-1, text, j-1, pattern, dp);
			}//If the pattern has a '*', it calculates two possibilities:
			else if(pattern.charAt(j)=='*'){
				//a by recursively calling rfmMemo with i, j-1 (indicating that '*' matches zero characters).
				int matchWith0Characters=rfmMemo(i, text, j-1, pattern, dp);
				//b by recursively calling rfmMemo with i-1, j (indicating that '*' matches one or more characters).
				int matchWithOneCharacter=rfmMemo(i-1, text, j, pattern, dp);
				dp[i][j]=(matchWith0Characters | matchWithOneCharacter );
				
			}//If none of the above conditions are met, it sets dp[i][j] to 0, indicating no match.
			else {
				dp[i][j]=0;
			}
		}
		return dp[i][j];
		
	}

}
