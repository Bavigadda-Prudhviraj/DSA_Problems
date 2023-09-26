package com.prudhvi.graphs.Floyd_Warshall_Algorithm;
/*
	https://www.scaler.com/topics/data-structures/floyd-warshall-algorithm/
	Floyd-Warshall's Algorithm is a dynamic programming algorithm used for finding the shortest paths between all pairs of vertices in a weighted graph. It's named after its inventors, Robert W. Floyd and Stephen Warshall, and is particularly useful for solving problems related to network routing, transportation, and any scenario where you need to find the shortest paths between all pairs of nodes in a graph. Here's a comprehensive overview:

	1. **Use Cases**:
	   - **Shortest Path Calculation**: The primary use of the Floyd-Warshall algorithm is to find the shortest distances between all pairs of vertices in a weighted, directed graph.
	   - **Network Routing**: It is used in computer networking to find optimal routes for data packets to traverse a network.
	   - **Transportation Planning**: It can be applied to optimize routes for vehicles, such as delivery trucks, to minimize travel time or distance.
	   - **Flight Planning**: Airlines use this algorithm to calculate the most efficient flight routes, considering factors like fuel consumption and travel time.
	   - **Game Development**: It can be used in game development to calculate the shortest paths for characters or objects to navigate through a game world.
	
	2. **How It is Implemented**:
	   The Floyd-Warshall algorithm works by considering all pairs of vertices and gradually updating the shortest distance between them. Here are the basic steps:
	   - Create a 2D array `dist` where `dist[i][j]` represents the shortest distance from vertex `i` to vertex `j`.
	   - Initialize `dist[i][j]` to the weight of the edge between vertices `i` and `j` if there is an edge; otherwise, initialize it to infinity.
	   - For each vertex `k`, iterate through all pairs of vertices `i` and `j`. If `dist[i][j]` is greater than `dist[i][k] + dist[k][j]`, update `dist[i][j]` to `dist[i][k] + dist[k][j]`.
	   - Repeat the above step for all vertices `k`.
	   - The final `dist` matrix will contain the shortest distances between all pairs of vertices.
	   -This algorithm follows the dynamic programming approach to find the shortest paths.
			For a graph with N vertices:
			Step 1: Initialize the shortest paths between any 2 vertices with Infinity.
			Step 2: Find all pair shortest paths that use 0 intermediate vertices, then find the shortest paths that use 1 intermediate vertex and so on.. until using all N vertices as intermediate nodes.
			Step 3: Minimize the shortest paths between any 2 pairs in the previous operation.
			Step 4: For any 2 vertices (i,j) , one should actually minimize the distances between this pair using the first K nodes, so the shortest path will be: min(dist[i][k]+dist[k][j],dist[i][j]).
			dist[i][k] represents the shortest path that only uses the first K vertices, dist[k][j] represents the shortest path between the pair k,j. As the shortest path will be a concatenation of the shortest path from i to k, then from k to j.
			for(int k = 1; k <= n; k++){
			    for(int i = 1; i <= n; i++){
			        for(int j = 1; j <= n; j++){
			            dist[i][j] = min( dist[i][j], dist[i][k] + dist[k][j] );
			        }
			    }
			}
	3. **When to Use**:
	   - Floyd-Warshall is suitable when you need to find the shortest paths between all pairs of vertices in a dense graph.
	   - It can handle graphs with both positive and negative edge weights (but must not contain negative cycles).
	   - When you want to precompute all-pair shortest paths and query them efficiently.
	
	4. **Time and Space Complexities**:
	   - Time Complexity: O(V^3) where V is the number of vertices. The algorithm involves three nested loops over all vertices.
	   - Space Complexity: O(V^2) for storing the `dist` matrix.
	
	5. **Problems Based on This Algorithm**:
	   Certainly! Here are some problem names or descriptions that you can search for on platforms like LeetCode, GeeksforGeeks, or HackerRank to find problems related to the Floyd-Warshall algorithm:
		1. **Shortest Path Problems**:
		   - "Shortest Path in Binary Matrix" (LeetCode)
		   - "Floyd Warshall" (GeeksforGeeks)
		
		2. **Graph Optimization Problems**:
		   - "All Nodes Distance K in Binary Tree" (LeetCode)
		   - "Shortest Route with Minimum Edges" (GeeksforGeeks)
		
		3. **Network Routing and Connectivity**:
		   - "Network Delay Time" (LeetCode)
		   - "Minimum Spanning Tree" (GeeksforGeeks)
		
		4. **Transportation and Travel Optimization**:
		   - "Cheapest Flights Within K Stops" (LeetCode)
		   - "Traveling Salesman Problem" (GeeksforGeeks)
		
		5. **Game Development and Navigation**:
		   - "Shortest Path in Binary Maze" (LeetCode)
		   - "Path Finding in 2D Grid" (GeeksforGeeks)
		These problem names should help you find relevant exercises and challenges that involve implementing or applying the Floyd-Warshall algorithm on different online platforms.
	In summary, the Floyd-Warshall algorithm is a versatile tool for finding all-pair shortest paths in weighted graphs and has various real-world applications, particularly in network and transportation optimization. It's a fundamental algorithm in graph theory and can be a valuable addition to your algorithmic toolbox.
	
*/