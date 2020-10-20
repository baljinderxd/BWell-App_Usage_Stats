public class Solution3{

  //mid
  ListNode getMid( ListNode head )
    {
        ListNode slow = head, fast = head;
        while( fast.next != null && fast.next.next != null )
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
