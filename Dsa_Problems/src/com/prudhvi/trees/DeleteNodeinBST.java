package com.prudhvi.trees;

import java.util.ArrayList;

public class DeleteNodeinBST {
	/*
	Problem Description
		Given a Binary Search Tree(BST) A. If there is a node with value B present in the tree delete it and return the tree.
		Note - If there are multiple options, always replace a node by its in-order predecessor
	Problem Constraints
		2 <= No. of nodes in BST <= 105
		1 <= value of nodes <= 109
		Each node has a unique value
	Input Format
		The first argument is the root node of a Binary Search Tree root.
		The second argument is the value target.
	Output Format
		Delete the given node if found and return the root of the BST.
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		
		TreeNode updatedRoot=deleteNode(root,10);
		ArrayList<Integer> nodesArrayList=InorderTraversal.inorderTraversal(updatedRoot);
		System.out.println(nodesArrayList);
	}
	/*
	The deleteNode method is used to delete a node with a given target value from a binary search tree (BST). 
	The method takes the root of the BST and the target value as input and returns the root of the modified BST after the deletion.
	
	Time Complexity:
			The time complexity of the deleteNode method is O(H), where H is the height of the BST. 
			In the worst case, the height of the BST can be N (number of nodes), 
			but in a balanced BST, the height can be log(N) in the best case.
	Space Complexity:
			The space complexity is O(H) as well, considering the recursive stack space used during the recursion. 
			In the worst case, the space complexity can be O(N), and 
			in the best case, it can be O(log(N)) for a balanced BST.
	 */
	public static TreeNode deleteNode(TreeNode root,int target){
		//If the value of the current node (root) is greater than the target value, move to the left subtree and recursively call deleteNode on the left child of the current node.
		if(root.val>target) {
			root.left=deleteNode(root.left, target);
		}
		// If the value of the current node (root) is less than the target value, move to the right subtree and recursively call deleteNode on the right child of the current node.
		else if (root.val<target){
			root.right=deleteNode(root.right, target);
		}
		//If the value of the current node (root) is equal to the target value, it means we have found the node to delete.
		else {
			//Check three cases for deletion:
			//a. If the node to delete has no children (both left and right are null), return null to remove the node from the tree.
			if(root.left==null && root.right==null) {
				return null;
			}
			//If the node to delete has only one child (either left or right is null), return the non-null child to link it to its parent.
			else if(root.left==null) {
				return root.right;
			}
			else if (root.right==null) {
				return root.left;
			}
			// If the node to delete has both left and right children, 
			//we find the maximum value node in the left subtree (maximum value in the left subtree is the rightmost node of the left subtree). 
			//We then replace the value of the current node with the value of the maximum node found, and then recursively call deleteNode on the left subtree to delete the duplicate node (maximum node).
			else {
				int maxOfLeftFromCurrentNode=findMax(root.left);
				root.val=maxOfLeftFromCurrentNode;
				root.left=deleteNode(root.left,maxOfLeftFromCurrentNode);
			}
		}
		//After deleting the target node or replacing its value, return the modified root of the BST.
		return root;
		
	}
	//The findMax method is used to find the maximum value node in a BST. It takes the root of the BST as input and returns the value of the maximum node.
	public static int findMax(TreeNode root){
		//In the findMax method, we traverse the BST towards the right subtree until we reach the rightmost node, which contains the maximum value in the BST.
		while(root.right!=null){
			root=root.right;
		}
		return root.val;
	}
}
