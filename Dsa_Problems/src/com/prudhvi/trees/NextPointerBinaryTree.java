package com.prudhvi.trees;

import java.util.LinkedList;
import java.util.Queue;

public class NextPointerBinaryTree {
	/*
	Problem Description
		Given a binary tree,Populate each next pointer to point to its next right node. 
		If there is no next right node, the next pointer should be set to NULL.
		Initially, all next pointers are set to NULL.
		Assume perfect binary tree.
	Problem Constraints
		1 <= Number of nodes in binary tree <= 100000
		0 <= node values <= 10^9
	 */

	public static void main(String[] args) {
		TreeLinkNode rootNode=new TreeLinkNode();
		connect(rootNode);
	}
	/*
	This code defines a method connect that takes a root node of a binary tree with a special property called "next right pointers," often used in a perfect binary tree structure. 
	The method establishes the next right pointers based on the structure of the tree, making it possible to traverse the tree level by level using these pointers.
	
	Time Complexity:
			The code visits each node once, and for each node, the enqueue and dequeue operations take constant time. 
			Therefore, the time complexity is O(N), where N is the number of nodes in the tree.

	Space Complexity:
			The space complexity depends on the maximum number of nodes in a single level of the tree. 
			In the worst case, for a perfect binary tree, this could be O(N/2) or approximately O(N) for each level. 
			Hence, the space complexity is O(N), where N is the number of nodes in the tree.
	 */
	public static void connect(TreeLinkNode root) {
		//The queue will be used to perform a level-order traversal of the binary tree.
        Queue<TreeLinkNode> queue=new LinkedList<>();
        //This adds the root node to the queue to start the level-order traversal.
        queue.add(root);
        //while loop that continues as long as there are nodes in the queue to process. The loop performs a level-order traversal of the tree.
        while(!queue.isEmpty()){
        	//This stores the current size of the queue, which represents the number of nodes in the current level of the tree.
            int queueSize=queue.size();
            //This loop iterates through each node in the current level.
            for(int i=1;i<=queueSize;i++){
            	//This dequeues the front node from the queue, representing the current node being processed.
                TreeLinkNode frontElement=queue.poll();
                //This condition checks if the current node is not the last node in the current level. 
                //If it's not the last node, it sets the next pointer of the current node to the next node in the queue (the front node) using frontElement.next = queue.peek();.
                //This connects nodes on the same level horizontally.
                if(i!=queueSize){
                    frontElement.next=queue.peek();
                }
                //This condition checks if the current node has a left child. If it does, the left child is enqueued into the queue for later processing.
                if(frontElement.left!=null){
                    queue.add(frontElement.left);
                }
                //This condition checks if the current node has a right child. If it does, the right child is enqueued into the queue for later processing.
                if(frontElement.right!=null){
                    queue.add(frontElement.right);
                }
                //The loop processes all nodes in each level, setting the next right pointers as needed to connect nodes horizontally at the same level.
            }
        }
	}
}
class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;
	TreeLinkNode(){
		
	}
	TreeLinkNode(int x) { val = x; }
}