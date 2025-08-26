package modulefive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DSASearching3BinarySearchonAnswer {

    /*
Q1. Painter's Partition Problem
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given 2 integers A and B and an array of integers C of size N. Element C[i] represents the length of ith board.
You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of the board.

Calculate and return the minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of the board.
NOTE:
1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
2. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3 but not 2 is invalid.

Return the ans % 10000003.



Problem Constraints

1 <= A <= 1000
1 <= B <= 106
1 <= N <= 105
1 <= C[i] <= 106



Input Format

The first argument given is the integer A.
The second argument given is the integer B.
The third argument given is the integer array C.



Output Format

Return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board % 10000003.



Example Input

Input 1:

 A = 2
 B = 5
 C = [1, 10]
Input 2:

 A = 10
 B = 1
 C = [1, 8, 11, 3]


Example Output

Output 1:

 50
Output 2:

 11


Example Explanation

Explanation 1:

 Possibility 1:- One painter paints both blocks, time taken = 55 units.
 Possibility 2:- Painter 1 paints block 1, painter 2 paints block 2, time take = max(5, 50) = 50
 There are no other distinct ways to paint boards.
 ans = 50 % 10000003
Explanation 2:

 Each block is painted by a painter so, Painter 1 paints block 1, painter 2 paints block 2, painter 3 paints block 3
 and painter 4 paints block 4, time taken = max(1, 8, 11, 3) = 11
 ans = 11 % 10000003
     */

    public static int paintersPartition (int painters, int unitOfTime, ArrayList<Integer> boards) {
        long boardLength = 0l;
        long maxBoard = 0;
        for (Integer board : boards) {
            boardLength = boardLength + board;
            if(board.longValue() > maxBoard) {
                maxBoard = board.longValue();
            }
        }
        long totalTimeRequired = boardLength;
        //binary search of answers
        long left = maxBoard, right = totalTimeRequired;
        long minTimeRequired = 0l;
        while (left <= right) {
            long mid = left + (right -left)/2;
            boolean isPossibleByThisTime = isPaintingPossible(boards, painters, mid);
            if(isPossibleByThisTime) {
                right = mid -1;
                minTimeRequired = mid;
            } else {
                left = mid +1;
            }
        }
        return (int)((minTimeRequired * unitOfTime) % 10000003l);
    }

    public static boolean isPaintingPossible(
            ArrayList<Integer> boards,
            int painters, long totalTime) {
        long paintersCount = 1, currTimetaken = 0;
        for(int i =0; i < boards.size();i++) {
            if(boards.get(i).longValue()   > totalTime) {
                //this board cannot be painted in given time
                return false;
            }
            if((currTimetaken + boards.get(i).longValue()) <= totalTime) {
                currTimetaken = currTimetaken + boards.get(i).longValue() ;
            } else{
                paintersCount ++;
                currTimetaken = boards.get(i).longValue();
            }
        }
        if (paintersCount <= painters) {
            return true;
        }
        return false;
    }

    /*
    Q2. Aggressive cows
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Farmer John has built a new long barn with N stalls. Given an array of integers A of size N where each element of the array represents the location of the stall and an integer B which represents the number of cows.

His cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?



Problem Constraints

2 <= N <= 100000
0 <= A[i] <= 109
2 <= B <= N



Input Format

The first argument given is the integer array A.
The second argument given is the integer B.



Output Format

Return the largest minimum distance possible among the cows.



Example Input

Input 1:

A = [1, 2, 3, 4, 5]
B = 3
Input 2:

A = [1, 2]
B = 2


Example Output

Output 1:

 2
Output 2:

 1


Example Explanation

Explanation 1:

 John can assign the stalls at location 1, 3 and 5 to the 3 cows respectively. So the minimum distance will be 2.
Explanation 2:

 The minimum distance will be 1.
     */

    public static int arrangementOfAggresiveCow(ArrayList<Integer> stalls, int cows) {

        Collections.sort(stalls);//to keep stalls in order
        int left = 1, right = stalls.get(stalls.size()-1).intValue() - stalls.get(0).intValue() , ans = 0;

        while (left <= right) {
            int mid = left + (right - left) /2;
            boolean rightDistance = isPossibleInThisDistance(stalls, cows, mid);
            if(rightDistance) {
                ans = mid;
                left = mid +1;
            } else {
                right = mid -1;
            }
        }
        return ans;
    }

    public static boolean isPossibleInThisDistance(ArrayList<Integer> stalls, int cows, int minDistance) {

        int countCow = 1, currentStall = stalls.get(0);
        for(int i =1;  i < stalls.size();i++) {
            if((stalls.get(i).intValue() - currentStall) >= minDistance) {
                countCow ++;
                currentStall = stalls.get(i).intValue();
            }
        }

        return countCow >= cows;
    }
    public static void main(String[] args) {
        /*System.out.println(paintersPartition(
                3, 10, new ArrayList<Integer> (Arrays.asList(185,186,938,558,655,461,441,234,902,681))));*/
        System.out.println(arrangementOfAggresiveCow(
                new ArrayList<Integer> (Arrays.asList(82,61,38,88,12,7,6,12,48,8,31,90,35,5,88,2,66,19,5,96,84,95)), 8));

    }
}
