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
	

	private static int[] dijsktraAlgo(int nodes, int[][] uv, int source) {
		int n=nodes;
		int[] distance=new int[n];
		Arrays.fill(distance,Integer.MAX_VALUE);
		distance[source]=0;
		ArrayList<PairD>[] graph=new ArrayList[n];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		for (int i = 0; i < uv.length; i++) {
			int u=uv[i][0];
			int v=uv[i][1];
			int w=uv[i][2];
			graph[u].add(new PairD(w,v));
			graph[v].add(new PairD(w,u));
		}
		PriorityQueue<PairD> minHeap=new PriorityQueue<>(new Comparator<PairD>() {
			public int compare(PairD o1, PairD o2) {
				return o1.weight-o2.weight;
			}
		});
		minHeap.add(new PairD(0, source));
		while(!minHeap.isEmpty()){
			PairD data=minHeap.poll();
			int weight=data.weight;
			int des=data.destination;
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

