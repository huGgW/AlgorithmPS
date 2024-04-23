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
    tailrec fun reverseList(head: ListNode?, prev: ListNode? = null): ListNode?
        = when (head) {
            null -> prev
            else -> {
                val origNext = head.next
                head.next = prev
                reverseList(origNext, head)
            }
        }
}