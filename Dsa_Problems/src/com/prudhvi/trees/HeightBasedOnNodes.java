package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class HeightBasedOnNodes {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		int heigth=heightBasedOnNodes(root);
		System.out.println(heigth);


	}
	/*
	The code is a recursive method to calculate the height of a binary tree based on nodes (the number of nodes from the root to the deepest leaf node). 
	The height of the tree is defined as the number of nodes on the longest path from the root to a leaf node.
	
	Time Complexity:
		The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
		In the worst case, the code may need to visit all nodes in the binary tree to calculate the height.

	Space Complexity:
		The space complexity of the code is O(H), where H is the height of the binary tree. 
		This is because the maximum space used by the call stack is equal to the height of the binary tree during the recursive calls. 
		In the best-case scenario, the binary tree is balanced, and the height is log N, where N is the number of nodes in the tree. 
		However, in the worst case, for a skewed binary tree, the height would be N, resulting in a space complexity of O(N).
	 */
	private static int heightBasedOnNodes(TreeNode root) {
		//The first conditional check if root equals to null verifies if the current node (root) is null. 
		//If it is null, it means the current subtree is empty, and the height is defined as 0 (the number of nodes from the root to a null node is 0). 
		//Thus, the method returns 0, terminating the recursion for that subtree
		if(root==null) {
			return 0;
		}
		//The next two lines recursively calculate the height of the left and right subtrees. 
		//The recursive calls are made to heightBasedOnNodes(root.left) and heightBasedOnNodes(root.right), respectively, to calculate the height of the left and right subtrees, respectively.
		int leftChildHeight=heightBasedOnNodes(root.left);
		int rightChildHeight=heightBasedOnNodes(root.right);
		//The line return 1 + (Math.max(leftChildHeight, rightChildHeight)); calculates the height of the current node. 
		//The height of the current node is the maximum height between its left and right subtrees plus one. 
		//Note:-The '+1' represents the current node itself, which adds one node to the height.
		return 1+(Math.max(leftChildHeight, rightChildHeight));
	}

}
