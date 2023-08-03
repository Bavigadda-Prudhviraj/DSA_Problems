package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ZigZagLevelOrderTraversalBT {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<ArrayList<Integer>> zigzahArrayList=zigZagTraversal(root);
		System.out.println(zigzahArrayList);

	}
	/*
	The zigZagTraversal method returns a list of lists representing the zigzag (or level-order) traversal of a binary tree. 
	The zigzag traversal starts from the root level (level 1), 
	then goes to the second level from right to left, 
	then the third level from left to right, 
	and so on, alternating direction after each level.
	
	Time Complexity:
		The time complexity of this algorithm is O(N), where N is the number of nodes in the binary tree, as each node is visited once.
	Space Complexity:
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
	private static ArrayList<ArrayList<Integer>> zigZagTraversal(TreeNode root) {
		// This creates the list that will store the zigzag level-order traversal elements.
		ArrayList<ArrayList<Integer>> zigZagArrayList=new ArrayList<>();
		//This queue is used to perform the level-order traversal. It stores the nodes of the binary tree.
		Queue<TreeNode> queue=new LinkedList<>();
		//Add the root node to the queue to start the level-order traversal.
		queue.add(root);
		// A count variable is used to keep track of the current level in the zigzag traversal. It starts from 1 as the root is at level 1.
        int count=1;
		while(!queue.isEmpty()){
			//Create a new ArrayList to store elements at the current level.
			ArrayList<Integer> eachLevelElements=new ArrayList<>();
			//Get the number of nodes currently in the queue. This represents the number of nodes at the current level.
			int queueSize=queue.size();
			//iterates through all nodes at the current level.
			for(int i=1;i<=queueSize;i++) {
				//Get the node from the front of the queue.
				TreeNode queueFrontNode=queue.poll();
				//Add the value of the current node to the ArrayList for the current level.
				eachLevelElements.add(queueFrontNode.val);
				//If the left child of the current node exists, add it to the queue.
				if(queueFrontNode.left!=null) {
					queue.add(queueFrontNode.left);
				}
				// If the right child of the current node exists, add it to the queue.
				if(queueFrontNode.right!=null) {
					queue.add(queueFrontNode.right);
				}
				//After the for loop, all nodes at the current level have been processed, and the eachLevelElements ArrayList contains the elements at this level.
			}
			//Check if the current level is even (i.e., count is even).
            if(count%2==0){
            	//Reverse the eachLevelElements ArrayList to read the elements from right to left for the even level.
                Collections.reverse(eachLevelElements);
                // Add the eachLevelElements ArrayList to the zigZagArrayList.
                zigZagArrayList.add(eachLevelElements);
            }
            //For odd levels, add the eachLevelElements ArrayList as it is.
            else{
                zigZagArrayList.add(eachLevelElements);
            }
            // Increment the count to move to the next level.
            count++;
            //After the while loop, all nodes in the binary tree have been processed, and the zigZagArrayList contains the zigzag level-order traversal.
		}
		//Finally, the zigZagArrayList is returned.____
		return zigZagArrayList;
	}

}
