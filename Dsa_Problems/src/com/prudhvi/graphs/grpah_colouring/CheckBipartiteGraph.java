package com.prudhvi.graphs.grpah_colouring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
	Problem Description
			Given a undirected graph having A nodes. A matrix B of size M x 2 is given which represents the edges such that there is an edge between B[i][0] and B[i][1].
			Find whether the given graph is bipartite or not.
			A graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B
			Note:
				There are no self-loops in the graph.
				No multiple edges between two pair of vertices.
				The graph may or may not be connected.
				Nodes are Numbered from 0 to A-1.	
				Your solution will run on multiple test cases. If you are using global variables make sure to clear them.

	Problem Constraints
		1 <= A <= 100000
		1 <= M <= min(A*(A-1)/2,200000)
		0 <= B[i][0],B[i][1] < A


 */
public class CheckBipartiteGraph {

	public static void main(String[] args) {
		int A1 = 3;
		int[][] B1 = {{0, 1}, {0, 2}, {1, 2}};
		System.out.println(isBipartite(A1, B1));

	}
	/*
	Time Complexity:
		The code iterates through all vertices exactly once, setting their color or checking their colors. This takes O(a) time.
		The BFS traversal from each vertex takes O(a + b) time in total because, in the worst case, you visit all vertices and edges once.
		So, the overall time complexity is O(a + b).
	Space Complexity:
		The space complexity is determined by the data structures used.
		The graph array, colour array, and the queue each consume O(a) space.
		Therefore, the overall space complexity is O(a).
	In summary, the code efficiently determines if the input graph is bipartite using BFS traversal. Its time complexity is O(a + b), and its space complexity is O(a), where a is the number of vertices, and b is the number of edges in the graph.
	 */
	private static int isBipartite(int a, int[][] b) {
		//Create an array of ArrayLists called graph, where each ArrayList represents the neighbors of a vertex. The size of the array is a.
		ArrayList<Integer>[] graph=new ArrayList[a];
		for (int i = 0; i < graph.length; i++) {//Initialize the ArrayLists inside the graph array.
			graph[i]=new ArrayList<>();
		}
		for (int i = 0; i < b.length; i++) {
			int u=b[i][0];
			int v=b[i][1];
			graph[u].add(v);
			graph[v].add(u);
		}
		//array to keep track of the color assigned to each vertex. Initialize all elements to -1, indicating that no color has been assigned yet.
		int[] colour=new int[a];
		Arrays.fill(colour,-1);
		for(int i=0;i<a;i++) {
			if (colour[i] == 0 || colour[i] == 1) //If the vertex's color is either 0 or 1, it has already been visited (colored), so skip it.
                continue; // Node already visited
			//If the vertex has not been visited (colored), call the check function to determine if the graph is bipartite.
			if(!check(i,graph,colour)) {
				return 0;//If the check function returns false, indicating that the graph is not bipartite, return 0 to indicate that it's not bipartite.
			}
		}
		//If all vertices are visited and the graph is bipartite, return 1 to indicate that it is bipartite.
		return 1;
	}

	private static boolean check(int s, ArrayList<Integer>[] graph, int[] colour) {
		Queue<Integer> queue=new LinkedList<>();
		queue.add(s);
		colour[s]=0;
		while(!queue.isEmpty()){
			int u=queue.poll();//Dequeue a vertex u from the queue.
			ArrayList<Integer> arr=graph[u];
			//Iterate through the neighbors of u (adjacent vertices) in the graph.
			for(int v:arr) {
				if(colour[v]==-1) {//f the neighbor v has not been colored yet, 
					colour[v]=1-colour[u];//assign the opposite color to it (either 0 or 1) and 
					queue.add(v);//enqueue it.
				//If the neighbor v has the same color as u, return false because the graph is not bipartite.
				}else if(colour[u]==colour[v]) {
					return false;			 
				}
			}
		}
		//If the entire BFS traversal completes without finding any conflicts, return true, indicating that the subgraph starting from s is bipartite.
		return true;
	}
	

	    public int isGraphBipartite(int numberOfNodes, int[][] edges) {
	        int[] nodeColors = new int[numberOfNodes];
	        List<Integer>[] adjacencyList = new ArrayList[numberOfNodes];

	        // Initialize adjacency list
	        for (int i = 0; i < numberOfNodes; i++) {
	            adjacencyList[i] = new ArrayList<>();
	        }

	        // Build the undirected graph
	        for (int i = 0; i < edges.length; i++) {
	            int source = edges[i][0];
	            int destination = edges[i][1];
	            adjacencyList[source].add(destination);
	            adjacencyList[destination].add(source);
	        }

	        // Check each connected component
	        for (int node = 0; node < numberOfNodes; node++) {
	            if (nodeColors[node] == 0) {
	                if (!isComponentBipartite(node, adjacencyList, nodeColors)) {
	                    return 0; // Not bipartite
	                }
	            }
	        }

	        return 1; // Entire graph is bipartite
	    }

	    private boolean isComponentBipartite(int startNode, List<Integer>[] adjacencyList, int[] nodeColors) {
	        Queue<Integer> bfsQueue = new LinkedList<>();
	        nodeColors[startNode] = 1; // Assign first color
	        bfsQueue.add(startNode);

	        while (!bfsQueue.isEmpty()) {
	            int currentNode = bfsQueue.poll();
	            for (int neighbor : adjacencyList[currentNode]) {
	                if (nodeColors[neighbor] == 0) {
	                    nodeColors[neighbor] = 3 - nodeColors[currentNode]; // Alternate color
	                    bfsQueue.add(neighbor);
	                } else if (nodeColors[neighbor] == nodeColors[currentNode]) {
	                    return false; // Same color on both ends => Not bipartite
	                }
	            }
	        }

	        return true;
	    }
	


}
