# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        self.p = p
        self.q = q
        self.ans: TreeNode | None = None

        self.trackChildrensAndCheckAns(root)
        return self.ans

    def trackChildrensAndCheckAns(self, node: TreeNode) -> tuple[bool, bool]:
        leftChecked = (False, False)
        rightChecked = (False, False)
        if node.left:
            leftChecked = self.trackChildrensAndCheckAns(node.left)
        if node.right:
            rightChecked = self.trackChildrensAndCheckAns(node.right)

        stat = self.mergeCheckStats(leftChecked, rightChecked)
        stat = self.updateCheckStats(stat, node.val)

        if (not self.isMoreCheckNeeded(stat)) and self.ans is None:
                self.ans = node

        return stat

    def mergeCheckStats(self, left: tuple[bool, bool], right: tuple[bool, bool]) -> tuple[bool, bool]:
        result =  (
            left[0] or right[0],
            left[1] or right[1]
        )
        return result

    def updateCheckStats(self, stat: tuple[bool, bool], val: int) -> tuple[bool, bool]:
        result = (
            stat[0] or val == self.p.val,
            stat[1] or val == self.q.val
        )
        return result

    def isMoreCheckNeeded(self, stat: tuple[bool, bool]) -> bool:
        return not(stat[0] and stat[1])

