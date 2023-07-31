package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class SizeOfTree {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		int size=treeSize(root);
		System.out.println(size);

	}
	/*
	The provided code is a recursive method to calculate the size of a binary tree, i.e., the total number of nodes in the tree. 
	It counts all the nodes in the tree rooted at the given root node
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, the code may need to visit all nodes in the binary tree to calculate the total size.

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			This is because the maximum space used by the call stack is equal to the height of the binary tree during the recursive calls. 
			In the best-case scenario, the binary tree is balanced, and the height is log N, where N is the number of nodes in the tree. 
			However, in the worst case, for a skewed binary tree, the height would be N, resulting in a space complexity of O(N).
	 */
	public static int treeSize(TreeNode root){
		//The first conditional check if (root == null) verifies if the current node (root) is null. 
		//If it is null, it means the current subtree is empty, and the method returns 0, indicating that the size of the current subtree is 0.
		if(root==null) {
			return 0;
		}
		//If the current node is not null, the method proceeds with the recursive calculations.
		
		//The treeSize method recursively calculates the size of the left subtree by calling itself with the left child node of the current node (treeSize(root.left)). 
		//The result is stored in the leftChildCount variable.
		int leftChildCount=treeSize(root.left);
		//Similarly, the method recursively calculates the size of the right subtree by calling itself with the right child node of the current node (treeSize(root.right)). 
		//The result is stored in the rightChildCount variable.
		int rightChildCount=treeSize(root.right);
		//The size of the current subtree is determined by adding 1 to the sum of the sizes of its left and right subtrees, along with the current node. 
		//Thus, the total size of the binary tree is returned.
		return (1+leftChildCount+rightChildCount);
		//The recursive calls continue until all nodes of the binary tree have been visited, and the size of the entire binary tree is calculated.
	}

}
