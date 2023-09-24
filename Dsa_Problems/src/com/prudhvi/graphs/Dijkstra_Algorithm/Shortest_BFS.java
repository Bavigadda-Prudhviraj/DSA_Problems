package com.prudhvi.graphs.Dijkstra_Algorithm;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
	Problem Description
		Given a weighted undirected graph having A nodes, a source node C and destination node D.
		Find the shortest distance from C to D and if it is impossible to reach node D from C then return -1.	
		You are expected to do it in Time Complexity of O(A + M).	
		Note:	
			There are no self-loops in the graph.	
			No multiple edges between two pair of vertices.		
			The graph may or may not be connected.		
			Nodes are Numbered from 0 to A-1.		
			Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
	Problem Constraints
		1 <= A <= 105
		0 <= B[i][0], B[i][1] < A	
		1 <= B[i][2] <= 2		
		0 <= C < A		
		0 <= D < A
	Input Format
		The first argument given is an integer A, representing the number of nodes.	
		The second argument given is the matrix B, where B[i][0] and B[i][1] are connected through an edge of weight B[i][2].	
		The third argument given is an integer C, representing the source node.		
		The fourth argument is an integer D, representing the destination node.		
		Note: B[i][2] will be either 1 or 2.
 */
public class Shortest_BFS {

	public static void main(String[] args) {
		int	A = 7;
		int[][]	B = {{1,6,1},
					 {5,6,1},
					 {3,4,1},
					 {0,3,2},
					 {2,5,1},
					 {1,5,2},
					 {0,1,1}}; 
		int	C = 6;
		int	D = 1;
		int shortpath=minPath(A,B,C,D);
		System.out.println(shortpath);
		

	}
    public int solve(int nodes, int[][] b, int source, int destination) {
        if(source==destination)return 0;
  		int n=nodes;
  		int idx=n;
  		int disSum=0;
  		for (int i = 0; i < b.length; i++) {
  			disSum+=b[i][2];
  		}
  		ArrayList<Integer>[] graph=new ArrayList[disSum+n];
  		for (int i = 0; i < graph.length; i++) {
  			graph[i]=new ArrayList<>();
  		}
  		for (int i = 0; i < b.length; i++) {
  			int u=b[i][0];
  			int v=b[i][1];
  			int d=b[i][2];
  			if(d==2) {
  				graph[u].add(idx);
  				graph[idx].add(u);
  				graph[idx].add(v);
  				graph[v].add(idx);
  				idx++;
  			}
  			else{
  				graph[u].add(v);
  				graph[v].add(u);
  			}
  		}
  		int level=-1;
  		boolean[] visited=new boolean[disSum+n];
  		Queue<Integer> queue=new LinkedList<>();
  		queue.add(source);  
  		while(!queue.isEmpty()){
  			int size=queue.size();
              level++;
              for(int j=0;j<size;j++){
            	  int s=queue.poll();
  			      visited[s]=true;
  			      ArrayList<Integer> neighbour=graph[s];
  			      for (int i = 0; i < neighbour.size(); i++) {
  			    	  int des=neighbour.get(i);
  			    	  if(!visited[des])
  			    		  queue.add(des);
  			      }
              }
  			if(visited[destination]){
  	            return level;
  	        }
  		}
  		return -1;
  	}

	/*
	Time Complexity:
			1.The code uses a breadth-first search (BFS) traversal to find the minimum path length between the source and destination nodes. In the worst case, it may visit all nodes reachable from the source.
			2.The BFS traversal has a time complexity of O(V + E), where V is the number of vertices (nodes) and E is the number of edges in the graph.
			3.In this case, the number of vertices V is determined by disSum, which is the sum of all distances in the input graph.
		Therefore, the overall time complexity of the code is O(Nodes + Edges), where E is the number of edges in the graph.
	Space Complexity:
			1.The code uses several data structures for storage, including arrays, ArrayLists, and queues.
			2.The space complexity is mainly determined by the space used for the graph adjacency list and the visited array.
			3.The size of the graph array is proportional to the number of nodes and edges in the input graph, so it contributes to the space complexity.
			4.The visited array keeps track of visited nodes and requires additional space for all nodes in the graph.
		Additionally, there are queues for BFS traversal, but their space usage is generally smaller than the graph and visited arrays.
	Therefore, the space complexity of the code can be approximated as O(disSum + V), where V is the number of vertices.
	 */
	private static int minPath(int nodes, int[][] b, int source, int destination) {
		if(source==destination)return 0;
		int n=nodes;
		int idx=n;
		//The code starts by calculating the sum of all distances in the input graph (disSum=total nodes needed to connect with 1 distance).
		int disSum=0;
		for (int i = 0; i < b.length; i++) {
			disSum+=b[i][2];
		}
		//It then initializes an adjacency list graph to represent the graph. The size of graph is determined by disSum.
		ArrayList<Pair>[] graph=new ArrayList[disSum+n];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		
		for (int i = 0; i < b.length; i++) {
			int u=b[i][0];
			int v=b[i][1];
			int d=b[i][2];
			if(d==2) {//For edges with a distance of 2, it introduces an intermediate node (idx) to ensure that the distance is 1 between the source, intermediate, and destination nodes.
				graph[u].add(new Pair(idx,1));
				graph[idx].add(new Pair(u,1));
				graph[idx].add(new Pair(v,1));
				graph[v].add(new Pair(idx,1));
				idx++;
			}
			else{//For edges with other distances, it adds them directly to the adjacency list.
				graph[u].add(new Pair(v, d));
				graph[v].add(new Pair(u, d));
			}
		}
		int level=0;//keep track of the depth of traversal.
		boolean[] visited=new boolean[disSum+n];//used to mark nodes as visited during the traversal.
		//Two queues, queue and tempQueue, are used to manage the nodes to be visited in each level of the traversal.
		Queue<Integer> queue=new LinkedList<>();
		queue.add(source);  
		Queue<Integer> tempQueue=new LinkedList<>();
		while(!queue.isEmpty()){
			//For each node (s) removed from the queue, it is marked as visited (visited[s] = true).
			int s=queue.poll();
			visited[s]=true;
			///explores all neighbors of the current node, s, and adds unvisited neighbors to the tempQueue.
			ArrayList<Pair> neighbour=graph[s];
			for (int i = 0; i < neighbour.size(); i++) {
				Pair des=neighbour.get(i);
				if(!visited[des.destination]) {
					
					tempQueue.add(des.destination);
				}	
			}
			//If the destination node (destination) is visited during this level of traversal, the function returns the current level.
			if(visited[destination]){
	            return level;
	        }
			//After processing all nodes at the current level, the code checks if the queue is empty but the tempQueue is not. If so, it increments the level.
			if(queue.isEmpty() && !tempQueue.isEmpty()) {
				level++;
				while(!tempQueue.isEmpty()){
					//The nodes in the tempQueue are moved to the queue to continue the BFS traversal at the next level.
					queue.add(tempQueue.poll());
				}
			}
		}
		//If the destination node is not reached during the BFS traversal, the function returns -1 to indicate that there is no valid path from the source to the destination.
		return -1;
	}

}
class Pair{

	int destination;
	int distance;
	public Pair(int destination,int distance) {
		
		this.destination=destination;
		this.distance=distance;
	}
	@Override
	public String toString() {
		return "Pair [destination=" + destination + ", distance=" + distance + "]";
	}
	
	
}
