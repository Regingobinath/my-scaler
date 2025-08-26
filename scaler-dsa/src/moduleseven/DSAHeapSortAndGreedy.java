package moduleseven;

import java.util.*;

public class DSAHeapSortAndGreedy {
    /*
    Q1. Running Median
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Flipkart is currently dealing with the difficulty of precisely estimating and displaying the expected delivery time for orders to a specific pin code. The existing method relies on historical delivery time data for that pin code, using the median value as the expected delivery time. As the order history expands with new entries, Flipkart aims to enhance this process by dynamically updating the expected delivery time whenever a new delivery time is added. The objective is to find the expected delivery time after each new element is incorporated into the list of delivery times. End Goal: With every addition of new delivery time, requirement is to find the median value.

Why Median ? The median is calculated because it provides a more robust measure of the expected delivery time The median is less sensitive to outliers or extreme values than the mean. In the context of delivery times, this is crucial because occasional delays or unusually fast deliveries (outliers) can skew the mean significantly, leading to inaccurate estimations.


Given an array of integers, A denoting the delivery times for each order. New arrays of integer B and C are formed, each time a new delivery data is encountered, append it at the end of B and append the median of array B at the end of C. Your task is to find and return the array C.

NOTE:

If the number of elements is N in B and N is odd, then consider the median as B[N/2] ( B must be in sorted order).
If the number of elements is N in B and N is even, then consider the median as B[N/2-1]. ( B must be in sorted order).



Problem Constraints

1 <= length of the array <= 100000
1 <= A[i] <= 109



Input Format

The only argument given is the integer array A.



Output Format

Return an integer array C, C[i] denotes the median of the first i delivery times.



Example Input

Input 1:

 A = [1, 2, 5, 4, 3]
Input 2:

 A = [5, 17, 100, 11]




Example Output

Output 1:

 [1, 1, 2, 2, 3]
Output 2:

 [5, 5, 17, 11]




Example Explanation

Explanation 1:



 Delivery Times      median
 [1]                   1
 [1, 2]                1
 [1, 2, 5]             2
 [1, 2, 5, 4]          2
 [1, 2, 5, 4, 3]       3
Explanation 2:

 Delivery Times     median
 [5]                  5
 [5, 17]              5
 [5, 17, 100]         17
 [5, 17, 100, 11]     11
     */
    public static ArrayList<Integer> runningMedian(ArrayList<Integer> times) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());//we need to get always max from first heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();//we need to get always min from second heap
        ArrayList<Integer> medians = new ArrayList<Integer>();
        for(int i=0; i< times.size(); i++) {

            int time = times.get(i);
            if (maxHeap.size() == 0 || time < maxHeap.peek()) {
                maxHeap.add(time);
            } else {
                minHeap.add(time);
            }
            if(maxHeap.size() - minHeap.size() == 2) {
                minHeap.add(maxHeap.peek());
                maxHeap.remove();
            }
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.peek());
                minHeap.remove();
            }
            if (maxHeap.size() > minHeap.size()) {
                medians.add(maxHeap.peek());
            } else {
                /*
                when sizes are equal n/2-1 will always falls at fist heap which is maxheap
                 */
                medians.add(maxHeap.peek());
            }
        }
        return medians;
    }

    /*
    Q2. Finish Maximum Jobs
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

There are N jobs to be done, but you can do only one job at a time.





Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.

Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.

Return the maximum number of jobs you can finish.







Problem Constraints

1 <= N <= 105

1 <= A[i] < B[i] <= 109



Input Format

The first argument is an integer array A of size N, denoting the start time of the jobs.
The second argument is an integer array B of size N, denoting the finish time of the jobs.



Output Format

Return an integer denoting the maximum number of jobs you can finish.



Example Input

Input 1:

 A = [1, 5, 7, 1]
 B = [7, 8, 8, 8]
Input 2:

 A = [3, 2, 6]
 B = [9, 8, 9]


Example Output

Output 1:

 2
Output 2:

 1


Example Explanation

Explanation 1:

 We can finish the job in the period of time: (1, 7) and (7, 8).
Explanation 2:

 Since all three jobs collide with each other. We can do only 1 job.
     */

    public static int findMaxNoOfJobsDone(ArrayList<Integer> jobsST, ArrayList<Integer> jobsET) {
        //construct Job
        List<Job> jobsList = new ArrayList<Job>();
        for(int i=0; i<jobsST.size(); i ++) {
            jobsList.add(new Job(jobsST.get(i), jobsET.get(i)));
        }
        Collections.sort(jobsList, new Comparator<Job>() {
            @Override
            public int compare(Job l, Job r) {
                return l.end - r.end;
            }
        });
        int minend = -1, ans = 0;
        for(Job jobItem : jobsList) {
            if(jobItem.start >= minend) {
                ans++;
                minend = jobItem.end;
            }
        }
        return ans;
    }

    public static void main(String args[]) {
       // System.out.println(runningMedian(new ArrayList<Integer>(Arrays.asList(59,64,10,39))));
        System.out.println(findMaxNoOfJobsDone(
                new ArrayList<Integer>(Arrays.asList(3, 2, 6)),
                        new ArrayList<Integer>(Arrays.asList(9, 8, 9))));
    }
}

class Job {
    int start;
    int end;
    Job(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
