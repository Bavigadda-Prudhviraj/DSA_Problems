package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LeftViewofBinarytree {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<Integer> leftViewArrayList=leftViewofBinaryTree(root);
		System.out.println(leftViewArrayList);

	}
	/*
	The leftViewofBinaryTree method performs a level-order traversal of a binary tree and returns the left view of the tree, 
	which is the list of nodes visible from the left side when looking at the tree from the root.
	
	Time Complexity:
			The time complexity of this algorithm is O(N), where N is the number of nodes in the binary tree, as each node is visited once.

	Space Complexity:
			The space complexity is O(M), where M is the maximum number of nodes at any level in the binary tree. 
			In the worst case, the maximum number of nodes at any level is N/2 for a balanced binary tree, and 
			it can be as large as N for a skewed binary tree.
			
			Note:
				it contains two level nodes at a time last level & before last level 
				LastLevel:in Balanced binary tree last level contains at most ((n+1)/2),which is greater then or equal to half nodes of the entire tree
				Last second level= it contains ((N+1)/4) nodes
				max space= last Level + last second level
						 =   (n+1)/2  + (n+1)/4
						 =       O(n)
	 */
	public static ArrayList<Integer> leftViewofBinaryTree(TreeNode root){
		//This creates the list that will store the left view of the binary tree.
		ArrayList<Integer> leftViewArrayList=new ArrayList<>();
		//This queue is used to perform the level-order traversal. It stores nodes at each level.
		Queue<TreeNode> queue=new LinkedList<>();
		queue.add(root);// The root node is added to the queue to start the level-order traversal.
		while(!queue.isEmpty()){
			//The current size of the queue is stored to determine the number of nodes at the current level.
			int queueSize=queue.size();
			//This loop iterates through all the nodes at the current level.
			for(int i=1;i<=queueSize;i++) {
				//Get the front node from the queue and remove it.
				TreeNode queueFrontNode=queue.poll();
				// If this is the first node in the current level, it is visible from the left view.
				if(i==1) {
					//Add the value of the current node to the leftViewArrayList.
					leftViewArrayList.add(queueFrontNode.val);
				}
				//If the left child of the current node exists, add it to the queue to be processed later.
				if(queueFrontNode.left!=null) {
					queue.add(queueFrontNode.left);
				}
				// If the right child of the current node exists, add it to the queue to be processed later.
				if(queueFrontNode.right!=null) {
					queue.add(queueFrontNode.right);
				}
				//After the for loop, all nodes at the current level have been processed, and the loop moves on to the next level.
			}
			//The process continues until all levels of the binary tree have been traversed.
			
		}
		//The leftViewArrayList now contains the left view of the binary tree, which is the nodes visible from the left side when looking at the tree from the root.
		return leftViewArrayList;
	}

}
