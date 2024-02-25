/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    var maxSum = Int.MIN_VALUE

    fun maxPathSum(root: TreeNode?): Int = root?.let {
        calcMaxAndPropagate(it)
        maxSum
    } ?: 0

    fun calcMaxAndPropagate(node: TreeNode?): Int = node?.let {
        val leftProp = calcMaxAndPropagate(it.left)
        val rightProp = calcMaxAndPropagate(it.right)

        val maxContainsIt = leftProp + rightProp + it.`val`
        maxSum = max(maxContainsIt, maxSum)

        max(max(leftProp, rightProp) + it.`val`, 0)
    } ?: 0
}
