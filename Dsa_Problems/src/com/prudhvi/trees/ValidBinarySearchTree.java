package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class ValidBinarySearchTree {
	/*
	Problem Description
		You are given a binary tree represented by root A. You need to check if it is a Binary Search Tree or not.
		Assume a BST is defined as follows:
			1) The left subtree of a node contains only nodes with keys less than the node's key.
			2) The right subtree of a node contains only nodes with keys greater than the node's key.
			3) Both the left and right subtrees must also be binary search trees.
		Problem Constraints
			1 <= Number of nodes in binary tree <= 105
			0 <= node values <= 232-1
		Input Format
			First and only argument is head of the binary tree A.
		Output Format
			Return 0 if false and 1 if true.
	 */
	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		int answer=isvalidBST(root);
		System.out.println(answer);

	}
	/*
	
	Time Complexity:
			The time complexity of the isvalidBST method is O(N), where N is the number of nodes in the binary tree. 
			Each node is visited once to check if the BST property is satisfied.
	
	Space Complexity:
			The space complexity of the isvalidBST method is O(N) in the worst case, where the binary tree is skewed and has a height equal to the number of nodes. 
			This is because the function may recursively call itself for each node in the tree, resulting in a maximum recursion depth of N. 
			In the best case, when the binary tree is balanced, the space complexity is O(log N), where log N is the height of the balanced binary tree.
	 */
	public static int isvalidBST(TreeNode root) {
		//Initialize start and end variables with the minimum and maximum possible integer values, respectively. 
		//These values represent the valid range for the root node and its children.
		int start=Integer.MIN_VALUE;
		int end=Integer.MAX_VALUE;
		//Call the helper function checkIsBST with the root of the binary tree and the range start to end to determine if it is a valid BST.
		int isBST=checkIsBST(root,start,end);
		//Return the result of the checkIsBST function, which will be 1 if the binary tree is a valid BST and 0 if it is not.
		return isBST;
		
	}
	public static int checkIsBST(TreeNode root,int start,int end){
		//If the current node is null (reached the end of a subtree), return 1, indicating that the subtree is a valid BST.
		if(root==null) {
			return 1;
		}
		//If the value of the current node is within the valid range, proceed to check its left and right subtrees.
		else if(start<=root.val && root.val<=end) {
			//Recursively call checkIsBST for the left subtree, passing the current start value and root.val - 1 as the new end value.
			int rootLeft=checkIsBST(root.left, start, root.val-1);
			//If the rootLeft subtree is not a valid BST (indicated by 0), return 0, as the whole tree is not a valid BST.
			if(rootLeft==0) {
				return 0;
			}
			//Recursively call checkIsBST for the right subtree, passing root.val + 1 as the new start value and the current end value.
			int rootRight=checkIsBST(root.right, root.val+1, end);
			//If the rootRight subtree is not a valid BST (indicated by 0), return 0, as the whole tree is not a valid BST.
			if(rootRight==0) {
				return 0;
			}
		// If the current node's value is not within the valid range, return 0, indicating that the current subtree is not a valid BST.
		}else {
			return 0;
		}
		//If both left and right subtrees are valid BSTs and the current node's value is within the valid range, return 1, indicating that the current subtree is a valid BST.
		return 1;
	}

}
