package com.prudhvi.Arrays.two_dimentsional_arrays;

import java.util.Arrays;

public class TwoDArrayPrefixSum {

	public static void main(String[] args) {
		int[][]	A = { {1, 2},{3, 4} };
		int[][] ans=prefixSum(A);
		System.out.println(Arrays.deepToString(ans));
		System.out.println("<----------------------------------------------->");
		int[][] arr = { {1, 1},{1, 1} };
		int[][] ans1=prefixSum(arr);
		System.out.println(Arrays.deepToString(ans1));
	}

	private static int[][] prefixSum(int[][] arr) {
		System.out.println(Arrays.deepToString(arr));
		//Row wise traversal
		for(int i=1;i<arr[0].length;i++) {
			for(int j=0;j<arr.length;j++) {
				arr[j][i]=arr[j][i-1]+arr[j][i];	
			}
		}
		//column wise traversal
		for(int i=1;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=arr[i-1][j]+arr[i][j];	
			}
		}
		
		return arr;
	}

}

