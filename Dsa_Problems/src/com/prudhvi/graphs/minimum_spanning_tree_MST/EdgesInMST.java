package com.prudhvi.graphs.minimum_spanning_tree_MST;

import java.util.ArrayList;
import java.util.Arrays;

public class EdgesInMST {

	public static void main(String[] args) {
		int A = 3;
		int[][]	B = {{1, 2, 2},
				     {1, 3, 2},
				     {2, 3, 3}};
		int[] edges=edgesInMst(A,B);
		System.out.println(edges);
	}

	private static int[] edgesInMst(int nodes, int[][] uv) {
		int n=nodes;
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		EdgesDsu dsu=new EdgesDsu(n);
		for (int i = 0; i < uv.length; i++) {
			int u=uv[i][0];
			int v=uv[i][1];
			dsu.union(u, v);
		}
		System.out.println(Arrays.toString(dsu.parent));
		
		return null;
	}

}
class EdgesDsu{
	int[] parent;
	public EdgesDsu(int nodes) {
		parent=new int[nodes+1];
		for (int i = 0; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	public int findGrandParent(int node) {
		if(node==parent[node]) {
			return node;
		}
		return parent[node]=findGrandParent(parent[node]);
	}
	public boolean isUnion(int node1,int node2){
		if(parent[node1]==parent[node2]) {
			return true;
		}
		return false;
	}
	public void union(int u,int v) {
		int gpu=findGrandParent(u);
		int gpv=findGrandParent(v);
		if(gpu==gpv)return;
		if(gpu<gpv) {
			parent[gpv]=gpu;
		}else {
			parent[gpu]=gpv;
		}
	}
	
}