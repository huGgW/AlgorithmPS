# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        if head is None:
            return True

        ln = self.checkLength(head)
        headFront, headBack = self.cutEvenly(head, ln)
        headBack = self.reverseList(headBack)

        return self.isEqual(headFront, headBack)


    def checkLength(self, head: Optional[ListNode]) -> int:
        ln = 0

        while head is not None:
            ln += 1
            head = head.next

        return ln


    def cutEvenly(self, head: ListNode, ln: int) -> Tuple[Optional[ListNode], Optional[ListNode]]:
        if head.next is None:
            return None, None

        last = head
        for _ in range(ln // 2 - 1):
            last = last.next

        if ln % 2 == 0:
            halfHead = last.next
        else:
            halfHead = last.next.next
        last.next = None

        return head, halfHead


    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None

        prev = None
        while head is not None:
            next = head.next
            head.next = prev
            prev = head
            head = next

        return prev


    def isEqual(self, head1: Optional[ListNode], head2: Optional[ListNode]) -> bool:
        while head1 is not None and head2 is not None:
            if head1.val != head2.val:
                return False
            head1 = head1.next
            head2 = head2.next

        return head1 is None and head2 is None