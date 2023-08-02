package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TopViewofBinaryTree {
	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<Integer> arrayList= topViewofBinaryTree(root);
		System.out.println(arrayList);
	}
	/*
	The topViewofBinaryTree method returns the top view of a binary tree, 
	which is the list of elements visible from the top of the tree when viewed from left to right.
	
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
	private static ArrayList<Integer> topViewofBinaryTree(TreeNode root) {
		//This creates the list that will store the top view elements of the binary tree.
		ArrayList<Integer> topViewArrayList=new ArrayList<>();
		//This queue is used to perform the level-order traversal. It stores pairs of nodes and their horizontal levels.
		Queue<PairTVBT> queue=new LinkedList<>();
		int minLevel=Integer.MAX_VALUE;//This variable is used to keep track of the minimum horizontal level encountered during traversal.
		int maxLevel=Integer.MIN_VALUE;//This variable is used to keep track of the maximum horizontal level encountered during traversal.
		//This hash map is used to store elements at each horizontal level. 
		//The key is the horizontal level, and the value is the list of elements at that level.
		HashMap<Integer, ArrayList<Integer>> hashMap=new HashMap<>();
		//Create a pair with the root node and its horizontal level (0), and add it to the queue to start the level-order traversal.
		PairTVBT pairTVBT=new PairTVBT(root, 0);
		queue.add(pairTVBT);//The root node with its horizontal level is added to the queue.
		while(!queue.isEmpty()){
			int level=queue.peek().level;// Get the horizontal level of the front node in the queue.
			TreeNode queueFrontNode=queue.peek().node;//Get the front node from the queue.
			//Create a new ArrayList to store elements at the current horizontal level.
			ArrayList<Integer> newArrayList=new ArrayList<>();
			//Get the ArrayList corresponding to the current horizontal level from the hash map. If it doesn't exist, create a new ArrayList.
			ArrayList<Integer> hashMapArrayList=hashMap.get(level)==null?newArrayList:hashMap.get(level);
			minLevel=Math.min(minLevel, level);//Update the minimum horizontal level encountered so far.
			maxLevel=Math.max(maxLevel, level);// Update the maximum horizontal level encountered so far.
			//Add the value of the current node to the ArrayList for the current horizontal level.
			hashMapArrayList.add(queueFrontNode.val);
			//Remove the front node from the queue.
			queue.poll();
			//Put the updated ArrayList back into the hash map.
			hashMap.put(level, hashMapArrayList);
			//If the left child of the current node exists, add it to the queue with the updated horizontal level (level-1).
			if(queueFrontNode.left!=null) {
				queue.add(new PairTVBT(queueFrontNode.left,level-1));
			}
			//If the right child of the current node exists, add it to the queue with the updated horizontal level (level+1).
			if(queueFrontNode.right!=null) {
				queue.add(new PairTVBT(queueFrontNode.right, level+1));
			}
			//After the while loop, all nodes in the binary tree have been processed, and the hash map contains elements at each horizontal level.
		}
		//Iterate through all horizontal levels from the minimum to the maximum.
		for(int i=minLevel;i<=maxLevel;i++) {
			//Get the ArrayList of elements at the current horizontal level from the hash map.
			ArrayList<Integer> leveArrayList=hashMap.get(i);
			//Add the first element of the ArrayList (the leftmost element at the current level) to the topViewArrayList
			topViewArrayList.add(leveArrayList.get(0));
		}
		//The topViewArrayList now contains the elements visible from the top view of the binary tree.
		//Finally, the topViewArrayList is returned, representing the top view of the binary tree.
		return topViewArrayList;
	}

}
class PairTVBT{
	TreeNode node;
	int level;
	public PairTVBT(TreeNode root,int leve) {
		this.node=root;
		this.level=leve;
	}
}