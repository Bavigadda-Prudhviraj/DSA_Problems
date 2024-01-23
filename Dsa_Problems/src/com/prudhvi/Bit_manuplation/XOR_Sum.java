package com.prudhvi.Bit_manuplation;
/*
	Problem Description
		Given two integers A and B. Find the minimum value (A ⊕ X) + (B ⊕ X) that can be achieved for any X.
		where P ⊕ Q is the bitwise XOR operation of the two numbers P and Q.
		Note: Bitwise XOR operator will return 1, if both bits are different. If bits are same, it will return 0.
 */

public class XOR_Sum {

	public static void main(String[] args) {
		int a=6;
		int b=12;
		int xorSum=xorSum(a, b);
		System.out.println(xorSum);
	}
	public static int xorSum(int A, int B) {
		int ans=((A^(A&B))+(B^(A&B)));
	    return ans;
	}
	public int solve(int A, int B) {
		return A^B;
	}
}
