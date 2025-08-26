package moduleseven;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DSAHeaps1Introduction {

    public static ArrayList<Integer> insertInHeap(ArrayList<Integer> heapTree, Integer node) {
        //push at end
        heapTree.add(node);
        heapifyUp(heapTree, heapTree.size() - 1);

        return heapTree;
    }
    public static void heapifyUp(ArrayList<Integer> heapTree, int curIdx) {
        int prnd = curIdx / 2;
        if(prnd >= 0) {
            if (heapTree.get(prnd) > heapTree.get(curIdx)) {
                //swap both child and parent
                swap(heapTree, prnd, curIdx);
                heapifyUp(heapTree, prnd);
            }
        }
    }

    public static ArrayList<Integer> deleteMin (ArrayList<Integer> heapTree) {
        //min always at head of tree in minHeapTree
        //swap head and tail
        swap(heapTree, 0, heapTree.size()-1);
        heapTree.remove(heapTree.size()-1);//if needed
        heapifyDown(heapTree, 0);
        return heapTree;
    }

    public static void heapifyDown(ArrayList<Integer> heapTree, Integer curIdx) {
        int size = heapTree.size();
        int left = (curIdx * 2) + 1, right  = (curIdx * 2) + 2;
        if(left >= size) {// no left node so no right node
            return;
        }
        //now left node is there, mak sure right node is not there
        if(right >= size) {// no right node, yes to left node
            if(heapTree.get(curIdx) < heapTree.get(left)) {
                swap(heapTree, curIdx, left);
                return;//only one child , so no heapify
            }
        }
        //both are there
        int min = Math.min(heapTree.get(right), heapTree.get(left));
        min = Math.min(min, heapTree.get(curIdx));
        if (min == heapTree.get(curIdx)) {
            return;
        }
        if (min == heapTree.get(left)) {
            swap(heapTree, left, curIdx);
            heapifyDown(heapTree, left);
        }
        if (min == heapTree.get(right)) {
            swap(heapTree, right, curIdx);
            heapifyDown(heapTree, right);
        }
    }

    public static ArrayList<Integer> swap(ArrayList<Integer> list, int leftIdx, int rightIdx) {
        int tmp = list.get(leftIdx);
        list.set(leftIdx, list.get(rightIdx));
        list.set(rightIdx, tmp);
        return list;
    }

    public static ArrayList<Integer> constructHeapTree(ArrayList<Integer> arrayList) {
        ArrayList<Integer> heapTree = new ArrayList<Integer>();
        for(int i = 0; i < arrayList.size(); i++) {
            insertInHeap(heapTree, arrayList.get(i));
        }
        return heapTree;
    }
    /*
    Q1. Connect ropes
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given an array A of integers that represent the lengths of ropes.




You need to connect these ropes into one rope. The cost of joining two ropes equals the sum of their lengths.

Find and return the minimum cost to connect these ropes into one rope.






Problem Constraints

1 <= length of the array <= 100000
1 <= A[i] <= 1000



Input Format

The only argument given is the integer array A.



Output Format

Return an integer denoting the minimum cost to connect these ropes into one rope.



Example Input

Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [5, 17, 100, 11]


Example Output

Output 1:

 33
Output 2:

 182


Example Explanation

Explanation 1:

 Given array A = [1, 2, 3, 4, 5].
 Connect the ropes in the following manner:
 1 + 2 = 3
 3 + 3 = 6
 4 + 5 = 9
 6 + 9 = 15

 So, total cost  to connect the ropes into one is 3 + 6 + 9 + 15 = 33.
Explanation 2:

 Given array A = [5, 17, 100, 11].
 Connect the ropes in the following manner:
 5 + 11 = 16
 16 + 17 = 33
 33 + 100 = 133

 So, total cost  to connect the ropes into one is 16 + 33 + 133 = 182.
     */

    public static int minCostOfRopes(ArrayList<Integer> ropes) {
        //need to take min, from array so using PriorityQueue
        PriorityQueue<Integer> queque = new PriorityQueue<Integer>(ropes);
        int min_cost = 0;
        while(queque.size() > 1) {
            int min_rope = queque.peek();
            queque.remove();
            int second_min_rope = queque.peek();
            queque.remove();
            min_cost = min_cost + ( min_rope + second_min_rope );
            queque.add( min_rope + second_min_rope );
        }
        return min_cost;
    }

    /*
    Q3. Heap Queries
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You have an empty min heap. You are given an array A consisting of N queries. Let P denote A[i][0] and Q denote A[i][1]. There are two types of queries:

P = 1, Q = -1 : Pop the minimum element from the heap.
P = 2, 1 <= Q <= 109 : Insert Q into the heap.

Return an integer array containing the answer for all the extract min operation. If the size of heap is 0, then extract min should return -1.



Problem Constraints

1 <= N <= 105

1 <= A[i][0] <= 2

1 <= A[i][1] <= 109 or A[i][1] = -1



Input Format

The only argument A is a 2D integer array



Output Format

Return an integer array



Example Input

Input 1:

A = [[1, -1], [2, 2], [2, 1], [1, -1]]
Input 2:

A = [[2, 5], [2, 3], [2, 1], [1, -1], [1, -1]]


Example Output

Output 1:

[-1, 1]
Output 2:

[1, 3]


Example Explanation

Explanation 1:

For the first extract operation the heap is empty so it gives -1. For the second
extract operation the heap contains the elements 2 and 1. Extract min returns the element 1.
Explanation 2:

The heap contains the elements 5, 3 and 1. The first extract min operation gets the
 element 1 and the second operation gets the element 3.
     */
    public static ArrayList<Integer> heapQueries(ArrayList<ArrayList<Integer>> queries) {
        // To store the results of extract min operations
        ArrayList<Integer> result = new ArrayList<>();
        // Min heap to store and retrieve minimum elements efficiently
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Iterate through each query in the input
        for (ArrayList<Integer> query : queries) {
            int p = query.get(0);  // Operation type (1: extract, 2: insert)
            int q = query.get(1);  // Value to insert (if p == 2)

            if (p == 1) {  // Extract min operation
                // If heap is empty, add -1 to result, else remove and add min element
                result.add(pq.isEmpty() ? -1 : pq.remove());
            } else if (p == 2) {  // Insert operation
                pq.add(q);  // Add the value to the min heap
            }
        }
        return result;  // Return the list of results from extract min operations
    }

    public static void main(String[] args) {
        //System.out.println(constructHeapTree(new ArrayList(Arrays.asList(5, 13, -2, 11, 27, 31, 0, 19))));
        //[8, 9, 10, 16, 12, 14, 25, 18, 34]
        /*System.out.println(deleteMin(
                constructHeapTree(
                        new ArrayList(Arrays.asList(8,10,9,16,12,14,25,18,34)))));*/
        //System.out.println(minCostOfRopes(new ArrayList(Arrays.asList(1, 2, 3, 4, 5))));
        ArrayList<ArrayList<Integer>> input  = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arr  = new ArrayList<Integer>(Arrays.asList(1,-1));input.add(arr);
        arr  = new ArrayList<Integer>(Arrays.asList(2,2));input.add(arr);
        arr  = new ArrayList<Integer>(Arrays.asList(2,1));input.add(arr);
        arr  = new ArrayList<Integer>(Arrays.asList(1,-1));input.add(arr);
        System.out.println(heapQueries(input));
    }
}
