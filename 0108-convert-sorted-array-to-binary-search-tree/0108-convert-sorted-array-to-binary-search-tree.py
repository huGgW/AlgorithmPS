# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        return self.mediumNodeCreate(0, len(nums)-1, nums)

    def mediumNodeCreate(self, b: int, e: int, nums: list[int]) -> Optional[TreeNode]:
        if b > e:
            return None

        m = (b + e) // 2
        return TreeNode(
            nums[m],
            self.mediumNodeCreate(b, m-1, nums),
            self.mediumNodeCreate(m+1, e, nums),
        )
