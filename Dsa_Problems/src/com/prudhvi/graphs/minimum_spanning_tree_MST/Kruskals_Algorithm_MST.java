package com.prudhvi.graphs.minimum_spanning_tree_MST;
import java.util.Arrays;
import java.util.Comparator;


/*
	Problem Description
		Given a graph with A nodes and C weighted edges. Cost of constructing the graph is the sum of weights of all the edges in the graph.
		Find the minimum cost of constructing the graph by selecting some given edges such that we can reach every other node from the 1st node.
		NOTE: Return the answer modulo 109+7 as the answer can be large.

	Problem Constraints
		1 <= A <= 100000
		0 <= C <= 100000
		1 <= B[i][0], B[i][1] <= N
		1 <= B[i][2] <= 109
 */
public class Kruskals_Algorithm_MST {

	public static void main(String[] args) {
		int A = 3;
		int[][]	B = {{1, 2, 14},
				     {2, 3, 7},
				     {3, 1, 2}};
		int minCost=kruskalAlgo(A,B);
		System.out.println(minCost);
	}
	/*
	Time Complexity:
				1.Sorting the edges in ascending order of weight using Arrays.sort takes O(E * log(E)) time, where E is the number of edges in the graph.
				2.Initializing the DSU (DSU dsu = new DSU(n)) takes O(n) time.
				3.The main loop iterates through the sorted edges once. In the worst case, it iterates through all edges, so it takes O(E) time.
				4.Inside the loop, the DSU operations (finding the grandparent, union) take nearly constant time because of path compression and union by rank techniques used in DSU. Therefore, these operations are considered O(1).
			Overall, the time complexity of the code is dominated by the edge sorting step, resulting in a final time complexity of O(E * log(E)).
	
	Space Complexity:
				1.The space complexity of the code is determined by the data structures used and their sizes.
				2.The Pair[] arr array of pairs used to store edge information takes O(E) space, where E is the number of edges.
				3.The DSU data structure maintains a parent and rank array for each node. Therefore, it takes O(n) space.
				4.Other variables like minCost, mod, and loop counters use constant space.
			Overall, the space complexity of the code is O(E + n), where E is the number of edges, and n is the number of nodes.
		
		In summary, the time complexity of this Kruskal's algorithm implementation is O(E * log(E)), and the space complexity is O(E + n), where E is the number of edges and n is the number of nodes in the graph.
	 */
	private static int kruskalAlgo(int nodes, int[][] b) {
		int n=nodes;
		int mod=1000000007;
		Pair[] arr=new Pair[b.length];//An array of pairs to store edge information.
		//The edge information from the b array is used to initialize the arr array of pairs. Each pair contains the source node (u), destination node (v), and weight of the edge (w).
		for(int i=0;i<b.length;i++) {
			arr[i]=new Pair(b[i][0], b[i][1], b[i][2]);
		}
		//The array of pairs is sorted in non-decreasing order based on edge weights using a custom comparator (Mysort). This step ensures that edges with the smallest weights are considered first.
		Arrays.sort(arr,new Mysort());
		DSU dsu=new DSU(n);//Initially, each node is in its own disjoint set.
		int minCost=0;//variable to keep track of the minimum cost of the MST.
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]!=null) {
				int u=arr[i].u;
				int v=arr[i].v;
				int w=arr[i].w;
				//it checks whether adding this edge to the MST would create a cycle
				//It does this by checking if the grandparent (representative) of u and v in the DSU are the same
				int gpu=dsu.grandParent(u);
				int gpv=dsu.grandParent(v);
				//If they are, adding the edge would create a cycle, so the edge is skipped (continue).
				if(gpu==gpv) continue;
				if(gpu!=gpv) {//If the grandparent of u and v are different it means adding the edge does not create a cycle.
					dsu.union(u,v);//the code unions the sets represented by u and v in the DSU and 
					minCost=(minCost+w)%mod;//adds the weight w to the minCost.
				}
			}
		}
		return minCost;//The minimum cost is updated modulo mod to prevent integer overflow.
	}
}
class Pair{
	int u;
	int v;
	int w;
	public Pair(int u,int v,int w) {
		this.u=u;
		this.v=v;
		this.w=w;
	}
	public Pair(int v,int w) {
		this.v=v;
		this.w=w;
	}
}
class DSU{
	int[] parent;
	public DSU(int nodes) {
		parent=new int[nodes+1];
		for (int i = 0; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	public int grandParent(int node){
		if(node==parent[node]) {
			return node;
		}
		return parent[node]=grandParent(parent[node]);
	}
	public void union(int u,int v){
		int gpu=grandParent(u);
		int gpv=grandParent(v);
		if(gpu==gpv)return;
		if(gpu<gpv){
			parent[gpv]=gpu;
		}else if(gpv<gpu) {
			parent[gpu]=gpv;
		}
	}
	public boolean isUnion(int u,int v){
		int gpu=grandParent(u);
		int gpv=grandParent(v);
		if(gpu==gpv) {
			return true;
		}
		return false;
	}
}
class Mysort implements Comparator<Pair>{

	@Override
	public int compare(Pair o1, Pair o2) {
		if(o1!=null && o2!=null ) {
			return o1.w-o2.w;
		}
		return 1;
	}
	
}
