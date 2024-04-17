# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def sortList(self, head: Optional[ListNode], ln: Optional[int] = None) -> Optional[ListNode]:
        # calculate length of list
        if ln is None:
            ln = self.lenOf(head)

        if ln < 2:
            return head
        fstList, scdList = self.split(head, ln)
        fstLn, scdLn = ln // 2, ln - (ln // 2)

        fstSorted = self.sortList(fstList, fstLn)
        scdSorted = self.sortList(scdList, scdLn)

        sortedHead = self.merge(fstSorted, scdSorted)
        return sortedHead

    def split(self, head: Optional[ListNode], ln: int) -> Tuple[Optional[ListNode], Optional[ListNode]]:
        splitTail = head
        half = ln // 2

        for _ in range(half-1):
            splitTail = splitTail.next
        splitHead = splitTail.next
        splitTail.next = None

        return head, splitHead

    def merge(self, a: Optional[ListNode], b: Optional[ListNode]) -> Optional[ListNode]:
        aNode, bNode = a, b
        mergeList = None
        mergeNode = None

        while aNode is not None and bNode is not None:
            isA = aNode.val <= bNode.val

            nextNode = aNode if isA else bNode
            if mergeList is None:
                mergeList = nextNode
                mergeNode = nextNode
            else:
                mergeNode.next = nextNode
                mergeNode = nextNode

            if isA:
                aNode = aNode.next
            else:
                bNode = bNode.next
            mergeNode.next = None

        if aNode is not None:
            mergeNode.next = aNode
        elif bNode is not None:
            mergeNode.next = bNode

        return mergeList





    def lenOf(self, head: Optional[ListNode]) -> int:
        cnt = 0
        while head is not None:
            cnt += 1
            head = head.next
        return cnt


        