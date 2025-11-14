package com.prudhvi.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FinishMaximumJobs {

    /*
    Problem Description
    There are N jobs to be done, but you can do only one job at a time.
    Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.
    Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.
    Return the maximum number of jobs you can finish.
    */

    public static void main(String[] args) {
        ArrayList<Integer> startTime = new ArrayList<>(List.of(9, 2, 7, 10, 4, 7, 10, 8));
        ArrayList<Integer> endTime = new ArrayList<>(List.of(11, 4, 9, 1, 6, 8, 3, 10));
        int maxJobs = finishMaxJobs(startTime, endTime);
        System.out.println("Maximum jobs that can be finished: " + maxJobs);
        System.out.println(solve(startTime, endTime));
    }

    // You can keep this if you want your own solve method that uses Pair class
    public static int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Pairs> list = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            list.add(new Pairs(A.get(i), B.get(i)));
        }
        list.sort((a, b) -> Integer.compare(a.end, b.end));
        int count = 1;
        int currentEnd = list.get(0).end;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).start >= currentEnd) {
                count++;
                currentEnd = list.get(i).end;
            }
        }
        return count;
    }
    public static int finishMaxJobs(ArrayList<Integer> startTime, ArrayList<Integer> endTime) {
        int maxJobs = 0;
        int size = startTime.size();
        Pair[] arr = new Pair[size];
        for (int i = 0; i < startTime.size(); i++) {
            arr[i] = new Pair(startTime.get(i), endTime.get(i));
        }
        Arrays.sort(arr, new MyComparator());
        int endTimeTrack = arr[0].endTime;
        maxJobs++;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].startTime >= endTimeTrack) {
                maxJobs++;
                endTimeTrack = arr[i].endTime;
            }
        }
        return maxJobs;
    }
}

// Simple Pair class to hold start and end times
class Pairs {
    int start;
    int end;

    public Pairs(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

/*
The below classes and method implement the greedy solution for maximum jobs with sorting by end time
*/

class Pair {
    int startTime;
    int endTime;

    public Pair(int start, int end) {
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "Pair [startTime=" + startTime + ", endTime=" + endTime + "]";
    }
}

class MyComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair o1, Pair o2) {
        return o1.endTime - o2.endTime;
    }
}


