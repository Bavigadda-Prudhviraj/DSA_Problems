package com.prudhvi.graphs.Dijkstra_Algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/*
	Problem Description
		Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.
		You have to find an integer array D of size A such that:
			D[i]: Shortest distance from the C node to node i.
			If node i is not reachable from C then -1.
		Note:	
			1.There are no self-loops in the graph.
			2.There are no multiple edges between two pairs of vertices.
			3.The graph may or may not be connected.
			4.Nodes are numbered from 0 to A-1.
			5.Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.
	Problem Constraints
		1 <= A <= 1e5
		0 <= B[i][0],B[i][1] < A
		0 <= B[i][2] <= 1e3
		0 <= C < A
 */
public class Dijsktra {

	public static void main(String[] args) {
		int A = 6;//7 6 5 6 0 6 
		int[][]B = {{0,4,9},
				    {3,4,6},
				    {1,2,1},
				    {2,5,1},
				    {2,4,5},
				    {0,3,7},
				    {0,1,1},
				    {4,5,7},
				    {0,5,1}} ;
		int	C = 4;
		int[] distances=dijsktraAlgo(A,B,C);
		System.out.println(Arrays.toString(distances));

	}
	/*
	Here's the time and space complexity analysis for the provided Dijkstra's algorithm implementation:

	**Time Complexity:**
			- Constructing the adjacency list graph by iterating through the edges in the `uv` array takes O(E) time, where E is the number of edges in the graph.
			- The main loop iterates as long as the `minHeap` is not empty. In the worst case, this loop can run for all nodes, resulting in O(V) iterations, where V is the number of nodes.
			- Inside the loop, the code performs operations like adding and removing elements from the priority queue, which takes O(log(V)) time per operation. Therefore, the total time spent inside the loop is O(V * log(V)).
			- For each node, the code explores its neighbors. The number of neighbors a node can have is limited by the number of edges, so this part takes O(E) time.
		- Overall, the dominant time complexity is O(V * log(V) + E), where V is the number of nodes and E is the number of edges.
	
	**Space Complexity:**
			- The space complexity of the code is determined by the data structures used and their sizes.
			- The `distance` array has a size of N, where N is the number of nodes, so it takes O(N) space.
			- The `graph` data structure, represented as an array of `ArrayList<PairD>`, takes O(N + E) space, where N is the number of nodes, and E is the number of edges.
			- The `minHeap` priority queue stores at most V elements (one for each node), so it takes O(V) space.
			- Other variables and objects used in the code take constant space.
		- Overall, the space complexity of the code is O(N + E).
	
	In summary, the time complexity of this Dijkstra's algorithm implementation is O(V * log(V) + E), and the space complexity is O(N + E), where V is the number of nodes, and E is the number of edges in the graph. The space complexity is dominated by the adjacency list representation of the graph and the data structures used for the algorithm.
	 */

	private static int[] dijsktraAlgo(int nodes, int[][] uv, int source) {
		int n=nodes;
		//It initializes an array distance of size n (where n is the number of nodes) to store the shortest distances from the source to all other nodes. 
		int[] distance=new int[n];
		Arrays.fill(distance,Integer.MAX_VALUE);//All distances are initially set to Integer.MAX_VALUE, except for the source node, which is set to 0.
		distance[source]=0;
		//It initializes an adjacency list graph as an array of lists. Each element of the array represents a node, and the list at each element stores the neighboring nodes along with their edge weights.
		ArrayList<PairD>[] graph=new ArrayList[n];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		//It populates the graph by iterating through the edges in the uv array. For each edge (u, v, w), it adds both v to the list of neighbors of u and u to the list of neighbors of v, along with the edge weight w. This step constructs the adjacency list representation of the graph.
		for (int i = 0; i < uv.length; i++) {
			int u=uv[i][0];
			int v=uv[i][1];
			int w=uv[i][2];
			graph[u].add(new PairD(w,v));
			graph[v].add(new PairD(w,u));
		}
		//It initializes a priority queue minHeap to store pairs of the form (weight, destination), where weight is the current distance from the source node, and destination is the node's index. The priority queue is used to always select the node with the smallest distance.
		PriorityQueue<PairD> minHeap=new PriorityQueue<>(new Comparator<PairD>() {
			public int compare(PairD o1, PairD o2) {
				return o1.weight-o2.weight;
			}
		});
		//It adds the source node to the minHeap with a weight of 0 to start the algorithm.
		minHeap.add(new PairD(0, source));
		while(!minHeap.isEmpty()){
			//It extracts the pair with the smallest weight from the minHeap.
			PairD data=minHeap.poll();
			int weight=data.weight;
			int des=data.destination;
			//For each neighbor of the current node, 
			//it calculates the potential new distance by adding the edge weight to the current distance. 
			//If this new distance is less than the stored distance for the neighbor, it updates the distance and adds the neighbor to the minHeap with the new distanc
			ArrayList<PairD> neighbour=graph[des];
			for (int i = 0; i < neighbour.size(); i++) {
				int v=neighbour.get(i).destination;
				int w=neighbour.get(i).weight;
				if(weight+w<distance[v]){
					distance[v]=weight+w;
					minHeap.add(new PairD(weight+w, v));
				}
			}
			
		}
		//After the loop, the distance array contains the shortest distances from the source node to all other nodes. Any nodes that remain with a distance of Integer.MAX_VALUE are considered unreachable and are set to -1.
		for (int i = 0; i < distance.length; i++) {
			if(distance[i]==Integer.MAX_VALUE) {
				distance[i]=-1;
			}
		}
		return distance;
	}
	
	
	
	
	
}
class PairD{
	int weight;
	int destination;
	public  PairD(int weight,int destination){
		this.weight=weight;
		this.destination=destination;
	}
	@Override
	public String toString() {
		return "PairD [weight=" + weight + ", destination=" + destination + "]";
	}
	
}

