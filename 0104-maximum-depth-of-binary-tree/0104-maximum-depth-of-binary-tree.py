# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        return self.maxDepthRecursive(root, 1) \
            if root is not None else 0

    def maxDepthRecursive(self, node: TreeNode, depth: int) -> int:
        return max(
            depth,
            self.maxDepthRecursive(node.left, depth+1) \
                if node.left is not None else 0,
            self.maxDepthRecursive(node.right, depth+1) \
                if node.right is not None else 0,
        )
