package com.prudhvi.Arrays.sliding_window;

import java.util.ArrayList;
import java.util.List;

public class Spiral_Matrix_Square_Mat {

	public static void main(String[] args) {
		int[][] matrix= { {1,   2,  3,  4, 5}, 
		                  {16, 17, 18, 19, 6}, 
		                  {15, 24, 25, 20, 7}, 
		                  {14, 23, 22, 21, 8}, 
		                  {13, 12, 11, 10, 9} };
		List<Integer> sprialPrint=sprialMatrix(matrix);
		System.out.println(sprialPrint);
	}
	public static List<Integer> sprialMatrix(int[][] matrix){
		List<Integer> sprial=new ArrayList<>();
		int n=matrix.length;
		int row=0;
		int col=0;
		int step=n-1;
		while(step>=1){
			for(int i=0;i<step;i++) {
				sprial.add(matrix[row][col]);
				col++;
			}
			for(int i=0;i<step;i++) {
				sprial.add(matrix[row][col]);
				row++;
			}
			for(int i=0;i<step;i++) {
				sprial.add(matrix[row][col]);
				col--;
			}
			for (int i=0;i<step;i++) {
				sprial.add(matrix[row][col]);
				row--;
				
			}
			row++;
			col++;
			step-=2;
		}
		if(step==0) {
			sprial.add(matrix[row][col]);
		}
		return sprial;
	}

}
