package com.prudhvi.Arrays.prefix_sum;
/*
	You are given an array A of integers of size N.
	Your task is to find the equilibrium index of the given array
	The equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes.
	If there are no elements that are at lower indexes or at higher indexes, then the corresponding sum of elements is considered as 0.
	Note:
		Array indexing starts from 0.
		If there is no equilibrium index then return -1.
		If there are more than one equilibrium indexes then return the minimum index.
 */
public class Equilibrium_Index {

	public static void main(String[] args) {
		int[] arr= {-7, 1, 5, 2, -4, 3, 0};
		int eqilibriumIndex=equilibriumIndex(arr);
		System.out.println(eqilibriumIndex);
	}
	public static int equilibriumIndex(int[] arr) {
		int n=arr.length;
		int[] leftPrefixSum=new int[n];
		leftPrefixSum[0]=arr[0];
		for(int i=1;i<n;i++) {
			leftPrefixSum[i]=leftPrefixSum[i-1]+arr[i];
		}
		int[] rightPrefixsum=new int[n];
		rightPrefixsum[n-1]=arr[n-1];
		for(int i=n-2;i>=0;i--) {
			rightPrefixsum[i]=rightPrefixsum[i+1]+arr[i];
		}
		for(int i=0;i<n;i++) {
			if(leftPrefixSum[i]==rightPrefixsum[i]) {
				return i;
			}
		}
		return -1;
	}

}
