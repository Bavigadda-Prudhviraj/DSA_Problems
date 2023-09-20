package com.prudhvi.graphs.topological_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/*
	Problem Description
		Given an directed acyclic graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
		Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.		
		Return the topological ordering of the graph and if it doesn't exist then return an empty array.
		If there is a solution return the correct ordering. If there are multiple solutions print the lexographically smallest one.		
		Ordering (a, b, c) is said to be lexographically smaller than ordering (e, f, g) if a < e or if(a==e) then b < f and so on.
		NOTE:
			There are no self-loops in the graph.
			The graph may or may not be connected.
			Nodes are numbered from 1 to A.
			Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
	Problem Constraints
		2 <= A <= 104
		1 <= M <= min(100000,A*(A-1))
		1 <= B[i][0], B[i][1] <= A

	Input Format
		The first argument given is an integer A representing the number of nodes in the graph.
		The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].

	Output Format
		Return a one-dimensional array denoting the topological ordering of the graph and it it doesn't exist then return empty array.
 */
public class TopologicalSort {
	public static void main(String[] args) {
		int A = 8;// [1, 4, 3, 5, 7, 8, 2, 6]
		int[][] B = {{1,4},{1,2},{4,2},{4,3},{3,2},{5,2},{3,5},{8,2},{8,6}};
		int[]  orderOfCompletionTasks=lexographicalTopologicalSort(A,B);
		System.out.println(Arrays.toString(orderOfCompletionTasks));
	}
	/*
	Time Complexity:
		Constructing the graph: O(E), where E is the number of edges.
		Initializing set and the first loop: O(V), where V is the number of vertices.
		Inside the loop, we visit each edge and each node once, so it's O(E + V).
		Checking for cycles: O(V).
		Overall, the time complexity is O(E + V).
		Note:
			The time complexity of operations like add and pollFirst on a TreeSet is O(log n) on average, where 'n' is the number of elements in the TreeSet. 
			In your code, the TreeSet is used to store nodes with indegree 0. Therefore, in the worst case, where all nodes have indegree 0, and you have to process all nodes, you might perform these operations O(n) times.

			Hence, the overall time complexity of the code is dominated by the O(E + V) complexity associated with constructing the graph and the loop that processes the nodes. 
			The contribution from the TreeSet operations would be a smaller factor in practice and would not change the overall time complexity of the code, making it O(E + V).
	Space Complexity:
		The space complexity is O(V + E), where V is the number of vertices, and E is the number of edges. 
		This is due to the space used by the adjacency list (graph), indegree array, and set.
	 */
	private static int[] lexographicalTopologicalSort(int nodes, int[][] uv) {
		int n=nodes;//The number of nodes (vertices) in the graph.
		int[] lexographicalOrder=new int[n];//An array to store the lexicographically ordered result.
		int index=0;//An index to keep track of the position where nodes are added to the result.
		int[] indegree=new int[n+1];//An array to keep track of the indegree (number of incoming edges) of each node.
		ArrayList<Integer>[] graph=new ArrayList[n+1];// An array of ArrayLists to represent the adjacency list of the graph.
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		//Populate the adjacency list (graph) based on the given edges (uv array) and calculate the indegree of each node. This step prepares the graph for topological sorting.
		for (int i = 0; i < uv.length; i++) {
			int u=uv[i][0];
			int v=uv[i][1];
			graph[u].add(v);
			indegree[v]++;
		}
		//Create a TreeSet named set to store nodes with indegree equal to 0. A TreeSet is used to maintain the nodes in sorted order.
		TreeSet<Integer> set=new TreeSet<>();
		//Iterate through all nodes (from 1 to n) and add nodes with indegree 0 to the set.
		for (int i = 1; i < indegree.length; i++) {
				if(indegree[i]==0)
					set.add(i);
		}
		//Perform the topological sort using a while loop:
		while(!set.isEmpty()){
			int idx=set.pollFirst();//Remove the smallest node (lexicographically) with indegree 0 from the set (set.pollFirst()).
			lexographicalOrder[index]=idx;//Add this node to the lexographicalOrder array at the current index.
			index++;//Increment the index.
			ArrayList<Integer> neighbour=graph[idx];
			//Iterate through the neighbors of this node:
			for (int i = 0; i < neighbour.size(); i++) {
				int ele=neighbour.get(i);
				indegree[ele]--;//Decrement the indegree of each neighbor.
				//If the indegree of a neighbor becomes 0, add it to the set to consider for the next iteration.
				if(indegree[ele]==0) {
					set.add(ele);
				}
			}	
		}
		//After the loop, check if there are any nodes with indegree greater than 0. If there are, this means the graph contains cycles, and it's not possible to perform a valid topological sort. 
		//In such cases, return an empty array.
		for (int i = 1; i < indegree.length; i++) {
			if(indegree[i]>0) {
				return new int[0];
	        }
	    }
		//return the lexographicalOrder array, which now contains the lexicographically smallest topological sort of the graph.
		return lexographicalOrder;
	}
	

	


}

