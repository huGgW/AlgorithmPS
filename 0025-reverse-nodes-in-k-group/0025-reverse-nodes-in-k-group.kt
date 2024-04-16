/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode?
        = getEndNodeOrNull(head, k)?.let {
            it.next = reverseKGroup(it.next, k)
            stackAndReverseGroup(head!!, k, k).first
        } ?: head

    fun stackAndReverseGroup(node: ListNode, k: Int, left: Int): Triple<ListNode, ListNode, ListNode>
        = if (left == 1) {
            Triple(node, node, node.next)
        } else if (left > 1) {
            val (fst, ahead, end) = stackAndReverseGroup(node.next, k, left-1)
            ahead.next = node

            if (k == left) {
                node.next = end
            }

            Triple(fst, node, end)
        } else {
            throw RuntimeException()
        }

    tailrec fun getEndNodeOrNull(head: ListNode?, k: Int): ListNode? = when {
        k == 1 -> head
        k > 1 -> if (head != null) {
            getEndNodeOrNull(head.next, k-1)
        } else {
            null
        }
        else -> throw RuntimeException()
    }
}