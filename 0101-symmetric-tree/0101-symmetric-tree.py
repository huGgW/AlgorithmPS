# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if root is None:
            return True

        leftQueue: list[TreeNode | None] = []
        rightQueue: list[TreeNode | None] = []

        leftQueue.append(root.left)
        rightQueue.append(root.right)


        while leftQueue and rightQueue:
            leftNext = leftQueue.pop(0)
            rightNext = rightQueue.pop(0)

            if (leftNext is None) != (rightNext is None):
                return False

            if leftNext is None and rightNext is None:
                continue

            if leftNext.val != rightNext.val:
                return False

            leftQueue.append(leftNext.left)
            leftQueue.append(leftNext.right)

            rightQueue.append(rightNext.right)
            rightQueue.append(rightNext.left)

        return True