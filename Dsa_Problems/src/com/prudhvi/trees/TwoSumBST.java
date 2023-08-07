package com.prudhvi.trees;

import java.util.HashMap;
import java.util.Map.Entry;

public class TwoSumBST {
	/*
	Problem Description
			Given a binary search tree A, where each node contains a positive integer, and an integer B, you have to find whether or not there exist two different nodes X and Y such that X.value + Y.value = B.
	Return 1 to denote that two such nodes exist. Return 0, otherwise.
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int answer=two2Sum(root, 11);
		System.out.println(answer);

	}
	/*
	This code defines a method two2Sum that takes a binary tree node root and an integer target as parameters. 
	The goal of the method is to determine whether there are two distinct nodes in the binary tree whose values add up to the given target. 
	The method returns an integer, where 1 indicates the presence of such two nodes and 0 indicates their absence.
	
	Time Complexity:
		The preOrder function visits each node of the binary tree once, resulting in a time complexity of O(N), where N is the number of nodes in the tree.
		The subsequent loop in the two2Sum method iterates over the entries in the hashMap, which also takes O(N) time in the worst case.
		Overall, the time complexity of the code is O(N), where N is the number of nodes in the binary tree.
	
	Space Complexity:
		The space complexity of the code is dominated by the space used by the hashMap. In the worst case, all distinct values in the tree could be stored in the map, resulting in a space complexity of O(N).
		Additionally, the recursive call stack of the preOrder function can go as deep as the height of the tree, resulting in an additional space complexity of O(H), where H is the height of the tree.
		
		Therefore, the total space complexity is O(N + H), where N is the number of nodes and H is the height of the binary tree.
	 */
	public static int two2Sum(TreeNode root, int target) {
		//This line initializes a hash map named hashMap which will be used to store the frequency of node values encountered during the tree traversal.
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        //his line calls the preOrder method, passing the root node of the binary tree and the hashMap to it. 
        //The preOrder method is responsible for traversing the tree in pre-order and populating the hashMap with node values and their frequencies.
        preOrder(root,hashMap);
        //This initializes an integer variable answer with 0, which will be used to store the final result (whether the two nodes with the desired sum are found or not).
        int answer=0;
        //This loop iterates through each entry (key-value pair) in the hashMap.
        for(Entry<Integer, Integer> ele:hashMap.entrySet()){
        	//the difference between the target and the current node value (ele.getKey()), and stores it in the num variable.
            int num=target-ele.getKey();
            //This conditional checks if the hashMap contains the calculated num. 
            //If it does, it means that there exists another node with the value num such that their sum would be equal to the target.
            if(hashMap.containsKey(num)){
            	//if num is equal to half of the target. 
            	//This is a special case where the target is even, and we need to ensure that we have at least two nodes with the same value to form the required sum. 
            	//If this condition is satisfied:
                if(num==target/2){
                	//This checks if the frequency of the node value num in the hashMap is exactly 2. 
                	//This is necessary to avoid considering the same node twice. If this condition is satisfied, 
                	//it means two nodes with the same value can be used to form the sum, and the answer is set to 1, indicating success, and the loop is broken.
                	
                    if(hashMap.get(num)==2){
                        answer=1;
                        break;
                    }
                }
                ///b. If the num is not equal to half of the target:
                else{
                    answer=1;
                    break;
                }
            }
        }
        //Finally, the method returns the answer which indicates whether two nodes were found in the binary tree that can be summed up to the given target.
        return answer;
    }
    static void preOrder(TreeNode root,HashMap<Integer,Integer> hs){
        if(root==null){
            return;
        }
        hs.put(root.val,hs.getOrDefault(hs.get(root.val),0)+1 );
        preOrder(root.left,hs);
        preOrder(root.right,hs);
    
    }

}
/*
public int t2Sum(TreeNode A, int B) {
        //Store values of tree nodes in Arraylist
        ArrayList<Integer> list=new ArrayList <>();
        inorder(A, list);
        int i=0,j=list.size()-1;
        //Using two pointer approach Binary Search
        while(i<j){
            if(list.get(i)+list.get(j)==B){
                return 1;
            }
            else if((list.get(i)+list.get(j))>B){
                j--;
            }
            else{
                i++;
            }
        }
        return 0;
    }
    private void inorder(TreeNode root, ArrayList<Integer> list){
        if(root==null){
            return;
        }
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }
}



 * */
