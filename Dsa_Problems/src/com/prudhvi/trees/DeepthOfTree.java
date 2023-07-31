package com.prudhvi.trees;



public class DeepthOfTree {

	public static void main(String[] args) {
		TreeNodeDepth root=new TreeNodeDepth(8, 0);
		depthOfEveryNode(root, 0);
	}
	/*
	The provided code defines a recursive method depthOfEveryNode that calculates the depth of each node in a binary tree and stores it in a field depth of the TreeNodeDepth class. 
	The method is called with the root node of the binary tree and an initial depth of 0.
	
	Time Complexity:
		The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
		In the worst case, the code visits all nodes in the binary tree to calculate and store the depth for each node.

	Space Complexity:
		The space complexity of the code is O(H), where H is the height of the binary tree. 
		In the worst case, the maximum space used by the call stack is equal to the height of the binary tree, as the recursive calls are made down the height of the tree. 
		In the best-case scenario, the space complexity would be O(log N) for a balanced binary tree, where log N is the height of the balanced binary tree. 
		However, for a skewed binary tree, the space complexity would be O(N), where N is the number of nodes in the tree.
	
	 */
	public static void depthOfEveryNode(TreeNodeDepth root,int depth) {
		//The first conditional check if (root == null) verifies if the current node (root) is null. 
		///If it is null, it means the current subtree is empty, and there's no node to calculate the depth for. 
		//Thus, the method returns, terminating the recursion for that subtree.
		if(root==null)
			return;
		//The  rootNodeDepth = root.depth; line retrieves the depth value associated with the current node (root). 
		//This depth value is initially set when constructing the TreeNodeDepth object and represents the depth of the node in the tree.
		int rootNodeDepth=root.depth;
		//The two recursive calls depthOfEveryNode(root.left, rootNodeDepth++); and depthOfEveryNode(root.right, rootNodeDepth++); are used to traverse the left and right subtrees of the current node, respectively. 
		//The rootNodeDepth value is incremented by one before being passed to the recursive calls to represent the increased depth in the subtrees.
		depthOfEveryNode(root.left, rootNodeDepth++);
		depthOfEveryNode(root.right, rootNodeDepth++);
	}

}
class TreeNodeDepth{
	int val;
	int depth;
	TreeNodeDepth left;
	TreeNodeDepth right;
	public TreeNodeDepth(int val, int depth) {
		super();
		this.val = val;
		this.depth = depth;
		this.left = null;
		this.right = null;
	}
	
}