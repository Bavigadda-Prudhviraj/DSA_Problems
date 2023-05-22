package com.prudhvi.Arrays.one_dimentsional_arrays;

import java.util.Arrays;

/*
given an array with size n will all 0 values and q queries
we have to add that element from that index to last index
here q[0]=index and q[i][1]=values
Note:-[ 0		0	    0	   0	  0	 ]
		A[0] + A[0] + A[0] + A[0] + A[0]
			   A[1] + A[1] + A[1] + A[1]
			   		  A[2} + A[2] +	A[2]
			   		  		 A[3] + A[3]
			   		  		 		A[4]
*/
public class A1_PrefixArray {

	public static void main(String[] args) {
		int[] arr= {0,0,0,0,0,0};
		int[][] queries= {{2,4},{3,-1},{0,2},{4,1}};
		int[] ans=prefixArr(arr, queries);
		System.out.print(Arrays.toString(ans));

	}
	public static int[] prefixArr(int[] arr, int[][] querie) {
		int[] ans=new int[arr.length];
		//here adding the elements to the given array
		for(int i=0;i<querie.length;i++) {
			arr[querie[i][0]]=querie[i][1];
		}
		//now we need to construct the prefix array why means in prefix array the values will carry forward the values
		ans[0]=arr[0];
		for(int i=1;i<ans.length;i++) {
			ans[i]=ans[i-1]+arr[i];
		}
		return ans;
		
	}

}
