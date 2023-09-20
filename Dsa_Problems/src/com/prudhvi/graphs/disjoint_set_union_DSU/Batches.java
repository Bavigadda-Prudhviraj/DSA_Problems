package com.prudhvi.graphs.disjoint_set_union_DSU;
import java.util.HashMap;
import java.util.Map;
/*
	Problem Description
		A students applied for admission in IB Academy. An array of integers B is given representing the strengths of A people i.e. B[i] represents the strength of ith student.
		Among the A students some of them knew each other. A matrix C of size M x 2 is given which represents relations where ith relations depicts that C[i][0] and C[i][1] knew each other.
		All students who know each other are placed in one batch.
		Strength of a batch is equal to sum of the strength of all the students in it.
		Now the number of batches are formed are very much, it is impossible for IB to handle them. So IB set criteria for selection: All those batches having strength at least D are selected.
		Find the number of batches selected.
		NOTE: If student x and student y know each other, student y and z know each other then student x and student z will also know each other.
	Problem Constraints
		1 <= A <= 105
		1 <= M <= 2*105
		1 <= B[i] <= 104
		1 <= C[i][0], C[i][1] <= A
		1 <= D <= 109
	example input
		A = 7
		B = [1, 6, 7, 2, 9, 4, 5]
		C = [[1, 2]
	    	 [2, 3] 
	   `	 [5, 6]
	    	 [5, 7]  ]
		D = 12
		o/p=2
	Initial Batches :
	Batch 1 = {1, 2, 3} Batch Strength = 1 + 6 + 7 = 14
	Batch 2 = {4} Batch Strength = 2
	Batch 3 = {5, 6, 7} Batch Strength = 9 + 4 + 5 = 18
	Selected Batches are Batch 1 and Batch 2.
*/
public class Batches {

	public static void main(String[] args) {
		int A = 7;
		int[] B = {1, 6, 7, 2, 9, 4, 5};
		int[][] C = {{1, 2},
			    	 {2, 3},
			    	 {5, 6},
			    	 {5, 7}};
		int	D = 12;
		int batchCount=batchDivision(A,B,C,D);
		System.out.println(batchCount);

	}
	/*
	Time Complexity (batchDivision method):

				1.BatchDsu Initialization: Creating an instance of the BatchDsu class and initializing it takes O(nodes) time.				
				2.Inserting Relations: Looping through the relation array and inserting relations into the DSU data structure (graph) takes O(relation.length) time.				
				3.Finding Grandparents: Looping through the relation array and performing findGrandParent operations on nodes takes O(relation.length * log(nodes)) time on average due to path compression.
										NOTE:In a standard Disjoint Set Union (DSU) structure with path compression, the amortized time complexity of the find operation is indeed very close to O(1). 
											 However, this amortized O(1) complexity assumes a balanced tree structure. 
											 In practice, the path compression optimizes the tree structure significantly, making most find operations take nearly constant time.
														1.While each individual findGrandParent operation is amortized to nearly O(1) due to path compression, the total time complexity of the batchDivision method becomes O(N) because you perform these operations for all relations. 
														  It's not strictly O(1) because the number of relations affects the total runtime, and in the worst case, when you have to perform a find operation for each relation, it becomes linear.
														So, the analysis is as follows:
														1.Amortized time complexity of a single findGrandParent: Nearly O(1).
														2.Total time complexity of batchDivision: O(N), where N is the number of relations.
														our code is efficient and has a linear time complexity in the worst case, which is typically acceptable for many practical scenarios.  
				4.Counting Strength by Group: Looping through the arr array (which contains the parent groups of nodes) and counting the total strength of each group takes O(nodes) time.				
				5.Counting Groups: Looping through the map to count the number of groups that have a total strength greater than or equal to capacity takes O(nodes) time.		
		The overall time complexity of the batchDivision method is dominated by the operations on the DSU data structure and is approximately O(relation.length).
	Space Complexity (batchDivision method):
				1.BatchDsu Data Structure: The BatchDsu object stores the rank and parent arrays, each of size nodes + 1, resulting in a space complexity of O(nodes).	
				2.HashMap (map): The map stores information about groups, and its size is proportional to the number of distinct groups formed. In the worst case, there can be as many groups as there are nodes, so the space complexity for the map is O(nodes).		
				3.Other variables and constants use negligible space compared to the data structures.	
		The overall space complexity of the batchDivision method is O(nodes).
				
	In summary, the batchDivision method has a time complexity of approximately O(relation.length * log(nodes)) and a space complexity of O(nodes) due to the use of the DSU data structure and the map to track groups.
	 */
	/*
	This is the entry point of the batch division algorithm. It takes several parameters:
		nodes: The number of nodes.
		strength: An array representing the strengths of nodes.
		relation: A 2D array representing relationships between nodes.
		capacity: The capacity constraint for each group.
	 */
	private static int batchDivision(int nodes, int[] strength, int[][] relation, int capacity) {
		BatchDsu graph=new BatchDsu(nodes);//An instance of the BatchDsu class is created. This class appears to implement a disjoint-set union (DSU) data structure to manage groups.
		//iterates through the relationships defined in the relation array and inserts them into the graph using graph.insetIntoGraph(relation[i][0], relation[i][1]);. This operation essentially combines nodes into groups based on relationships.
		for (int i = 0; i < relation.length; i++) {
			graph.insetIntoGraph(relation[i][0], relation[i][1]);
		}
		// iterates through the relationships again. However, in this loop, it calls graph.findGrandParent(relation[i][0]); and graph.findGrandParent(relation[i][1]);. This appears to be an attempt to optimize the DSU structure by finding the grandparent of nodes.
		//it is imp because it makes all group members parent same
		for (int i = 0; i < relation.length; i++) {
			 graph.findGrandParent(relation[i][0]);
			 graph.findGrandParent(relation[i][1]);
		}
		int[] arr=graph.parent;//The parent array of the BatchDsu instance is retrieved. This array represents the group assignments for each node.
		//A HashMap is created to map group IDs to their total strengths.
		HashMap<Integer, Integer> map=new HashMap<>();
		//iterates through the parent array, and for each node, it calculates the group (represented by the parent) and accumulates the strength of nodes in the same group.
		for (int i = 1; i < arr.length; i++) {
			int group=arr[i];
			if(map.containsKey(group)) {
				map.put(group, map.get(group)+strength[i-1]);
			}else {
				map.put(group, strength[i-1]);
			}
		}
		int groups=0;//a variable groups to count the number of groups that meet the capacity constraint.
		//iterates through the HashMap, where each entry represents a group ID and its total strength.
		for(Map.Entry<Integer,Integer> ele:map.entrySet()) {
			//it calculates the number of groups by dividing the total strength of each group by the specified capacity. If the result is greater than 0, it increments the groups counter.
			int cap=ele.getValue()/capacity;
			if(cap>0) {
				groups+=1;
			}
		}
		//returns the groups variable, which represents the number of groups formed based on the relationships and strength with the capacity constraint.
		return groups;
	}

}
class BatchDsu{
	int[] rank;
	int[] parent;
	public BatchDsu(int n) {
		rank=new int[n+1];
		parent=new int[n+1];
		for (int i = 0; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	public int findGrandParent(int node) {
		if(node==parent[node]) {
			return node;
		}
		return parent[node]=findGrandParent(parent[node]);
	}
	public void insetIntoGraph(int u,int v){
		int gpu=findGrandParent(u);
		int gpv=findGrandParent(v);
		int uRank=rank[gpu];
		int vRank=rank[gpv];
		if(gpu==gpv){return;}
		if(uRank<vRank) {
			parent[gpu]=gpv;
		}
		else if(vRank<uRank){
			parent[gpv]=gpu;
		}else {
			parent[gpu]=gpv;
			rank[gpu]+=uRank+1;
		}
	}
}
/*
	Here's an explanation of the key components and how they work:

	BatchDsu Class: 
		This is an implementation of a Disjoint Set Union (DSU) data structure. It's used to maintain and manipulate connected components based on the provided relationships.
						1.rank and parent arrays are used to store the rank and parent of each node.
						2.findGrandParent finds the ultimate ancestor (grandparent) of a node with path compression.
						3.insetIntoGraph performs a union operation between two nodes based on their ranks.
						4.isNodesFromSameConnectedGraph checks if two nodes belong to the same connected component.

	batchDivision Function: 
		This function takes several inputs, including the number of nodes, their strengths, the relationships between nodes, and a capacity constraint.
				1.An instance of the BatchDsu class, named graph, is created to manage the connected components based on the relationships.		
				2.The relationships are inserted into graph using the insetIntoGraph method.			
				3.The grandparent of each node in the relationships is found. This step is likely intended to ensure that the DSU structure is properly updated.				
				4.A HashMap called map is used to store the total strength of nodes in each connected component (group or batch).				
				5.The loop iterates through each node and updates the strength in the corresponding batch in the map based on the connected component it belongs to.				
				6.Finally, the code calculates the number of groups (batches) where the total strength exceeds the given capacity.				
				7.It seems that the code is designed to find how many batches can be formed, given the strength and relationships of nodes, while ensuring that each batch's total strength does not exceed the provided capacity constraint. The code effectively uses DSU to manage and track connected components and their strengths.
				
*/