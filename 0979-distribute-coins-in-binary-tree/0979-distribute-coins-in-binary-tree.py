# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def distributeCoins(self, root: TreeNode | None) -> int:
        if root is None:
            return 0

        leftMove = self.distributeCoins(root.left)
        rightMove = self.distributeCoins(root.right)

        totalMove = leftMove + rightMove

        if root.left is not None:
            totalMove += abs(root.left.val - 1)
            root.val += root.left.val - 1
            root.left.val = 1

        if root.right is not None:
            totalMove += abs(root.right.val - 1)
            root.val += root.right.val - 1
            root.right.val = 1

        return totalMove
 