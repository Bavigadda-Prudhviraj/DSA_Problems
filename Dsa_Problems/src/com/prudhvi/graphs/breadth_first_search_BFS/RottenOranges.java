package com.prudhvi.graphs.breadth_first_search_BFS;
import java.util.LinkedList;
import java.util.Queue;


/*
	Given a grid of dimension n*m where each cell in the grid can have values 0, 1 or 2 which has the following meaning:
		0 : Empty cell
		1 : Cells have fresh oranges
		2 : Cells have rotten oranges
	We have to determine what is the minimum time required to rot all oranges. A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time.
	
	 Your Task:
		You don't need to read or print anything, Your task is to complete the function orangesRotting() which takes grid as input parameter and returns the minimum time to rot all the fresh oranges. 
		If not possible returns -1.
	Expected Time Complexity: O(n*m)
	Expected Auxiliary Space: O(n*m)
 */
public class RottenOranges {

	public static void main(String[] args) {
		int[][] oranges= {{0,1,2},{0,1,2},{2,1,1}};
		int minTime=minTimeToRotOranges(oranges);
		System.out.println(minTime);
	}
	/*
	Time Complexity:
			The code iterates through all cells in the grid once to initialize the queue and mark visited cells. This initial loop has a time complexity of O(m * n), where m is the number of rows and n is the number of columns.
			The BFS traversal then processes each rotten orange once, and it explores neighboring cells (up, down, left, right) for each rotten orange. In the worst case, each cell is processed only once, resulting in a time complexity of O(m * n).
			The final loop that checks if there are any unrotten oranges takes O(m * n) time in the worst case.
			Therefore, the overall time complexity is O(m * n).
	Space Complexity:
			The space complexity is determined by the additional data structures used, including the visited boolean array, the two queues (queue and tempQueue), and the Pair objects.
			The visited boolean array has a space complexity of O(m * n) because it tracks whether each cell has been visited or not.
			Both the queue and tempQueue can store at most O(m * n) cells in the worst case, which contributes to O(m * n) space complexity.
			The Pair objects have a negligible space impact compared to the other data structures.
			Therefore, the overall space complexity is O(m * n).
	In summary, the provided code efficiently finds the minimum time required to rot all oranges in the grid with a time complexity of O(m * n) and a space complexity of O(m * n) in the worst case, where m is the number of rows and n is the number of columns in the grid.
	
	 */
	private static int minTimeToRotOranges(int[][] oranges) {
		int m=oranges.length;// Get the number of rows in the grid.
		int n=oranges[0].length;//Get the number of columns in the grid.
		boolean[][] visited=new boolean[m][n];// Create a boolean array visited to keep track of visited cells.
		Queue<Pair> queue=new LinkedList<>();//store the coordinates of rotten oranges.
		for (int i=0; i<m; i++) {
			for(int j=0;j<n;j++) {
				if(oranges[i][j]==2) {
					queue.add(new Pair(i, j));//Add the coordinates of rotten oranges to the queue.
					visited[i][j]=true;//Mark visited cells as true Visited cells rotten 
				}
				if(oranges[i][j]==0) {
					visited[i][j]=true;//Mark visited cells as true empty cells.
				}
			}
		}
		int time=0;//will keep track of the time steps taken to rot all oranges.
		Queue<Pair> tempQueue=new LinkedList<>();// store the next set of oranges that will become rotten in the next time step.
		//The main BFS loop begins with , which iterates as long as there are rotten oranges to process.
		while(!queue.isEmpty()){
			//Dequeue a rotten orange (Pair rotten = queue.poll();) and get its coordinates.
			Pair rotten=queue.poll();
			int i=rotten.i;
			int j=rotten.j;
			//Check the neighboring cells (up, down, left, and right) for fresh oranges that can be turned rotten.
			//If a neighboring cell has a fresh orange (oranges[i][j] == 1) and is not visited, mark it as visited, change its status to rotten (oranges[i][j] = 2), and add its coordinates to tempQueue.
			if (i>=1 && j>=0 && i<m && j<n && (oranges[i-1][j]==1 && !visited[i-1][j])) {//top
					visited[i-1][j]=true;
					oranges[i-1][j]=2;
					tempQueue.add(new Pair(i-1, j));
			}
			if (i>=0 && j>=0 && i<m-1 && j<n && (oranges[i+1][j]==1 && !visited[i+1][j])) {//down
				visited[i+1][j]=true;
				oranges[i+1][j]=2;
				tempQueue.add(new Pair(i+1, j));
			}
			if (i>=0  && j>=1 && i<m && j<n && (oranges[i][j-1]==1 && !visited[i][j-1])) {//left
				visited[i][j-1]=true;
				oranges[i][j-1]=2;
				tempQueue.add(new Pair(i, j-1));
			}
			if (i>=0 && j>=0 && i<m && j<n-1 && (oranges[i][j+1]==1 && !visited[i][j+1])) {//right
				visited[i][j+1]=true;
				oranges[i][j+1]=2;
				tempQueue.add(new Pair(i, j+1));
			}
			//After processing all rotten oranges in the current time step, if queue is empty but tempQueue is not empty, 
			//it means there are fresh oranges that will become rotten in the next time step. Increment time and transfer the oranges from tempQueue to queue.
			if(queue.isEmpty() && !tempQueue.isEmpty()) {
				time++;
				while(!tempQueue.isEmpty()){
					queue.add(tempQueue.poll());
				}
			}
			
		}
		//After the BFS loop, check if there are any remaining fresh oranges in the grid. 
		//If any fresh oranges remain (oranges[i][j] == 1), return -1 to indicate that it's impossible to rot all oranges.
		for (int i = 0; i < oranges.length; i++) {
			for (int j = 0; j < oranges[i].length; j++) {
				if(oranges[i][j]==1) {
					return -1;
				}
			}
		}
		//If no fresh oranges remain, return time, which represents the minimum time required to rot all oranges.
		return time;
	}

}
class Pair{
	int i;
	int j;
	public Pair(int i,int j) {
		this.i=i;
		this.j=j;
	}
	@Override
	public String toString() {
		return "pair [i=" + i + ", j=" + j + "]";
	}
	
	
}
