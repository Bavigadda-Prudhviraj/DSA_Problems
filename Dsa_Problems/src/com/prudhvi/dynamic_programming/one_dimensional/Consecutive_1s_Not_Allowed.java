package com.prudhvi.dynamic_programming.one_dimensional;
//https://www.youtube.com/watch?v=nqrXHJWMeBc

/*
Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s. Output your answer modulo 109 + 7.
Example 1:
	Input:
    N = 3
	Output: 5
	Explanation:
	5 strings are (000,
	001, 010, 100, 101).
Example 2:
	Input:
	N = 2
	Output: 3
	Explanation: 
	3 strings are (00,01,10).
Your Task:
You don't have to print answer or take inputs. Complete the function countStrings() which takes single integer n, as input parameters and returns an integer denoting the answer. 

Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N)
 */
public class Consecutive_1s_Not_Allowed {

	public static void main(String[] args) {
		int n = 3;
		System.out.println(countStrings(n));

	}
	public static long countStrings(int n) {
	    // Arrays to store the counts of binary strings of length n ending with 0 or 1
	    long[] dp0 = new long[n + 1];
	    long[] dp1 = new long[n + 1];
	    // Initial values for strings of length 1
	    dp0[1] = dp1[1] = 1;
	    // Modulo value for handling large numbers
	    long mod = 1000000007;
	    // Iterate from length 2 to n
	    for (int i = 2; i <= n; i++) {
	        // Calculate counts for strings ending with 0 and 1
	        dp0[i] = dp1[i - 1];
	        dp1[i] = (dp0[i - 1] + dp1[i - 1] + mod) % mod;
	    }
	    // Return the sum of counts for strings of length n ending with 0 or 1
	    return (dp0[n] + dp1[n]) % mod;
	}


}
