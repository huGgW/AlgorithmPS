"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        idToCopy = {}

        copyHead = None
        copyBefore = None
        node = head
        while node is not None:
            idOfNode = id(node)

            copyNode = Node(node.val)
            idToCopy[idOfNode] = copyNode
            if copyBefore is not None:
                copyBefore.next = copyNode
            if copyHead is None:
                copyHead = copyNode

            copyBefore = copyNode
            node = node.next

        node = head
        copyNode = copyHead
        while node is not None:
            if node.random is not None:
                idOfRan = id(node.random)
                copyOfRan = idToCopy[idOfRan]
                copyNode.random = copyOfRan

            node = node.next
            copyNode = copyNode.next

        return copyHead
        