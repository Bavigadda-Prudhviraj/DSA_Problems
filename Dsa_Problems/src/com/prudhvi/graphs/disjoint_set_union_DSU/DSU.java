package com.prudhvi.graphs.disjoint_set_union_DSU;

import java.util.Arrays;
/*
	Class Implements Disjoint Set Union (DSU) data structure, also known as Union-Find. 
	DSU is used for efficient operations on disjoint sets, such as union (merging two sets) and find (determining the set to which an element belongs). 
	It employs two common optimization techniques: "union by rank" and "path compression."
 */
public class DSU {
	int[] rank;
	int[] size;
	int[] parent;
	//This initializes the DSU data structure with a given number of nodes. It initializes the rank, size, and parent arrays. 
	//Initially, each node is its parent, and its rank=0 initially and size are set to 1.
	public DSU(int nodes) {
		rank=new int[nodes+1];
		size=new int[nodes+1];
		parent=new int[nodes+1];
		Arrays.fill(size,1);
		for (int i = 0; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	//This method finds and returns the grandparent (ultimate ancestor) of a given node. It utilizes path compression to optimize future find operations.
	public int fingGrandParent(int nodes){
		if(nodes==parent[nodes]) {
			return nodes;
		}
		return parent[nodes]=fingGrandParent(parent[nodes]);
		
	}
	//This method performs a union operation on two nodes u and v based on the ranks of their ultimate ancestors (grandparents). The DSU keeps track of the rank of each set to balance the tree. 
	public void unionByRank(int u,int v){
		int gpu=fingGrandParent(u);
		int gpv=fingGrandParent(v);
		int uRank=rank[gpu];
		int vRank=rank[gpv];
		//If the ranks of u and v's grandparents are different, it attaches the smaller rank tree to the larger rank tree, which helps keep the tree relatively balanced. 
		if(gpu==gpv) {
			return;
		}
		if(uRank<vRank) {
			parent[gpu]=gpv;
		}
		else if(vRank<uRank) {
			parent[gpv]=gpu;
		}
		//If both ranks are the same, one tree is attached to the other, and the rank of the new root is increased.
		else {
			parent[gpv]=gpu;
			rank[gpu]+=uRank+1;
		}
	}
	//This method checks whether two nodes u and v belong to the same connected component. 
	public boolean isNodesFromSameConnectedGraph(int u,int v){
		int gpu=fingGrandParent(u);
		int gpv=fingGrandParent(v);
		//If their ultimate ancestors (grandparents) are the same, they belong to the same set and are part of the same connected component.
		if(gpu==gpv) {
			return true;
		}
		return false;
		
	}
	//This method performs a union operation on two nodes u and v based on the sizes of their sets. 
	public void unionBySize(int u, int v) {
        int gpu = fingGrandParent(u);
        int gpv = fingGrandParent(v);
        if (gpu == gpv) return;
        //It attaches the smaller size set to the larger size set. This operation can help prevent tall trees in the DSU structure, which can occur in union by rank when two sets of the same rank are combined.
        if (size[gpu] < size[gpv]) {
            parent[gpu] =gpv;
            size[gpu]= size[gpv] + size[gpu];
        } else {
            parent[gpv]= gpu;
            size[gpu]= size[gpu] + size[gpv];
        }
    }
	/*
	This DSU implementation is versatile and can be used in various applications, including solving graph problems like finding connected components and detecting cycles in graphs. 
	  It efficiently handles union and find operations with the help of rank and size optimizations.
	 */

	

}
