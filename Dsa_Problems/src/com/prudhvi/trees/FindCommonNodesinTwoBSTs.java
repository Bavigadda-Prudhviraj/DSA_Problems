package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.HashSet;
/*
	Given two Binary Search Trees. Find the nodes that are common in both of them, ie- find the intersection of the two BSTs.
	Note: Return the common nodes in sorted order.
 */
public class FindCommonNodesinTwoBSTs {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		int[] arr2= {1,2,3,4,5,6,7,8};
		TreeNode root1=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		TreeNode root2=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr2);
		ArrayList<Integer> answer=findCommon(root1,root2);
		System.out.println(answer);

	}
	/*
	The overall approach is to traverse both trees, with the addValToSet function populating a HashSet with values from one tree, and the addCommonNodesToArr function looking for common elements in the other tree while traversing it in an in-order fashion. 
	The HashSet ensures that value lookup is efficient (O(1)), making the code relatively efficient.
	
	Time Complexity:
		The code visits each node once in both trees while building the HashSet and looking for common elements. Therefore, the time complexity is O(N + M), where N is the number of nodes in root1, and M is the number of nodes in root2.
	Space Complexity:
		The space complexity is O(N) because the HashSet stores the values of the nodes in root1, and the ArrayList stores the common elements.
	 */
	public static ArrayList<Integer> findCommon(TreeNode root1,TreeNode root2){
        HashSet<Integer> set=new HashSet<>();//It initializes a HashSet called set to store the values of the nodes in root1.
        ArrayList<Integer> arr=new ArrayList<>();//It initializes an ArrayList called arr to store the common elements.
        addValToSet(set,root1);//function to add the values from root1 to the set.
        addCommonNodesToArr(set,root2,arr);//function to find common nodes in root2 and add them to the arr.
        return arr;
    }
	//This function takes three parameters: the set containing values from root1, the current root node in root2, and the arr to store common elements.
	//It's a recursive function that traverses the tree in an in-order fashion.
    public static void addCommonNodesToArr(HashSet<Integer> set,TreeNode root,ArrayList<Integer> arr){
        //If the root node is null, it returns, as there's nothing to process.
    	if(root==null){
            return;
        }
    	//It first recursively traverses the left subtree.
        addCommonNodesToArr(set,root.left,arr);
        //Then, if the set contains the value of the current root, it adds it to the arr, indicating a common element.
        if(set.contains(root.val)){
            arr.add(root.val);
        }
        //Finally, it recursively traverses the right subtree.
        addCommonNodesToArr(set,root.right,arr);
    }
    //This function is responsible for populating the set with values from root1.
    //It follows a similar recursive in-order traversal pattern:
    public static void addValToSet(HashSet<Integer> set,TreeNode root){
        if(root==null){//If the root is null, it returns.
            return;
        }
        addValToSet(set,root.left);//It recursively traverses the left subtree.
        set.add(root.val);//It adds the value of the current root to the set.
        addValToSet(set,root.right);//It recursively traverses the right subtree.
    }

}

