package com.prudhvi.trees;

import java.util.ArrayList;

public class KthSmallestElementInBSTUsingInorderTraversalArr {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int kthSmallestElement=kthsmallest(root, 5);
		System.out.println(kthSmallestElement);

	}
	/*
	The kthsmallest method finds the kth smallest element in a binary search tree (BST) using an In-order traversal approach. 
	It first performs an In-order traversal of the BST to store all elements in sorted order in the inOrderTraversalArrayList. 
	Then, it returns the kth element from the sorted list, which corresponds to the kth smallest element in the BST.
	
	Time Complexity:
			The time complexity of the kthsmallest method is O(n) because it first performs an In-order traversal of the entire BST (O(n) time complexity) and 
			then retrieves the kth smallest element from the inOrderTraversalArrayList (O(1) time complexity).
	
	Space Complexity:
			The space complexity of the method is O(n) because 
			it uses the inOrderTraversalArrayList to store all elements of the BST during In-order traversal, and 
			the size of the list will be equal to the number of nodes in the BST.
	 */
	public static int kthsmallest(TreeNode A, int B) {
		//Creates an ArrayList to store elements during the Inorder traversal.
        ArrayList<Integer> inOrderTraversalArrayList=new  ArrayList<>();
        //Calls the preOrder method to perform the In-order traversal of the BST. The In-order traversal will store elements in the inOrderTraversalArrayList in sorted order.
        preOrder(A,inOrderTraversalArrayList);
        // contains only one element. If so, it means the BST has only one node, and that node is the kth smallest element. 
        //In this case, it directly returns that element.
        if(inOrderTraversalArrayList.size()==1){
            return inOrderTraversalArrayList.get(0);
        }
        //Returns the kth smallest element from the sorted inOrderTraversalArrayList. 
        //Since the elements are stored in sorted order, the kth smallest element is at index B-1 in the list (0-based indexing).
        return inOrderTraversalArrayList.get(B-1);
    }
    public static void preOrder(TreeNode root,ArrayList<Integer> inOrderTraversalArrayList){
        if(root==null){
            return;
        }
        preOrder(root.left,inOrderTraversalArrayList);
        inOrderTraversalArrayList.add(root.val);
        preOrder(root.right,inOrderTraversalArrayList);
    }

}
