package com.prudhvi.trees;
/*
Given a Binary Tree with all unique values and two nodes value, n1 and n2. The task is to find the lowest common ancestor of the given two nodes. We may assume that either both n1 and n2 are present in the tree or none of them are present.

LCA: It is the first common ancestor of both the nodes n1 and n2 from bottom of tree.
 */
public class LowestCommonAncestorInBinaryTree {

	/**
	 * Finds the Lowest Common Ancestor (LCA) of two nodes in a binary tree.
	 *
	 * @param root The root of the binary tree.
	 * @param n1   The value of the first node.
	 * @param n2   The value of the second node.
	 * @return The LCA of the two nodes.
	 *
	 * Time Complexity: O(N) - N is the number of nodes in the binary tree.
	 * Space Complexity: O(H) - H is the height of the binary tree (recursion stack space).
	 */
	public Node lca(Node root, int n1, int n2) {
	    // Base case: If the root is null, return null.
	    if (root == null)
	        return null;

	    // If the current root data matches either of the nodes, it is the LCA.
	    if (root.data == n1 || root.data == n2)
	        return root;

	    // Recursively find the LCA in the left and right subtrees.
	    Node left = lca(root.left, n1, n2);
	    Node right = lca(root.right, n1, n2);

	    // If both left and right are not null, the current root is the LCA.
	    if (left != null && right != null)
	        return root;
	    // If right is null and left is not null, the LCA is in the left subtree.
	    else if (right == null && left != null)
	        return left;
	    // If left is null and right is not null, the LCA is in the right subtree.
	    else
	        return right;
	}

	
	
	
	
	
	class Node
	{
	    int data;
	    Node left, right;

	    Node(int item)
	    {
	        data = item;
	        left = right = null;
	    }
	}

}
