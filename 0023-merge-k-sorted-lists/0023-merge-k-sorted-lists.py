# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
import heapq

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        hp = [(n.val, i) for i, n in enumerate(lists) if n is not None]
        heapq.heapify(hp)

        mergeList: Optional[ListNode] = None
        mergeHead: ListNode
        while hp:
            val, idx = heapq.heappop(hp)
            newNode = ListNode(val)

            if mergeList is None:
                mergeList = newNode
            else:
                mergeHead.next = newNode
            mergeHead = newNode

            lists[idx] = lists[idx].next
            if lists[idx] is not None:
                heapq.heappush(hp, (lists[idx].val, idx))

        return mergeList
        