package com.prudhvi.graphs.Floyd_Warshall_Algorithm;
import java.util.Arrays;

/*
	Problem Description
		Given a matrix of integers A of size N x N, where A[i][j] represents the weight of directed edge from i to j (i ---> j).
		If i == j, A[i][j] = 0, and if there is no directed edge from vertex i to vertex j, A[i][j] = -1.
		Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j.
		If there is no possible path from vertex i to vertex j , B[i][j] = -1
		Note: Rows are numbered from top to bottom and columns are numbered from left to right.
	Problem Constraints
		1 <= N <= 200
		-1 <= A[i][j] <= 1000000
 */
public class FloydWarshallAlgorithm {

	public static void main(String[] args) {
		int[][] A= {{0 , 50 , 39},
		            {-1 , 0 , 1},
		            {-1 , 10 , 0}};
		int[][] shorestDistanceOfAllNodesFromOtherNodes=floydWarshallAlgorithm(A);
		for (int i = 0; i < shorestDistanceOfAllNodesFromOtherNodes.length; i++) {
			System.out.println(Arrays.toString(shorestDistanceOfAllNodesFromOtherNodes[i]));
		}
		

	}
	/*
	Time Complexity (Floyd-Warshall Algorithm):
				1.Initialization: The code first initializes the elements in the input matrix A by replacing -1 with Integer.MAX_VALUE. This initialization step takes O(m * n) time, where m is the number of rows and n is the number of columns in the matrix.
				2.Triple Nested Loop: The core of the Floyd-Warshall algorithm consists of three nested loops. These loops iterate through all pairs of vertices and update the shortest path distances. The time complexity of this part is O(m^3), where m is the number of vertices.
				3.Negative Cycle Detection: After the main algorithm, there is a loop to check if there is a negative cycle in the graph. This loop has a time complexity of O(m^2), where m is the number of vertices.
			The overall time complexity of the Floyd-Warshall algorithm is O(m^3), where m is the number of vertices.
	Space Complexity (Floyd-Warshall Algorithm):
				1.The space complexity of the algorithm is determined by the space required to store the input matrix A and any additional data structures used in the algorithm.	
				2.Input Matrix A: The space complexity for the input matrix is O(m * n), where m is the number of rows and n is the number of columns.	
				3.Boolean Flag: A boolean variable isNegativeCycleExist is used to check if a negative cycle exists. This variable requires constant space, so its space complexity is O(1).
				4.Temporary Variables: The algorithm uses temporary variables for iterations and comparisons, which require negligible space compared to the input matrix.
			The overall space complexity of the Floyd-Warshall algorithm is O(m * n), primarily due to the input matrix.
	In summary, the Floyd-Warshall algorithm has a time complexity of O(m^3) and a space complexity of O(m * n), where m is the number of vertices and n is the number of columns in the input matrix.
	 */
	//Floyd-Warshall Algorithm: The code then proceeds to apply the Floyd-Warshall algorithm. It uses three nested loops:
	//The outer loop via iterates through all possible "via" nodes (intermediate nodes) in the graph.
	//The next two loops (i and j) iterate through all pairs of nodes. These loops are used to update the shortest path distances between nodes i and j through the "via" node.
	public static int[][] floydWarshallAlgorithm(int[][] A) {
        int m=A.length;
        int n=A[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]==-1){
                    A[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        //Relax Edges: Inside the via loop, the code checks whether there is a shorter path from node i to node j through the "via" node (A[i][via] + A[via][j]). 
        //If such a shorter path exists and A[i][via] and A[via][j] are not equal to Integer.MAX_VALUE, it updates the distance A[i][j] to the minimum of its current value and the sum of the distances through the "via" node.
        for(int via=0;via<m;via++){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(A[i][via]==Integer.MAX_VALUE || A[via][j]==Integer.MAX_VALUE){
                       continue;
                    }
                    A[i][j]=Math.min(A[i][j],A[i][via]+A[via][j]);
                }
             }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]== Integer.MAX_VALUE ){
                    A[i][j]=-1;
                }
            }
        }
        //Detect Negative Cycles: Finally, the code checks for the existence of negative cycles. It iterates through all nodes and checks if any diagonal element (A[i][i]) is non-zero. 
        //If it finds a non-zero diagonal element, it sets isNegativeCycleExist to true, indicating the presence of a negative cycle in the graph.
        boolean isNegativeCycleExist=false;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
            	if(A[i][j]!=0 && i==j){
                    isNegativeCycleExist=true;
                    break;
                }
            }
        }
        System.out.println(isNegativeCycleExist);
        return A;
    }

}
