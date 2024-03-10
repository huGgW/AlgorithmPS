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

    def trackChildrensAndCheckAns(self, node: TreeNode) -> set[int]:
        leftChildren, rightChildren = set(), set()

        if node.left:
            leftChildren.update(self.trackChildrensAndCheckAns(node.left))
        if node.right:
            rightChildren.update(self.trackChildrensAndCheckAns(node.right))

        children = leftChildren.union(rightChildren)
        children.add(node.val)

        if self.ans is None and self.p.val in children and self.q.val in children:
            self.ans = node

        return children
