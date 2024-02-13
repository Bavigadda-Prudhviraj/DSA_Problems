package com.prudhvi.Arrays.sub_arrays;

public class MaxSubArray {
	public static void main(String[] args) {
		int[] arr= {-2,1,-3,4,-1,2,1,-5,4};
		int maxSum=kadnesAlgo(arr);
		System.out.println(maxSum);
	}
	public static int kadnesAlgo(int[] arr) {
		int sum=Integer.MIN_VALUE;
		int currentSum=0;
		for(int i=0;i<arr.length;i++){
			currentSum+=arr[i];
			sum=Math.max(sum,currentSum);
			if(currentSum<0)
				currentSum=0;
		}
		return sum;
	}

}
