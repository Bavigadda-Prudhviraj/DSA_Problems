package com.prudhvi.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Subset {
	/*
	Problem Description
			Given a set of distinct integers A, return all possible subsets.
		NOTE:
			Elements in a subset must be in non-descending order.
			The solution set must not contain duplicate subsets.
			Also, the subsets should be sorted in ascending ( lexicographic ) order.
			The list is not necessarily sorted.
	Problem Constraints
			1 <= |A| <= 16
			INTMIN <= A[i] <= INTMAX
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(List.of(1,2,3));
		ArrayList<ArrayList<Integer>> subSets=subSets(arr);
		System.out.println(subSets);

	}
	/*
	
	Time Complexity:
			The function generates all subsets by exploring all possible combinations of elements.
			The time complexity is determined by the number of subsets, which is 2^n (where n is the size of the input array).
			The sorting step takes O(2^n * n * log n) time complexity.
		Therefore, the overall time complexity of the algorithm is O(2^n * n * log n).
	
	Space Complexity:
			The space complexity is determined by the space required for the recursion stack and the additional data structures used.
			The recursion stack can go as deep as the size of the input array.
			The allSubsets list can store up to 2^n subsets.
			The subset list can have at most n elements.
		Therefore, the overall space complexity is O(n) for the recursion stack and O(2^n * n) for the additional data structures, resulting in a space complexity of O(2^n * n).
	 */
	private static ArrayList<ArrayList<Integer>> subSets(ArrayList<Integer> arr) {
		ArrayList<ArrayList<Integer>> allSubsets=new ArrayList<>();
		ArrayList<Integer> subset=new ArrayList<>();
		Collections.sort(arr);// Sort the input array
		generatePermutations(subset,0,arr,allSubsets);// Generate subsets recursively
		Collections.sort(allSubsets,new MySort()); // Sort subsets based on custom comparator
		return allSubsets;// Return sorted subsets
	}
	// Recursive function to generate subsets
	private static void generatePermutations(ArrayList<Integer> subset, int idx, ArrayList<Integer> arr,ArrayList<ArrayList<Integer>> allSubsets) {
		if(idx==arr.size()) {
			allSubsets.add(new ArrayList<>(subset));// Add the current subset to the result list
			return;
		}
		subset.add(arr.get(idx));// Include the current element in the subset
		generatePermutations(subset, idx+1, arr, allSubsets);// Recursively include next element
		subset.remove(subset.size()-1);// Exclude the current element from the subset
		generatePermutations(subset, idx+1, arr, allSubsets);// Recursively exclude next element
	}
}
class MySort implements Comparator<ArrayList<Integer>> {
    @Override
    public int compare(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
    	/*
    	If num is negative, it means arr1 is smaller.
		If num is positive, it means arr1 is larger.
		If num is 0, it means the arrays are equal.
    	 */
        int num=-1;//Initializes the variable to -1. This variable will be used to store the comparison result.
        if(arr1.size()==0){//The first if condition checks if arr1 is empty. If it is, num is set to -1, indicating that arr1 is smaller.
            num=-1;
        }
        else if(arr2.size()==0) {//The else if condition checks if arr2 is empty. If it is, num is set to 1, indicating that arr1 is larger.
            num=1;
        }
        else if(arr1.size()<=arr2.size()) {//The else if condition compares the sizes of arr1 and arr2. If arr1 is smaller or equal in size, the loop iterates through the elements of both arrays.
            for(int i=0;i<arr1.size();i++) {
                if(arr2.get(i)!=arr1.get(i)) {
                    num= arr1.get(i)-arr2.get(i);
                    break;
                }
            }
        }
        else if(arr2.size()<arr1.size()) {//The next else if condition handles the case where arr2 is smaller than arr1 in size. In this case, num is set to 1 initially.
            num=1;//why reason below mention
            for(int i=0;i<arr2.size();i++) {
                if(arr2.get(i)!=arr1.get(i)) {
                    num= arr1.get(i)-arr2.get(i);
                    break;
                }
            }
        }
        return num;
    }
}

/*
 	 []
     [1]
	 [1, 2]
	 [1, 2, 3]
	 [1, 3]
	 [2]
	 [2, 3]
	 [3]
	 The purpose of setting num to 1 in this condition is to establish a default value for the comparison result. This default value is used when both arr1 and arr2 are non-empty and have different sizes.
			Here's the rationale behind it:
					1.When the condition arr2.size() < arr1.size() is met, it means that the size of arr2 is smaller than the size of arr1. 
					  Therefore, arr1 is larger in some way.
					2.By setting num = 1, we initialize num to a value that indicates arr1 being larger. 
					  This is a starting point for the comparison.
					3.Inside the loop that follows (for loop), the code compares the elements of arr1 and arr2. 
					  If any element is different, the value of num is adjusted to reflect the difference between those elements, effectively providing the proper comparison result.
			The loop breaks as soon as a difference is found and num is adjusted.
		So, by setting num = 1 initially, the code establishes a default comparison result assuming arr1 is larger than arr2. This allows the loop to fine-tune the comparison result based on the actual differences between elements in the arrays.
 */
