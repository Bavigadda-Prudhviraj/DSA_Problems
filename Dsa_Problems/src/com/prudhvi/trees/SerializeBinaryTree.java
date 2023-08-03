package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeBinaryTree {
	/*
	Problem Description
		Given the root node of a Binary Tree denoted by A. You have to Serialize the given Binary Tree in the described format.
		Serialize means encode it into a integer array denoting the Level Order Traversal of the given Binary Tree.
	NOTE:
		In the array, the NULL/None child is denoted by -1.
		For more clarification check the Example Input.
	Problem Constraints
		1 <= number of nodes <= 105
	Input Format
		Only argument is a A denoting the root node of a Binary Tree.
	Output Format
		Return an integer array denoting the Level Order Traversal of the given Binary Tree.
	Example Input
		Input 1:
				
				           1
				         /   \
				        2     3
				       / \
				      4   5
		Input 2:

				            1
				          /   \
				         2     3
				        / \     \
				       4   5     6


	Example Output
		Output 1:
			[1, 2, 3, 4, 5, -1, -1, -1, -1, -1, -1]
		Output 2:
			[1, 2, 3, 4, 5, -1, 6, -1, -1, -1, -1, -1, -1]
	 */
	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<Integer> serialzeArrayList=serializeBinaryTree(root);
		System.out.println(serialzeArrayList);
		//[8, 6, 4, 2, 10, 14, 9, 11, 16, -1, 12, -1, 18, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]

	}
	/*
	The serializeBinaryTree method is used to serialize a binary tree into an ArrayList of integers. 
	The serialization is done using a level-order traversal (Breadth-First Search). 
	The method takes the root node of the binary tree as input and returns an ArrayList of integers representing the serialized binary tree.
	
	Time Complexity:
			The time complexity of the serializeBinaryTree method is O(N), where N is the number of nodes in the binary tree. 
			The method performs a level-order traversal, visiting each node once.

	Space Complexity:
			The space complexity is O(M), where M is the maximum number of nodes at any level in the binary tree. 
			In the worst case, the queue can store all nodes at the last level of a completely unbalanced tree, leading to a space complexity of O(N). 
			In a balanced tree, the space complexity can be O(1) if we ignore the ArrayList used for serialization.
	 */
	public static ArrayList<Integer> serializeBinaryTree(TreeNode root ){
		// Create a queue to perform a level-order traversal of the binary tree.
        Queue<TreeNode> queue=new LinkedList<>();
        //Create an ArrayList to store the serialized binary tree.
        ArrayList<Integer> serialArray=new ArrayList<>();
        queue.add(root);//Add the root node to the queue to start the traversal.
        //Add the value of the root node to the ArrayList to begin the serialization.
        serialArray.add(root.val);
        //Start the level-order traversal using the while loop:
        while(!queue.isEmpty()){
        	//Dequeue(remove) the next node from the queue.
            TreeNode queueFrontElement=queue.poll();
            //Get the left child of the dequeued node.
            TreeNode queueFrontElementLeft=queueFrontElement.left;
            //Get the right child of the dequeued node.
            TreeNode queueFrontElementRight=queueFrontElement.right;
            //Serialize the left child:
            //If queueFrontElementLeft is null, add -1 to the serialArray to represent a null child.
            if(queueFrontElementLeft==null) {
            	serialArray.add(-1);
            }
            //Otherwise, add the value of the left child to the serialArray and enqueue the left child for further processing.
            else {
				serialArray.add(queueFrontElementLeft.val);
				queue.add(queueFrontElementLeft);
			}  
            //Serialize the right child:
            //If queueFrontElementRight is null, add -1 to the serialArray to represent a null child.
            if(queueFrontElementRight==null) {
            	serialArray.add(-1);
            }
            //Otherwise, add the value of the right child to the serialArray and enqueue the right child for further processing.
            else {
				serialArray.add(queueFrontElementRight.val);
				queue.add(queueFrontElementRight);
			}
        }
        //After the traversal is complete, the serialArray contains the serialized binary tree, and it is returned.
        return serialArray;
    }

}
