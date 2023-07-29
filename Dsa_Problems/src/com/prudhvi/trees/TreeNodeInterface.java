package com.prudhvi.trees;

import java.util.ArrayList;

public interface TreeNodeInterface {
	
	public  int size();
	public TreeNode preOrder(ArrayList<Integer> arr);
	public TreeNode inOrder(ArrayList<Integer> arr);
	public TreeNode postOrder(ArrayList<Integer> arr);
	public int height();
	public int depth();
}
