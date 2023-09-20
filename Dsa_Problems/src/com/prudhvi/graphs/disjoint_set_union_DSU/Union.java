package com.prudhvi.graphs.disjoint_set_union_DSU;

public class Union {

	public static void main(String[] args) {
		int nodes=7;
		int[][] arr={{1,2}, {2,3}, {4,5}, {6,7}, {5,6}, {3,7}};
		testDsu(nodes,arr);
	}

	private static void testDsu(int nodes, int[][] arr) {
		DSU dsu=new DSU(nodes+1);
		for(int i=0;i<arr.length-1;i++) {
			int u=arr[i][0];
			int v=arr[i][1];
			dsu.unionByRank(u,v);
		}
		System.out.println(dsu.isNodesFromSameConnectedGraph(3,7));
		
	}

}
