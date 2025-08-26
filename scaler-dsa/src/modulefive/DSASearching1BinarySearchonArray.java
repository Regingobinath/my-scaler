package modulefive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DSASearching1BinarySearchonArray {
    /*
    Q1. Search for a Range
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given a sorted array of integers A (0-indexed) of size N, find the left most and the right most index of a given integer B in the array A.

Return an array of size 2, such that
          First element = Left most index of B in A
          Second element = Right most index of B in A.
If B is not found in A, return [-1, -1].

Note : Note: The time complexity of your algorithm must be O(log n)..


Problem Constraints

1 <= N <= 106
1 <= A[i], B <= 109


Input Format

The first argument given is the integer array A.
The second argument given is the integer B.


Output Format

Return the left most and right most index (0-based) of B in A as a 2-element array. If B is not found in A, return [-1, -1].


Example Input

Input 1:

 A = [5, 7, 7, 8, 8, 10]
 B = 8
Input 2:

 A = [5, 17, 100, 111]
 B = 3


Example Output

Output 1:

 [3, 4]
Output 2:

 [-1, -1]


Example Explanation

Explanation 1:

 The first occurrence of 8 in A is at index 3.
 The last occurrence of 8 in A is at index 4.
 ans = [3, 4]
Explanation 2:

 There is no occurrence of 3 in the array.
     */
    public static ArrayList<Integer> searchRange(final List<Integer> input, int key) {
        int left = 0, right = input.size()-1;
        int leftMost = -1, rightMost = -1;

        //find first occurrence
        while (left <= right) {
            int mid = left + (right - left)/2;
            if(input.get(mid) == key) {
                leftMost = mid;
                right = mid-1;//Go left to find leftmost
            } else if(input.get(mid) > key) {
                right = mid -1;
            } else {
                left = mid +1;
            }
        }
        //find second occurrence
        left = 0; right = input.size()-1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if(input.get(mid) == key) {
                rightMost = mid;
                left = mid+1;//Go right to find rightmost
            } else if(input.get(mid) > key) {
                right = mid -1;
            } else {
                left = mid +1;
            }
        }

        return new ArrayList<>(Arrays.asList(leftMost,rightMost));
    }

    /*
    Q2. Sorted Insert Position
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given a sorted array A of size N and a target value B.
Your task is to find the index (0-based indexing) of the target value in the array.

If the target value is present, return its index.
If the target value is not found, return the index of least element greater than equal to B.
If the target value is not found and least number greater than equal to target is also not present, return the length of array (i.e. the position where target can be placed)
Your solution should have a time complexity of O(log(N)).


Problem Constraints

1 <= N <= 105
1 <= A[i] <= 105
1 <= B <= 105



Input Format

The first argument is an integer array A of size N.
The second argument is an integer B.



Output Format

Return an integer denoting the index of target value.



Example Input

Input 1:

A = [1, 3, 5, 6]
B = 5
Input 2:

A = [1, 4, 9]
B = 3


Example Output

Output 1:

2
Output 2:

1


Example Explanation

Explanation 1:

The target value is present at index 2.
Explanation 2:

The target value should be inserted at index 1.
     */
    public static int searchInsert(ArrayList<Integer> input, int searchKey) {

        if(input.size() == 1) {
            return input.get(0) == searchKey ? 0 : 1;
        }
        int left = 0, right = input.size()-1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right -left)/2;
            if(input.get(mid) == searchKey) {
                ans = mid;
                break;
            }
            if(input.get(mid) > searchKey) {
                ans = mid;
                right = mid -1;
            } else {
                left = mid +1;
            }
        }
        return ans > -1 ? ans : input.size();
    }

    /*
    Q3. Single Element in Sorted Array
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.

Elements which are appearing twice are adjacent to each other.

NOTE: Users are expected to solve this in O(log(N)) time.



Problem Constraints

1 <= |A| <= 100000

1 <= A[i] <= 10^9



Input Format

The only argument given is the integer array A.



Output Format

Return the single element that appears only once.



Example Input

Input 1:

A = [1, 1, 7]
Input 2:

A = [2, 3, 3]


Example Output

Output 1:

 7
Output 2:

 2


Example Explanation

Explanation 1:

 7 appears once
Explanation 2:

 2 appears once
     */

    public static int singleElementInSortedArray(ArrayList<Integer> input) {
        if(input.size() < 3) {
            return input.get(0);
        }
        int l = input.get(0), r = input.get(1);
        if(l != r) {
            return l;
        }

        l = input.get(input.size() - 1); r = input.get(input.size() - 2);
        if(l != r) {
            return l;
        }
        int left = 1, right = input.size()-2, ans = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int firstOcr = -1;
            int midVal = input.get(mid), lval = input.get(mid-1), rval = input.get(mid+1);
            if(midVal == lval) {
                firstOcr = mid-1;
            } else if(midVal == rval) {
                firstOcr = mid;
            } else{
                ans = input.get(mid);
                break;
            }
            System.out.println(firstOcr);
            if(firstOcr % 2 == 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return ans;
    }

    /*
    Q4. Find a peak element
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array of integers A, find and return the peak element in it.
An array element is considered a peak if it is not smaller than its neighbors. For corner elements, we need to consider only one neighbor.

NOTE:

It is guaranteed that the array contains only a single peak element.
Users are expected to solve this in O(log(N)) time. The array may contain duplicate elements.


Problem Constraints

1 <= |A| <= 100000

1 <= A[i] <= 109



Input Format

The only argument given is the integer array A.



Output Format

Return the peak element.



Example Input

Input 1:

A = [1, 2, 3, 4, 5]
Input 2:

A = [5, 17, 100, 11]




Example Output

Output 1:


 5
Output 2:

 100


Example Explanation

Explanation 1:

 5 is the peak.
Explanation 2:

 100 is the peak.
     */

    public static int findPeekElement(ArrayList<Integer> elements) {

        if(elements.size() == 1) {
            return elements.get(0);
        }
        int midVal = elements.get(0), rightVal = elements.get(1), leftVal = 0;
        if(midVal > rightVal) {
            return midVal;
        }
        midVal = elements.get(elements.size() -1);leftVal = elements.get(elements.size() -2);
        if(midVal > leftVal) {
            return midVal;
        }
        int left = 0, right = elements.size()-1;
        boolean equalFound = false;
        while (left <= right) {
            int mid = left + (right - left)/2;
            midVal = elements.get(mid);
            leftVal = mid > 0 ? elements.get(mid - 1) : Integer.MIN_VALUE;
            rightVal = mid < elements.size() -1 ? elements.get(mid + 1) : Integer.MIN_VALUE;
            boolean isLeftOk = mid == 0 || midVal > leftVal,
                    isRightOk = (mid == elements.size()-1) || midVal > rightVal;
            if(isLeftOk && isRightOk) {
                return midVal;
            }
            if(mid > 0 && !isLeftOk) {
                right = mid - 1;
            } else if(!isRightOk) {
                left = mid + 1;
            } else {
                //Duplicate value, either way
                if(equalFound == false) {
                    //go left this time
                } else {
                    //go right this time
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        //System.out.println(searchRange(Arrays.asList(5, 7, 7, 8, 8, 10), 8));
        //System.out.println(searchRange(Arrays.asList( 5, 17, 100, 111), 120));
        /*System.out.println(searchInsert(new ArrayList<>(Arrays.asList(
                141, 144, 145, 154, 229, 235, 243, 266, 344, 351, 466,
                499, 512, 565, 641, 675, 690, 726, 805, 879, 978, 986 )), 65));*/
        /*System.out.println(singleElementInSortedArray(new ArrayList<Integer>(Arrays.asList(
                13,13,21,21,27,50,50,102,102,108,108,110,110,117,117,120,120,123,123,124,124,132,132,164,
                164,166,166,190,190,200,200,212,212,217,217,225,225,238,238,261,261,276,276,347,347,348,
                348,386,386,394,394,405,405,426,426,435,435,474,474,493,493))));*/
        System.out.println(findPeekElement(new ArrayList<Integer>(Arrays.asList(5, 17, 100, 11))));
    }
}
