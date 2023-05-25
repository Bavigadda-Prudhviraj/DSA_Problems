package com.prudhvi.Arrays.two_dimentsional_arrays;

/*

Given a 2D Matrix A of dimensions N*N, we need to return the sum of all possible sub matrices.

Here we are using array contribution technique

Algo:- we have to determine that in how many sub matrices that element will be present
	   1.For that we have to find out in how many sub matrices that element will be present in top and bottom
	   2.To find in how many sub matrices the element present at bottom we generalize formula is given as= (i+1)*(j+1)
	   3.To find in how many sub matrices the element present at top  we generalize formula is given as=(length of row)*(length of column )
	   4.After calculating that in how many sub matrices that element present at top and bottom position we have to multiple that top and bottom values
	   	 By multiplying that two values we will get the frequency that in how many total sun matrices that element will be there
	   	 Frequency(top*bottom)=(i+1)*(j+1) * (length of row -i)*(length of column - j )
	   5.After getting the getting the frequency will have to multiply that frequency with current element
	     So, subSumOfOneElement=array[i][j]*frequency or
	     	 sum= array[i][j]*(i+1)(j+1)+ (length of row -i)*(length of column - j );
	     	 
	   6.Above formula is to calculate the contribution of one element to find all elements contribution then we have to iterate over the given array then we will get total sum
	  
	   NOte:- Time complexity - O(n*m)
	   		  Space complexity-O(1)
*/


public class SumOfAllSubMatrices {

	public static void main(String[] args) {
	int[][]	A = { {1, 2},{3, 4} };
	int ans=allSubSum(A);
	System.out.println(ans);
	int[][] arr = { {1, 1},{1, 1} };
	int ans1=allSubSum(arr);
	System.out.println(ans1);
	}
	private static int allSubSum(int[][] arr) {
		int ans=0;
		int lengthRow=arr.length;
		int lengthCol=arr[0].length;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				int frequency=((i+1)*(j+1))*(lengthRow-i)*(lengthCol-j);
				int element=arr[i][j];
				ans+=(frequency*element);
			}
		}
		return ans;
	}

}

