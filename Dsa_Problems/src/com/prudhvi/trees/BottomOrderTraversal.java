package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BottomOrderTraversal {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<ArrayList<Integer>> verticalorderArrayList=bottomOrderTraversal(root);
		System.out.println(verticalorderArrayList);

	}
	/*
	The bottomOrderTraversal method performs a bottom-up level-order traversal of a binary tree and returns a list of lists, where each inner list contains the nodes at a specific vertical level, ordered from bottom to top.
	
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
	public static ArrayList<ArrayList<Integer>> bottomOrderTraversal(TreeNode root){
		ArrayList<ArrayList<Integer>> bottomOrderTraversArrayList=new ArrayList<>();//the list that will store the bottom order traversal of the binary tree.
		//This variable is used to track the minimum vertical level encountered during the traversal and is initialized with the maximum possible integer value.
		int minVerticalLevel=Integer.MAX_VALUE;
		//This variable is used to track the maximum vertical level encountered during the traversal and is initialized with the minimum possible integer value.
		int maxVerticalLevel=Integer.MIN_VALUE;
		//This hash map is used to store the nodes based on their vertical levels. 
		//The key represents the vertical level, and the value is a list containing the nodes at that level.
		HashMap<Integer,ArrayList<Integer>> hashMap=new HashMap<>();
		// This queue is used to perform the level-order traversal. Each element of the queue is a pair (pairBT) containing a node and its corresponding vertical level.
		Queue<pairBT> queue=new LinkedList<>();
		//The root node is added to the queue along with its vertical level (0) to start the level-order traversal
		queue.add(new pairBT(root, 0));
		while(!queue.isEmpty()){
			//Get the node from the front of the queue without removing it.
			TreeNode rootNode=queue.peek().node;
			//Get the vertical level of the node from the front of the queue.
			int nodeLevel=queue.peek().level;
			//Create a new ArrayList to store the nodes at the current vertical level.
			ArrayList<Integer> newArrayList=new ArrayList<>();
			//Retrieve the ArrayList from the hash map corresponding to the current vertical level. If it doesn't exist, a new ArrayList is used.
			ArrayList<Integer> hashmapArrayList=hashMap.get(nodeLevel)==null?newArrayList:hashMap.get(nodeLevel);
			//Add the value of the current node to the ArrayList.
			hashmapArrayList.add(rootNode.val);
			//Update the hash map with the new ArrayList.
			hashMap.put(nodeLevel,hashmapArrayList);
			//Remove the node from the front of the queue since it has been processed.
			queue.poll();
			maxVerticalLevel=Math.max(maxVerticalLevel, nodeLevel);//Update the maximum vertical level encountered so far.
			minVerticalLevel=Math.min(minVerticalLevel, nodeLevel);//Update the minimum vertical level encountered so far.
			//If the left child of the current node exists, add it to the queue with its corresponding vertical level (nodeLevel - 1).
			if(rootNode.left!=null) {
				queue.add(new pairBT(rootNode.left, nodeLevel-1));
			}
			//If the right child of the current node exists, add it to the queue with its corresponding vertical level (nodeLevel + 1).
			if(rootNode.right!=null) {
				queue.add(new pairBT(rootNode.right, nodeLevel+1));
			}
			//After the while loop, all nodes have been processed, and the hash map contains the nodes grouped by their vertical levels.
		}
		//The code then creates the final result by extracting the nodes from the hash map and adding them to the bottomOrderTraversArrayList in bottom-up order.
		for(int i=minVerticalLevel;i<=maxVerticalLevel;i++) {
			//get the hash map corresponding level
			ArrayList<Integer> bottomOrderElementsInReverseOrder=hashMap.get(i);
			//Each inner ArrayList (representing nodes at a vertical level) is reversed to obtain the bottom-up order.
			Collections.reverse(bottomOrderElementsInReverseOrder);
			//he reversed inner ArrayList is added to the bottomOrderTraversArrayList.
			bottomOrderTraversArrayList.add(bottomOrderElementsInReverseOrder);
		}
		return bottomOrderTraversArrayList;
	}

}
class pairBT{
	TreeNode node;
	int level;
	public pairBT(TreeNode root,int level) {
		this.node=root;
		this.level=level;
		
		
	}
}
