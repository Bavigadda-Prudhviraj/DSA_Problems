package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckKinTree {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		boolean answer=checkKinTree(root,11);
		System.out.println(answer);

	}
	/*
	The code checks if a given target value is present in a binary tree with the given root node. 
	It uses a recursive approach to traverse the tree and check if the target value is equal to the value of any node in the tree.

	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, the code may need to visit all nodes in the binary tree to find the target value. 
			However, in the best-case scenario, the target value could be found in the root node, resulting in a time complexity of O(1).

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			In the worst case, the maximum space used by the call stack is equal to the height of the binary tree, as the recursive calls are made down the height of the tree. 
			In the best case, for a skewed binary tree, the space complexity would be O(N), where N is the number of nodes in the tree. 
			For a balanced binary tree, the space complexity would be closer to O(log N), where log N is the height of a balanced binary tree.
	 */
	public static boolean checkKinTree(TreeNode root,int target) {
		//The first conditional check if (root == null) checks if the current node is null. 
		//If it is, it means the current subtree is empty, and the target value is not found. 
		//Hence, the method returns false.
		if(root==null) {
			return false;
		}
		//The second conditional check if (root.val == target) compares the value of the current node (root.val) with the target value. 
		//If they are equal, it means the target value is found in the current subtree, and the method returns true.
		if(root.val==target) {
			return true;
		}
		//The method returns the result of a recursive call on both the left and right subtrees using the logical OR operator ||. 
		//The recursive calls checkKinTree(root.left, target) and checkKinTree(root.right, target) check if the target value is present in the left and right subtrees, respectively. 
		//The || operator ensures that if the target value is found in either the left or right subtree, the method returns true.
		return (checkKinTree(root.left, target) || checkKinTree(root.right, target) );
	}

}
