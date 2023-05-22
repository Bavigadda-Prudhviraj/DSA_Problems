package com.prudhvi.Arrays.one_dimentsional_arrays;

import java.util.Arrays;

/*
here we we have to add the elements from start index to given index 
in previous question we are adding elements from beginning to end , but in this question we have to add up to certain index

Note:here also we are going to construct the prefix array but we will add the values to the given index and we will add that negative values at the
	 endIndex+1,we can come across corner case that if we get index n-1, then no need to put negative values at end because arr.length index does not exist
	
	
	
Question :- 
			There are A beggars sitting in a row outside a temple. Each beggar initially has an empty pot. 
			When the devotees come to the temple, they donate some amount of coins to these beggars. 
			Each devotee gives a fixed amount of coin(according to their faith and ability) to some K beggars sitting next to each other.

			Given the amount P donated by each devotee to the beggars ranging from L to R index, 
			where 1 <= L <= R <= A,find out the final amount of money in each beggar's pot at the end of the day, 
			provided they don't fill their pots by any other means.
			For i th devotee 
			B[i][0] = L, 
			B[i][1] = R, 
			B[i][2] = P, Given by the 2D array B
*/
public class A2_PrefixArray_A1_Continue {

	public static void main(String[] args) {
		int arraySize=5;
		int[][] quries= {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
		int[] ans=begger(arraySize, quries);
		System.out.println(Arrays.toString(ans));
	}
	
	public static int[] begger(int A,int[][] quries) {
		int[] ans=new int[A];
		//iterating over the given queries array
		for(int i=0;i<quries.length;i++) {
			int start=quries[i][0];
			int end=quries[i][1];
			int value=quries[i][2];
			//note if any values are starting at the same index then we have to add the element present in the index previously
			ans[start-1]=ans[start-1]+value;
			//here this condition if end values is A
			if(end<A) {
				//if any positive values is presented in that end index values then it will subtract that values and make one values to get carry forward
				ans[end]=ans[end]-value;
			}
		}
		//here we are modifying the answer because we don't need construct an extra array because there in no queries for process 
		for(int i=1;i<ans.length;i++) {
			ans[i]=ans[i-1]+ans[i];
		}
		return ans;		
	}
}
