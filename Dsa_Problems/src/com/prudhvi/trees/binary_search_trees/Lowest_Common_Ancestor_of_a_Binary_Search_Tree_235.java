package com.prudhvi.trees.binary_search_trees;

public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree_235 {
	/**
     * Returns the lowest common ancestor (LCA) of nodes p and q in the given BST.
     *
     * @param root The root node of the BST
     * @param p First target node
     * @param q Second target node
     * @return TreeNode representing the LCA
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Case 1: If both p and q are smaller than root, recurse on left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Case 2: If both p and q are greater than root, recurse on right subtree
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Case 3: If p and q are on opposite sides (or one equals root), root is the LCA
        else {
            return root;
        }
    }
     public class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    	  }

}
