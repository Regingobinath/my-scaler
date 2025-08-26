package modulefive;

import java.util.ArrayList;
import java.util.Arrays;

public class DSASearching2BinarySearchProblems {

    /*
    Q1. Square Root of Integer
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an integer A. Compute and return the square root of A.
If A is not a perfect square, return floor(sqrt(A)).

NOTE:
   The value of A*A can cross the range of Integer.
   Do not use the sqrt function from the standard library.
   Users are expected to solve this in O(log(A)) time.


Problem Constraints

0 <= A <= 109


Input Format

The first and only argument given is the integer A.


Output Format

Return floor(sqrt(A))


Example Input

Input 1:

 11
Input 2:

 9


Example Output

Output 1:

 3
Output 2:

 3


Example Explanation

Explanation 1:
When A = 11 , square root of A = 3.316. It is not a perfect square so we return the floor which is 3.
Explanatino 2:
When A = 9 which is a perfect square of 3, so we return 3.
     */

    public static int sqrt(int n) {
        if(n <= 1) {
            return n;
        }
        long left = 0, right = n-1, ans = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long mul = mid * mid;
            if(mul <= n) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return (int)ans;
    }
    /*
    Q2. Rotated Sorted Array Search
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given a sorted array of integers A of size N and an integer B,
where array A is rotated at some pivot unknown beforehand.

For example, the array [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0, 1, 2].

Your task is to search for the target value B in the array. If found, return its index; otherwise, return -1.

You can assume that no duplicates exist in the array.

NOTE: You are expected to solve this problem with a time complexity of O(log(N)).


Problem Constraints

1 <= N <= 1000000
1 <= A[i] <= 109
All elements in A are Distinct.


Input Format

The First argument given is the integer array A.
The Second argument given is the integer B.


Output Format

Return index of B in array A, otherwise return -1


Example Input

Input 1:

A = [4, 5, 6, 7, 0, 1, 2, 3]
B = 4
Input 2:

A : [ 9, 10, 3, 5, 6, 8 ]
B : 5


Example Output

Output 1:

 0
Output 2:

 3


Example Explanation

Explanation 1:

Target 4 is found at index 0 in A.
Explanation 2:

Target 5 is found at index 3 in A.
     */

    public static int searchInRotatedArray(ArrayList<Integer> searchSpace, int key) {
        int firstVal = searchSpace.get(0);
        boolean isKeyInpart1 = firstVal <= key;
        int left = 0, right = searchSpace.size()-1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            boolean isMidInPart1 = false;
            int midVal = searchSpace.get(mid);
            if(midVal >= firstVal) {
                isMidInPart1 = true;
            }
            if(isKeyInpart1 == isMidInPart1) {
                if(midVal == key) {
                    return mid;
                } else if(midVal > key) {
                    right = mid-1;
                } else {
                    left = mid +1;
                }
            } else if(isKeyInpart1){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*
    Q3. Median of two sorted arrays
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given two sorted arrays A and B of size M and N respectively, return the median of the two sorted arrays.
Round of the value to the floor integer [2.6=2, 2.2=2]


Problem Constraints

0 <= M <= 105
0 <= N <= 105
-109 <= A[i], B[i] <= 109


Input Format

First argument A is an array of integers.
First argument B is an array of integers.


Output Format

Return an integer.


Example Input

Input 1:

A = [1, 3]
B = [2]

Input 2:

A = [1, 2]
B = [3,4]


Example Output

Output 1:


3


Output 2:


3


Example Explanation

Example 1:


merged array = [1,2,3] and median is 2.
Example 2:


merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2
     */
    /*public static int medianOfTwoSortedArrays(ArrayList<Integer> spaceOne, ArrayList<Integer> spaceTwo) {
        if(spaceOne.size() > spaceTwo.size()) {
            ArrayList<Integer> tmp = spaceOne;
            spaceOne = spaceTwo;
            spaceTwo = spaceOne;
        }
        int n = spaceOne.size(), m = spaceTwo.size();
        int lenOfPartA = (n + m + 1) / 2;
        int left = 0, right = n;
        while(left <= right) {
            int midA = (left + right)/2;
            int midB = lenOfPartA - midA;
            int lA = midA > 0 ? spaceOne.get(midA-1) : Integer.MIN_VALUE;
            int rA = midA < n ? spaceOne.get(midA) : Integer.MAX_VALUE;
            int lB = midB > 0 ? spaceTwo.get(midB-1) : Integer.MIN_VALUE;
            int rB = midB < n ? spaceTwo.get(midB) : Integer.MAX_VALUE;

            if(lA <= rB && lB <= rA) {
                if((n + m) % 2 == 0) {
                    return (Math.max(lA,lB) + Math.min(rA,rB)) / 2;
                } else {
                    return Math.max(lA,lB);
                }
            } else if (lA > rB){
                    right = midA-1;
            } else {
                left = midA +1;
            }
        }
        return -1;
    }*/
    public static int medianOfTwoSortedArrays(ArrayList<Integer> spaceOne, ArrayList<Integer> spaceTwo) {
        int N = spaceOne.size();
        int M = spaceTwo.size();
        int leftHalf = (N+M+1)/2; // to keep more elements on left side > so that median lies in left portion only in case of odd no. of elements
        double median = 0;
        if(N == 0){ // handle case when A is of zero size
            if(M % 2 == 0){
                return (spaceTwo.get(M/2) + spaceTwo.get((M/2)-1))/2;
            }
            else{
                return spaceTwo.get(M/2);
            }
        }
        if(M == 0){ // handle case when B is of zero size
            if(N % 2 == 0){
                return (spaceOne.get(N/2) + spaceTwo.get((N/2)-1))/2;
            }
            else{
                return spaceTwo.get(N/2);
            }
        }
        int low = 0; // min 0 elements from array A
        int high = N; // max N elements from array A
        while(low <= high){
            int m = (low + high)/2;
            int fromA = m;
            int fromB = leftHalf - fromA;
            if(fromB < 0){ // elements fromB cannot be -ve, so reset no. of max elements from A
                high = m -1;
                continue;
            }
            if(fromB > M){ // elements from B cannot be more than M, so reset min elements from A
                low = m+1;
                continue;
            }
            int l1 = Integer.MIN_VALUE; int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE; int r2 = Integer.MAX_VALUE;

            if(fromA -1 >= 0) l1 = spaceOne.get(fromA-1);
            if(fromB - 1 >= 0) l2 = spaceTwo.get(fromB-1);
            if(fromA < N) r1 = spaceOne.get(fromA);
            if(fromB < M) r2 = spaceTwo.get(fromB);

            if(l1 <= r2 && l2 <= r1){
                if((N+M) % 2 == 0){ // total length is even
                    double maxl = Math.max(l1,l2);
                    double minr = Math.min(r1,r2);
                    median = (int)(maxl+minr)/2.0;
                    return (int)median;
                }
                else{ // total length is odd
                    double maxl = Math.max(l1,l2);
                    median = maxl;
                    return (int)median;
                }
            }
            else if(l1 > r2){
                high = m -1;
            }
            else{
                low = m +1;
            }
        }
        return (int)median;
    }



    public static void main(String[] args) {
        //System.out.println(sqrt(930675566));
        /*System.out.println(searchInRotatedArray(
                new ArrayList<Integer>(Arrays.asList(9, 10, 3, 5, 6, 8)), 5));*/
        System.out.println(medianOfTwoSortedArrays(
                new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)),
                new ArrayList<Integer>(Arrays.asList(10))));
    }
}
