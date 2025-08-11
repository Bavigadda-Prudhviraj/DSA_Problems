package com.prudhvi.graphs.breadth_first_search_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
Problem Description:

You are given N towns, numbered from 1 to N. These towns are connected through unique **directed edges**, 
defined by an integer array A of size N (0-indexed). These edges form a tree-like structure with each town 
(except town 1) having exactly one incoming edge.

For each index i from 1 to N-1:
    - There is a directed edge from A[i] → i + 1

Note:
- A[0] is ignored, as it doesn't define any edge.
- It is guaranteed that A[i] ≤ i for all i ≥ 1 (ensuring no forward reference/cycles).

Your task is:
Given two towns B and C, check whether you can reach **town B starting from town C**, following the directed edges,
**without repeating any edge**.

In simpler terms:
"Can we get to town B from town C using the directed paths defined by A?"

----------------------------------------------------------
Example:

Let A = [1, 1, 1, 3, 4, 4]
(N = 6 towns → towns are 1 through 6)

Constructing the edges:
- A[1] = 1 → edge from 1 → 2
- A[2] = 1 → edge from 1 → 3
- A[3] = 3 → edge from 3 → 4
- A[4] = 4 → edge from 4 → 5
- A[5] = 4 → edge from 4 → 6

Graph:
     1
   /   \
  2     3
         \
          4
         / \
        5   6

Queries:

1. B = 5, C = 3
   Path: 3 → 4 → 5 → ✅ Yes, return 1

2. B = 6, C = 1
   Path: 1 → 3 → 4 → 6 → ✅ Yes, return 1

3. B = 2, C = 4
   No path from 4 to 2 → ❌ No, return 0

----------------------------------------------------------

Constraints:
- 1 ≤ B, C ≤ N
- A.length == N
- A[0] = 1 (can be ignored)
- For 1 ≤ i < N, A[i] ≤ i
- The structure guarantees no cycles and only one incoming edge per node
*/

public class FirstDepthFirstSearch {

	public static void main(String[] args) {
		int[] towns = { 1, 1, 1, 3, 4, 4 };
		int B = 6;
		int C = 1;
		System.out.println(FirstDepthFirstSearch.solve(towns, B, C));
	}

	public static int solve(int[] parentArray, final int destinationNode, final int sourceNode) {
		// If start and end are the same node, path exists trivially
		if (sourceNode == destinationNode)
			return 1;

		int totalNodes = parentArray.length;

		// Initialize adjacency list for graph representation
		ArrayList<Integer>[] adjacencyList = new ArrayList[totalNodes + 1];
		for (int i = 0; i <= totalNodes; i++) {
			adjacencyList[i] = new ArrayList<>();
		}

		// Build the graph: edges from parentArray[i] → i+1
		for (int i = 0; i < parentArray.length; i++) {
			// Skip the first node if it points to itself (root)
			if (i == 0 && parentArray[0] == 1)
				continue;

			int fromNode = parentArray[i];
			int toNode = i + 1;
			adjacencyList[fromNode].add(toNode);
		}

		// BFS setup
		boolean[] visited = new boolean[totalNodes + 1];
		Queue<Integer> bfsQueue = new LinkedList<>();

		visited[sourceNode] = true;
		bfsQueue.add(sourceNode);

		// Perform BFS to find path from source to destination
		while (!bfsQueue.isEmpty()) {
			int currentNode = bfsQueue.poll();

			for (int neighbor : adjacencyList[currentNode]) {
				if (!visited[neighbor]) {
					if (neighbor == destinationNode) {
						return 1;
					}
					visited[neighbor] = true;
					bfsQueue.add(neighbor);
				}
			}
		}

		return 0; // No path found
	}

}
