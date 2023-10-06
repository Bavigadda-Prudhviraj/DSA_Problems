package com.prudhvi.graphs.grpah_colouring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
	Problem Description
		A country consist of N cities connected by N - 1 roads. King of that country want to construct maximum number of roads such that the new country formed remains bipartite country.
		Bipartite country is a country, whose cities can be partitioned into 2 sets in such a way, that for each road (u, v) that belongs to the country, u and v belong to different sets. Also, there should be no multiple roads between two cities and no self loops.
		Return the maximum number of roads king can construct. Since the answer could be large return answer % 109 + 7.
		NOTE: All cities can be visited from any city.
	Problem Constraints
		1 <= A <= 105
		1 <= B[i][0], B[i][1] <= N


 */
public class ConstructRoads {

	public static void main(String[] args) {
		int A = 5;//26755256
		int[][] B = {{1, 3},
				     {1, 4},
				     {3, 2},
				     {3, 5}};
		System.out.println(roadConstruction(A,B));;
	}

	private static int roadConstruction(int n, int[][] uv) {
		int mod=1000000007;
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		for (int i = 0; i < uv.length; i++) {
			int u=uv[i][0];
			int v=uv[i][1];
			graph[u].add(v);
			graph[v].add(u);
		}
		int[] colour=new int[n+1];
		Arrays.fill(colour,-1);
		for(int i=1;i<colour.length;i++) {
			if (colour[i]==1 || colour[i]==0)continue;
			if(!check(i,graph,colour)){
				return 0;
			}
		}
		long group1=0;
		long group2=0;
		for (int i = 1; i < colour.length; i++) {
			if(colour[i]==1)group1++;
			if(colour[i]==0)group2++;
		}
		return (int)((group1*group2)%mod)-(n-1) ;
	}
	private static boolean check(int source, ArrayList<Integer>[] graph, int[] colour) {
		Queue<Integer> queue=new LinkedList<>();
		queue.add(source);
		colour[source]=0;
		while(!queue.isEmpty()){
			int u=queue.poll();
			ArrayList<Integer> arr=graph[u];
			for(int v:arr) {
				if(colour[u]==colour[v]) {
					return false;
				}
				if(colour[v]==-1) {
					colour[v]=1-colour[u];
					queue.add(v);
				}
			}
			
		}
		
		return true;
	}

}
