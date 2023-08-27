package com.prudhvi.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SubsetsII {

	public static void main(String[] args) {
		ArrayList<Integer> arrayList=new ArrayList<>(List.of(1, 2, 2));
		ArrayList<ArrayList<Integer>> set=subsetsWithDup(arrayList);
		System.out.println(set);

	}
	 public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> A) {
	        int[] frequency=new int[17];
	        for(int i=0;i<A.size();i++){
	            frequency[A.get(i)]++;
	        }
	        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
	        HashSet<ArrayList<Integer>> set=new HashSet<>();
	        ArrayList<Integer> ele=new ArrayList<>();
	       generatePermutations(arr,frequency,A,0,ele,set);
	        return arr;
	    }
	 public static void generatePermutations(ArrayList<ArrayList<Integer>> arr, int[] frequency,ArrayList<Integer> A,int idx,ArrayList<Integer> ele,HashSet<ArrayList<Integer>> set){
	        if(idx==A.size()){
	        	if(!set.contains(new ArrayList(ele))) {
	        		arr.add(new ArrayList(ele));
	        		set.add(new ArrayList(ele));
	        	}
	            
	            return;
	        }
	        for(int i=0;i<frequency.length;i++){
	            if(frequency[i]>0){
	                ele.add(i);
	                frequency[i]--;
	                generatePermutations( arr, frequency, A,idx+1,ele,set);
	                ele.remove(ele.size()-1);
	                generatePermutations( arr, frequency, A,idx+1,ele,set);
	                frequency[i]++;
	            }
	        }

	    }

}
