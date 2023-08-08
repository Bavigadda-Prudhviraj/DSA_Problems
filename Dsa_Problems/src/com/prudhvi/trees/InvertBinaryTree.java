package com.prudhvi.trees;

import java.util.ArrayList;

public class InvertBinaryTree {
	/*
	Problem Description
		Given a binary tree A, invert the binary tree and return it.
		Inverting refers to making the left child the right child and vice versa.
	Problem Constraints
		1 <= size of tree <= 100000
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		TreeNode rootNode=invertGivenBinaryTree(root);
		ArrayList<Integer> arrayList=new ArrayList<>();
		InorderTraversal.inorderTraversal(rootNode,arrayList);
		System.out.println(arrayList);
	}
	/*
	This code defines a method invertGivenBinaryTree that takes a root node of a binary tree as input and returns the same binary tree with its structure inverted. 
	In other words, it swaps the left and right children for each node in the binary tree. 
	The inversion is performed using a post-order traversal approach.
	
	Time Complexity:
			The code visits each node in the binary tree once, performing constant time operations for each node. 
			Therefore, the time complexity is O(N), where N is the number of nodes in the tree.
	
	Space Complexity:
			The space complexity is determined by the call stack during the recursive traversal. 
			In the worst case, the maximum depth of the call stack would be the height of the tree, which could be O(N) for a skewed tree. 
			Hence, the space complexity is O(N) in the worst case.
	 */
	public static TreeNode invertGivenBinaryTree(TreeNode root) {
		//This is the main method that takes a root node of a binary tree as a parameter and returns the inverted binary tree. 
		//It calls the postOrderTraversal method to perform the inversion.
		postOrderTraversal(root);
		return root;
	}
	//This is a private helper method that performs a post-order traversal of the binary tree and performs the inversion by swapping the left and right children of each node.
	private static void postOrderTraversal(TreeNode root) {
		//This condition checks if the current root node is null. If it is, the method returns, as there's nothing to invert for a null node.
		if(root==null) {
			return;
		}
		//This recursively calls the postOrderTraversal method on the left child of the current root node. 
		//This ensures that the left subtree is fully inverted before inverting the right subtree.
		postOrderTraversal(root.left);
		//Similarly, this recursively calls the postOrderTraversal method on the right child of the current root node.
		postOrderTraversal(root.right);
		//This line creates a temporary tempNode to store the reference to the left child of the current root node before the inversion.
		TreeNode tempNode=root.left;
		root.left=root.right;//This line swaps the left child of the current root node with its right child, effectively inverting the children.
		root.right=tempNode;//This line assigns the original left child (stored in tempNode) to the right child of the current root node.
		//Finally, the method invertGivenBinaryTree returns the root node of the inverted binary tree.
		
	}

}
