package modulefour;

import java.util.ArrayList;
import java.util.Arrays;

public class DSAArrays3InterviewProblems {

    /*
    Q1. First Missing Integer
    Problem Description

Given an unsorted integer array, A of size N. Find the first missing positive integer.





Note: Your algorithm should run in O(n) time and use constant space.







Problem Constraints

1 <= N <= 1000000

-109 <= A[i] <= 109



Input Format

First argument is an integer array A.



Output Format

Return an integer denoting the first missing positive integer.



Example Input

Input 1:

[1, 2, 0]
Input 2:

[3, 4, -1, 1]
Input 3:

[-8, -7, -6]






Example Output

Output 1:

3
Output 2:

2
Output 3:

1






Example Explanation

Explanation 1:

A = [1, 2, 0]
First positive integer missing from the array is 3.
Explanation 2:

A = [3, 4, -1, 1]
First positive integer missing from the array is 2.
Explanation 3:

A = [-8, -7, -6]
First positive integer missing from the array is 1.

     */

    public static int firstMissingPositive(ArrayList<Integer> input) {
        int size = input.size();
        for(int i = 0; i < size; i++) {
            if(input.get(i) <= 0) {
                input.set(i, size + 1);
            }
        }
        for (int i = 0; i < size; i++) {
            int val = Math.abs(input.get(i));
            if(val >= 1 && val <= size) {
                int idx = val -1;
                input.set(idx, Math.abs(input.get(idx)) * (-1));
            }
        }

        for (int i = 0; i < size; i++) {
            if(input.get(i) > 0){
                return i + 1;
            }
        }
        return size + 1;
    }

    /*
    Q2. Merge Sorted Overlapping Intervals - 2
    Problem Description

    You are given a collection of intervals A in a 2-D array format, where each interval is represented by a pair of integers `[start, end]`. The intervals are sorted based on their start values.

    Your task is to merge all overlapping intervals and return the resulting set of non-overlapping intervals.


    Problem Constraints

    1 <= len(A) <= 100000.
    1 <= A[i][0] <= A[i][1] <= 100000

    A is sorted based on the start value (A[i][0])



    Input Format

    First argument is a list of intervals in 2-Dimentional Array.



    Output Format

    Return the sorted list of intervals after merging all the overlapping intervals.



    Example Input

    Input 1:

    [ [1, 3], [2, 6], [8, 10], [15, 18] ]
    Input 2:

    [ [2, 10], [4, 9], [6, 7] ]


    Example Output

    Output 1:

    [ [1, 6], [8, 10], [15, 18] ]
    Output 2:

    [ [2, 10] ]


    Example Explanation

    Explanation 1:

    Merge intervals [1,3] and [2,6] -> [1,6].
    so, the required answer after merging is [1,6],[8,10],[15,18].
    No more overlapping intervals present.
    Explanation 2:

    Merge intervals [2, 10], [4, 9], [6, 7] as [2,10].
    Since [4, 9] and [6, 7] is overlapping inside the interval [2, 10].

    so, the required answer after merging is [2, 10].
     */

    public static ArrayList<ArrayList<Integer>> mergeSortedOverlapingIntr(ArrayList<ArrayList<Integer>> input) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int start = input.get(0).get(0), end = input.get(0).get(1);
        for(int i = 1; i < input.size(); i++) {
            if(input.get(i).get(0) <= end) {
                start = Math.min(start, input.get(i).get(0));
                end = Math.max(end, input.get(i).get(1));
            } else {
                ArrayList<Integer> interval = new ArrayList<Integer>();
                interval.add(start);
                interval.add(end);
                result.add(interval);
                start = input.get(i).get(0);
                end = input.get(i).get(1);
            }
        }
        ArrayList<Integer> interval = new ArrayList<Integer>();
        interval.add(start);
        interval.add(end);
        result.add(interval);
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(3, 4, -1, 1));
        //System.out.println(firstMissingPositive(input));
        ArrayList<ArrayList<Integer>> intervals = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> inter = new ArrayList<>();
        inter.add(1);inter.add(3);intervals.add(inter);
        inter = new ArrayList<>();
        inter.add(2);inter.add(6);intervals.add(inter);
        inter = new ArrayList<>();
        inter.add(8);inter.add(10);intervals.add(inter);
        inter = new ArrayList<>();
        inter.add(15);inter.add(18);intervals.add(inter);

        System.out.println(mergeSortedOverlapingIntr(intervals));
    }
}
