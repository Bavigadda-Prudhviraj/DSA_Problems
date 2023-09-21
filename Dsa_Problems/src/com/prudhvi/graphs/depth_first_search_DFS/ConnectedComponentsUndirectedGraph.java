package com.prudhvi.graphs.depth_first_search_DFS;

import java.util.ArrayList;

/*
	Problem Description
		Given a graph with A nodes.
		The edges in graph are given in a 2D array B.
		There is an undirected edge between B[i][0] and B[i][1].
		Find the number of connected components in the given graph.
	Problem Constraints
		1 <= A <= 105
		1 <= |B| <= 105
		1 <= B[i][0], B[i][1] <= A
		//
 */
public class ConnectedComponentsUndirectedGraph {

	public static void main(String[] args) {
		int A = 4;
		int[][]	B = {{1, 2},{2, 3}};
		int graphsCount=graphsCount(A,B);
		System.out.println(graphsCount);
	}
	private static int graphsCount(int nodes, int[][] uv){
		int n=nodes;
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		for(int i=0;i<uv.length;i++) {
			int u=uv[i][0];
			int v=uv[i][1];
			graph[u].add(v);
			graph[v].add(u);
		}
		int count=0;
		boolean[] visited=new boolean[n+1];
		for (int i = 1; i < visited.length; i++) {
			if(!visited[i]) {
				dfs(graph,visited,i);
				count++;
			}
		}
		return count;
	}
	private static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int u) {
		if(visited[u]){
			return;
		}
		visited[u]=true;
		for(int i=0;i<graph[u].size();i++) {
			int idx=graph[u].get(i);
			dfs(graph, visited,idx);
		}
		
	}

}
