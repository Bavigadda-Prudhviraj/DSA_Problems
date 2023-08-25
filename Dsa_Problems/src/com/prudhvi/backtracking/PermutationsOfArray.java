package com.prudhvi.backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsOfArray {

	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(List.of(1, 2, 3));
		ArrayList<ArrayList<Integer>> permutations=permutations(arr);
		System.out.println(permutations);
		
	}
	/*
	The code is meant to generate all possible permutations of the given array of integers. 
	It uses a recursive backtracking approach to generate permutations and maintains a boolean array visited to keep track of visited elements.
	
	Time Complexity:
			The function generates all permutations by exploring all possible orders of the elements.
			The time complexity is determined by the number of permutations, which is n! (factorial of the size of the input array).
			For each position in the permutation, the function tries out all remaining elements, resulting in n * n! recursive calls.
		Therefore, the overall time complexity of the algorithm is O(n * n!).

	Space Complexity:
			The space complexity is determined by the space required for the recursion stack and the additional data structures used.
			The recursion stack can go as deep as the size of the input array.
			The permutations list can store up to n! permutations, and the elements list can have at most n elements.
			The visited array requires O(n) space.
		Therefore, the overall space complexity is O(n) for the recursion stack and O(n * n) for the additional data structures(to Store permutations), resulting in a space complexity of O(n * n).
	 */
	public static ArrayList<ArrayList<Integer>> permutations(ArrayList<Integer> arr){
		ArrayList<ArrayList<Integer>> permutations=new ArrayList<>();//The permutations function initializes an empty list to store all permutations 
		boolean[] visited=new boolean[arr.size()];//creates a boolean array visited to keep track of visited elements.
		List<Integer> elements=Arrays.asList(new Integer[arr.size()]);//A list named elements is created with the same size as the input array, and it's used to store the current permutation being generated.
		generatePermutations(elements,0,permutations,visited,arr);//The generatePermutations function is the main backtracking function that generates permutations recursively.
		return permutations;
	}
	private static void generatePermutations(List<Integer> ele,int index,ArrayList<ArrayList<Integer>> permutations,boolean[] visited,ArrayList<Integer> arr) {
		if(index==ele.size()) {//When index reaches the size of ele, a complete permutation has been generated, and it's added to the permutations list.
			permutations.add(new ArrayList<>(ele));
			return;
		}
		for(int i=0;i<arr.size();i++) {
			if(!visited[i]) {
				ele.set(index,arr.get(i));//For each element in the input array arr, if the element hasn't been visited yet, it's added to the ele list at the current index.
				visited[i]= true;//The element is marked as visited using the visited array to ensure that the same element is not used again in the same permutation.
				generatePermutations(ele, index+1, permutations, visited, arr);//Recursively, the function generates permutations with the updated ele list
				visited[i]= false;//marks the element as unvisited afterward to explore other possibilities.
			}
		}	
	}
}
/*		Note:it wont work for boolean
 		ArrayList<ArrayList<Integer>> permutations=new ArrayList<>();
		List<Integer> ele=Arrays.asList(new Integer[arr.size()]);
		permutations.add(new ArrayList<>(ele));
 */
