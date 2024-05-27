"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

from typing import Optional
from collections import deque

class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if node is None:
            return None

        queue: deque[Node] = deque([node])
        copyHistory: dict[int, Node] = {id(node): Node(node.val)}

        while queue:
            origNode = queue.pop()
            copyNode = copyHistory[id(origNode)]

            for origNeighbor in origNode.neighbors:
                if id(origNeighbor) not in copyHistory:
                    newNeighbor = Node(origNeighbor.val)
                    copyHistory[id(origNeighbor)] = newNeighbor
                    queue.appendleft(origNeighbor)
                else:
                    newNeighbor = copyHistory[id(origNeighbor)]

                copyNode.neighbors.append(newNeighbor)

        return copyHistory[id(node)]