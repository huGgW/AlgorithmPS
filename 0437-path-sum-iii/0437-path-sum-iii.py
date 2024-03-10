# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        self.cnt = 0
        self.target = targetSum

        if root:
            self.trackSums(root)

        return self.cnt

    def trackSums(self, node: TreeNode) -> list[int]:
        sumsBelow = []
        if node.left:
            sumsBelow += self.trackSums(node.left)
        if node.right:
            sumsBelow += self.trackSums(node.right)

        for i in range(len(sumsBelow)):
            newSum = sumsBelow[i] + node.val

            if newSum == self.target:
                self.cnt += 1

            sumsBelow[i] = newSum

        if node.val == self.target:
            self.cnt += 1
        sumsBelow.append(node.val)

        return sumsBelow