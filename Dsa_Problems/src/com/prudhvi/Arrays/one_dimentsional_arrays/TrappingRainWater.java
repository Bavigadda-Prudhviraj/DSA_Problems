package com.prudhvi.Arrays.one_dimentsional_arrays;


import java.util.*;

public class TrappingRainWater {

	public static void main(String[] args) {
		int[] arr= {0, 1, 0, 2};
		int totalVolumeOfWater=volume(arr);
		//System.out.println(totalVolumeOfWater);
		int[] arr1= {4, 2, 0, 3,2,5};
		int totalVolumeOfWater1=volume(arr1);
		System.out.println(totalVolumeOfWater1);

	}
	//note:- here time complexity is O(n) & space complexity is O(2N);
	private static int volume(int[] arr) {
		//first we need to construct the prefix Array for left height calculation
		int[] lb=new int[arr.length];
		//for left highest 0th element is the left highest element
		lb[0]=arr[0];
		for(int i=1;i<arr.length;i++) {
			lb[i]=Math.max(lb[i-1], arr[i]);
		}
		//second we need to construct the right highest of current element prefix Array
		int[] rb=new int[arr.length];
		//last element is the right highest element 
		rb[rb.length-1]=arr[arr.length-1];
		for(int i=arr.length-2;i>=0;i--) {
			rb[i]=Math.max(rb[i+1],arr[i]);
		}
		int totalVolume=0;
		for(int i=1;i<arr.length-1;i++) {
			int len=Math.min(lb[i], rb[i]);
			totalVolume=totalVolume+(len-arr[i]);
			//System.out.println("i="+i+" lb[i]="+lb[i]+" rb[i]="+rb[i]+" len(max)="+len+" currentVolume="+totalVolume);
		}
//		System.out.println(Arrays.toString(lb));
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(rb));
		
		
		return totalVolume;
	}

}
