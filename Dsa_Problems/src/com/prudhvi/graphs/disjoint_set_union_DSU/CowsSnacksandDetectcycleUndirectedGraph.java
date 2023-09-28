package com.prudhvi.graphs.disjoint_set_union_DSU;


/*
	Problem Description
		The legendary Farmer John is throwing a huge party, and animals from all over the world are hanging out at his house. His guests are hungry, so he instructs his cow Bessie to bring out the snacks! Moo!
		There are A snacks flavors, numbered with integers 1,2,…,A. Bessie has A snacks, one snack of each flavor. There are M guests and every guest has exactly two favorite flavors. The procedure for eating snacks will go as follows:
		First, Bessie will line up the guests in some way.
		Each guest in their turn will eat all remaining snacks of their favorite flavor. In case no favorite flavors are present when a guest goes up, they become very sad.
		Help Bessie to minimize the number of sad guests by lining the guests in an optimal way.
	Problem Constraints
		2 <= A <= 100000
		1 <= M <= 100000
		1 <= B[i][0] , B[i][1] <= A
		B[i][0] != B[i][1]
	Input Format
		First argument of input contains a single integer A.
		Second argument of input contains a M x 2 integer matrix B denoting favorite flavors of each guest.
 */
public class CowsSnacksandDetectcycleUndirectedGraph {

	public static void main(String[] args) {
		int A = 1000;
		int[][] B = {{8,7},{1,9},{5,4},{11,12},{7,8},{3,4},{3,5},{12,15},{15,13},{13,14}};
		int minSadGuests=GuestWithOutSnacks(A,B);
		System.out.println(minSadGuests);

	}
	/*
	Time Complexity:
		The code iterates through the favFlavours array once, performing union operations and checks for whether two guests prefer the same flavor. These operations take nearly constant time due to the efficient union-find data structure. Therefore, the time complexity is O(favFlavours.length).
	Space Complexity:
		The primary space usage is for the DsuCS data structure, which has a size of flavours + 1. Therefore, the space complexity is O(flavours).
	 */
	private static int GuestWithOutSnacks(int flavours, int[][] favFlavours) {
		//The code initializes an instance of the DsuCS class, which implements Disjoint-Set Union (Union-Find) data structure, with flavours + 1 nodes. 
		//This data structure will be used to keep track of which flavors are in the same group.
		DsuCS dsu=new DsuCS(flavours+1);
		int ans=0;//used to count the number of guests who won't get their preferred snacks.
		//It iterates through the favFlavours array, which contains pairs of guests' preferred flavors.
		for (int i = 0; i < favFlavours.length; i++) {
			int u=favFlavours[i][0];
			int v=favFlavours[i][1];
			//For each pair (u, v) in favFlavours, it checks whether u and v are already in the same group using the dsu.isUnion(u, v) method. If they are in the same group, 
			//it means both guests prefer the same flavor that snack was eaten, and ans is incremented.
			if(dsu.isUnion(u, v)) {//loop detected
				ans++;
			}else {
				dsu.union(u, v);
			}
		}
		return ans;
	}

}
class DsuCS{
	int[] parent;
	public DsuCS(int nodes) {
		parent=new int[nodes];
		for (int i = 0; i < parent.length; i++) {
			parent[i]=i;
		}
	}
	public boolean isUnion(int u,int v){
		int pu=grandParent(u);
		int pv=grandParent(v);
		if(pu==pv)return true;
		return false;
	}
	public int grandParent(int node) {
		if(node==parent[node]) {
			return node;
		}
		return parent[node]=grandParent(parent[node]);
	}
	public void union(int u,int v) {
		int gpu=grandParent(u);
		int gpv=grandParent(v);
		if(gpv==gpu)return;
		else if(gpu<gpv)parent[gpv]=parent[gpu];
		else if(gpv<gpu)parent[gpu]=parent[gpv];
	}
}
