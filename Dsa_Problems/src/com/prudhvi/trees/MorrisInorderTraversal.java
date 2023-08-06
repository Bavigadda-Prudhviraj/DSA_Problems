package com.prudhvi.trees;

import java.util.ArrayList;

public class MorrisInorderTraversal {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		ArrayList<Integer> inorderTraversalArrayList=morriesInorderTraversal(root);
		System.out.println(inorderTraversalArrayList);
	}
	/*
	The morriesInorderTraversal method performs an in-order traversal of a binary tree using the Morris Traversal algorithm. 
	This algorithm allows performing the in-order traversal without using any additional space other than the output list. 
	It achieves this by making use of the "right" pointers of the tree nodes to create temporary links between nodes during the traversal.
	
	Time Complexity:
			The time complexity of the morriesInorderTraversal method is O(n), where n is the number of nodes in the binary tree. 
			This is because each node is visited exactly twice (once as the left child and once as the right child) during the traversal.
	Space Complexity:
			The space complexity of the method is O(1), as it does not use any additional space other than the output list. 
			The algorithm achieves this by reusing the "right" pointers of the tree nodes to create temporary links during the traversal.
	 */
	public static ArrayList<Integer> morriesInorderTraversal(TreeNode root){
		//an ArrayList to store the elements in In-order traversal order.
		ArrayList<Integer> morriesInorderTraversArrayList= new ArrayList<>();
	    //The method starts a while loop that continues as long as the root is not null. This loop is used to traverse the binary tree.
		while(root!=null){
			//In each iteration of the loop, it first checks if the root.left is null. 
			//If it is null, it means the root node is the leftmost node in its current subtree,
			if(root.left==null) {
				//and it directly adds the root.val to the morriesInorderTraversArrayList.
				morriesInorderTraversArrayList.add(root.val);
				//Then, it moves root to its right child (root = root.right).
				root=root.right;
			}
			//If root.left is not null, it means the root node has a left subtree. 
			//The algorithm needs to find the rightmost node in the left subtree of root (the in-order predecessor).
			else {
				//To find the in-order predecessor, it sets a temporary node tempNode to the root.left 
				TreeNode tempNode=root.left;
				//keeps moving to the rightmost node of the left subtree while tempNode.right != null && tempNode.right != root. 
				//The second condition ensures that we don't enter an infinite loop.
				while(tempNode.right!=null && tempNode.right!=root){
					tempNode=tempNode.right;
					
				}
				//Once the in-order predecessor is found, two possibilities arise:
				//a.If tempNode.right == null, it means that the in-order predecessor's right child is not connected to the root, and 
				if(tempNode.right==null) {
					//it connects the in-order predecessor's right child to the root. 
					tempNode.right=root;
					//Then, it moves root to its left child (root = root.left).
					root=root.left;
					
				}
				//If tempNode.right == root, it means that the in-order predecessor's right child is already connected to the root, indicating that the left subtree of root has already been traversed. 
				else {
					//In this case, it disconnects the in-order predecessor's right child (tempNode.right = null), 
					tempNode.right=null;
					//adds root.val to the morriesInorderTraversArrayList, and 
					morriesInorderTraversArrayList.add(root.val);
					//then moves root to its right child (root = root.right).
					root=root.right;
				}
			}
			//The process continues until the root becomes null, indicating that the entire tree has been traversed.
			
		}
		//Finally, the method returns the morriesInorderTraversArrayList, which contains the elements of the binary tree in In-order traversal order.
		return morriesInorderTraversArrayList;
		
	}

}
