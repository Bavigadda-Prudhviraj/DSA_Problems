package com.prudhvi.Bit_manuplation;

public class LongestSubarrayWithMaximumBitwiseAND {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 3, 2, 2 };
		System.out.println(longestSubarray(nums));

	}

	public static int longestSubarray(int[] nums) {
		int maxLen = 0;
		int maxNum = 0;
		int maxCurrentLen = 0;
		for (int ele : nums) {

			if (ele > maxNum) {
				maxNum = ele;
				maxCurrentLen = 0;
				maxLen = 0;
			}
			maxCurrentLen = (ele == maxNum) ? maxCurrentLen+1 : 0;

			maxLen = Math.max(maxLen, maxCurrentLen);
		}
		return maxLen;

	}

}
