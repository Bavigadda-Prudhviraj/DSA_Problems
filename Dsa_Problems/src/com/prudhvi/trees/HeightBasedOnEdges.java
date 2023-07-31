package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class HeightBasedOnEdges {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		int heigth=heightBasedOnEdges(root);
		System.out.println(heigth);


	}

	private static int heightBasedOnEdges(TreeNode root) {
		if(root==null) {
			return -1;
		}
		int leftChildHeight=heightBasedOnEdges(root.left);
		int rightChildHeight=heightBasedOnEdges(root.right);
		return Math.max(leftChildHeight, rightChildHeight)+1;
	}

}
