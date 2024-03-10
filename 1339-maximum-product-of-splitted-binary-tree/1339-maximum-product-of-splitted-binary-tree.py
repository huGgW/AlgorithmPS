# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def maxProduct(self, root: TreeNode) -> int:
        self.memo: dict[TreeNode, int] = {}
        self.traverseSum(root)

        totalSum = self.memo[root]
        possibleOptimalSum = totalSum / 2
        optimalSum = 0
        for nodeSum in self.memo.values():
            if abs(possibleOptimalSum - optimalSum) > abs(possibleOptimalSum - nodeSum):
                optimalSum = nodeSum

        return ((optimalSum % (10**9 + 7)) * ((totalSum - optimalSum) % (10**9 + 7))) % (10**9 + 7)


    def traverseSum(self, node: TreeNode):
        if node in self.memo:
            return

        if node.left:
            self.traverseSum(node.left)
        if node.right:
            self.traverseSum(node.right)

        self.memo[node] = node.val + self.memo.get(node.left, 0) + self.memo.get(node.right, 0)