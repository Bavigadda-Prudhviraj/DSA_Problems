package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PostorderTraversalByIterative {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<Integer> postOrderArrayList=postOrder(root);
		System.out.println(postOrderArrayList);
	}
	/*
	The provided code performs a post-order traversal on a binary tree and returns the result as an ArrayList of integers. 
	It uses an iterative approach with a stack to traverse the binary tree in the order of left child, right child, and then the root node.
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, we visit each node exactly once during the traversal, resulting in a time complexity of O(N).

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			In the worst case, the maximum space used by the stack is equal to the height of the binary tree, as each node is pushed onto the stack once. 
			In the best case, for a balanced binary tree, the space complexity would be O(log N), where log N is the height of a balanced binary tree.
	 */
	//The postOrder method takes the root of the binary tree as input and returns an ArrayList named arrayList, which will contain the post-order traversal of the tree.
	public static ArrayList<Integer> postOrder(TreeNode root){
		//An empty stack of Pairss objects (a custom class) is created to help in the iterative traversal.
		ArrayList<Integer> arrayList=new ArrayList<>();
		Stack<Pairss> stack=new Stack<>();
		//A Pairs object pairs is created with the root node and task set to 1. 
		//The task represents the state of traversal for the node, where 1 indicates that the left subtree needs to be processed, 2 indicates that the right subtree needs to be processed, and 3 indicates that the node itself needs to be added to the result.
		Pairss pairss=new Pairss(root);
		//The pairs object is pushed onto the stack to start the iterative traversal.
		stack.push(pairss);
		//The iterative traversal continues until the stack is empty
		while(!stack.isEmpty()){
			//In each iteration, the code checks the task of the node at the top of the stack.
			//If the task is 1, it means the left subtree needs to be processed. 
			if(stack.peek().task==1) {
				stack.peek().task++;
				////The task is incremented, and if the left child of the current node is not null, a new Pairs object is created for the left child and pushed onto the stack.
				if(stack.peek().node.left!=null) {
					Pairss pairs=new Pairss(stack.peek().node.left);
					stack.push(pairs);
				}
			}
			//If the task is 2, it means the right subtree needs to be processed. 
			else if (stack.peek().task==2) {
				stack.peek().task++;//The task is incremented, and 
				//if the right child of the current node is not null, a new Pairs object is created for the right child and pushed onto the stack.
				if(stack.peek().node.right!=null) {
					Pairss pairs=new Pairss(stack.peek().node.right);
					stack.push(pairs);
				}
			}
			//If the task is 3, it means the node itself needs to be added to the result. 
			else if (stack.peek().task==3) {
				//The task is incremented, and the value of the node is added to the arrayList
				stack.peek().task++;
				arrayList.add(stack.peek().node.val);
				
			}
			//If the task is not 1, 2, or 3, it means that both subtrees have been processed, and the current node can be popped from the stack.
			else {
				stack.pop();
			}
			//The traversal continues until all nodes are processed.
		}
		return arrayList;
	}
}
class Pairss{
	TreeNode node;
	int task;
	public Pairss(TreeNode node) {
		super();
		this.node = node;
		this.task = 1;
	}
	
}

