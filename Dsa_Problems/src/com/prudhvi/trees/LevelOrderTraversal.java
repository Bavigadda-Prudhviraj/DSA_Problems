package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<ArrayList<Integer>> leverOrderElements=levelOrderElements(root);
		System.out.println(leverOrderElements);

	}
	/*
	The levelOrderElements method performs a level-order traversal of a binary tree and returns a list of lists, 
	where each inner list represents the elements of each level of the tree.
	
	Time Complexity:
		The time complexity of this algorithm is O(N), where N is the number of nodes in the binary tree, as each node is visited once.
	space Complexity:
		The space complexity is O(M), where M is the maximum number of nodes at any level in the binary tree. 
		In the worst case, the maximum number of nodes at any level is N/2 for a balanced binary tree, 
		and it can be as large as N for a skewed binary tree.
		Note:
				it contains two level nodes at a time last level & before last level 
				LastLevel:in Balanced binary tree last level contains at most ((n+1)/2),which is greater then or equal to half nodes of the entire tree
				Last second level= it contains ((N+1)/4) nodes
				max space= last Level + last second level
						 =   (n+1)/2  + (n+1)/4
						 =       O(n)
	 */
	public static ArrayList<ArrayList<Integer>> levelOrderElements(TreeNode root){
		//This creates the list of lists that will store the elements of each level.
		ArrayList<ArrayList<Integer>> answerArrayList=new ArrayList<>();
		//This queue is used to perform the level-order traversal. It stores nodes at each level.
		Queue<TreeNode> queue=new LinkedList<>();
		//The root node is added to the queue to start the level-order traversal.
		queue.add(root);
		while(!queue.isEmpty()){
			//This list will store the elements of the current level.
			ArrayList<Integer> eachLevelElements=new ArrayList<>();
			//The current size of the queue is stored to determine the number of nodes at the current level.
			int queueSize=queue.size();
			//This loop iterates through all the nodes at the current level.
			for(int i=1;i<=queueSize;i++) {
				//Get the front node from the queue and remove it.
				TreeNode queueFrontNode=queue.poll();
				//Add the value of the current node to the eachLevelElements list.
				eachLevelElements.add(queueFrontNode.val);
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
			//Add the eachLevelElements list to the answerArrayList, representing one level of the binary tree.
			answerArrayList.add(eachLevelElements);
		}
		//The process continues until all levels of the binary tree have been traversed.
		//The answerArrayList now contains a list of lists, where each inner list represents the elements of each level of the binary tree
		return answerArrayList;
	}

}
