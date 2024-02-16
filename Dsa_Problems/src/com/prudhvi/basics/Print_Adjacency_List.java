package com.prudhvi.basics;

import java.util.ArrayList;
import java.util.List;

public class Print_Adjacency_List {

	public static void main(String[] args) {
		int v=7;
		int[][] edges= {{0,1},{0,4},{4,1},{4,3},{1,3},{1,2},{3,2}};
		List<List<Integer>> graph=printGraph(v, edges);
		for(int i=0;i<graph.size();i++) {
			System.out.println(graph.get(i));
		}

	}
	public static List<List<Integer>> printGraph(int V, int edges[][]) {
		List<List<Integer>> graph=new ArrayList<>();
		for(int i=0;i<V;i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<edges.length;i++) {
			graph.get(edges[i][0]).add(edges[i][1]);
			graph.get(edges[i][1]).add(edges[i][0]);
		}
		return graph;
	}

}
