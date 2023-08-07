package com.prudhvi.trees;

public class DistanceBetweenNodesBST {
	/*
	Problem Description
		Given a binary search tree.
		Return the distance between two nodes with given two keys B and C. It may be assumed that both keys exist in BST.
		NOTE: 
			Distance between two nodes is number of edges between them.
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int answer=distanceBetweenTwoNodes(root, 2,9);
		System.out.println(answer);

	}
	/*
	The code defines a method distanceBetweenTwoNodes that calculates the distance between two nodes (B and C) in a binary search tree (BST) rooted at node A. 
	The distance is defined as the number of edges between the two nodes. 
	The code also defines helper functions to find the least common ancestor (LCA) of the two nodes and to calculate the distance of a node from a given root.
	
	The distanceBetweenNodes function calculates the distance from a given root node to a target child node in the binary search tree. 
	It uses a loop to traverse the tree from the root node downward towards the child node, keeping track of the number of steps taken. 
	When the target child node is found, the count is returned.

	The leastCommonAncestorNode function finds the LCA of two nodes with values leftChild and rightChild in the binary search tree. 
	It also uses a loop to traverse the tree, maintaining a candidate LCA node. 
	The loop condition is based on the positions of the two nodes relative to the current root node. 
	When the loop breaks, the LCA node is returned.
	
	Time Complexity:
			The time complexity of this approach is O(h), where h is the height of the binary search tree. 
			The distanceBetweenNodes and leastCommonAncestorNode functions each take O(h) time to traverse from the root to the target node.
	
	Space Complexity:
			The space complexity is O(1) since the code uses a constant amount of additional space for variables and storage regardless of the input size.
	 */
	//This method takes the root of the BST (A) and two target node values (B and C) as input. 
	//It first finds the least common ancestor (LCA) node of the two target nodes using the leastCommonAncestorNode method. 
	//Then, it calculates the distances of the target nodes from the LCA using the distanceBetweenNodes method. 
	//Finally, it returns the sum of these distances, which represents the distance between the two target nodes.
	public static int distanceBetweenTwoNodes(TreeNode A, int B, int C) {
		//Calculates the least common ancestor (LCA) of nodes B and C in the BST rooted at A.
        TreeNode lca=leastCommonAncestorNode(A,B,C);
        //Calculates the distance between node B and the LCA.
        int leftChildCount=distanceBetweenNodes(lca,B);
        //Calculates the distance between node C and the LCA.
        int rightChildCount=distanceBetweenNodes(lca,C);
        //Returns the sum of distances between the LCA and nodes B and C, which is the distance between nodes B and C.
        return leftChildCount+rightChildCount;
    }
	//This function calculates the distance between a given node child and a specified root node in the BST:
    public static int distanceBetweenNodes(TreeNode root,int child){
        int count=0;//Initializes a count variable to keep track of the distance.
        while(root!=null){
        	//If the root value is less than child, move to the right child.
            if(root.val<child){
                root=root.right;
            }
            //If the root value is greater than child, move to the left child.
            else if(root.val>child){
                root=root.left; 
            }
            //If the root value is equal to child, return the count (distance).
            else{
                return count;
            }
            count++;//Increment the count.
        }
        return count;
    }
    // This function calculates the least common ancestor (LCA) of two nodes with values leftChild and rightChild in the BST:
    public static TreeNode leastCommonAncestorNode(TreeNode root,int leftChild,int rightChild){
        TreeNode lca=null;//Initializes an lca variable to store the LCA node.
        while(root!=null){
        	//If both leftChild and rightChild are greater than the root value, move to the right child.
            if(root.val<leftChild && root.val<rightChild){
            root=root.right;
            }
            //If both leftChild and rightChild are less than the root value, move to the left child.
            else if(root.val>leftChild && root.val>rightChild){
            	root=root.left;
            }
            //Otherwise, the root is the LCA, set lca to root and break the loop.
            else{
            	lca=root;
            	break;
        	}
        }
        //Return the lca.
        return lca;
    }

}
