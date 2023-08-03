package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DeserializeBinaryTree {
	/*
	Problem Description
			You are given an integer array A denoting the Level Order Traversal of the Binary Tree.
			You have to deserialize the given Traversal in the Binary Tree and return the root of the Binary Tree.
		NOTE:
			In the array, the NULL/None child is denoted by -1.
			For more clarification check the Example Input.
	Problem Constraints
		 1 <= number of nodes <= 105
		-1 <= A[i] <= 105
	Input Format
		Only argument is an integer array A denoting the Level Order Traversal of the Binary Tree.
	Output Format
		Return the root node of the Binary Tree.
	 */

	public static void main(String[] args) {
		int[] arr= {8, 6, 4, 2, 10, 14, 9, 11, 16, -1, 12, -1, 18, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		TreeNode rootNode=deserializeBinaryTree(arr);
		ArrayList<Integer> serializedArrayList=SerializeBinaryTree.serializeBinaryTree(rootNode);
		System.out.println(serializedArrayList);

	}
	/*
	The deserializeBinaryTree method is used to deserialize a binary tree from an integer array representation. 
	The method takes an integer array as input, where each consecutive pair of elements in the array represents the left and right child of a node in the binary tree. 
	A value of -1 indicates a null child.
	
	Time Complexity:
			The time complexity of the deserializeBinaryTree method is O(N), where N is the number of elements in the array. 
			The method processes each element in the array once.
	Space Complexity:
			The space complexity is O(W), where W is the maximum width (maximum number of nodes at any level) of the binary tree. 
			In the worst case, the queue can contain all the nodes at the last level, which can be at most N/2 nodes for a balanced binary tree, leading to O(N/2) space complexity. 
			However, in the best case (a skewed binary tree), the width can be as small as log(N), resulting in O(log(N)) space complexity.
	 */
	public  static TreeNode deserializeBinaryTree(int[] arr) {
		// Create the root node of the binary tree with the first element of the array.
		TreeNode rootNode=new TreeNode(arr[0]);
		//Create a queue to perform a level-order traversal of the binary tree.
		Queue<TreeNode> queue=new LinkedList<>();
		queue.add(rootNode);//Add the root node to the queue to start the traversal.
		//Initialize an index to track the current position in the array. 
		//Start at index 1 because we have already used the first element for the root node.
		int index=1;
		//Start the level-order traversal using the while loop:
		while(!queue.isEmpty()){
			TreeNode currentNode=queue.poll();//Dequeue the next node from the queue.
			//If the dequeued node is null, skip the rest of the loop and continue to the next iteration. 
			//This can happen when the current node is a null child (value -1).
			if(currentNode==null) {
				continue;
			}
			//Get the next element from the array, which represents the value of the left child of the current node.
			int currentNodeLeftElement=arr[index];
			//Get the element after the left child element, which represents the value of the right child of the current node.
			int currentNodeRightElement=arr[index+1];
			// Increment the index by 2 to move to the next pair of elements in the array.
			index=index+2;
			//Create left and right child nodes for the current node based on the values from the array:
			//If currentNodeLeftElement is -1, set the left child to null.
			if(currentNodeLeftElement==-1) {
				currentNode.left=null;
			}
			//Otherwise, create a new TreeNode with the value currentNodeLeftElement and set it as the left child of the current node.
			else {
				currentNode.left=new TreeNode(currentNodeLeftElement);
			}
			//If the currentNodeLeftElement is not -1, create a new TreeNode with the value currentNodeLeftElement and set it as the left child of the currentNode.
			if(currentNodeRightElement==-1) {
				currentNode.right=null;
			}
			else {
				currentNode.right=new TreeNode(currentNodeRightElement);
			}
			// Add the left child to the queue for further processing in the traversal.
			queue.add(currentNode.left);
			//Add the right child to the queue for further processing in the traversal.
			queue.add(currentNode.right);
		}
		//After the traversal is complete, the binary tree is deserialized from the array representation, and the root node is returned.
		return rootNode;
		
	}

}
