package com.prudhvi.dynamic_programming.bitmanuplation;

import java.util.Arrays;

public class CountingBits {

	public static void main(String[] args) {
		int num=5;
		int[] bits=countBits(num);
		System.out.println(Arrays.toString(bits));
	}
	/*
	implementation of counting the number of set bits (1s) for each number from 0 to num. 
	It uses dynamic programming to compute the count of set bits for each number in the range
	
	Time Complexity:
		The function iterates through numbers from 2 to num, so the time complexity is O(num).
	Space Complexity:
		The function uses an array dp of size num + 1 to store the set bit counts for each number, resulting in a space complexity of O(num).
	 */
	private static int[] countBits(int num) {
		//base cases
		if(num<=1){
            int[] arr={0,1};
            int[] arr1={0};
           return num==0?arr1:arr;
        }
		//Create an integer array dp of size num + 1 to store the count of 1 bits for each number from 0 to num.
		int[] dp=new int[num+1];
		//nitialize dp[0] to 0 since there are no 1 bits in the binary representation of 0, and dp[1] to 1 since there is one 1 bit in the binary representation of 1.
		dp[0]=0;dp[1]=1;
		for(int i=2;i<dp.length;i++) {
			// If 'i' is even, the number of set bits in 'i' is the same as in 'i/2'.
			if(i%2==0)
				dp[i]=dp[i>>1];// Right shift is equivalent to dividing by 2.
			else
				dp[i]=dp[i-1]+1;// If 'i' is odd, the number of set bits in 'i' is one more than in 'i-1'.
		}
		return dp;
	}
	

}
