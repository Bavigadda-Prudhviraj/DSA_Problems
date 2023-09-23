package com.prudhvi.graphs.minimum_spanning_tree_MST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

/*
	Problem Description
		There are A islands and there are M bridges connecting them. Each bridge has some cost attached to it.
		We need to find bridges with minimal cost such that all islands are connected.
		It is guaranteed that input data will contain at least one possible scenario in which all islands are connected with each other.
	Problem Constraints
		1 <= A, M <= 6*104
		1 <= B[i][0], B[i][1] <= A
		1 <= B[i][2] <= 103
	Input Format
	The first argument contains an integer, A, representing the number of islands.
	The second argument contains an 2-d integer matrix, B, of size M x 3 where Island B[i][0] and B[i][1] are connected using a bridge of cost B[i][2].
 */
public class Prims_Algorithm_MST {

	public static void main(String[] args) {
		int A = 4;
		int[][] B = {{1, 2, 1},
				     {2, 3, 4},
				     {1, 4, 3},
				     {4, 3, 2},
				     {1, 3, 10}};
		int  minCost=primsAlgo(A,B);
		System.out.println(minCost);

	}
	/*
	Time Complexity:
			1.Constructing the graph by iterating through the edges in the b array and adding them to the adjacency list representation takes O(E) time, where E is the number of edges in the graph.
			2.The main loop iterates as long as the minHeap is not empty, which in the worst case could be E iterations.
			3.Inside the loop, the code performs operations like adding and removing elements from the priority queue, which takes O(log(E)) time per operation. Therefore, the total time spent inside the loop is O(E * log(E)).
			4.In each iteration, the code explores the neighbors of the current node and adds them to the minHeap, which takes O(deg(v)) time, where deg(v) is the degree of the node v.
		Overall, the dominant time complexity is O(E * log(E)) due to the operations involving the priority queue.
	Space Complexity:
			1.The space complexity of the code is determined by the data structures used and their sizes.
			2.The graph data structure, represented as an array of ArrayList<PairPA>, takes O(N + E) space, where N is the number of nodes and E is the number of edges.
			3.The minHeap priority queue stores at most E elements, so it takes O(E) space.
			4.The visited array has a size of (N + 1), taking O(N) space.
			5.Other variables and objects used in the code take constant space.
		Overall, the space complexity of the code is O(N + E).
		
	In summary, the time complexity of this Prim's algorithm implementation is O(E * log(E)), and the space complexity is O(N + E), where E is the number of edges, and N is the number of nodes in the graph. The space complexity is dominated by the adjacency list representation of the graph.
	 */
	private static int primsAlgo(int node, int[][] b) {
		int n=node;
		//Create an array of ArrayLists called graph, where each ArrayList represents the neighbors of a node. The size of the array is n+1, and each index corresponds to a node.
		ArrayList<PairPA>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();//Initialize the ArrayLists inside the graph array.
		}
		for (int i = 0; i < b.length; i++) {
			//For each edge in the b array, extract the source node (u), destination node (v), and edge weight (w).
			int u=b[i][0];
			int v=b[i][1];
			int w=b[i][2];
			//Add an edge between u and v with weight w by adding a PairPA object to both graph[u] and graph[v]. This represents that there is an undirected edge between these two nodes with the given weight.
			graph[u].add(new PairPA(w, v));
			graph[v].add(new PairPA(w, u));
		}
		//Create a PriorityQueue called minHeap to store edges by their weights. The custom comparator ensures that edges are dequeued in ascending order of weight.
		PriorityQueue<PairPA> minHeap=new PriorityQueue<>(new Comparator<PairPA>() {
			@Override
			public int compare(PairPA o1, PairPA o2) {
				return o1.w-o2.w;
			}
		});
		//Initialize an ArrayList for extracting the neighbors of the first node (graph[1]).
		ArrayList<PairPA> arr=graph[1];
		//Add all edges from arr to the minHeap to kick start the algorithm.
		for (int i = 0; i < arr.size(); i++) {
			minHeap.add(arr.get(i));
		}
		//Create a boolean array called visited to keep track of visited nodes. 
		boolean[] visited=new boolean[n+1];
		visited[1]=true;//Mark the first node as visited (visited[1] = true).
		int cost=0;//variable will keep track of the total weight of the minimum spanning tree.
		//Start a loop that continues as long as the minHeap is not empty.
		while(!minHeap.isEmpty()){
			//Dequeue an edge with the smallest weight from the minHeap.
			PairPA pair=minHeap.poll();
			//Extract the destination node v and edge weight w from the dequeued edge.
			int v=pair.v;
			int w=pair.w;
			//Check if v has already been visited (visited[v]). If so, skip this edge.
			if(visited[v]) continue;
			//If v has not been visited,   
			if(!visited[v]) {
				cost+=w;//add the weight w to the cost,
				visited[v]=true;//mark v as visited,
				ArrayList<PairPA> neighbour=graph[v];
				//and add all unvisited neighbors of v to the minHeap.
				for (int i = 0; i < neighbour.size(); i++) {
					if(!visited[neighbour.get(i).v]) {
						minHeap.add(neighbour.get(i));
					}	
				}
			}
		}
		return cost;
	}

}
class PairPA{
	int w;
	int v;
	public  PairPA(int w,int v) {
		this.w=w;
		this.v=v;
	}
}

