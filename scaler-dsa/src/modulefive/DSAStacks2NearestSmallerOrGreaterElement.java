package modulefive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DSAStacks2NearestSmallerOrGreaterElement {
   /*
   Q2. Nearest Smaller Element
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.


More formally,

G[i] for an element A[i] = an element A[j] such that

j is maximum possible AND

j < i AND

A[j] < A[i]

Elements for which no smaller element exist, consider the next smaller element as -1.




Problem Constraints

1 <= |A| <= 100000


-109 <= A[i] <= 109





Input Format

The only argument given is integer array A.



Output Format

Return the integar array G such that G[i] contains the nearest smaller number than A[i]. If no such element occurs G[i] should be -1.



Example Input

Input 1:


 A = [4, 5, 2, 10, 8]

Input 2:


 A = [3, 2, 1]



Example Output

Output 1:


 [-1, 4, -1, 2, 2]

Output 2:


 [-1, -1, -1]
    */

    public static int[] prevSmaller(ArrayList<Integer> input) {
        int N = input.size();
        int[] C = new int[N];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < N; i++)
        {
            int ele = input.get(i);
            while(st.size() != 0 && st.peek() >= ele)
            {
                st.pop();
            }
            if(st.size() == 0)
            {
                C[i] = -1;
            }
            else
            {
                C[i] = st.peek();
            }
            st.push(ele);
        }
        return C;
    }
    public static void main(String[] args) {
       //
        prevSmaller(new ArrayList<Integer>(Arrays.asList(4, 5, 2, 10, 8)));
    }
}
