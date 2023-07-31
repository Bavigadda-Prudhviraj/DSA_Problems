package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PreorderTraversalByIterative {

	public static void main(String[] args) {
		ArrayList<Integer> preOrder=new ArrayList<>(Arrays.asList(8,6,2,11,16,10,12,4,14,18,9,15));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList(11,2,16,6,10,12,8,14,18,4,9,15));
		TreeNode root=BinaryTreeFromInorderAndPreorder.buildTree(preOrder, inOrder);
		ArrayList<Integer> arrayList=preOrder(root);
		System.out.println(arrayList);
	}
	/*
	The provided code performs a preOrder traversal on a binary tree and returns the result as an ArrayList of integers. 
	It uses an iterative approach with a stack to traverse the binary tree in the order of root node, left child, and then right child.
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, we visit each node exactly once during the traversal, resulting in a time complexity of O(N).

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			In the worst case, the maximum space used by the stack is equal to the height of the binary tree, as each node is pushed onto the stack once. 
			In the best case, for a skewed binary tree, the space complexity would be O(N), where N is the number of nodes in the tree. 
			For a balanced binary tree, the space complexity would be O(log N), where log N is the height of a balanced binary tree.
	 */
	//The preOrder method takes the root of the binary tree as input and returns an ArrayList named preOrderArrayList, which will contain the preOrder traversal of the tree.
	public static ArrayList<Integer> preOrder(TreeNode root){
		ArrayList<Integer> preOrderArrayList=new ArrayList<>();
		//An empty stack of Pair objects (a custom class) is created to help in the iterative traversal.
		Stack<Pair> stack=new Stack<>();
		//A Pair object pair is created with the root node and task set to 1. 
		//The task represents the state of traversal for the node, where 1 indicates that the node itself needs to be added to the result, 2 indicates that the left subtree needs to be processed, and 3 indicates that the right subtree needs to be processed.
		Pair pair=new Pair(root);
		//The pair object is pushed onto the stack to start the iterative traversal.
		stack.push(pair);
		//The iterative traversal continues until the stack is empty
		while(!stack.isEmpty()){
			//In each iteration, the code checks the task of the node at the top of the stack.
			
			//If the task is 1, it means the node itself needs to be added to the result. , and 
			if(stack.peek().task==1) {
				stack.peek().task++;//The task is incremented
				//the value of the node is added to the preOrderArrayList.
				preOrderArrayList.add(stack.peek().node.val);
			}
			//If the task is 2, it means the left subtree needs to be processed.
			else if (stack.peek().task==2){
				stack.peek().task++;//The task is incremented
				//if the left child of the current node is not null, 
				if(stack.peek().node.left!=null) {
					//a new Pair object is created for the left child and pushed onto the stack.
					Pair leftPair=new Pair(stack.peek().node.left);
					stack.push(leftPair);
				}
			}
			//If the task is 3, it means the right subtree needs to be processed.
			else if (stack.peek().task==3) {
				stack.peek().task++;//The task is incremented
				//if the right child of the current node is not null, a new Pair object is created for the right child and pushed onto the stack.
				if(stack.peek().node.right!=null) {
					//a new Pair object is created for the right child and pushed onto the stack.
					Pair rightPair=new Pair(stack.peek().node.right);
					stack.push(rightPair);
				}
			}
			//If the task is not 1, 2, or 3, it means that both subtrees have been processed, and the current node can be popped from the stack.
			else {
				stack.pop();
			}	
			//The traversal continues until all nodes are processed.
		}
		return preOrderArrayList;
	}

}
class Pair{
	TreeNode node;
	int task;
	public Pair(TreeNode node) {
		super();
		this.node = node;
		this.task =1;
	}
	
}
