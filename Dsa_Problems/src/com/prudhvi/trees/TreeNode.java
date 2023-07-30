package com.prudhvi.trees;

import java.util.ArrayList;

public  class TreeNode  implements TreeNodeInterface{
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) {
		this.val=val;
		this.left=null;
		this.right=null;
	}

	@Override
	public int size() {
		
		return 0;
	}

	public TreeNode preOrder(ArrayList<Integer> arr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode inOrder(ArrayList<Integer> arr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode postOrder(ArrayList<Integer> arr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int depth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}
	
	
}
