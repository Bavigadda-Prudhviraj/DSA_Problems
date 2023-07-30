package com.prudhvi.trees;

import java.util.ArrayList;
/*
	The provided code performs a post-order traversal on a binary tree and returns the result as an ArrayList of integers. 
	It uses a recursive approach to traverse the binary tree in the order of left child, right child, and then the root node.
	
	Time Complexity:
			The time complexity of the code is O(N), where N is the number of nodes in the binary tree. 
			In the worst case, we visit each node exactly once during the traversal, resulting in a time complexity of O(N).

	Space Complexity:
			The space complexity of the code is O(H), where H is the height of the binary tree. 
			In the worst case, the maximum space used by the call stack is equal to the height of the binary tree, as each recursive call occupies space on the stack. 
			In the best case, for a balanced binary tree, the space complexity would be O(log N), where log N is the height of a balanced binary tree.
 */
public class PostOrderTraversal {
	//The postOrderTraversal method takes the root of the binary tree and an ArrayList named preOrderTraversalArr as input. 
	//It returns the ArrayList containing the post-order traversal of the tree.
	public ArrayList<Integer> postOrderTraversal( TreeNode Node) {
		ArrayList<Integer> preOrderTraversalArr=new ArrayList<>();
		//The method calls the postOrderTraversal function to perform the actual traversal.
	    postOrderTraversal(Node,preOrderTraversalArr);
	    
	    return preOrderTraversalArr;
	}
	//The postOrderTraversal function is a recursive function that takes a node of the binary tree and the answer ArrayList as input.
	public static void postOrderTraversal(TreeNode node,ArrayList<Integer> answer){
		//If the current node is null, i.e., there is no node, the function returns immediately (base case for recursion).
        if(node==null){
            return;
        }
        //Otherwise, the function calls itself recursively for the left subtree, passing the left child node and the answer ArrayList.
        postOrderTraversal(node.left,answer);
        //Then, the function calls itself recursively for the right subtree, passing the right child node and the answer ArrayList.
        postOrderTraversal(node.right,answer);
        //Finally, after traversing the left and right subtrees, the value of the current node is added to the answer ArrayList. 
        //This corresponds to the post-order traversal, where we visit the left child, then the right child, and finally the root node.
        answer.add(node.val);
        //The recursive calls continue until all nodes are traversed.
        //Once the recursion is complete, the answer ArrayList contains the post order traversal sequence of the binary tree.
    }

}
