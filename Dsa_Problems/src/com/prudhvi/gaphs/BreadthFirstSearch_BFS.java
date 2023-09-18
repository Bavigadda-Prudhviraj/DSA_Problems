package com.prudhvi.gaphs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
	Problem Description
		Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node
		B[i][0] to node B[i][1].
		Find whether a path exists from node 1 to node A.
		Return 1 if path exists else return 0.
		NOTE:
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
public class BreadthFirstSearch_BFS {

	public static void main(String[] args) {
		int destination = 1;
		int[][] s_d= {{1, 2},
				      {2, 3},
				      {4, 3} };
		int isWayExist=breadthFirstSearch(s_d,destination);
		System.out.println(isWayExist);

	}
	/*
	Time Complexity (breadthFirstSearch function):
			1.Graph Initialization: 
				You initialize an adjacency list graph based on the input s_d. The loop to initialize the graph runs in O(E) time, where E is the number of edges in the graph. In this case, the number of edges is equal to s_d.length, so this part contributes O(s_d.length) to the time complexity.
			2.Breadth-First Search (BFS): 
				The BFS algorithm traverses the graph starting from node 1 and checks if it can reach the destination node. The BFS algorithm's time complexity is O(V + E), where V is the number of vertices and E is the number of edges.
					1.In this case, V is n, the destination node, and E is s_d.length, the number of edges.
					2.The BFS algorithm typically visits each vertex and edge once.
		So, the overall time complexity of the breadthFirstSearch function is O(n + s_d.length), which simplifies to O(n) in most practical cases.
	
	Space Complexity (breadthFirstSearch function):
			1.Graph Data Structure: 
				You create an adjacency list graph (graph) to represent the input connections. The space used by the graph depends on the number of edges, which is equal to s_d.length. So, the space complexity for the graph is O(s_d.length).
			2.Visited Array and Queue: 
				You use a boolean array (visited) to keep track of visited nodes and a queue (queue) for BFS traversal. Both the visited array and queue have sizes proportional to the number of vertices (n), so they each consume O(n) space.
			3.Other variables and constants use negligible space compared to the graph, visited array, and queue.	
		Therefore, the overall space complexity of the breadthFirstSearch function is O(s_d.length + n), which simplifies to O(n) in most practical cases.	
	
	In summary, the breadthFirstSearch function has a time complexity of O(n) and a space complexity of O(n) in most practical scenarios.			
	
	 */
	//which takes two parameters: s_d (an array of source-destination pairs representing edges in the graph) and destination (the node you want to check if there is a path to).
	private static int breadthFirstSearch(int[][] s_d, int destination) {
		int n=destination;//This line sets n to the value of the destination, which represents the number of nodes in the graph.
		//An array of ArrayLists is created to represent the graph. Each index in the array corresponds to a node, and the ArrayList at that index stores the neighbors of that node.
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		//loop initializes each ArrayList in the graph array.
	    for (int i = 0; i <=n; i++) { 
	        graph[i] = new ArrayList<>();
	    }
	    //This loop iterates through the s_d array, which contains source-destination pairs representing edges in the graph.
		for (int i = 0; i < s_d.length; i++) {
			// extract the source and destination nodes from the current pair.
			int s=s_d[i][0];
			int d=s_d[i][1];
			//adds the destination node d to the ArrayList at index s, representing an edge from node s to node d.
			graph[s].add(d);
		}
		//An array of booleans, visited, is created to keep track of visited nodes in the BFS traversal. Each index corresponds to a node, and initially, all nodes are marked as unvisited.
	    boolean[] visited = new boolean[n + 1];
	    //A queue is created for BFS traversal. It starts with node 1 (assuming the graph is 1-based).
	    Queue<Integer> queue = new LinkedList<>();
	    queue.add(1);
	    //The while loop performs BFS traversal:
	    while (!queue.isEmpty()) {
	        int ele = queue.poll();//Get the next node from the front of the queue.
	        visited[ele] = true;//Mark the current node as visited.
	        ArrayList<Integer> arr = graph[ele];// Get the neighbors of the current node from the graph array.
	        //Loop through the neighbors and enqueue any unvisited neighbors.
	        for (int i = 0; i < arr.size(); i++) {
	            int neighbor = arr.get(i);
	            if (!visited[neighbor]) {
	                queue.add(neighbor);
	            }
	        }
	    }
	    //After the BFS traversal, the method returns visited[destination] ? 1 : 0;, indicating whether there is a path from node 1 to the given destination node. 
	    //If visited[destination] is true, there is a path, and the method returns 1; otherwise, it returns 0.
	    return visited[destination] ? 1 : 0;
	}

}
