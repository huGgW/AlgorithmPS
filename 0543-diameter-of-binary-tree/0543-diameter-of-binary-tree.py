# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        self.ans = 0
        self.maximumPathFromLeaf(root)
        return self.ans

    def maximumPathFromLeaf(self, tree: TreeNode) -> int:
        leftLen = self.maximumPathFromLeaf(tree.left) \
            if tree.left is not None \
            else 0

        rightLen = self.maximumPathFromLeaf(tree.right) \
            if tree.right is not None \
            else 0

        self.ans = max(self.ans, leftLen + rightLen)

        return max(leftLen, rightLen) + 1

