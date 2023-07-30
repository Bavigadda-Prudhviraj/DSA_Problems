package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeFromInorderAndPreorder {

	public static void main(String[] args) {
		ArrayList<Integer> preOrder=new ArrayList<>(Arrays.asList(8,6,2,11,16,10,12,4,14,18,9,15));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList(11,2,16,6,10,12,8,14,18,4,9,15));
		TreeNode root= buildTree(preOrder,inOrder);
		
		ArrayList<Integer> preOrderTraversArrayList=new ArrayList<>();
		ArrayList<Integer> inOrderTraversArrayList=new ArrayList<>();
		
		InorderTraversal.inorderTraversal(root,inOrderTraversArrayList);
		PreOrderTraversal.preOrderTraversal(root, preOrderTraversArrayList);
		System.out.println(preOrderTraversArrayList);
		System.out.println(inOrderTraversArrayList);
	}
	/*
	This code is used to construct a binary tree from its preOrder and in-order traversals. 
	The binary tree is represented using the TreeNode class, where each node has a value, a left child, and a right child.
	
	Time Complexity:
			The time complexity of this code is O(N), where N is the number of nodes in the binary tree. 
			This is because each node is processed once, and the recursive function is called once for each node.

	Space Complexity:
			The space complexity of this code is O(N) in the worst-case scenario, where N is the number of nodes in the binary tree. 
			This is because the recursion can create a maximum of N function calls on the call stack. 
			Additionally, the inOrderValIndex map may store up to N key-value pairs, each representing a node value and its index in the in-order traversal. 
			In the best-case scenario (a balanced binary tree), the space complexity is O(log N) due to the height of the call stack.
	 */
	public static TreeNode buildTree(ArrayList<Integer> preOrder, ArrayList<Integer> inOrder) {
		//It initializes variables preOrderStart, preOrderEnd, inOrderStart, and inOrderEnd to represent the ranges of indices in the preOrder and in-order sequences.
		int preOrderStart=0;
	    int preOrderEnd=preOrder.size()-1;
	    int inOrderStart=0;
	    int inOrderEnd=inOrder.size()-1;
	    //It creates a HashMap inOrderValIndex to store the index of each element in the inOrder traversal.
	    Map<Integer,Integer> inOrderValIndex=new HashMap<>();
	    //It populates the inOrderValIndex HashMap with the elements of inOrder as keys and their corresponding indices as values.
	    for(int i=0;i<inOrder.size();i++){
	        inOrderValIndex.put(inOrder.get(i),i);
	    }
	    //It calls the binaryTreeFromInorderAndPreorder method with appropriate parameters to construct the binary tree and 
	    TreeNode root=binaryTreeFromInorderAndPreorder(preOrderStart,preOrderEnd,inOrderStart,inOrderEnd,preOrder,inOrder,inOrderValIndex);
	    //returns the root of the binary tree.
	    return root;
	}
	//This method takes several parameters representing the ranges of indices in the preOrder and inOrder sequences, along with the preOrder, inOrder lists, and the inOrderValIndex HashMap.
	//The method is a recursive function that constructs the binary tree using the given preOrder and inOrder traversals.
	public static TreeNode binaryTreeFromInorderAndPreorder(int preOrderStart,int preOrderEnd,int inOrderStart,int inOrderEnd,ArrayList<Integer> preOrder,ArrayList<Integer> inOrder,Map<Integer,Integer> inOrderValIndex){
		//It checks if the current range of indices is valid. If either the preOrder or inOrder range is empty or invalid, it returns null, indicating an empty subtree.
		if(preOrderStart>preOrderEnd || inOrderStart>inOrderEnd){
			return null;
	    }
		//It extracts the root node value from the preOrder list using the preOrderStart index 
	    int rootNodeVal=preOrder.get(preOrderStart);
	    //and creates a new TreeNode with this value as the root node.
	    TreeNode root=new TreeNode(rootNodeVal);
	    //It finds the index of the root node value in the inOrderValIndex HashMap to determine the number of elements in the left subtree.
	    int rootIndexInInOrder=inOrderValIndex.get(rootNodeVal);
	    int noOfElements=rootIndexInInOrder-inOrderStart;
	    //It recursively constructs the left subtree by calling the binaryTreeFromInorderAndPreorder method with updated parameters for the left subtree.
	    root.left=binaryTreeFromInorderAndPreorder(preOrderStart+1,noOfElements+preOrderStart,inOrderStart,rootIndexInInOrder-1,preOrder,inOrder,inOrderValIndex);
	    //It recursively constructs the right subtree by calling the binaryTreeFromInorderAndPreorder method with updated parameters for the right subtree.
	    root.right=binaryTreeFromInorderAndPreorder(noOfElements+preOrderStart+1,preOrderEnd,rootIndexInInOrder+1 ,inOrderEnd,preOrder,inOrder,inOrderValIndex);
	    //It returns the root node of the current subtree.
	    return root;
	}
	/*
	Why noOfElements calculation?
		Calculating noOfElements:
				1.noOfElements represents the number of elements in the left subtree of the current root node.
				2.It is calculated as the difference between the index of the root node in the inOrder traversal (rootIndexInInOrder) and the starting index of the inOrder traversal (inOrderStart).
				3.This value is used to determine the range of indices for the left subtree in both the inOrder and preOrder traversals.
				4.By knowing the number of elements in the left subtree, we can determine how many elements belong to the right subtree in the inOrder traversal. 
				  The remaining elements will then be part of the right subtree in the preOrder traversal.
		
	Ranges Calculation:-
		 let's dive into how the ranges are calculated in the binaryTreeFromInorderAndPreorder method in a clearer manner:
				1.The method binaryTreeFromInorderAndPreorder is responsible for constructing the binary tree from the given inOrder and preOrder traversals.
				2.The method is called recursively with different arguments to construct the left and right subtrees of each node in the binary tree.
				3.Initially, the ranges for the entire tree are given by preOrderStart and preOrderEnd for the preOrder traversal and inOrderStart and inOrderEnd for the inOrder traversal.
				4.The method finds the rootNodeVal, which is the value of the root node for the current subtree. 
				  This value is taken from the beginning of the preOrder traversal (preOrderStart).
				5.Then, it finds the rootIndexInInOrder, which is the index of the rootNodeVal in the inOrder traversal. 
				  This index is used to determine the number of elements in the left subtree.
				6.The noOfElements is calculated as the difference between rootIndexInInOrder and inOrderStart. 
				  This value represents the number of nodes in the left subtree.

		Now, the method creates a new TreeNode with rootNodeVal.
			
			For the left subtree, the recursive call is made with updated parameters:
					1.preOrderStart remains the same because the root node has already been considered.
					2.preOrderEnd is set to noOfElements + preOrderStart - 1, which represents the end of the left subtree in the preOrder traversal.
					3.inOrderStart remains the same because the left subtree starts from the current inOrderStart.
					4.inOrderEnd is set to rootIndexInInOrder - 1, which represents the end of the left subtree in the inOrder traversal.
	
			For the right subtree, the recursive call is made with updated parameters:
					1.preOrderStart is set to noOfElements + preOrderStart, which represents the start of the right subtree in the preOrder traversal.
					2.preOrderEnd remains the same because the right subtree is constructed using the remaining elements in the preOrder traversal.
   					3.inOrderStart is set to rootIndexInInOrder + 1, which represents the start of the right subtree in the inOrder traversal.
					4.inOrderEnd remains the same because the right subtree starts from the current rootIndexInInOrder + 1.
			The recursive calls continue in this manner until the base case is reached, where the range is either empty or contains a single node.
	 */
	


}
