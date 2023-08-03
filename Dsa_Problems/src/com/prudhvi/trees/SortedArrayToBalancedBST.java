package com.prudhvi.trees;

import java.util.ArrayList;

public class SortedArrayToBalancedBST {
	/*
	Problem Description
		Given an array where elements are sorted in ascending order, convert it to a height Balanced Binary Search Tree (BBST).
		Balanced tree : 
				a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
	Problem Constraints
		1 <= length of array <= 100000
	Input Format
		First argument is an integer array A.
	Output Format
		Return a root node of the Binary Search Tree.
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=sortedArrayToBalancedBST(arr);
		ArrayList<Integer> arrayList=InorderTraversal.inorderTraversal(root);
		System.out.println(arrayList);

	}
	/*
	The sortedArrayToBalancedBST method takes a sorted integer array as input and returns the root of a balanced binary search tree (BST) created from the array. 
	The method calls the convertSortedArrayToBalancedBST helper function to build the balanced BST recursively.
	
	Time Complexity:
			The time complexity of the sortedArrayToBalancedBST method is O(N), where N is the number of elements in the input array. 
			The method creates a balanced BST by performing binary search and constructing the tree in a top-down recursive manner.
	
	Space Complexity:
			The space complexity is O(log N) in the best and average cases when the BST is perfectly balanced. 
			This is because the recursion depth will be equal to the height of a balanced BST, which is logarithmic to the number of nodes. 
			However, in the worst case, when the input array is already sorted in ascending or descending order, the BST will be unbalanced, and the space complexity can be O(N) due to the skewed recursive calls.
	 */
	public static TreeNode sortedArrayToBalancedBST(int[] arr) {
		//Initialize start and end variables to represent the range of elements in the input sorted array.
		int start=0;
		int end=arr.length-1;
		//Call the helper function convertSortedArrayToBalancedBST with the entire array to build the balanced BST.
		TreeNode root=convertSortedArrayToBalancedBST(arr,start,end);
		// Return the root of the balanced BST.
		return root;
		
	}
	private static TreeNode convertSortedArrayToBalancedBST(int[] arr, int start, int end) {
		// If the start index exceeds the end index, return null to indicate an empty subtree.
		if(start>end) {
			return null;
		}
		//Calculate the middle index of the current range to create the root of the subtree.
		int midIndex=start+(end-start)/2;
		//Create a new TreeNode with the value at the middle index as the root of the current subtree.
		TreeNode root=new TreeNode(arr[midIndex]);
		//Recursively build the left subtree by calling convertSortedArrayToBalancedBST with the range start to midIndex - 1.
		//Attach the left and right subtrees to the root node.
		root.left=convertSortedArrayToBalancedBST(arr, start, midIndex-1);
		//Recursively build the right subtree by calling convertSortedArrayToBalancedBST with the range midIndex + 1 to end.
		//Attach the left and right subtrees to the root node.
		root.right=convertSortedArrayToBalancedBST(arr, midIndex+1, end);
		//Return the root of the current subtree.
		return root;
	}

}
