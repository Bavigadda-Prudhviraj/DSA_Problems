package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class BalancedBinaryTree {
	/*
	Problem Description
		Given a root of binary tree A, determine if it is height-balanced.
		A height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
	Problem Constraints
		1 <= size of tree <= 100000
	Input Format
		First and only argument is the root of the tree A.
	Output Format
		SReturn 0 / 1 ( 0 for false, 1 for true ) for this problem.
	 */
	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		int answer=PairBBT.isBinaryTreeBalanced(root)?1:0;
		System.out.println(answer);
		

	}

}
class PairBBT{
	int height;
	boolean isBalanced;
	public PairBBT(int height,boolean isBalanced) {
		this.height=height;
		this.isBalanced=isBalanced;
	}
	/*
	The provided code aims to determine whether a binary tree is balanced or not. 
	Note:-A binary tree is considered balanced if the difference in height between its left and right subtrees is at most 1 for every node in the tree.
		  Balanced Tree=|root.left.height-root.right.height|<=1;
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, the code needs to visit all nodes in the binary tree to check whether it is balanced.

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			This is because the maximum space used by the call stack during the recursive calls is equal to the height of the binary tree. 
			In the best-case scenario, the binary tree is balanced, and the height is log N, where N is the number of nodes in the tree. 
			However, in the worst case, for a skewed binary tree, the height would be N, resulting in a space complexity of O(N).
	 */
	public static boolean isBinaryTreeBalanced(TreeNode root){
		//Inside the isBinaryTreeBalanced method, it calls the checkTreeIsBalanced method to perform the actual checking and get the result.
		PairBBT answerBbt=checkTreeIsBalanced(root);
		return  answerBbt.isBalanced;
		
	}
	//The checkTreeIsBalanced method is a helper method that recursively checks whether a binary tree starting from the given root is balanced or not.
	//The checkTreeIsBalanced method returns a custom class PairBBT, which holds two pieces of information: the height of the subtree rooted at the current node and a boolean value indicating whether the subtree is balanced.
	private static PairBBT checkTreeIsBalanced(TreeNode root) {
		//if the root is null, it means the current subtree is empty, so it returns a PairBBT object with height 0 and isBalanced set to true.
		if(root==null) {
			return new PairBBT(0, true);
		}
		//it recursively checks the left and right subtrees using PairBBT leftChild = checkTreeIsBalanced(root.left) and PairBBT rightChild = checkTreeIsBalanced(root.right).
		PairBBT leftChild=checkTreeIsBalanced(root.left);
		PairBBT rightChild=checkTreeIsBalanced(root.right);
		//If either the left or right subtree is not balanced, it returns a PairBBT object with height -1 and isBalanced set to false, indicating that the entire tree is not balanced.
		if(!leftChild.isBalanced|| !rightChild.isBalanced) {
			return new PairBBT(-1, false);
		}
		//If the height difference between the left and right subtrees is greater than 1, it also returns a PairBBT object with height -1 and isBalanced set to false.
		if(Math.abs(leftChild.height-rightChild.height)>1) {
			return new PairBBT(-1, false);
		}
		//If the subtree is balanced, it calculates the height of the current node as 1 + Math.max(leftChild.height, rightChild.height). 
		//It then returns a PairBBT object with this calculated height and isBalanced set to true.
		else {
			int height=1+Math.max(leftChild.height, rightChild.height);
			return new PairBBT(height, true);
		}
	}
}
