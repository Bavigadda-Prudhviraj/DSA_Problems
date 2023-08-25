package com.prudhvi.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllUniquePermutationsWhenDuplicates {
	/*
	Problem Description
		Given an array A of size N denoting collection of numbers that might contain duplicates, return all possible unique permutations.
			NOTE: No 2 entries in the permutation sequence should be the same.
	Problem Constraints
			1 <= |A| <= 9
			0 <= A[i] <= 10
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(List.of(2,3,4));
		ArrayList<ArrayList<Integer>> uniquePermutaions=uniQuePermutations(arr);
		System.out.println(uniquePermutaions);

	}

	private static ArrayList<ArrayList<Integer>> uniQuePermutations(ArrayList<Integer> arr) {
		ArrayList<ArrayList<Integer>> permutations=new ArrayList<>();
		int[] frequencyArr=new int[11];
		for(int i=0;i<arr.size();i++){
			frequencyArr[arr.get(i)]++;
		}
		List<Integer> elements=Arrays.asList(new Integer[arr.size()]);
		generatePermutation(elements,0,frequencyArr,arr,permutations);
		return permutations;
				
	}

	private static void generatePermutation(List<Integer> elements, int idx, int[] frequencyArr, ArrayList<Integer> arr,ArrayList<ArrayList<Integer>> permutations) {
		if(idx==arr.size()) {
			permutations.add(new ArrayList<>(elements));
			return;
		}
		for(int i=0;i<11;i++) {
			if(frequencyArr[i]>0) {
				elements.set(idx,i);
				frequencyArr[i]--;
				generatePermutation(elements, idx+1, frequencyArr, arr, permutations);
				frequencyArr[i]++;
			}
		}
		
	}

}

