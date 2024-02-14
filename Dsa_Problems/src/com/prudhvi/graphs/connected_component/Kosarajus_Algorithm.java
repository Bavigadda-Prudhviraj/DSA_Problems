package com.prudhvi.graphs.connected_component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Kosarajus_Algorithm {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		adj.add(new ArrayList<>(Arrays.asList(1,0)));
		adj.add(new ArrayList<>(Arrays.asList(0,2)));
		adj.add(new ArrayList<>(Arrays.asList(2,1)));
		adj.add(new ArrayList<>(Arrays.asList(0,3)));
		adj.add(new ArrayList<>(Arrays.asList(3,4)));
		System.out.println(kosaraju(5, adj));


	}

	/**
	 * Computes the number of strongly connected components (SCCs) in a directed graph
	 * using Kosaraju's algorithm.
	 *
	 * @param V    The number of vertices in the graph.
	 * @param adj  The adjacency list representation of the graph.
	 * @return     The number of SCCs in the graph.
	 *
	 * @TimeComplexity: O(V + E), where V is the number of vertices and E is the number of edges.
	 *                  The algorithm performs two DFS traversals, one on the original graph and
	 *                  the other on the transposed graph.
	 * @SpaceComplexity: O(V + E), additional space is used for the adjacency list and visited array.
	 */
	public static int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
	    // Stack to store nodes in the order of their finishing times.
	    Stack<Integer> stack = new Stack();
	    // Array to mark visited nodes during DFS.
	    boolean[] visited = new boolean[V];

	    // Step 1: Perform DFS to compute finishing times and fill the stack.
	    for (int i = 0; i < V; i++) {
	        if (!visited[i]) {
	            finishingTimeDfs(i, adj, visited, stack);
	        }
	    }

	    // Step 2: Transpose the graph by reversing all its edges.
	    ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
	    for (int i = 0; i < V; i++) {
	        adjT.add(new ArrayList<>());
	    }

	    for (int i = 0; i < V; i++) {
	        visited[i] = false;
	        for (int adjEle : adj.get(i)) {
	            adjT.get(adjEle).add(i);
	        }
	    }

	    int countOfSCC = 0;

	    // Step 3: Perform DFS on the transposed graph and count SCCs.
	    while (!stack.isEmpty()) {
	        int node = stack.pop();
	        if (!visited[node]) {
	            countOfSCC++;
	            dfsSCC(node, adjT, visited);
	        }
	    }

	    return countOfSCC;
	}

	// Helper method for DFS in finding strongly connected components.
	private static void dfsSCC(int node, ArrayList<ArrayList<Integer>> adjT, boolean[] visited) {
	    visited[node] = true;
	    for (int i = 0; i < adjT.get(node).size(); i++) {
	        if (!visited[adjT.get(node).get(i)]) {
	            dfsSCC(adjT.get(node).get(i), adjT, visited);
	        }
	    }
	}

	// Helper method for DFS to compute the finishing time of each node.
	private static void finishingTimeDfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
	    visited[node] = true;
	    for (int i = 0; i < adj.get(node).size(); i++) {
	        if (!visited[adj.get(node).get(i)]) {
	            finishingTimeDfs(adj.get(node).get(i), adj, visited, stack);
	        }
	    }
	    stack.add(node);
	}


}
