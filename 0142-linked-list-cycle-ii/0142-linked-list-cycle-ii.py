# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        history = set()
        while head is not None:
            if head in history:
                return head
            history.add(head)
            head = head.next

        return None
