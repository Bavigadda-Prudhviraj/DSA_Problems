package com.prudhvi.Arrays.sub_arrays;

import java.util.ArrayList;

public class GenerateAllsubArrays {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5};
		ArrayList<ArrayList<Integer>> allSubArrays=generateSubArrays(arr);
		for(int i=0;i<allSubArrays.size();i++) {
			System.out.println(allSubArrays.get(i));
		}
	}
	public static ArrayList<ArrayList<Integer>> generateSubArrays(int[] arr){
		ArrayList<ArrayList<Integer>> allsubArrays=new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			for(int j=i;j<arr.length;j++) {
				ArrayList<Integer> subArray=new ArrayList<>();
				for(int k=i;k<=j;k++) {
					subArray.add(arr[k]);
				}
				allsubArrays.add(subArray);
			}
			
		}
		return allsubArrays;
	}

}
