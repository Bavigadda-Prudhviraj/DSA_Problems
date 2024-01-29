package com.prudhvi.Arrays.prefix_sum;
import java.util.Arrays;


/*
	You are given an array A of length N and Q queries given by the 2D array B of size Q×2.
	Each query consists of two integers B[i][0] and B[i][1].
	For every query, your task is to find the count of even numbers in the range from A[B[i][0]] to A[B[i][1]].
 */
public class EvenNumbersInRange {

	public static void main(String[] args) {
		int[] arr = {2, 1, 8, 3, 9, 6};
		int[][]	queries = {{0, 3},
				           {3, 5},
				           {1, 3}, 
				           {2, 4}};
		int[] countofEvenNumbers=countEvenNumbers(arr,queries);
		System.out.println(Arrays.toString(countofEvenNumbers));
	}
	public static int[] countEvenNumbers(int[] arr,int[][] queries) {
		int[] evenNumbers=new int[queries.length];
		arr[0]=arr[0]%2==0?1:0;
		for(int i=1;i<arr.length;i++) {
			arr[i]=arr[i]%2==0?arr[i-1]+1:arr[i-1];
		}
		for(int i=0;i<queries.length;i++) {
			int l=queries[i][0];
			int r=queries[i][1];
			if(l==0) {
				evenNumbers[i]=arr[r];
			}else {
				evenNumbers[i]=arr[r]-arr[l-1];
			}
			
		}
		return evenNumbers;
		
	}

}
