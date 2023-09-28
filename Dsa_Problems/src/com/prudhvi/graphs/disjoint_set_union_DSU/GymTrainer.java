package com.prudhvi.graphs.disjoint_set_union_DSU;
import java.util.HashSet;
/*
	Problem Description
		You are the trainer of a gym and there are A people who come to your gym.
		Some of them are friends because they walk together, and some of them are friends because they talk together.
		But people become possessive about each other, so a person cannot walk with one friend and talk with another. Although he can walk with two or more people or talk with two or more people.
		You being the trainer, decided to suggest each one of the 2 possible diets. But friends, being friends will always have the same diet as all the other friends are having.
		Find and return the number of ways you can suggest each of them a diet.
		As the number of ways can be huge, return the answer modulo 109+7.
		NOTE: 
			If there is any person who walks with one person and talks with the another person, then you may return 0.
	Problem Constraints
		1 <= A, N, M <= 105
		1 <= B[i][0], B[i][1], C[i][0], C[i][1] <= A
 */

public class GymTrainer {

	public static void main(String[] args) {
		int A=20;//512
		int[][] B= {{1,5},{4,6},{18,3},{4,5},{15,9},{15,4}};
		int[][] C={{13,20},{7,20},{8,19},{13,7},{13,8},{2,19}};
		System.out.println(solve(A, B, C));

	}
	/*
	Time Complexity:
		The code iterates through the talk and walk arrays once, each time taking constant time operations for adding elements to the set and checking if elements are in the set. Therefore, this part takes O(talk.length + walk.length) time.
		The DsuGym operations for union and grandparent retrieval typically have an amortized time complexity of O(α(n)), where α(n) is the inverse Ackermann function, which grows extremely slowly and can be considered a constant for practical purposes.
		Calculating the result and taking modulo is a constant time operation.
		So, the overall time complexity of this code can be considered as O(talk.length + walk.length).
	Space Complexity:
		The primary space usage is for the uniqueGroupOfFriends set, which can contain up to A distinct group IDs. Therefore, the space complexity is O(A).
	
	In summary, this code efficiently calculates the number of unique groups of friends who can talk and walk together based on given relationships. Its time complexity is O(talk.length + walk.length), and its space complexity is O(A).
	 */
	public static int solve(int A, int[][] talk, int[][] walk) {
        HashSet<Integer> uniqueGroupOfFrineds=new HashSet<>();//to store unique groups of friends.
        //Iterate through the talk array and add both the first and second elements of each talk pair to the uniqueGroupOfFriends set. 
        //This step collects all unique groups of friends that are involved in talking.
        for(int i=0;i<talk.length;i++){
            uniqueGroupOfFrineds.add(talk[i][0]);
            uniqueGroupOfFrineds.add(talk[i][1]);
        }
        //Check if either the first or second element of each walk pair is in the uniqueGroupOfFriends set. 
        //If any of them is, return 0 because it means that someone from the talking group is walking with someone else, which is not allowed.
        for(int i=0;i<walk.length;i++){
            if(uniqueGroupOfFrineds.contains(walk[i][0]) || uniqueGroupOfFrineds.contains(walk[i][1])){
                return 0;
            }
        }
        //Create an instance of the DsuGym class with A+1 nodes.class implements a disjoint-set data structure (Union-Find) for grouping friends.
        DsuGym dsu=new DsuGym(A+1);
        //Use the union method of the DsuGym class to merge the groups represented by the first and second elements of each talk pair.
        for(int i=0;i<talk.length;i++){
        	dsu.union(talk[i][0],talk[i][1]);
        }
        //Use the union method of the DsuGym class to merge the groups represented by the first and second elements of each walk pair.
        for(int i=0;i<walk.length;i++){
        	dsu.union(walk[i][0],walk[i][1]);
        }
       uniqueGroupOfFrineds.clear();//Clear the uniqueGroupOfFriends set to prepare for populating it with unique group representatives.
       //Add the grandparent (the ultimate root representative) of each friend to the uniqueGroupOfFriends set. This step ensures that you have a set of unique group representatives after all unions have been performed.
       for (int i = 1; i <= A; i++) {
          uniqueGroupOfFrineds.add(dsu.grandParent(i));
       }
       //Initialize the mod variable to 1000000007 and the result variable to 1.
       int mod = 1000000007;
       long result = 1;
       //Multiply the result by 2 and take the result modulo mod. This step effectively calculates 2^number_of_unique_group_representatives modulo mod.
       for (int i = 0; i < uniqueGroupOfFrineds.size(); i++) {
           result = (result * 2) % mod;
       }
       return (int) result;
    }

}
class DsuGym{
	int[] parent;
	public DsuGym(int nodes) {
		parent=new int[nodes];
		for (int i = 1; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	public int grandParent(int a){
		if(a==parent[a]) {
			return a;
		}
		return parent[a]=grandParent(parent[a]);
	}
	public void union(int u,int v){
		int gpu=grandParent(u);
		int gpv=grandParent(v);
		if(gpu==gpv)return ;
		else if(gpu<gpv)
			parent[gpv]=parent[gpu];
		else if(gpv<gpu)
			parent[gpu]=parent[gpv];
	}
}


