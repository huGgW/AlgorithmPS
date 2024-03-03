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
    var maxAbsDiff = 0
    final val MINBOUNDARY = -1
    final val MAXBOUNDARY = 1e5.toInt() + 1

    fun maxAncestorDiff(root: TreeNode?): Int {
        minMaxNodeTraverse(root!!)
        return maxAbsDiff
    }

    fun minMaxNodeTraverse(node: TreeNode): Pair<Int, Int> {
        val (leftMin, leftMax) = node.left?.let {
            minMaxNodeTraverse(it)
        } ?: Pair(null, null)

        val (rightMin, rightMax) = node.right?.let {
            minMaxNodeTraverse(it)
        } ?: Pair(null, null)

        val nodeMin = min(
            min(leftMin ?: MAXBOUNDARY, rightMin ?: MAXBOUNDARY),
            node.`val`
        )

        val nodeMax = max(
            max(leftMax ?: MINBOUNDARY, rightMax ?: MINBOUNDARY),
            node.`val`
        )

        maxAbsDiff = max(
            maxAbsDiff,
            max(nodeMax - node.`val`, node.`val` - nodeMin),
        )

        return nodeMin to nodeMax
    }
}
