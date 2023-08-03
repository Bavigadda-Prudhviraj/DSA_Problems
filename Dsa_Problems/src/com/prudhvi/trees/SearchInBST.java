package com.prudhvi.trees;

public class SearchInBST {
	/*
	Problem Description
		Given a Binary Search Tree A. Check whether there exists a node with value B in the BST.
	Problem Constraints
		1 <= Number of nodes in binary tree <= 105
		0 <= B <= 106
	Input Format
		First argument is a root node of the binary tree, A.
		Second argument is an integer B.
	Output Format
		Return 1 if such a node exist and 0 otherwise
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int answer=isTargetInBST(root,1);
		System.out.println(answer);
	}
	/*
	The isTargetInBST method takes the root of a binary search tree (BST) and a target value as input, and it checks if the target value is present in the BST. 
	It uses a recursive approach to traverse the tree and find the target value.
	
	Time Complexity:
			The time complexity of the isTargetInBST method is O(H), where H is the height of the binary search tree. 
			In the worst case, when the binary tree is skewed, the height of the tree can be N, where N is the number of nodes in the tree. 
			However, in the best case, when the binary tree is balanced, the height is log N, where log N is the logarithm of N to the base 2.
	Space Complexity:
			The space complexity of the isTargetInBST method is O(H) in the worst case due to the recursive call stack. 
			In the best case, the space complexity is O(log N) for a balanced binary tree. 
			In the worst case, when the binary tree is skewed, the space complexity can be O(N).
	 */
	public  static int isTargetInBST(TreeNode root,int target){
		// If the current node is null (reached the end of the tree), return 0, indicating that the target value is not found in the BST.
		if(root==null) {
			return 0;
		}
		//If the value of the current node is equal to the target value, return 1, indicating that the target value is found in the BST.
		if(root.val==target) {
			return 1;
		}
		//If the value of the current node is less than the target value, it means that the target value (if present) lies in the right subtree. 
		//So, recursively call the isTargetInBST function with the right subtree as the new root.
		else if(root.val<target) {
			return isTargetInBST(root.right, target);
		}
		//If the value of the current node is greater than the target value, it means that the target value (if present) lies in the left subtree. 
		//So, recursively call the isTargetInBST function with the left subtree as the new root.
		else{
			return isTargetInBST(root.left, target);
		}
		
	}

}
