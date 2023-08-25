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
	/*
	The function to generate unique permutations of a given ArrayList of integers. 
	The code uses backtracking while taking into account the frequency of elements to ensure unique permutations are generated
	
	Time Complexity:
			The function generates unique permutations while taking into account the frequency of elements.
			The time complexity is determined by the number of unique permutations, which is usually less than n! (factorial of the size of the input array).
			For each element, the function tries out different possibilities based on its frequency.
		The overall time complexity is less than O(n!), but the exact complexity can vary depending on the input distribution.
	Space Complexity:
			The space complexity is determined by the space required for the recursion stack and the additional data structures used.
			The recursion stack can go as deep as the size of the input array.
			The permutations list can store all the unique permutations, and the elements list can have at most n elements.
			The frequencyArr requires O(11) space (constant space).
		Therefore, the overall space complexity is O(n) for the recursion stack and additional data structures.
	 */
	private static ArrayList<ArrayList<Integer>> uniQuePermutations(ArrayList<Integer> arr) {
		ArrayList<ArrayList<Integer>> permutations=new ArrayList<>();//The uniQuePermutations function initializes an empty list to store unique permutations and 
		int[] frequencyArr=new int[11];//an array frequencyArr to keep track of the frequency of each element.
		for(int i=0;i<arr.size();i++){//The frequency of each element in the input array is counted and stored in the frequencyArr.
			frequencyArr[arr.get(i)]++;
		}
		List<Integer> elements=Arrays.asList(new Integer[arr.size()]);//A list named elements is created with the same size as the input array, and it's used to store the current permutation being generated.
		generatePermutation(elements,0,frequencyArr,arr,permutations);//The generatePermutation function is the main backtracking function that generates unique permutations while considering the frequency of elements.
		return permutations;
				
	}

	private static void generatePermutation(List<Integer> elements, int idx, int[] frequencyArr, ArrayList<Integer> arr,ArrayList<ArrayList<Integer>> permutations) {
		if(idx==arr.size()) {//When idx reaches the size of elements, a complete unique permutation has been generated, and it's added to the permutations list.
			permutations.add(new ArrayList<>(elements));
			return;
		}
		for(int i=0;i<11;i++) {
			//For each element in the frequency array, if the frequency of that element is greater than 0, 
			if(frequencyArr[i]>0) {
				elements.set(idx,i);//it's added to the elements list at the current idx.
				frequencyArr[i]--;//The frequency of the element is then decremented to ensure it's used only once in the permutation.
				generatePermutation(elements, idx+1, frequencyArr, arr, permutations);//Recursively, the function generates unique permutations with the updated elements list and 
				frequencyArr[i]++;//restores the frequency of the element afterward.
			}
		}
		
	}

}

