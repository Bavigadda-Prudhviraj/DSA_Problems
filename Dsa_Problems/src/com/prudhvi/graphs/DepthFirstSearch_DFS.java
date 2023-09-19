package com.prudhvi.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
	Problem Description
		You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.
		Given 2 towns find whether you can reach the first town from the second without repeating any edge.
		B C : query to find whether B is reachable from C.
		Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).
		There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i for every 1 <= i < N.
		NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.
	Problem Constraints
		1 <= N <= 100000
 */
public class DepthFirstSearch_DFS {

	public static void main(String[] args) {
		int[] A = {1, 1, 2};
		int	  B = 1;
		int	  C = 2;
		int wayExistDFS=DepthFirstSearch_DFS(A,B,C);
		System.out.println(wayExistDFS);
		int wayExistBFS=bfs(A,B,C);
		System.out.println(wayExistBFS);
	
		
	}
	/*
	Time Complexity (DepthFirstSearch_DFS function):
		Graph Initialization: 
			You initialize an adjacency list graph based on the input array a. The loop to initialize the graph runs in O(n) time, where 'n' is the length of the input array a.
		Depth-First Search (DFS): 
			The DFS algorithm traverses the graph starting from node c and checks if it can reach node b. The DFS algorithm's time complexity is O(V + E), where V is the number of vertices and E is the number of edges.
				1.In this case, V is n, the number of nodes in the graph.
				2.The DFS algorithm typically visits each vertex and edge once.
		So, the overall time complexity of the DepthFirstSearch_DFS function is O(n) for graph initialization and O(n) for the DFS traversal, resulting in a total time complexity of O(n).
	Space Complexity (DepthFirstSearch_DFS function):
		Graph Data Structure: You create an adjacency list graph (graph) to represent the input connections. The space used by the graph depends on the number of edges, which is equal to the length of the input array a. So, the space complexity for the graph is O(n).
		Visited Array: You use a boolean array (visited) to keep track of visited nodes during DFS traversal. The size of the visited array is proportional to the number of vertices (n), so it consumes O(n) space.
		Other variables and constants use negligible space compared to the graph and visited array.
		Therefore, the overall space complexity of the DepthFirstSearch_DFS function is O(n) due to the graph and the visited array.
		
	In summary, both the DepthFirstSearch_DFS function and the dfsRec function have a time complexity of O(n) and a space complexity of O(n) in most practical cases. 
	 */
	// This is the beginning of the DepthFirstSearch_DFS method, which takes three parameters: a (an array representing the graph edges), b (the destination node), and c (the source node).
	private static int DepthFirstSearch_DFS(int[] a, int b, int c) {
		int n=a.length;//This line calculates the number of nodes in the graph, assuming that the graph is 1-based (the array a contains source nodes for each index from 0 to n-1).
		//An array of ArrayLists is created to represent the graph. Each index in the array corresponds to a node, and the ArrayList at that index stores the neighbors of that node.
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		//The following for loop initializes each ArrayList in the graph array.
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		//This loop iterates through the a array, which contains source nodes for edges in the graph.
		for (int i = 0; i < a.length; i++) {
			//extract the source and destination nodes for the current edge. Since the graph is 1-based, d is set to i+1.
			int s=a[i];
			int d=i+1;
			//adds the destination node d to the ArrayList at index s, representing an edge from node s to node d.
			graph[s].add(d);
		}
		//An array of booleans, visited, is created to keep track of visited nodes during the DFS traversal. Each index corresponds to a node, and initially, all nodes are marked as unvisited.
		boolean[] visited=new boolean[n+1];
		//The DFS traversal is initiated by calling the dfsRec method with the source node c, the graph, and the visited array as parameters.
		dfsRec(c,graph,visited);
		//After the DFS traversal is completed, the method returns 1 if the destination node b is marked as visited (indicating that there is a path from c to b), otherwise, it returns 0.
		return visited[b]?1:0;
	}
	//This dfsRec method responsible for performing the recursive DFS traversal.
	private static void dfsRec(int u, ArrayList<Integer>[] graph, boolean[] visited) {
		visited[u]=true;//Mark the current node u as visited.
		//Loop through the neighbors of the current node using .
		for (int i = 0; i < graph[u].size(); i++) {
			//Get the neighbor v of the current node u.
			int v=graph[u].get(i);
			//Check if the neighbor v has not been visited (if(visited[v]==false)), and if so, recursively call dfsRec(v, graph, visited) to explore that neighbor.
			if(visited[v]==false) {
				dfsRec(v, graph, visited);
			}
		}
		
	}

	private static int bfs(int[] a, int b, int c) {
		int n=a.length;
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		for (int i = 0; i < a.length; i++) {
			int s=a[i];
			int d=i+1;
			graph[s].add(d);
		}
		
		boolean[] visited=new boolean[n+1];
		Queue<Integer> queue=new LinkedList<>();
		queue.add(c);
		while(!queue.isEmpty()){
			int index=queue.poll();
			visited[index]=true;
			ArrayList<Integer> arr=graph[index];
			for (int i = 0; i < arr.size(); i++) {
				int ele=arr.get(i);
				if(!visited[ele]) {
					queue.add(ele);
				}
				
			}
			
		}
		return visited[b]?1:0;
	}

}
