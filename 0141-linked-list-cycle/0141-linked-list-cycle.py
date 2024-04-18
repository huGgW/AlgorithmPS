# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        fast = head
        tail = head

        while fast is not None and tail is not None:
            fast = fast.next
            if fast is not None:
                fast = fast.next
            else:
                break
            
            tail = tail.next

            if fast is not None and tail is not None and fast is tail:
                return True

        return False