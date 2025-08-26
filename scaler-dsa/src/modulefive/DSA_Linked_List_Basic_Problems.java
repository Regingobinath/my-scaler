package modulefive;


public class DSA_Linked_List_Basic_Problems {

    /*
    Q1. Reverse Linked List
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given a singly linked list having head node A. You have to reverse the linked list and return the head node of that reversed list.

NOTE: You have to do it in-place and in one-pass.



Problem Constraints

1 <= Length of linked list <= 105

Value of each node is within the range of a 32-bit integer.



Input Format

First and only argument is a linked-list node A.



Output Format

Return a linked-list node denoting the head of the reversed linked list.



Example Input

Input 1:

 A = 1 -> 2 -> 3 -> 4 -> 5 -> NULL
Input 2:

 A = 3 -> NULL


Example Output

Output 1:

 5 -> 4 -> 3 -> 2 -> 1 -> NULL
Output 2:

 3 -> NULL


Example Explanation

Explanation 1:

 The linked list has 5 nodes. After reversing them, the list becomes : 5 -> 4 -> 3 -> 2 -> 1 -> NULL
Expalantion 2:

 The linked list consists of only a single node. After reversing it, the list becomes : 3 -> NULL
     */
    public static void reverseLinkedList(ListNodeOne head){
        ListNodeOne curr, prev;
        curr = head;
        prev = null;
        while (curr != null) {
            ListNodeOne tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        head = prev;
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
    }

    /*
    Q2. Copy List
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given a linked list A
Each node in the linked list contains two pointers: a next pointer and a random pointer
The next pointer points to the next node in the list
The random pointer can point to any node in the list, or it can be NULL
Your task is to create a deep copy of the linked list A
The copied list should be a completely separate linked list from the original list, but with the same node values and random pointer connections as the original list
You should create a new linked list B, where each node in B has the same value as the corresponding node in A
The next and random pointers of each node in B should point to the corresponding nodes in B (rather than A)


Problem Constraints

0 <= |A| <= 106



Input Format

The first argument of input contains a pointer to the head of linked list A.



Output Format

Return a pointer to the head of the required linked list.



Example Input

Given list
   1 -> 2 -> 3
with random pointers going from
  1 -> 3
  2 -> 1
  3 -> 1



Example Output

   1 -> 2 -> 3
with random pointers going from
  1 -> 3
  2 -> 1
  3 -> 1



Example Explanation

You should return a deep copy of the list. The returned answer
should not contain the same node as the original list, but a copy of them.
The pointers in the returned list should not link to any node in the original input list.

     */

    public static RandomListNode deepCopy(RandomListNode head) {
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode tmp = new RandomListNode(curr.label);
            tmp.next = curr.next;
            curr.next = tmp;
            curr = tmp.next;
        }

        curr = head;
        RandomListNode currc = curr.next;
        while (curr != null) {
            if(curr.random != null) {
                currc.random = curr.random.next;
            }
            curr = currc.next;
            if(curr != null){
                currc = curr.next;
            }
        }
        curr = head;
        currc = head.next;
        RandomListNode headc = currc;
        while (currc != null){
            curr = currc.next;
            if (curr != null) {
                currc.next = curr.next;
                currc = currc.next;
            } else {
                currc = null;
            }
        }
        return headc;
    }

    /*
    Q3. Insert in Linked List
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given A which is the head of a linked list. Also given is the value B and position C. Complete the function that should insert a new node with the said value at the given position.

Notes:

In case the position is more than length of linked list, simply insert the new node at the tail only.
In case the pos is 0, simply insert the new node at head only.
Follow 0-based indexing for the node numbering.


Problem Constraints

0 <= size of linked list <= 105

1 <= value of nodes <= 109

1 <= B <= 109

0 <= C <= 105



Input Format

The first argument A is the head of a linked list.

The second argument B is an integer which denotes the value of the new node

The third argument C is an integer which denotes the position of the new node



Output Format

Return the head of the linked list


Example Input

Input 1:
A = 1 -> 2
B = 3
C = 0
Input 2:
A = 1 -> 2
B = 3
C = 1


Example Output

Output 1:
3 -> 1 -> 2
Output 2:
1 -> 3 -> 2


Example Explanation

For Input 1:
The new node is add to the head of the linked list
For Input 2:
The new node is added after the first node of the linked list
     */
    public static ListNodeOne insertInkedList(ListNodeOne head, int val, int idx) {

        if(head == null) {
            return new ListNodeOne(val);
        }
        int i=0;
        ListNodeOne curr = head;
        ListNodeOne prev = null;
        while (i < idx && curr != null) {
            prev = curr;
            curr = curr.next;
            i++;

        }
        ListNodeOne newNode = new ListNodeOne(val);
        if(curr == null) {
            //add at last
            prev.next = newNode;
        } else{
            newNode.next = curr;
            if(prev != null) {
                prev.next = newNode;
            }
            if (idx ==0) {
                head = newNode;
            }
        }

        return head;
    }

    /*
    Q4. Delete in Linked List
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given the head of a linked list A and an integer B. Delete the B-th node from the linked list.

Note : Follow 0-based indexing for the node numbering.



Problem Constraints

1 <= size of linked list <= 105
1 <= value of nodes <= 109
0 <= B < size of linked list




Input Format

The first argument A is the head of a linked list.

The second arguement B is an integer.



Output Format

Return the head of the linked list after deletion



Example Input

Input 1:
A = 1 -> 2 -> 3
B = 1
Input 2:
A = 4 -> 3 -> 2 -> 1
B = 0


Example Output

Output 1:
1 -> 3
Output 2:
3 -> 2 -> 1


Example Explanation

For Input 1:
The linked list after deletion is 1 -> 3.
For Input 2:
The linked list after deletion is 3 -> 2 -> 1.
     */

    public static ListNodeOne deleteNode(ListNodeOne head, int idxToDelete) {
        // if list is empty
        if (head == null) {
            return head;
        }
        // if 0th position is to be deleted
        if (idxToDelete == 0) {
            return head.next;
        }
        ListNodeOne temp = head;
        int i = 0;
        while (i < idxToDelete - 1) {
            i++;
            temp = temp.next;
        }
        // break the link with next node and make a link with the following node of the broken node.
        temp.next = temp.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNodeOne head = new ListNodeOne(1);
        ListNodeOne node = head;
        node.next = new ListNodeOne(2);node = node.next;
        node.next = new ListNodeOne(3);node = node.next;
        node.next = new ListNodeOne(4);node = node.next;
        node.next = new ListNodeOne(5);node = node.next;
        node.next = new ListNodeOne(8);node = node.next;
        node.next = new ListNodeOne(7);node = node.next;
        node.next = new ListNodeOne(3);node = node.next;
        node.next = new ListNodeOne(7);node = node.next;


        /*reverseLinkedList(head);*/

        /*RandomListNode head = new RandomListNode(1);
        RandomListNode node = head;
        node.next = new RandomListNode(2);node = node.next;
        node.next = new RandomListNode(3);node = node.next;
        node.next = new RandomListNode(4);node = node.next;
        //node.random = head;
        node.next = new RandomListNode(5);
        deepCopy(head);*/

       // insertInkedList(head, 3,5);
        deleteNode(head, 3);
    }
}
class ListNodeOne {
    public int val;
    public ListNodeOne next;
    ListNodeOne(int x) { val = x; next = null;}
}


class RandomListNode {
      public int label;
      public RandomListNode next, random;
    RandomListNode(int x) { label = x; next = null;random = null; }
}