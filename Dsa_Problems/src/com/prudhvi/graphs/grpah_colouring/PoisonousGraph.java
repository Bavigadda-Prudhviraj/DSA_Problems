package com.prudhvi.graphs.grpah_colouring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Description:
 * 
 * You are given an undirected unweighted graph consisting of A vertices and M edges,
 * represented by a 2D matrix B of size M x 2, where (B[i][0], B[i][1]) denotes an edge.
 * 
 * You must write exactly one number (either 1, 2, or 3) on each vertex such that the
 * graph becomes "poisonous", meaning for every edge, the sum of the values on its two
 * vertices is odd.
 * 
 * Return the number of valid ways to assign values modulo 998244353.
 */
public class PoisonousGraph {
    // Constant modulus value for large number handling
    private static final int MOD = 998244353;

    public static void main(String[] args) {
        PoisonousGraph solver = new PoisonousGraph();
        
        // Example test case: 3 nodes, 2 edges
        int nodes = 3;
        int[][] edges = {
            {1, 2},
            {2, 3}
        };

        int result = solver.solve(nodes, edges);
        System.out.println("Total valid assignments: " + result);
    }

    /**
     * Solves the Poisonous Graph problem.
     *
     * @param numberOfVertices Total number of nodes in the graph (A)
     * @param edgeList         2D array where each row is an edge [u, v]
     * @return Total number of valid assignments modulo 998244353
     */
    public int solve(int numberOfVertices, int[][] edgeList) {
        // Step 1: Build adjacency list for the undirected graph
        List<Integer>[] graph = new ArrayList[numberOfVertices + 1];
        for (int i = 0; i <= numberOfVertices; i++) {
            graph[i] = new ArrayList<>();
        }

        // Fill the adjacency list using the edge list
        for (int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u); // Because the graph is undirected
        }

        // Step 2: Precompute powers of 2 modulo MOD
        // powerOfTwo[i] will store (2^i) % MOD
        long[] powerOfTwo = new long[numberOfVertices + 1];
        powerOfTwo[0] = 1;
        for (int i = 1; i <= numberOfVertices; i++) {
            powerOfTwo[i] = (powerOfTwo[i - 1] * 2) % MOD;
        }

        // Step 3: Color the graph using BFS and check for bipartiteness
        int[] vertexColors = new int[numberOfVertices + 1]; // 0 = unvisited, 1 = color1, 2 = color2
        long totalValidAssignments = 1; // Final answer

        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
            // Only process unvisited nodes (start of a new component)
            if (vertexColors[vertex] == 0) {
                // Start BFS
                Queue<Integer> queue = new LinkedList<>();
                queue.add(vertex);
                vertexColors[vertex] = 1; // Assign initial color (say, 1)

                int countColor1 = 1;
                int countColor2 = 0;

                // BFS traversal of current component
                while (!queue.isEmpty()) {
                    int currentNode = queue.poll();
                    for (int neighbor : graph[currentNode]) {
                        if (vertexColors[neighbor] == 0) {
                            // Assign opposite color to neighbor
                            vertexColors[neighbor] = 3 - vertexColors[currentNode];
                            queue.add(neighbor);

                            // Count how many nodes are of each color
                            if (vertexColors[neighbor] == 1) {
                                countColor1++;
                            } else {
                                countColor2++;
                            }
                        } else if (vertexColors[neighbor] == vertexColors[currentNode]) {
                            // If two adjacent nodes have same color -> not bipartite
                            return 0;
                        }
                    }
                }

                // If this connected component is bipartite:
                // Valid assignments = (2^countColor1 + 2^countColor2) % MOD
                // Reason: Nodes of one color can only be assigned either 1 or 3,
                // the others (opposite color) get the remaining odd sums
                long componentWays = (powerOfTwo[countColor1] + powerOfTwo[countColor2]) % MOD;
                totalValidAssignments = (totalValidAssignments * componentWays) % MOD;
            }
        }

        return (int) totalValidAssignments;
    }
}
