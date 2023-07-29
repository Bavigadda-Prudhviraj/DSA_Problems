package com.prudhvi.trees;

import java.util.ArrayList;

public class InorderTraversal {
	/*
	
	The given code is for performing an in-order traversal of a binary tree and returns an ArrayList containing the elements of the tree in ascending order.
	
	Explanation of the methods:
		1.inorderTraversal(TreeNode Node): 
				This is the public method that initiates the in-order traversal of the binary tree. 
				It takes a TreeNode object representing the root of the binary tree as input and returns an ArrayList of integers containing the in-order traversal.

		2.inorderTraversal(TreeNode node, ArrayList<Integer> answer): 
				This is the private helper method that performs the actual in-order traversal. 
				It takes two parameters: node, which represents the current node being processed during traversal, and answer, which is the ArrayList used to store the elements in the correct order.
	
	Time Complexity:
			The time complexity of the inorderTraversal method is O(N), 
			where N is the number of nodes in the binary tree. 
			This is because each node is visited once during the traversal.

	Space Complexity:
			The space complexity of the inorderTraversal method is O(Height),
			where N is the number of nodes in the binary tree. 
			This is due to the space used by the ArrayList inOrderTraversal, which can store N elements at most, and the recursive function call stack, 
			which can have a maximum depth equal to the height of the binary tree. In the worst-case scenario, where the binary tree is skewed (essentially a linked list), 
			the height of the tree can be N, resulting in a space complexity of O(N). 
			In the best-case scenario, where the binary tree is balanced, the height of the tree is log(N), resulting in a space complexity of O(log(N)).
	 */
	public ArrayList<Integer> inorderTraversal( TreeNode Node) {
		//The public inorderTraversal method initializes an empty ArrayList called inOrderTraversal to store the result.
		ArrayList<Integer> inOrderTraversalArr=new ArrayList<>();
		//It calls the private inorderTraversal method and passes the root node of the binary tree (Node) and the inOrderTraversal ArrayList.
	    inorderTraversal(Node,inOrderTraversalArr);
	    return inOrderTraversalArr;
	}
	/*
	The private inorderTraversal method performs the actual traversal using a recursive approach. 
	It starts by checking if the current node (node) is null, which serves as the base case for the recursion. 
	If the current node is null, it returns immediately, as there is no further processing needed.
	 */
	public static void inorderTraversal(TreeNode node,ArrayList<Integer> answer){
		//If the current node is not null, it continues with the following steps:
        if(node==null){
            return;
        }
        //a. Recursively call inorderTraversal on the left child of the current node (node.left).
        inorderTraversal(node.left,answer);
        //b. Add the value of the current node to the answer ArrayList (answer.add(node.val)).
        answer.add(node.val);
        //c.Recursively call inorderTraversal on the right child of the current node (node.right).
        inorderTraversal(node.right,answer);
    }

}
