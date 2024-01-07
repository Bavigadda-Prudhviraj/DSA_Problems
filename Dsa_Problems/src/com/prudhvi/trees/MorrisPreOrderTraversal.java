package com.prudhvi.trees;

import java.util.ArrayList;

public class MorrisPreOrderTraversal {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		ArrayList<Integer> preOrderTraversalArrayList=morrisPreOrderTraversal(root);
		System.out.println(preOrderTraversalArrayList);

	}
	/*
	Time complexity: Morris Traversal allows us to achieve O(n) time complexity for tree traversals, where n is the number of nodes in the binary tree. 
	   				 This is because each edge is traversed twice (once for creating threads and once for traversal), and the while loop runs in O(n) time.

	Space complexity: The space complexity is O(1) because the algorithm uses a constant amount of extra space, mainly for the temp variable and the result ArrayList. 
					  The space required does not depend on the size of the input tree.
	 */
	public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root){
		// Initialize an ArrayList to store the result.
	    ArrayList<Integer> arr = new ArrayList<>(); 
	    while (root != null) { // Continue the loop until we reach the end of the tree.
	    	// If the left child is null, visit the current node and move to the right child.
	        if (root.left == null) { 
	            arr.add(root.val);
	            root = root.right;
	        } else { // If the left child is not null, perform Morris traversal.
	            TreeNode temp = root.left; // Store the left child in a temporary variable.

	            while (temp.right != null && temp.right != root) {
	                // Traverse to the rightmost node of the left subtree or until a thread is found.
	                temp = temp.right;
	            }

	            if (temp.right == null) { // If a thread is not found, create a thread and move to the left child.
	                arr.add(root.val);
	                temp.right = root;
	                root = root.left;
	            } else { // If a thread is found, break the thread and move to the right child.
	                root = root.right;
	            }
	        }
	    }

	    return arr; // Return the result ArrayList.
	}


}
