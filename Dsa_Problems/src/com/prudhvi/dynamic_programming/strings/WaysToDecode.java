package com.prudhvi.dynamic_programming.strings;

public class WaysToDecode {

	public static void main(String[] args) {
		String A = "12";
		System.out.println(numDecodings(A));
	}

	public static int numDecodings(String A) {
		int mod = 1000000007;
		int[] dp = new int[A.length() + 1];

		if (A.charAt(0) == '0') {
			return 0;
		}

		int n = A.length();

		dp[0] = 1; // Base: empty string
		dp[1] = 1; // Base: single valid digit (not '0')

		for (int i = 2; i <= n; i++) {

			// Check if the single digit at (i-1) is valid (1 to 9)
			if (A.charAt(i - 1) >= '1' && A.charAt(i - 1) <= '9') {
				dp[i] = dp[i - 1];
			}

			// Check if the two-digit number formed by (i-2) and (i-1) is valid (10 to 26)
			char prev = A.charAt(i - 2);
			char curr = A.charAt(i - 1);

			if (prev == '1' || (prev == '2' && curr >= '0' && curr <= '6')) {
				dp[i] = (dp[i] + dp[i - 2]) % mod;
			}
		}

		return dp[n];
	}

	

}
