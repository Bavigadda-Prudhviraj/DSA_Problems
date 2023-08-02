package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class VerticalOrderTraversal {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<ArrayList<Integer>> verticalorderArrayList=verticalOrderTraversal(root);
		System.out.println(verticalorderArrayList);

	}
	/*
	The verticalOrderTraversal method returns a list of lists representing the vertical order traversal of a binary tree. 
	The vertical order traversal is the list of elements for each vertical level in the binary tree, starting from the leftmost vertical level to the rightmost.
	
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
	public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root){
		//This creates the list that will store the vertical order traversal elements.
		ArrayList<ArrayList<Integer>> verticalOrderTraversArrayList=new ArrayList<>();
		int minVerticalLevel=Integer.MAX_VALUE;//This creates the list that will store the vertical order traversal elements.
		int maxVerticalLevel=Integer.MIN_VALUE;//This variable is used to keep track of the maximum vertical level encountered during traversal.
		//This hash map is used to store elements at each vertical level. The key is the vertical level, and the value is the list of elements at that level.
		HashMap<Integer,ArrayList<Integer>> hashMap=new HashMap<>();
		//This queue is used to perform the level-order traversal. It stores pairs of nodes and their vertical levels.
		Queue<pairVT> queue=new LinkedList<>();
		//Create a pair with the root node and its vertical level (0), and add it to the queue to start the level-order traversal.
		queue.add(new pairVT(root, 0));
		while(!queue.isEmpty()){
			//Get the node from the front of the queue.
			TreeNode rootNode=queue.peek().node;
			//Get the vertical level of the front node in the queue.
			int nodeLevel=queue.peek().level;
			//Create a new ArrayList to store elements at the current vertical level.
			ArrayList<Integer> newArrayList=new ArrayList<>();
			//Get the ArrayList corresponding to the current vertical level from the hash map. If it doesn't exist, create a new ArrayList.
			ArrayList<Integer> hashmapArrayList=hashMap.get(nodeLevel)==null?newArrayList:hashMap.get(nodeLevel);
			//Add the value of the current node to the ArrayList for the current vertical level.
			hashmapArrayList.add(rootNode.val);
			//Put the updated ArrayList back into the hash map.
			hashMap.put(nodeLevel,hashmapArrayList);
			//Remove the front node from the queue.
			queue.poll();
			maxVerticalLevel=Math.max(maxVerticalLevel, nodeLevel);//Update the maximum vertical level encountered so far.
			minVerticalLevel=Math.min(minVerticalLevel, nodeLevel);//Update the minimum vertical level encountered so far.
			//If the left child of the current node exists, add it to the queue with the updated vertical level (nodeLevel - 1).
			if(rootNode.left!=null) {
				queue.add(new pairVT(rootNode.left, nodeLevel-1));
			}
			//If the right child of the current node exists, add it to the queue with the updated vertical level (nodeLevel + 1).
			if(rootNode.right!=null) {
				queue.add(new pairVT(rootNode.right, nodeLevel+1));
			}
			//After the while loop, all nodes in the binary tree have been processed, and the hash map contains elements at each vertical level.
		}
		//Iterate through all vertical levels from the minimum to the maximum.
		for(int i=minVerticalLevel;i<=maxVerticalLevel;i++) {
			//Add the ArrayList of elements at the current vertical level to the verticalOrderTraversArrayList.
			verticalOrderTraversArrayList.add(hashMap.get(i));
		}
		//The verticalOrderTraversArrayList now contains the elements for each vertical level in the binary tree, representing the vertical order traversal.
		//Finally, the verticalOrderTraversArrayList is returned.
		return verticalOrderTraversArrayList;
	}

}
class pairVT{
	TreeNode node;
	int level;
	public pairVT(TreeNode root,int level) {
		this.node=root;
		this.level=level;
		
		
	}
}