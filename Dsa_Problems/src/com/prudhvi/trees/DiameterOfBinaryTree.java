package com.prudhvi.trees;

public class DiameterOfBinaryTree {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int diameterOfBT=diameterOfBT(root);
		System.out.println(diameterOfBT);

	}
	public static int diameter;
	private static int diameterOfBT(TreeNode root) {
		int heightOfEntireTree=heightOfBinaryTree(root);
		return diameter;
	}

	private static int heightOfBinaryTree(TreeNode root) {
		if(root==null) {
			return -1;
		}
		int leftChildHeight=heightOfBinaryTree(root.left);
		int rightCjildHeight=heightOfBinaryTree(root.right);
		diameter=Math.max(diameter, rightCjildHeight+leftChildHeight+2);
		return Math.max(leftChildHeight, rightCjildHeight)+1;
	}

}
