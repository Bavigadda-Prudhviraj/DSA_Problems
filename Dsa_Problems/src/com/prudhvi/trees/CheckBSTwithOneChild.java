package com.prudhvi.trees;

public class CheckBSTwithOneChild {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		String answer=nodeWithOneChild(root);
	}
	public static String nodeWithOneChild(TreeNode root) {
		return null;
		
	}

}
