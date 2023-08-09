package com.prudhvi.trees;

public class PathSumFromLeafNode {
	/*
	Problem Description
			Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
	Problem Constraints
			1 <= number of nodes <= 105
			-100000 <= B, value of nodes <= 100000
	*/
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		boolean answr=targetPathSumIsExist(root,17);
		System.out.println(answr);
	}
	/*
	The goal of the method is to determine whether there exists a path from the root to a leaf node in the binary tree such that the sum of node values along the path is equal to the given sum. 
	The method returns a boolean value indicating whether such a path exists.
	
	Time Complexity:
			Best Case Scenario (Balanced Binary Tree):
					1.In the best case scenario, the code will find the target path relatively early in the traversal. 
					  This could happen in the first few levels of the tree, so the algorithm will not need to traverse all nodes.
					2.The time complexity in this case is determined by the height of the tree. 
					  For a balanced binary tree, the height is approximately log₂(N), where N is the number of nodes.
					Therefore, the best-case time complexity is O(log N), where N is the number of nodes in the tree.
			Worst Case Scenario (Skewed Binary Tree):
					1.In the worst case scenario, the code might need to explore all nodes in the tree to find the path with the target sum.
					2.In this case, the code visits each node once, and the recursive calculations and updates take constant time.
					Therefore, the worst-case time complexity is O(N), where N is the number of nodes in the tree.
	Space Complexity:
			The space complexity is determined by the maximum depth of the recursive call stack during traversal. 
			This occurs in the worst-case scenario, which is when the tree is completely skewed (essentially a linked list).
			Best Case Scenario: The space complexity in the best case is the same as the height of the balanced tree, which is O(log N).
			Worst Case Scenario: The space complexity in the worst case is the maximum depth of the skewed tree, which is O(N).

	 */
	//This declares a static integer variable answer to keep track of whether a path with the desired sum exists. 
	//It is used to indicate the result of the path existence check
	static int answer=0;
	public  static boolean targetPathSumIsExist(TreeNode root,int sum){
		//This is the main method that clients will use to check whether a path with the target sum exists. 
		//It takes the root of the binary tree and the target sum as inputs.
		checkIsPathExistWithSum(root,sum);
		return answer==1;
		
	}
	/*
	The code uses a recursive approach to traverse the tree and check for a path with the target sum. 
	It starts from the root and recursively checks each node's left and right subtrees, subtracting the node's value from the remaining target sum.
	 */
	private static int checkIsPathExistWithSum(TreeNode root,int sum) {
		//This is the base case of the recursion. If the current root node is null, the method returns 0.
		if(root==null) {
			return 0;
		}
		//This condition checks if the current root node is a leaf node and its value is equal to the remaining sum. 
		//If this condition is true, it means a valid path with the target sum is found, so answer is set to 1, and 1 is returned.
		if(root.left==null && root.right==null && root.val==sum) {
			answer=1;
			return 1;
		}
		//This line calculates the sum of the path from the left child with the remaining sum - root.val and stores it in leftChildSum.
		int leftChildSum=checkIsPathExistWithSum(root.left, sum-root.val);
		//This line calculates the sum of the path from the right child with the remaining sum - root.val and stores it in rightChildSum.
		int rightChildSum=checkIsPathExistWithSum(root.right,sum-root.val);
		//Finally, the method returns the total sum of the path that passes through the current root node, including its value and the sums of paths from its left and right children.
		return root.val+leftChildSum+rightChildSum;
	}

}
