public class Solution{
  
  // 328 
     public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode oHead = head.next;
        
        ListNode cur1 = head;
        ListNode cur2 = head.next;
        
        while(cur1.next != null && cur2.next != null)
        {
            cur1.next = cur2.next;
            cur1 = cur1.next;

            cur2.next = cur1.next;
            cur2 = cur2.next;
        }

        cur1.next = oHead;
        return head;
    }
}
