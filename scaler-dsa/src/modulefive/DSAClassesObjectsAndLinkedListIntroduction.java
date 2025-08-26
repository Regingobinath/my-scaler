package modulefive;

public class DSAClassesObjectsAndLinkedListIntroduction {

    public static void printLinkedList(ListNode listNode) {
        System.out.println(listNode.val+" ");
        while (listNode.next != null){
            System.out.println(listNode.next.val+" ");
            listNode = listNode.next;
        }
    }
    public static void main(String[] args) {
        printLinkedList(new ListNode(0));
    }
}
class ListNode {
    public int val;
    public ListNode next;
    ListNode(int x) { val = x; next = null; }
 }

