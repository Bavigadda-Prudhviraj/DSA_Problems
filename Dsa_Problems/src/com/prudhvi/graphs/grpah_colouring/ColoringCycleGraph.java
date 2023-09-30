package com.prudhvi.graphs.grpah_colouring;
/*
	Problem Description
		Given the number of vertices A in a Cyclic Graph.
		Your task is to determine the minimum number of colors required to color the graph so that no two Adjacent vertices have the same color.
		A cyclic graph with A vertices is a graph with A edges, such that it forms a loop. See example test case explanation for more details.
	Problem Constraints
		3 <= A <= 109
 */
public class ColoringCycleGraph {

	public static void main(String[] args) {
		int nodes=7;
		int minColours=graphcoloring(nodes);
		System.out.println(minColours);

	}

	private static int graphcoloring(int nodes) {
		return nodes%2==0?2:3;
	}
	/*
	 `graphcoloring` function is to determine the number of colors needed to color a graph based on the number of nodes. 
	 It returns 2 if the number of nodes is even and 3 if the number of nodes is odd. 
	
	This function performs graph coloring with a goal of using as few colors as possible to color the graph in such a way that no two adjacent nodes have the same color. 
	For most practical scenarios, this is an oversimplified approach. Graph coloring problems are typically more complex and involve graph-specific constraints and relationships.
	
	In many real-world graph coloring problems, determining the minimum number of colors needed is an NP-hard problem and cannot be simply determined based on whether the number of nodes is even or odd. 
	Graph coloring algorithms, such as greedy coloring or backtracking, are used to find a valid coloring with as few colors as possible.
	
	 */

}
