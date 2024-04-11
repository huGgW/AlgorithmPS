# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None

        forward = head.next
        if forward is None:
            return head

        fforward = self.swapPairs(forward.next)
        head.next = fforward
        forward.next = head
        return forward
        