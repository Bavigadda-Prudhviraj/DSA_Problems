package com.prudhvi.Arrays;

import java.util.Arrays;

public class PartitionArraySuchThatMaximumDifferenceIsK_2294 {

	public static void main(String[] args) {
		int[] nums = {2,2,4,5};
		int k = 0;
		System.out.println(partitionArray(nums, k)); //4
	}
	 public static int partitionArray(int[] nums, int k) {
	    	if(nums.length == 1) {
	    		return 1;
	    	}
	    	Arrays.sort(nums);
	    	System.out.println(Arrays.toString(nums));
	    	int subSequence = 1;
	    	int min = nums[0];
	    	for (int i = 1; i < nums.length; i++) {
	    		int differenace = nums[i] - min;
	    		if(differenace > k) {
					
					subSequence++;
					min = nums[i];
				}
			}
	        return subSequence;
	        
	    }

}
