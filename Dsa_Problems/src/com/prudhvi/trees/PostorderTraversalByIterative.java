package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PostorderTraversalByIterative {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<Integer> postOrderArrayList=postOrder(root);
		System.out.println(postOrderArrayList);
	}
	public static ArrayList<Integer> postOrder(TreeNode root){
		ArrayList<Integer> arrayList=new ArrayList<>();
		Stack<Pairss> stack=new Stack<>();
		Pairss pairss=new Pairss(root);
		stack.push(pairss);
		while(!stack.isEmpty()){
			if(stack.peek().task==1) {
				stack.peek().task++;
				if(stack.peek().node.left!=null) {
					Pairss pairs=new Pairss(stack.peek().node.left);
					stack.push(pairs);
				}
			}
			else if (stack.peek().task==2) {
				stack.peek().task++;
				if(stack.peek().node.right!=null) {
					Pairss pairs=new Pairss(stack.peek().node.right);
					stack.push(pairs);
				}
			}
			else if (stack.peek().task==3) {
				stack.peek().task++;
				arrayList.add(stack.peek().node.val);
				
			}
			else {
				stack.pop();
			}
			
		}
		return arrayList;
	}
}
class Pairss{
	TreeNode node;
	int task;
	public Pairss(TreeNode node) {
		super();
		this.node = node;
		this.task = 1;
	}
	
}

