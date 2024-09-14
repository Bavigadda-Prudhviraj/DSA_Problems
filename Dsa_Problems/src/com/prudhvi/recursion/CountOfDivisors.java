package com.prudhvi.recursion;

import java.util.Arrays;
/*
Given an array of integers A, find and return the count of divisors of each element of the array.

NOTE: The order of the resultant array should be the same as the input array.
Approach:
	Using seive, we can store the smallest prime factor for all the numbers upto the maximum no (here it is 106).
	This above information helps in determining the prime factors for any no in O(log n) time-complexity for each query.
	We take each no in the input array. Then prime factorise it to count the powers of each prime factors in a number.
	N = (p1n1) * (p2n2) * (p3n3).
	Here, N can be represented as p1 prime raised to the power n1 muliply p2 prime raised to n2 multiply p3 raised to n3.
	So, total factors of N will be (n1 + 1) * (n2 + 1) * (n3 + 1).
	For eg: 18 = (21) * (32).
	So, total factors = 2*3 = 6.
 */
public class CountOfDivisors {

	public static void main(String[] args) {
		int [] a = {2, 3, 4, 5};
		int[] divisiorsList = countDivisors(a);
		System.out.println(Arrays.toString(divisiorsList));

	}
    public static int[] countDivisors(int[] A) {
        int maxEle=Integer.MIN_VALUE;
        for(int i=0;i<A.length;i++){
           maxEle = Math.max(maxEle, A[i]);
        }
        int[] fact=new int[maxEle+1];
        for(int i=1;i<=maxEle;i++){
            for(int j=i;j<=maxEle;j=j+i){
                fact[j]+=1;
            }
        }
        int[] ans=new int[A.length];
        for(int i=0;i<A.length;i++){
            int num=A[i];
            ans[i]=fact[num];

        }
        return ans;
    }

}
