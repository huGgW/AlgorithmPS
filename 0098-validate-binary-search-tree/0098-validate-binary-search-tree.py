# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
lowestBound = -(2 ** 31) - 1
biggestBound = 2**31

class Solution:
    def isValidBST(
        self, 
        root: Optional[TreeNode],
        lowerBound: int = lowestBound,
        higherBound: int = biggestBound,
    ) -> bool:
        if root is None:
            return True
        
        if not lowerBound < root.val < higherBound:
            return False

        return self.isValidBST(root.left, lowerBound, root.val) \
            and self.isValidBST(root.right, root.val, higherBound)