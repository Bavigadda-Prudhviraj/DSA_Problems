package com.prudhvi.Arrays.sliding_window;
/*
	Given an array A of length N. Also given are integers B and C.
	Return true if there exists a subarray with length B having sum C and false otherwise
 */
public class SubarrayWithGivenSumAndLength {

	public static void main(String[] args) {
		int[] arr= {4, 3, 2, 6, 1};
		int	len = 3;
		int	sum = 11;
		boolean existSubArrayWithSum=subArrayWithSum(arr,len,sum);
		System.out.println(existSubArrayWithSum);
	}
	public static boolean subArrayWithSum(int[] arr,int len,int targetSum) {
		int sum=0;
		for(int i=0;i<len;i++) {
			sum+=arr[i];
		}
		if(sum==targetSum) {
			return true;
		}
		for(int i=len;i<arr.length;i++) {
			sum+=arr[i];
			sum-=arr[i-len];
			if(sum==targetSum) {
				return true;
			}
		}
		return false;
		
	}

}
