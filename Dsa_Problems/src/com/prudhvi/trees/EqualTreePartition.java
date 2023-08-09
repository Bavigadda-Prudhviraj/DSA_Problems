package com.prudhvi.trees;
public class EqualTreePartition {
	/*
	Problem Description
			Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal sum of values after removing exactly one edge on the original tree.
	Problem Constraints
			1 <= size of tree <= 100000
			0 <= value of node <= 109
	Output Format
			Return true if the tree can be partitioned into two trees of equal sum else return false.
	 */
	public static void main(String[] args) {
		int[] arr= {999999518,999999649,-1,999999906,999999844,999999529,999999514,999999806,999999654,-1,-1,999999900,999999963,999999608,-1,999999830,-1,999999560,-1,-1,-1,-1,999999632,-1,999999855,999999508,-1,-1,-1,-1,-1,999999669,-1,-1,999999677,-1,999999886,999999523,999999657,-1,-1,-1,999999974,-1,-1};
		TreeNode root=DeserializeBinaryTree.deserializeBinaryTree(arr);
		boolean answer=canTreeDivideIntoTwoEqualHalf(root);
		System.out.println(answer);
		
		

	}
	/*
	This code aims to determine whether a binary tree can be divided into two equal halves by removing a single edge. The goal is to find a node in the tree such that removing its edge separates the tree into two subtrees with equal sum of node values. The code uses a combination of pre-order traversal and recursive calculations to achieve this.
	
	Time Complexity:
			Best Case Scenario (Balanced Binary Tree): O(N)
			Worst Case Scenario (Skewed Binary Tree): O(N)
	Space Complexity:
			The space complexity varies based on the height of the binary tree
				Best Case Scenario (Balanced Binary Tree): O(log N)
				Worst Case Scenario (Skewed Binary Tree): O(N)
	 */
    public static boolean canTreeDivideIntoTwoEqualHalf(TreeNode A) {
    	//This line calls the preOrderTraversal method to calculate the total sum of node values in the binary tree.
        preOrderTraversal(A);
        //This condition checks if the total sum of node values is odd. If it's odd, it's impossible to split it into two equal halves, so the method returns false.
        if(sum%2!=0){
            return false;
        }
        else{
        	//calls the checkIsPathExistWithSum method to perform the actual traversal to find a path with the required sum.
            checkIsPathExistWithSum(A);
            //After the traversal is done, the method returns true if isTrue is equal to 1, indicating that a valid path with the required sum was found; otherwise, it returns false.
             return isTrue==1;
        }
    }
    static long sum=0;//This static variable sum is used to store the sum of all node values in the binary tree.
    static int isTrue=0;//This static variable isTrue is used to track whether a valid path with the required sum is found (1) or not (0).
    //This is a helper method responsible for performing the traversal to check if a valid path with the required sum exists.
    private static long checkIsPathExistWithSum(TreeNode root) {
    	//This is the base case of the recursion. If the current root node is null, the method returns 0.
		if(root==null) {
			return 0;
		}
		//The code recursively calculates the sums of the left and right subtrees and updates the isTrue variable if a valid path is found with one of the subtrees having the required sum.
		long leftChildSum=checkIsPathExistWithSum(root.left);
		long rightChildSum=checkIsPathExistWithSum(root.right);
		//updates the isTrue variable if a valid division into equal halves is found with one of the subtrees.
		if(leftChildSum==sum/2 || rightChildSum==sum/2){
			isTrue=1;
		}
		
		//The method returns the sum of the current node's value and the values from its left and right subtrees.
		return root.val+leftChildSum+rightChildSum;
	}
    public static void preOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        sum+=(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

}
