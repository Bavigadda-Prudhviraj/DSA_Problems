package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KpathFromNode {
	public static ArrayList<Integer> pathArrayList=new ArrayList<>();
	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		pathOfKfromRoot(root,11);
		System.out.println("due to recursion it will be from bottom to top \n"+pathArrayList);
		Collections.reverse(pathArrayList);
		System.out.println("Proper Order from root to target Node \n"+pathArrayList);
	}
	/*
	The provided code is a recursive method to find a path from the root of a binary tree to a node with a given target value. 
	It returns a boolean value to indicate whether a path with the target value exists or not. 
	If a path is found, it also populates an ArrayList<Integer> called pathArrayList with the values of the nodes in the path from the root to the target node.
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, the code may need to visit all nodes in the binary tree to find the path to the target value.

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			This is because the maximum space used by the call stack is equal to the height of the binary tree during the recursive calls. 
			In the best-case scenario, the binary tree is balanced, and the height is log N, where N is the number of nodes in the tree. 
			However, in the worst case, for a skewed binary tree, the height would be N, resulting in a space complexity of O(N). 
			
			Additionally, the pathArrayList can also contribute to the space complexity depending on its size, which would be at most O(N) in the worst case if the path is from the root to a leaf node.
	 */
	public static  boolean  pathOfKfromRoot(TreeNode root,int target){
		//The first conditional check if (root == null) verifies if the current node (root) is null. 
		//If it is null, it means the current subtree is empty, and the method returns false, indicating that the target node is not found in the current subtree.
		if(root==null) {
			return false;
		}
		//The second conditional check if (root.val == target) verifies if the value of the current node (root) is equal to the target. 
		//If it is, it means the target node is found, and the value of the current node is added to the pathArrayList. 
		//The method then returns true, indicating that the target node is found in the current subtree.
		if(root.val==target) {
			pathArrayList.add(root.val);
			return true;
		}
		//The next conditional check if (pathOfKfromRoot(root.left, target) || pathOfKfromRoot(root.right, target)) recursively searches for the target node in the left and right subtrees of the current node. 
		//If the target node is found in either subtree, the value of the current node is added to the pathArrayList, and the method returns true.
		if ( pathOfKfromRoot(root.left, target) || pathOfKfromRoot(root.right, target) ) {
			pathArrayList.add(root.val);
			return true;
		}
		//If the target node is not found in the current node or its subtrees, the method returns false, indicating that the target node is not present in the current subtree or any of its subtrees.
		return false;
	}

}
