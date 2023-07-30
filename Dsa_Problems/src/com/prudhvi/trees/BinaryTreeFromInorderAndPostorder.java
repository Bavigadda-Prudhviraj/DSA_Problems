package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeFromInorderAndPostorder {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root= buildTree(inOrder,postOrder);
		
		ArrayList<Integer> postOrderTraversArrayList=new ArrayList<>();
		ArrayList<Integer> inOrderTraversArrayList=new ArrayList<>();
		
		
		PostOrderTraversal.postOrderTraversal(root,postOrderTraversArrayList);
		InorderTraversal.inorderTraversal(root, inOrderTraversArrayList);
		System.out.println(postOrderTraversArrayList);
		System.out.println(inOrderTraversArrayList);

	}
	/*
	The given code is for constructing a binary tree from its in-order and post-order traversals. 
	It takes two ArrayLists: inOrder, which represents the in-order traversal of the tree, and postOrder, which represents the post-order traversal of the tree. 
	The code builds the binary tree using recursion and returns the root of the constructed tree.
	
	Time Complexity:
			The time complexity of the buildTree method and binaryTreeFromInorderAndPreorder method is O(N), where N is the number of nodes in the binary tree. 
			This is because each node is visited once during the construction of the binary tree.

	Space Complexity:
			The space complexity of the buildTree method and binaryTreeFromInorderAndPreorder method is O(N), where N is the number of nodes in the binary tree. 
			This is due to the space used by the recursive function call stack, which can have a maximum depth equal to the height of the binary tree. 
			In the worst-case scenario, where the binary tree is skewed (essentially a linked list), the height of the tree can be N, resulting in a space complexity of O(N). 
			In the best-case scenario, where the binary tree is balanced, the height of the tree is log(N), resulting in a space complexity of O(log(N)).
	 */
	//It takes two ArrayLists as input: inOrder, which contains the in-order traversal of the binary tree, and postOrder, which contains the post-order traversal of the binary tree.
	public static TreeNode buildTree(ArrayList<Integer>  inOrder , ArrayList<Integer>  postOrder) {
		//It initializes variables to represent the start and end indices of the in-order and post-order sequences.
		int postOrderStart=0;
	    int postOrderEnd=postOrder.size()-1;
	    int inOrderStart=0;
	    int inOrderEnd=inOrder.size()-1;
	    //It creates a HashMap called inOrderValIndex to store the mapping of values to their indices in the in order sequence for efficient lookup.
	    Map<Integer,Integer> inOrderValIndex=new HashMap<>();
	    for(int i=0;i<inOrder.size();i++){
	        inOrderValIndex.put(inOrder.get(i),i);
	    }
	    //It calls the binaryTreeFromInorderAndPreorder method with appropriate parameters to construct the binary tree and 
	    TreeNode root=binaryTreeFromInorderAndPreorder(postOrderStart,postOrderEnd,inOrderStart,inOrderEnd,postOrder,inOrder,inOrderValIndex);
	    //returns the root of the constructed tree.
	    return root;
	}
	//This is the recursive method that constructs the binary tree. 
	//It takes the start and end indices of the in-order and post-order traversals, along with the postOrder and inOrder ArrayLists and the inOrderValIndex HashMap.
	public static TreeNode binaryTreeFromInorderAndPreorder(int postOrderStart,int postOrderEnd,int inOrderStart,int inOrderEnd,ArrayList<Integer> postOrder,ArrayList<Integer> inOrder,Map<Integer,Integer> inOrderValIndex){
		//The method first checks if the current subtree is empty, i.e., if postOrderStart is greater than postOrderEnd or inOrderStart is greater than inOrderEnd. 
		//If so, it returns null, indicating an empty subtree.
		if(postOrderStart>postOrderEnd || inOrderStart>inOrderEnd){
			return null;
	    }
		//Otherwise, it proceeds to construct the root of the subtree. 
		//It extracts the value of the root from the last element of the postOrder ArrayList (int rootNodeVal = postOrder.get(postOrderEnd)).
	    int rootNodeVal=postOrder.get(postOrderEnd);
	    //It creates a new TreeNode with the rootNodeVal as the value.
	    TreeNode root=new TreeNode(rootNodeVal);
	    //it finds the index of the root node in the in-order sequence using the inOrderValIndex HashMap (rootIndexInInOrder).
	    int rootIndexInInOrder=inOrderValIndex.get(rootNodeVal);
	    //The method then calculates the number of elements in the left subtree (noOfElements) by subtracting the inOrderStart index from rootIndexInInOrder.
	    int noOfElements=rootIndexInInOrder-inOrderStart;
	    //It recursively calls the binaryTreeFromInorderAndPreorder method to construct the left subtree using the appropriate indices and updates the left child of the current root.
	    root.left=binaryTreeFromInorderAndPreorder(postOrderStart,noOfElements+postOrderStart-1 ,inOrderStart/**/,rootIndexInInOrder-1   ,postOrder,inOrder,inOrderValIndex);
	    //Similarly, it recursively calls the binaryTreeFromInorderAndPreorder method to construct the right subtree using the appropriate indices and updates the right child of the current root.
	    root.right=binaryTreeFromInorderAndPreorder(noOfElements+postOrderStart, postOrderEnd-1,rootIndexInInOrder+1 ,inOrderEnd/**/,       postOrder,inOrder,inOrderValIndex);
	    return root;
	}
	/*
	 Ranges calculation:-
	 		Here's how the ranges are calculated:
				For the left subtree:
						1.The starting index (inOrderStart) for the in-order sequence is the same as the starting index of the current subtree.
						2.The ending index (rootIndexInInOrder - 1) for the in-order sequence is one position before the index of the root node in the in-order sequence (rootIndexInInOrder). 
						  This ensures that all elements to the left of the root node in the in-order sequence are considered as the left subtree's in-order traversal.
						3.The starting index (postOrderStart) for the post-order sequence is the same as the starting index of the current subtree.
						4.The ending index (noOfElements + postOrderStart - 1) for the post-order sequence is calculated by adding the number of elements in the left subtree (noOfElements) to the starting index of the current subtree. 
						  This ensures that all elements corresponding to the left subtree in the post-order sequence are considered.
				For the right subtree:
						1.The starting index (rootIndexInInOrder + 1) for the in-order sequence is one position after the index of the root node in the in-order sequence (rootIndexInInOrder). 
						  This ensures that all elements to the right of the root node in the in-order sequence are considered as the right subtree's in-order traversal.
						2.The ending index (inOrderEnd) for the in-order sequence is the same as the ending index of the current subtree.
						3.The starting index (noOfElements + postOrderStart) for the post-order sequence is calculated by adding the number of elements in the left subtree (noOfElements) to the starting index of the right subtree in the post-order sequence. 
						  This ensures that all elements corresponding to the right subtree in the post-order sequence are considered.
						4.The ending index (postOrderEnd - 1) for the post-order sequence is one position before the ending index of the current subtree. 
						  This ensures that all elements to the left of the root node in the post-order sequence are considered as the right subtree's post-order traversal.
			
			By using these calculated ranges, the binaryTreeFromInorderAndPreorder method constructs the left and right subtrees of the current root node recursively. 
			The method ensures that the elements are selected correctly for each subtree based on their positions in the in-order and postorder sequences.

The time and space complexity of the entire process is commented in at the beginning and remains the same.
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 */

}
