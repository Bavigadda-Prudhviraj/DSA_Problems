package com.prudhvi.graphs.topological_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
	Problem Description
		Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
		Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
		NOTE:
			The cycle must contain atleast two nodes.
			There are no self-loops in the graph.
			There are no multiple edges between two nodes.
			The graph may or may not be connected.
			Nodes are numbered from 1 to A.
			Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
	Problem Constraints
		2 <= A <= 105
		1 <= M <= min(200000,A*(A-1))	
		1 <= B[i][0], B[i][1] <= A
 */
public class CycleDirectedGraph {

	public static void main(String[] args) {
		int nodes = 5;
		int[][]	B = {{1,2},{1,3},{2,3},{1,4},{4,3},{4,5},{3,5}};
		int isCycleExist=cycleExist(nodes,B);
		System.out.println(isCycleExist);

	}
	/*
	Time Complexity (cycleExist function):
				1.Graph Initialization: You initialize an adjacency list graph (graph) based on the input 2D array b. The loop to initialize the graph runs in O(E) time, where 'E' is the number of edges in the graph. 
				                      In this case, the number of edges is equal to b.length, so this part contributes O(b.length) to the time complexity.	
				2.In-Degree Calculation: You calculate the in-degrees for each node. This loop runs in O(b.length) time, where 'b.length' is the number of edges.
				3.Queue Initialization: You initialize a queue with nodes that have no incoming edges (in-degree equals 0). The initialization step takes O(n) time because you iterate through the indegree array once, where 'n' is the number of nodes.
				4.Topological Sorting (BFS): You perform a topological sorting of the nodes using BFS. The BFS algorithm's time complexity is O(V + E), where V is the number of vertices (nodes) and E is the number of edges.
						1.In this case, V is n, the number of nodes.
						2.The BFS algorithm typically visits each vertex and edge once.
			So, the overall time complexity of the cycleExist function is O(n + b.length) due to graph initialization and BFS traversal.
	 Space Complexity (cycleExist function):
				1.Graph Data Structure: You create an adjacency list graph (graph) to represent the relationships between nodes. 
										The space used by the graph depends on the number of edges, which is equal to the length of the input array b. So, the space complexity for the graph is O(b.length).
				2.In-Degree Array: You create an array indegree to store the in-degrees of each node. The size of the indegree array is proportional to the number of nodes (n), so it consumes O(n) space.
				3.Queue and Other Variables: The space used by the queue and other variables is negligible compared to the graph and the indegree array.
			Therefore, the overall space complexity of the cycleExist function is O(n + b.length) due to the graph and the indegree array.
				
	In summary, the cycleExist function has a time complexity of O(n + b.length) and a space complexity of O(n + b.length).
					 
	 */
	private static int cycleExist(int nodes, int[][] b) {
		int n=nodes;
		//It initializes an adjacency list graph, where each node is represented by an ArrayList of integers. The index of the ArrayList corresponds to the node number.
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		//It initializes an array indegree to keep track of the in-degrees (the number of incoming edges) for each node.
		int[] indegree=new int[n+1];
		//It populates the graph and indegree based on the input 2D array b, where each pair [source, destination] represents a directed edge from source to destination. 
		for (int i = 0; i < b.length; i++) {
			int source=b[i][0];
			int destination=b[i][1];
			//It adds destination to the ArrayList at index source in the graph and increments the in-degree of destination by one.
			graph[source].add(destination);
			indegree[destination]++;
		}
		//It initializes a queue queue and adds all nodes with an in-degree of 0 to the queue. These are the starting nodes for the traversal.
		Queue<Integer> queue=new LinkedList<>();
		for (int i = 1; i < indegree.length; i++) {
			if(indegree[i]==0) {
				queue.add(i);
			}
		}
		//It enters a loop where it processes nodes in a topological order:
		while(!queue.isEmpty()){
			//It dequeues a node from the queue.
			int idx=queue.poll();
			ArrayList<Integer> neighbour=graph[idx];
			//It iterates through the neighbors of the dequeued node and decrements their in-degrees.
			for (int i = 0; i < neighbour.size(); i++) {
				int ele=neighbour.get(i);
				indegree[ele]--;
				//If the in-degree of a neighbor becomes 0 after decrementing, it means that all incoming edges have been processed, so it adds that neighbor to the queue.
				if(indegree[ele]==0) {
					queue.add(ele);
				}
			}
		}
		//After processing all nodes, it checks if there are any nodes remaining with in-degrees greater than 0. If such nodes exist, it returns 1 to indicate the presence of a cycle in the graph. 
		for (int i = 1; i < indegree.length; i++) {
			if(indegree[i]>0) {
				return 1;
			}
		}
		//Otherwise, it returns 0, indicating that there is no cycle in the graph.
		return 0;
	}

}
