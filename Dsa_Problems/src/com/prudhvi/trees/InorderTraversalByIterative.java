package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class InorderTraversalByIterative {

	public static void main(String[] args) {
		ArrayList<Integer> preOrder=new ArrayList<>(Arrays.asList(8,6,2,11,16,10,12,4,14,18,9,15));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList(11,2,16,6,10,12,8,14,18,4,9,15));
		TreeNode root= BinaryTreeFromInorderAndPreorder.buildTree(preOrder, inOrder);
		ArrayList<Integer> inorderTraversArrayList=InorderTraversal(root);
		System.out.println(inorderTraversArrayList);
		

	}
	/*
	The given code performs an iterative inOrder traversal on a binary tree using a stack and a custom Pairs class to keep track of the traversal progress. 
	The code aims to visit all the nodes of the binary tree in an inOrder sequence and store the node values in an ArrayList.
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, each node is visited once and added to the arrayList, which takes O(N) time.

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			In the worst case, the stack may hold all nodes along the longest path from the root to a leaf node, leading to a space complexity of O(H). 
			In the best case, for a balanced binary tree, the space complexity would be O(log N), where log N is the height of a balanced binary tree.
	 */
	private static ArrayList<Integer> InorderTraversal(TreeNode root) {
		//The method initializes an empty stack named stack to keep track of the nodes during traversal and an ArrayList named arrayList to store the inOrder traversal sequence.
		Stack<Pairs> stack=new Stack<>();
		ArrayList<Integer> arrayList=new ArrayList<>();
		//A new Pairs object is created with the root node and an initial task value of 1. This represents the task of visiting the left child of the current node.
		Pairs pairs=new Pairs(root);
		//The Pairs object is pushed onto the stack to initiate the traversal.
		stack.push(pairs);
		//The main loop starts, and it continues until the stack becomes empty.
		while(!stack.isEmpty()){
			//If the task is 1, it means we need to visit the left child of the node. 
			if(stack.peek().task==1) {
				//The task is incremented, and 
				stack.peek().task++;
				//if the left child exists, 
				if(stack.peek().node.left!=null) {
					//a new Pairs object with the left child as the node and task set to 1 is pushed onto the stack. 
					Pairs leftPairs=new Pairs(stack.peek().node.left);
					//This represents the task of visiting the left child's left child.
					stack.push(leftPairs);
				}	
			}
			//If the task is 2, it means we need to visit the current node.
			else if (stack.peek().task==2) {
				//The task is incremented
				stack.peek().task++;
				//and the node's value is added to the arrayList, representing the inOrder traversal sequence.
				arrayList.add(stack.peek().node.val);	
				
			}
			//If the task is 3, it means we need to visit the right child of the node. 
			else if (stack.peek().task==3) {
				stack.peek().task++;//The task is incremented
				//and if the right child exists
				if(stack.peek().node.right!=null) {
					//a new Pairs object with the right child as the node 
					Pairs rightPairs=new Pairs(stack.peek().node.right);
					//and task set to 1 is pushed onto the stack. This represents the task of visiting the left child of the right child.
					stack.push(rightPairs);
				}
			}
			//If the task is 4, it means we have visited both the left and right subtrees of the current node. 
			//We pop the current node from the stack and move to its parent node.
			else  {
				stack.pop();
				
			}
			//The loop continues until all nodes are processed.
		}
		return arrayList;
	}
	

}
class Pairs{
	TreeNode node;
	int task;
	public Pairs(TreeNode node) {
		super();
		this.node = node;
		this.task = 1;
	}
	
	
	
}
