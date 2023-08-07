package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckBSTwithOneChild {
	/*
	Problem Description
		Given preorder traversal of a binary tree, 
		check if it is possible that it is also a preorder traversal of a Binary Search Tree (BST), 
		where each internal node (non-leaf nodes) have exactly one child.
	 */

	public static void main(String[] args) {
		ArrayList<Integer> preOrderTraversArrayList=new ArrayList<>(Arrays.asList(4, 10, 5, 8));
		String answer=checkIsBSTEveryNodeHasOneNullChild(preOrderTraversArrayList);
		System.out.println(answer);
		
	}
	/*
	The method checkIsBSTEveryNodeHasOneNullChild that takes an ArrayList of integers as input and determines whether the given binary tree, 
	represented by the input list, is a Binary Search Tree (BST) where every non-leaf node has either one or zero null child. 
	The method returns "YES" if the conditions are met and "NO" otherwise
	
	Time Complexity:
			The time complexity of this method is O(n), where n is the number of elements in the input list A. 
			This is because the method iterates through the list once.
	Space Complexity:
			The space complexity of the method is O(1), as it uses a constant amount of additional space regardless of the size of the input list.
	 */
	public static String checkIsBSTEveryNodeHasOneNullChild(ArrayList<Integer> A) {
		// Initialize the maximum limit for the values in the tree. 
		//This limit helps to ensure that the current node's value is less than this maximum limit.
        int maximumLimit =Integer.MAX_VALUE;
        //Initialize the minimum limit for the values in the tree. 
        //This limit helps to ensure that the current node's value is greater than this minimum limit.
        int minimumLimit=Integer.MIN_VALUE;
        //Get the value of the root node, which is the first element of the ArrayList.
        int root=A.get(0);
        //The code then iterates over the remaining elements of the ArrayList, starting from index 1.
        for(int i=1;i<A.size();i++){
        	//If the current element is greater than the root value, it means that it should be in the right subtree, 
        	//so minimumLimit is set to the root value.
            if(A.get(i)>root){
                minimumLimit=root;
            }
            //If the current element is less than or equal to the root value, 
            //it means that it should be in the left subtree, so maximumLimit is set to the root value.
            else{
                maximumLimit=root;
            }
            //If the current element violates the BST property (i.e., it's less than minimumLimit or greater than maximumLimit), 
            if(A.get(i)<minimumLimit || A.get(i)>maximumLimit){
            	////the method returns "NO" immediately, indicating that the given binary tree does not meet the required conditions.
                return "NO";
            }
            //Update the root variable to the current element for the next iteration.
            root=A.get(i);
        }
        //If the loop completes without returning "NO", 
        //it means that the binary tree meets the conditions of a BST where every non-leaf node has either one or zero null children. 
        //In this case, the method returns "YES".
        return "YES";

    }

}
