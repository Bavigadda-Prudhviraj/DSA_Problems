package com.prudhvi.strings;

public class FindTheLexicographicallyLargestStringFromTheBoxI_3403 {

	public static void main(String[] args) {
		System.out.println(answerString("aann", 2));

	}
	public static String answerString(String word, int numFriends) {
	    int n = word.length();
	    
	    // Base case: if numFriends is 1, return the entire word
	    if (numFriends == 1) {
	        return word;
	    }
	    
	    String result = "";
	    int longestPossible = n - (numFriends - 1);
	    
	    // Iterate over the string
	    for (int i = 0; i < n; i++) {
	        // Calculate the maximum length we can take at this position
	        int canTakeLength = Math.min(longestPossible, n - i);
	        // Ensure we don't go beyond the string length
	        if (i + canTakeLength <= n) {
	            // Take substring from index i of length canTakeLength
	            String substr = word.substring(i, i + canTakeLength);
	            // Update result if the new substring is lexicographically larger
	            if (result.compareTo(substr) < 0) {
	                result = substr;
	            }
	        }
	    }
	    
	    return result;
	}
}
