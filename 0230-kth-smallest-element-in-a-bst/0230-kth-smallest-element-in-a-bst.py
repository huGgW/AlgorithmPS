# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.counter = 0
        self.answer: Optional[int] = None
    
    def kthSmallest(self, root: TreeNode, k: int) -> int:
        self.setInOrderUntilKth(root, k)
        return self.answer

    def setInOrderUntilKth(self, node: TreeNode, k: int):
        if self.counter >= k:
            return
        if node.left is not None:
            self.setInOrderUntilKth(node.left, k)

        if self.counter >= k:
            return
        self.counter += 1
        if self.counter == k:
            self.answer = node.val
        
        if self.counter >= k:
            return
        if node.right is not None:
            self.setInOrderUntilKth(node.right, k)
