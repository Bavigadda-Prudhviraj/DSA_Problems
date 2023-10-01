package com.prudhvi.graphs.Bellman_Ford_Algorithm;


import java.util.Arrays;

/*
	Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of all the vertex's from the source vertex S. 
	If a vertices can't be reach from the S then mark the distance as 10^8. 
	Note: If the Graph contains a negative cycle then return an array consisting of only -1.
	Your Task:
		You don't need to read input or print anything. 
		Your task is to complete the function bellman_ford( ) which takes a number of vertices V and an E-sized list of lists of three integers where the three integers are u,v, and w; 
		denoting there's an edge from u to v, which has a weight of w and source node S as input parameters and returns a list of integers where the ith integer denotes the distance of an ith node from the source node.
		
 		some node isn't possible to visit, then its distance should be 100000000(1e8). Also, If the Graph contains a negative cycle then return an array consisting of a single -1.
	Expected Time Complexity: O(V*E).
	Expected Auxiliary Space: O(V).
 */
public class BellmanFordAlgorithm {

	public static void main(String[] args) {
		int[][] A= {{0,1,5},{1,0,3},{1,2,-1},{2,0,1}};
		int source=2;
		int nodes=3;
		int[] bellmanArr=bellamFord(nodes,A,source);
		System.out.println(Arrays.toString(bellmanArr));

	}
	/*
	Time Complexity:
			The outer loop runs for nodes - 1 iterations.
			The inner loop iterates through all edges in the a array, which has a length of a.length.
			The time complexity of the inner loop is O(a.length).
		Overall, the time complexity is O(nodes * a.length), where a.length represents the number of edges in the graph.
	Space Complexity:
			The code uses two arrays of size nodes for distance and path, which results in a space complexity of O(nodes).
		The space complexity is further influenced by the size of the input graph, which is represented by the a array. In the worst case, where all nodes are connected to each other, the space complexity can be O(nodes^2) due to the a array.
	In summary, the Bellman-Ford algorithm has a time complexity of O(nodes * a.length) and a space complexity of O(nodes) or potentially O(nodes^2) depending on the graph structure.
	 */
	private static int[] bellamFord(int nodes,int[][] a, int source) {
		// Create arrays to store distances and paths
		int[] distance=new int[nodes];
		int[] path=new int[nodes];
		// Initialize path with -1 (indicating no predecessor) and distance with a large value
		Arrays.fill(path, -1);
		Arrays.fill(distance,(int)(1e8));
		// Set the distance of the source node to 0
		distance[source]=0;
		// Loop to relax edges (V - 1) times, where V is the number of nodes
		for(int i=0;i<nodes-1;i++) {
			for (int j = 0; j < a.length; j++) {
				int u=a[j][0];// Source node of the edge
				int v=a[j][1];// Destination node of the edge
				int w=a[j][2];// Weight of the edge
				boolean isUpdating=true;
				// Relax the edge (u, v)
				if(distance[u]+w<distance[v] && distance[u]!=Integer.MAX_VALUE) {
					distance[v]=distance[u]+w;
					path[i]=u;// Update the path to track the previous node
					isUpdating=false;
				}
				if(isUpdating) {
					break;// Optimization: if no updates were made in this iteration, stop early
				}
			}
		}
		//Loop deduction
		for(int i=0;i<a.length;i++) {
			int u=a[i][0];
			int v=a[i][1];
			int w=a[i][2];
			if(distance[u]+w<distance[v] && distance[u]!=Integer.MAX_VALUE) {
				return new int[]{-1};
			}	
		}	
		return distance;
	}
}
/*
	Explanation of the code:

		1.The bellmanFord function takes three arguments: 
				nodes (the number of nodes in the graph), a (an array representing the edges of the graph with weights), and source (the source node from which to find the shortest paths).
		2.It initializes two arrays: 
				distance to store the shortest distances from the source node to all other nodes, and path to store the predecessor nodes in the shortest paths.
		3.It sets the initial values in the distance and path arrays.
		4.The code then enters a loop that runs for nodes - 1 iterations. 
		5.In each iteration, it iterates through all the edges in the a array and tries to relax each edge. 
		  If it finds a shorter path to a node v through node u, it updates the distance and path arrays.
		6.Inside the loop, there is an optimization check (isUpdating) to break out of the loop early if no updates were made in the current iteration.
		7.After the loop, there is another loop to check for negative weight cycles. If a negative weight cycle is detected, the function returns [-1].
		
		Finally, if no negative weight cycles were found, the function returns the distance array, which contains the shortest distances from the source node to all other nodes.
 */
