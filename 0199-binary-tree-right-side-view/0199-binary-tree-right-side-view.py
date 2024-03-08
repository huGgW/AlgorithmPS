# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        queue: list[tuple[TreeNode, int]] = [(root, 0)]
        result: list[int] = []

        nextDepth = 0
        while queue:
            node, depth = queue.pop(0)

            if node is None:
                continue

            if nextDepth == depth:
                result.append(node.val)
                nextDepth += 1

            queue.append((node.right, depth + 1))
            queue.append((node.left, depth + 1))

        return result