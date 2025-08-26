package moduleseven;

public class DSAHeaps1IntroductionArray {
/*
Q2. Build a Heap
Solved
feature icon
Get your doubts resolved blazing fast with Chat GPT Help
Check Chat GPT
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array A of N integers, convert that array into a min heap and return the array.

NOTE: A min heap is a binary tree where every node has a value less than or equal to its children.



Problem Constraints

1 ≤ N ≤ 105

0 ≤ A[i] ≤ 109



Input Format

First and only argument of input contains a single integer array A of length N.



Output Format

Return the reordered array A such that it forms a min heap.



Example Input

Input:

A = [5, 13, -2, 11, 27, 31, 0, 19]


Example Output

Output:

A = [-2, 5, 0, 13, 11, 19, 27, 31]


Example Explanation

One possible Heap is

                -2
               /    \
             5       0
            / \    /  \
          13  11  19   27
          /
        31

It can be seen that each parent has a value smaller than its children. Hence it is a Valid Heap.

The Heap in the Array format is [-2, 5, 0, 13, 11, 19, 27, 31].

Some more possible heaps are  [-2, 0, 5, 13, 11, 27, 19, 31], [-2, 5, 0, 11, 27, 13, 19, 31], etc.
You can return any possible Valid Heap Structure.
 */
    static void swap( int[] A, int i1, int i2 ) {
        int temp = A[i1];
        A[i1] = A[i2];
        A[i2] = temp;
    }
    public static void heapify( int[] A, int index ) {
        int n = A.length;
        int min =index;
        int lc= index*2+1;
        int rc = index*2+2;

        if(lc<n && A[min]>A[lc]){
            min =lc;
        }
        if(rc<n && A[min]>A[rc]){
            min =rc;
        }
        if(min!=index){
            swap(A,min,index);
            heapify(A,min);
        }
    }

    public static int[] buildHeap( int[] A ) {

        int n = A.length;
        for( int i= n/2-1; i>=0; i-- ) {
            heapify( A, i );
        }
        return A;
    }

    public static void main(String[] args) {
        int[] heapTree = buildHeap(new int[]{5, 13, -2, 11, 27, 31, 0, 19});
        for(int i=0;i<heapTree.length;i++) {
            System.out.print(heapTree[i] + " ");
        }
    }
}
