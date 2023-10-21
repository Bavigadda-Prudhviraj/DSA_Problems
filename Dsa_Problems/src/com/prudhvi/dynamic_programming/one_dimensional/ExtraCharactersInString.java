package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

public class ExtraCharactersInString {

	public static void main(String[] args) {
		String s = "leetscodees", 
		String[] = {"leet","code","leetcode"};
		int extraChar=minExtraChar(s, String);
		System.out.println(extraChar);

	}
	/*
	**Time Complexity**:
		- The code uses a nested loop structure. The outer loop iterates through the characters of the string 's' (from 1 to n), where 'n' is the length of the string.
		- The inner loop iterates through the words in the dictionary. For each word, it checks whether a substring of 's' ending at the current position 'i' is equal to the word. This involves comparing substrings of maximum length, which is limited by the longest word in the dictionary. Therefore, the inner loop's time complexity is proportional to the number of words in the dictionary and the length of the longest word.
		- Since the outer loop runs for each character in 's' and the inner loop iterates over the dictionary, the overall time complexity is O(n * k), where 'n' is the length of the string 's,' and 'k' is the maximum length of a word in the dictionary.
	
	**Space Complexity**:
		- The code uses an array 'dp' of size 'n + 1' to store the minimum number of extra characters needed to form valid substrings. Therefore, the space complexity is O(n) for the 'dp' array.
	In summary, the code has a time complexity of O(n * k), where 'n' is the length of the input string and 'k' is the maximum length of a word in the dictionary. The space complexity is O(n) due to the 'dp' array.
	 */
	public static int minExtraChar(String s, String[] dictionary) {
        int n=s.length();
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            for(String word:dictionary){
                int len=word.length();
                if(i>=len){
                    String sub=s.substring(i-len,i);
                    if(sub.equals(word)){
                        dp[i]=Math.min(dp[i],dp[i-len]);
                    }
                }
            }
            dp[i]=Math.min(dp[i],dp[i-1]+1);
        }
        return dp[n];
        
    }
/*
The  code is used to find the minimum number of extra characters that need to be added to a given string `s` to make it a valid concatenation of words from a given dictionary. It uses dynamic programming to efficiently compute this minimum value. Let's break down the code step by step:

1. `int n = s.length();`: This line calculates the length of the input string `s`.

2. `int[] dp = new int[n+1];`: It initializes an array `dp` of size `n + 1` to store the minimum number of extra characters required to make the first `i` characters of `s` a valid concatenation of words from the dictionary.

3. `Arrays.fill(dp, Integer.MAX_VALUE);`: It initializes all elements in the `dp` array with the maximum possible integer value, which serves as an initial "infinite" value.

4. `dp[0] = 0;`: The first element of the `dp` array is set to 0 since no extra characters are needed to make an empty string.

5. The code then enters a loop from `i = 1` to `n`:
   - For each value of `i`, it iterates through the words in the dictionary using the `for` loop.
   - For each word in the dictionary, it checks if the length of the word is less than or equal to `i`.
   - If the length condition is satisfied, it extracts a substring of `s` from `i - len` to `i`, where `len` is the length of the current word.
   - If the substring equals the current word, it means the word can be concatenated at the end of the previous valid string, so it updates `dp[i]` with the minimum of its current value and `dp[i - len]`.
   - It also considers the option of adding an extra character by setting `dp[i]` to the minimum between its current value and `dp[i - 1] + 1`.

6. After the loop, the function returns `dp[n]`, which represents the minimum number of extra characters required to make the entire string `s` a valid concatenation of words from the dictionary.

In summary, this code efficiently calculates the minimum number of extra characters needed to make the input string `s` a valid concatenation of words from the dictionary using dynamic programming. The time complexity of this algorithm is O(n * m), where `n` is the length of the string and `m` is the number of words in the dictionary. The space complexity is O(n), as it uses an additional array of size `n + 1` for dynamic programming.
 */
}
