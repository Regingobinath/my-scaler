package moduleseven;

import java.util.*;

public class DSALabSessiononHeapsAndGreedy {
    public static ArrayList<Integer> nthLargestElement(int n, ArrayList<Integer> input) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            minHeap.add(input.get(i));
            if (i < n-1) {
                result.add(-1);
            } else {
                result.add(minHeap.peek());
            }
        }
        for(int i = n; i < input.size(); i++) {
            minHeap.add(input.get(i));
            minHeap.remove();
            result.add(minHeap.peek());
        }
        return result;
    }
/*
Q2. Merge K Sorted Lists
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given a list containing head pointers of N sorted linked lists.
Merge these given sorted linked lists and return them as one sorted list.



Problem Constraints

1 <= total number of elements in given linked lists <= 100000



Input Format

The first and only argument is a list containing N head pointers.



Output Format

Return a pointer to the head of the sorted linked list after merging all the given linked lists.



Example Input

Input 1:

 1 -> 10 -> 20
 4 -> 11 -> 13
 3 -> 8 -> 9
Input 2:

 10 -> 12
 13
 5 -> 6


Example Output

Output 1:

 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20
Output 2:

 5 -> 6 -> 10 -> 12 ->13


Example Explanation

Explanation 1:

 The resulting sorted Linked List formed after merging is 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20.
Explanation 2:

 The resulting sorted Linked List formed after merging is 5 -> 6 -> 10 -> 12 ->13.
 */
    public static ListNode mergeLinkedList(ArrayList<ListNode> list) {
        PriorityQueue<ListNode> minQueque = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l, ListNode r) {
                return l.val - r.val;
            }
        });
        for (ListNode node : list) {
            minQueque.add(node);
            while (node.next != null) {
                ListNode tmp = node.next;
                node.next = null;
                minQueque.add(tmp);
                node = tmp;
            }
        }
        ListNode head = minQueque.peek();
        ListNode node = head;
        minQueque.remove();
        int n = minQueque.size();
        for(int i = 1; i < n+1; i++) {
            node.next = minQueque.peek();
            node = node.next;
            minQueque.remove();
        }
        return head;
    }

    /*
    Q4. Flipkart's Challenge in Effective Inventory Management
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

In the recent expansion into grocery delivery, Flipkart faces a crucial challenge in effective inventory management. Each grocery item on the platform carries its own expiration date and profit margin, represented by two arrays, A and B of size N. A[i] denotes the time left before expiration date for the ith item, and B[i] denotes profit margin for the ith item. To mitigate potential losses due to expiring items, Flipkart is seeking a strategic solution.

The objective is to identify a method to strategically buy certain items, ensuring they are sold before their expiration date, thereby maximizing overall profit. Can you assist Flipkart in developing an innovative approach to optimize their grocery inventory and enhance profitability?

Your task is to find the maximum profit one can earn by buying groceries considering that you can only buy one grocery item at a time.

NOTE:

You can assume that it takes 1 minute to buy a grocery item, so you can only buy the ith grocery item when the current time <= A[i] - 1.
You can start buying from day = 0.
Return your answer modulo 109 + 7.







Problem Constraints

1 <= N <= 105
1 <= A[i] <= 109
0 <= B[i] <= 109



Input Format

The first argument is an integer array A represents the deadline for buying the grocery items.
The second argument is an integer array B represents the profit obtained after buying the grocery items.



Output Format

Return an integer denoting the maximum profit you can earn.



Example Input

Input 1:

 A = [1, 3, 2, 3, 3]
 B = [5, 6, 1, 3, 9]
Input 2:

 A = [3, 8, 7, 5]
 B = [3, 1, 7, 19]


Example Output

Output 1:

 20
Output 2:

 30


Example Explanation

Explanation 1:





 At time 0, buy item with profit 5.
 At time 1, buy item with profit 6.
 At time 2, buy item with profit 9.
 At time = 3 or after , you can't buy any item, as there is no item with deadline >= 4.
 So, total profit that one can earn is 20.
Explanation 2:

 At time 0, buy item with profit 3.
 At time 1, buy item with profit 1.
 At time 2, buy item with profit 7.
 At time 3, buy item with profit 19.
 We are able to buy all items within their deadline. So, total profit that one can earn is 30
     */

    public static int findMaxCostOfGrocery(ArrayList<Integer> expDats, ArrayList<Integer> prices) {

        ArrayList<Grocery> groceries = new ArrayList<>();
        for(int i = 0; i< expDats.size(); i++) {
            groceries.add(new Grocery(expDats.get(i), prices.get(i)));
        }
        Collections.sort(groceries, new Comparator<Grocery>() {
            @Override
            public int compare(Grocery l, Grocery r) {
                return l.expDate - r.expDate ;
            }
        });
        PriorityQueue<Grocery> minHeapPrice = new PriorityQueue<Grocery>(new Comparator<Grocery>() {
            @Override
            public int compare(Grocery l, Grocery r) {
                return l.price - r.price ;
            }
        });
        int noOfGrocery = groceries.size(), curTime = 0, maxPrice= 0;
        for(int i = 0; i < noOfGrocery; i++) {
            Grocery grocery = groceries.get(i);
            if (curTime < grocery.expDate) {
                minHeapPrice.add(grocery);
                curTime ++;
            } else {
                if (grocery.price > minHeapPrice.peek().price) {
                    minHeapPrice.remove();
                    minHeapPrice.add(grocery);
                }
            }
        }
        while(!minHeapPrice.isEmpty()) {
            maxPrice = (maxPrice + minHeapPrice.remove().price) % 1000000007;
        }
        return maxPrice;
    }

    /*
    Q1. Distribute Candy
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

N children are standing in a line. Each child is assigned a rating value.




You are giving candies to these children subjected to the following requirements:


Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.

What is the minimum number of candies you must give?






Problem Constraints

1 <= N <= 105
-109 <= A[i] <= 109



Input Format

The first and only argument is an integer array A representing the rating of children.



Output Format

Return an integer representing the minimum candies to be given.



Example Input

Input 1:

 A = [1, 2]
Input 2:

 A = [1, 5, 2, 1]


Example Output

Output 1:

 3
Output 2:

 7


Example Explanation

Explanation 1:

 The candidate with 1 rating gets 1 candy and candidate with rating 2 cannot get 1 candy as 1 is its neighbor.
 So rating 2 candidate gets 2 candies. In total, 2 + 1 = 3 candies need to be given out.
Explanation 2:

 Candies given = [1, 3, 2, 1]
     */

    public static int distributionOfCandy(ArrayList<Integer> students) {
        ArrayList<Integer> candies = new ArrayList<>(students.size());
        for(int i =0; i < students.size();i++) {
            candies.add(1);
        }
        for(int j = 0; j < students.size()-1; j++){
            if(students.get(j+1) > students.get(j)){  //checking on the RHS
                candies.set(j+1, candies.get(j) + 1);
            }
        }

        for(int k = students.size()-1; k > 0; k--){   //checking from LHS
            if( (students.get(k-1) > students.get(k)) && (candies.get(k-1) <= candies.get(k))){
                candies.set(k-1, candies.get(k) + 1);
            }
        }
        int totalCandies = 0;
        for(int i = 0; i < candies.size();i++) {
            totalCandies = totalCandies + candies.get(i);
        }
        return totalCandies;
    }

    public static void main(String args[]) {
       // System.out.println(nthLargestElement(2,new ArrayList<Integer>(Arrays.asList(15, 20, 99, 1))));
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(10);node = node.next;node.next = new ListNode(20);
        list.add(head);
        head = new ListNode(4);
        node = head;
        node.next = new ListNode(11);node = node.next;node.next = new ListNode(13);
        list.add(head);
        head = new ListNode(3);
        node = head;
        node.next = new ListNode(8);node = node.next;node.next = new ListNode(9);
        list.add(head);
       // ListNode result = mergeLinkedList(list);
        System.out.println();

        /*System.out.println(findMaxCostOfGrocery(new ArrayList<>(Arrays.asList(3, 8, 7, 5)),
                new ArrayList<>(Arrays.asList(3, 1, 7, 19))));*/
        System.out.println(distributionOfCandy(new ArrayList<>(Arrays.asList(1, 5, 2, 1))));
    }
}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}

class Grocery {
    public int expDate;
    public int price;
    public Grocery (int expDate, int price) {
        this.expDate = expDate;
        this.price = price;
    }
}
