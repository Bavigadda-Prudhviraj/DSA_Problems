package com.prudhvi.dynamic_programming.catalan_numbers;
/*
	Problem Description
		Given an integer A, how many structurally unique BST's (binary search trees) exist that can store values 1...A?
	Problem Constraints
		1 <= A <=18
 */
public class UniqueBinarySearchTrees_II {

	public static void main(String[] args) {
		int n=5;
		System.out.println(catalanNumber(n));
		

	}

	private static int catalanNumber(int n) {
		int[] dp=new int[n+1];
		dp[0]=1;dp[1]=1;
		for(int i=2;i<=n;i++) {
			int c0=0;
			int cn=i-1;
			while(c0<i ){
				dp[i]+=(dp[c0]*dp[cn]);
				c0++;
				cn--;
			}
		}
		return dp[n];
	}
/*
The  code calculates the nth Catalan number using dynamic programming. Catalan numbers are a sequence of natural numbers that appear in various combinatorial problems. The code efficiently computes the nth Catalan number using an iterative approach. Let's break down the code step by step:

1. `int[] dp = new int[n + 1];`: It initializes an integer array `dp` of size `n + 1` to store the Catalan numbers. The array is indexed from 0 to `n`.

2. `dp[0] = 1; dp[1] = 1;`: It sets the initial values for the Catalan numbers. The first two Catalan numbers are always 1.

3. The code enters a loop from `i = 2` to `n` to calculate the Catalan number for each value of `i`.

4. Inside the loop, two variables are initialized:
   - `int c0 = 0;` represents the index for the first Catalan number being multiplied.
   - `int cn = i - 1;` represents the index for the second Catalan number being multiplied.

5. The code uses a `while` loop to calculate the sum of products of Catalan numbers for the current value of `i`. It continues until `c0` is less than `i`.

6. In each iteration of the `while` loop, it calculates `dp[i]` by adding the product of `dp[c0]` and `dp[cn]` to the current value of `dp[i]`.

7. The values of `c0` and `cn` are updated in each iteration.

8. After the `while` loop, the code has calculated the Catalan number for `i`.

9. The loop continues until `i` reaches `n`, and the code returns `dp[n]`, which is the nth Catalan number.

In summary, the code efficiently calculates the nth Catalan number using dynamic programming, where each Catalan number is calculated as the sum of products of two other Catalan numbers. The time complexity of this algorithm is O(n^2) because it uses two nested loops, and the space complexity is O(n) due to the dynamic programming array of size `n + 1`.
 */

}
