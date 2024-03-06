# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if root is not None:
            self._flattenAndReturnLastElem(root)

    def _flattenAndReturnLastElem(self, node: TreeNode) -> TreeNode:
        if node.left is None and node.right is None:
            return node

        if node.left is None:
            return self._flattenAndReturnLastElem(node.right)
        
        if node.left is not None:
            leftLastElem = self._flattenAndReturnLastElem(node.left)
            if node.right is None:
                node.right = node.left
                node.left = None
                return leftLastElem
            else:
                leftLastElem.right = node.right
                node.right = node.left
                node.left = None
                return self._flattenAndReturnLastElem(leftLastElem.right)
