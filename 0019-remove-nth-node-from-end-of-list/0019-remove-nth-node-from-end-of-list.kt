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
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // Count length
        var len = 0
        var node = head
        while (node != null) {
            len++
            node = node.next
        }

        if (n == len) {
            return head!!.next
        }

        var before = head!!
        var curr = head!!
        repeat(len - n) {
            before = curr
            curr = before.next!!
        }
        before.next = curr.next
        return head
    }
}