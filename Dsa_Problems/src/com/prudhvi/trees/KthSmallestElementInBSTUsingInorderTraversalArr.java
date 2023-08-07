package com.prudhvi.trees;

import java.util.ArrayList;

import javax.swing.event.AncestorEvent;

public class KthSmallestElementInBSTUsingInorderTraversalArr {
	
	public static void main(String[] args) {
		int[] arr= {1,2,3};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int kthSmallestElement=kthsmallest(root, 2);
		System.out.println(kthSmallestElement);
	
		KthSmallestElementInBSTUsingInorderTraversalArr.inorderTraversal(root, 2);
		System.out.println(answer);
	}
	/*first Approach 
	The provided code defines a method inorderTraversal that performs an inorder traversal of a binary search tree (BST) rooted at root and finds the kth smallest element in the BST. 
	It uses a combination of recursive traversal and a global counter to keep track of the visited nodes and find the kth smallest element.
	
	Time Complexity:
			The time complexity of the inorderTraversal method is determined by the number of nodes visited during the inorder traversal until the kth smallest element is found. 
			In the worst case, where we have to traverse the entire BST to find the kth smallest element, the time complexity is O(N), where N is the total number of nodes in the BST.
			However, in practice, the algorithm can be more efficient if the kth smallest element is found earlier in the traversal, potentially reducing the number of visited nodes. 
			In the best case, where the kth smallest element is close to the root of the BST, the time complexity can be closer to O(log N) if the tree is balanced.
				Worst Case: O(N)
				Best Case (Balanced Tree): O(log N)

	Space Complexity:
			The space complexity of the inorderTraversal method is determined by the space used by the call stack during the recursive calls. 
			Each recursive call adds a new frame to the call stack until the base case is reached. 
			In the worst case, where the BST is skewed (essentially a linked list), the maximum depth of the call stack will be N, where N is the total number of nodes in the BST. 
			Therefore, the space complexity is O(N) in the worst case.
				Worst Case (Skewed Tree): O(N)
				Best Case (Balanced Tree): O(log N)
	The actual performance of the algorithm will depend on the specific structure of the BST and the location of the kth smallest element. 
	If k is much smaller than N, the algorithm might terminate early, leading to better time complexity. Similarly, if the tree is well-balanced, 
	the space complexity can be reduced compared to a skewed tree.
	 */
	
	static int count;//A static variable to keep track of the count of visited nodes during the inorder traversal.
	static int answer;//A static variable to store the kth smallest element.
	public static void inorderTraversal(TreeNode root,int kthEle){
		//it means we have reached the end of a branch, so we return.
		if(root==null) {
			return;
		}
		//Recursively call inorderTraversal on the left subtree (root.left) with the same value of kthEle.
		inorderTraversal(root.left,kthEle);
		count++;//Increment the count to keep track of the number of visited nodes.
		//If the count equals kthEle, it means we have found the kth smallest element (answer), 
		if(count==kthEle) {
			//so we set answer to the value of the current root and return.
			answer=root.val;
			return;
		}
		//Recursively call inorderTraversal on the right subtree (root.right) with the same value of kthEle.
		inorderTraversal(root.right,kthEle);
		
	}
	/*
	The kthsmallest method finds the kth smallest element in a binary search tree (BST) using an In-order traversal approach. 
	It first performs an In-order traversal of the BST to store all elements in sorted order in the inOrderTraversalArrayList. 
	Then, it returns the kth element from the sorted list, which corresponds to the kth smallest element in the BST.
	
	Time Complexity:
			The time complexity of the kthsmallest method is O(n) because it first performs an In-order traversal of the entire BST (O(n) time complexity) and 
			then retrieves the kth smallest element from the inOrderTraversalArrayList (O(1) time complexity).
	
	Space Complexity:
			The space complexity of the method is O(n) because 
			it uses the inOrderTraversalArrayList to store all elements of the BST during In-order traversal, and 
			the size of the list will be equal to the number of nodes in the BST.
	 */
	public static int kthsmallest(TreeNode A, int B) {
		//Creates an ArrayList to store elements during the Inorder traversal.
        ArrayList<Integer> inOrderTraversalArrayList=new  ArrayList<>();
        //Calls the preOrder method to perform the In-order traversal of the BST. The In-order traversal will store elements in the inOrderTraversalArrayList in sorted order.
        inOrder(A,inOrderTraversalArrayList);
        // contains only one element. If so, it means the BST has only one node, and that node is the kth smallest element. 
        //In this case, it directly returns that element.
        if(inOrderTraversalArrayList.size()==1){
            return inOrderTraversalArrayList.get(0);
        }
        //Returns the kth smallest element from the sorted inOrderTraversalArrayList. 
        //Since the elements are stored in sorted order, the kth smallest element is at index B-1 in the list (0-based indexing).
        return inOrderTraversalArrayList.get(B-1);
    }
    public static void inOrder(TreeNode root,ArrayList<Integer> inOrderTraversalArrayList){
        if(root==null){
            return;
        }
        inOrder(root.left,inOrderTraversalArrayList);
        inOrderTraversalArrayList.add(root.val);
        inOrder(root.right,inOrderTraversalArrayList);
    }
    

}
