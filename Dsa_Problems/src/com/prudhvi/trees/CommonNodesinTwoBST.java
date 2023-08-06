package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.HashSet;

public class CommonNodesinTwoBST {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int answer=commonNodesSum(root, root);
		System.out.println(answer);

	}
	static long sum=0;
    public static int commonNodesSum(TreeNode A, TreeNode B) {
    	long mod=1000000007;
        HashSet<Integer> arr1=new HashSet<>();
        HashSet<Integer> arr2=new HashSet<>();
        inorderTraversal(A,arr1);   
        inorderTraversal(B,arr2);  
        System.out.println(arr1+" "+arr2);
        for(int element:arr1) {
        	if(arr2.contains(element)) {
        		sum=(sum+element)%mod;
        	}
        }
        
        return (int)sum;
    }
    public static void inorderTraversal(TreeNode root,HashSet<Integer> arr){
        if(root==null){
            return;
        }
        arr.add(root.val);
        inorderTraversal(root.left,arr);
        inorderTraversal(root.right,arr);
    }
    public static long treeTraversal(TreeNode root,HashSet<Integer> arr,long mod){
        if(root==null){
            return 0;
        }
        if(arr.contains(root.val)){
            sum=(sum+root.val)%mod;
        }
        long leftSum=treeTraversal(root.left,arr,mod);
        long rightSum=treeTraversal(root.right,arr,mod);
        return leftSum+rightSum;
    }

}
