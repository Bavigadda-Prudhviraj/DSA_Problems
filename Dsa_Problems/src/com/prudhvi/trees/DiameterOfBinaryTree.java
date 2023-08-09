package com.prudhvi.trees;

public class DiameterOfBinaryTree {
	/*
	Problem Description
			Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
	Problem Constraints
			1 <= number of nodes <= 105
			-100000 <= B, value of nodes <= 100000
*/
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int diameterOfBT=diameterOfBT(root);
		System.out.println(diameterOfBT);

	}
	/*
	This code calculates the diameter of a binary tree, which is defined as the length of the longest path between any two nodes in a tree. 
	The path doesn't necessarily pass through the root node. 
	The code uses a recursive approach and calculates both the height of the binary tree and the diameter simultaneously.
	
	Time Complexity:
			In both the best case and worst case, each node is visited only once during the recursive traversal of the binary tree.
					1.Best case:when the binary tree is balanced, the heightOfBinaryTree function will have a time complexity of O(n), where 'n' is the number of nodes in the tree. 
					  This is because each node is visited once, and the recursion depth is proportional to the height of the tree.
					2.worst case, when the binary tree is completely unbalanced (essentially a linked list), the heightOfBinaryTree function will still have a time complexity of O(n). 
					  Even though the tree is unbalanced, each node is still visited only once, and the recursion depth is still proportional to the height of the tree (which is 'n' in this case).
					Therefore, the time complexity of both the heightOfBinaryTree and diameterOfBT functions is O(n) in both the best and worst cases.
	Space Complexity:
			The space complexity is determined by the recursion stack used for the function calls.
					1.Best case (balanced tree):The maximum depth of the recursion stack is O(log n), where 'n' is the number of nodes in the tree. 
					  This is because the binary tree is balanced, and the maximum number of recursive calls is proportional to the height of the tree.
					2.worst case (unbalanced tree):the maximum depth of the recursion stack is O(n), where 'n' is the number of nodes in the tree. 
					  This is because the tree is unbalanced, and the recursion depth can become equal to the number of nodes in the tree.
					The additional space used for variables and computations within each recursive call is constant and doesn't depend on the input size.
								Best Case Time Complexity: O(n)
								Worst Case Time Complexity: O(n)
								Best Case Space Complexity: O(log n)
								Worst Case Space Complexity: O(n)
	 */
	//This static variable diameter is used to store the calculated diameter of the binary tree. It's initialized to 0.
	public static int diameter=0;
	private static int diameterOfBT(TreeNode root) {
		//calls the heightOfBinaryTree method, which calculates the height of the binary tree and updates the diameter variable.
		heightOfBinaryTree(root);
		//After calculating the diameter, the method returns the value of the diameter variable.
		return diameter;
	}
	//This is a helper method responsible for calculating the height of the binary tree and updating the diameter.
	private static int heightOfBinaryTree(TreeNode root) {
		//This is the base case of the recursion. If the current root node is null, the method returns 0.
		if(root==null) {
			return 0;
		}
		//The code recursively calculates the heights of the left and right subtrees using a post-order traversal.
		int leftChildHeight=heightOfBinaryTree(root.left);
		int rightCjildHeight=heightOfBinaryTree(root.right);
		//This line updates the diameter variable by calculating the potential diameter passing through the current node. 
		//It takes the maximum of the current diameter and the sum of heights of the left and right subtrees plus 2 (2 represents the edge lengths between the nodes).
		diameter=Math.max(diameter, rightCjildHeight+leftChildHeight+2);
		//returns the height of the current subtree by computing Math.max(leftChildHeight, rightChildHeight) + 1.
		return Math.max(leftChildHeight, rightCjildHeight)+1;
	}

}
