package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<ArrayList<Integer>> leverOrderElements=levelOrderElements(root);
		System.out.println(leverOrderElements);

	}
	public static ArrayList<ArrayList<Integer>> levelOrderElements(TreeNode root){
		ArrayList<ArrayList<Integer>> answerArrayList=new ArrayList<>();
		Queue<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			ArrayList<Integer> eachLevelElements=new ArrayList<>();
			int queueSize=queue.size();
			for(int i=1;i<=queueSize;i++) {
				TreeNode queueFrontNode=queue.poll();
				eachLevelElements.add(queueFrontNode.val);
				if(queueFrontNode.left!=null) {
					queue.add(queueFrontNode.left);
				}
				if(queueFrontNode.right!=null) {
					queue.add(queueFrontNode.right);
				}
				
			}
			answerArrayList.add(eachLevelElements);
		}
		return answerArrayList;
	}

}
