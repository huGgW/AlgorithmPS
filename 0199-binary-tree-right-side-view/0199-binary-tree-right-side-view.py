# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if root is None:
            return

        queue: list[tuple[TreeNode, int]] = [(root, 0)]
        result: list[int] = []

        nextDepth = 0
        while queue:
            node, depth = queue.pop(0)

            if nextDepth == depth:
                result.append(node.val)
                nextDepth += 1

            if node.right is not None:
                queue.append((node.right, depth + 1))

            if node.left is not None:
            queue.append((node.left, depth + 1))

        return result